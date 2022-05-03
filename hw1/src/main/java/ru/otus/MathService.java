package ru.otus;

import org.apache.commons.math3.util.MathUtils;
import org.apache.commons.math3.util.Precision;

public class MathService {

    public static final double EPSILON = 0.000001d;

    //will be initialized to zero
    public static double ZERO;

    public double[] solve(double a, double b, double c) {
        if (MathUtils.equals(a, Double.NaN) || MathUtils.equals(b, Double.NaN) || MathUtils.equals(c, Double.NaN)) {
            throw new IllegalArgumentException("params can't be NaN");
        }
        MathUtils.checkFinite(new double[]{a, b, c});
        if (Precision.equals(a, ZERO, EPSILON)) {
            throw new IllegalArgumentException("a can't be null");
        }
        double discriminant = b * b - 4 * a * c;
        if (Precision.compareTo(discriminant, ZERO, EPSILON) > 0) {
            return new double[]{
                    -b + Math.sqrt(discriminant) / 2 * a,
                    -b - Math.sqrt(discriminant) / 2 * a
            };
        } else {
            if (Precision.compareTo(discriminant, ZERO, EPSILON) < 0) {
                return new double[]{};
            } else {
                return new double[]{
                        -b / 2 * a,
                        -b / 2 * a
                };
            }
        }
    }
}
