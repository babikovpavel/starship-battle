package ru.otus.homework6.adapters;

import lombok.SneakyThrows;
import org.joor.Reflect;
import ru.otus.homework6.commands.Command;
import ru.otus.homework6.model.UObject;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.stream.Collectors;

public class AdaptersGeneratorImpl<T> implements AdaptersGenerator<T> {

    public static final String NAME_TEMPLATE = "ru.otus.homework6.model.<Type>Adapter";
    public static final String CONTENT_TEMPLATE =
            "package ru.otus.homework6.model;" +
                    "import ru.otus.homework6.ioc.IoC;" +
                    "public class <Type>Adapter implements <Type> {" +
                    "private UObject uObject;" +
                    "public <Type>Adapter(UObject uObject) {" +
                    "   this.uObject = uObject;" +
                    "}";

    @Override
    @SneakyThrows
    public T generate(String type, UObject uObject) {
        Class<?> aClass = Class.forName(type);
        String name = NAME_TEMPLATE.replace("<Type>", aClass.getSimpleName());
        String content = CONTENT_TEMPLATE.replace("<Type>", aClass.getSimpleName());
        StringBuilder builder = new StringBuilder(content);
        for (Method method : aClass.getDeclaredMethods()) {
            if (Modifier.isPublic(method.getModifiers())) {
                String params = collectParams(method);
                String paramsNames = collectParamsNames(method);
                String signature = generateMethodSignature(method, params);
                String methodBody;
                if (method.getReturnType().equals(Void.TYPE)) {
                    methodBody = generateSetter(aClass, method, paramsNames);
                } else {
                    methodBody = generateGetter(aClass, method);
                }
                builder.append(signature)
                        .append(methodBody)
                        .append("}");
            }
        }
        builder.append("}");
        return Reflect.compile(name, builder.toString())
                .create(uObject)
                .get();
    }

    private static String collectParamsNames(Method method) {
        return Arrays.stream(method.getParameters())
                .map(parameter -> String.format(", %s", parameter.getName()))
                .collect(Collectors.joining());
    }

    private static String collectParams(Method method) {
        return Arrays.stream(method.getParameters())
                .map(String::valueOf)
                .collect(Collectors.joining(","));
    }

    private static String generateMethodSignature(Method method, String params) {
        return String.format("public %s %s(%s) {",
                method.getReturnType().getName(), method.getName(), params);
    }

    private static String generateGetter(Class<?> aClass, Method method) {
        return String.format("return (%s) IoC.resolve(\"%s.%s\", uObject);",
                method.getReturnType().getName(), aClass.getSimpleName(), method.getName());
    }

    private static String generateSetter(Class<?> aClass, Method method, String paramsNames) {
        return String.format("((%s) IoC.resolve(\"%s.%s\", uObject%s)).execute();",
                Command.class.getName(), aClass.getSimpleName(), method.getName(),
                paramsNames);
    }
}
