package ru.otus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MathServiceTest {

    private MathService mathService;

    @BeforeEach
    void init() {
        mathService = new MathService();
    }

    @Test
    @DisplayName("there are no roots for the equation x^2+1 = 0 (an empty array is returned)")
    void solveTest1() {
        //arrange
        double a = 1;
        double b = 0;
        double c = 1;
        //act
        double[] actual = mathService.solve(a, b, c);
        //assert
        assertThat(actual)
                .as("roots of equation").isEmpty();
    }

    @Test
    @DisplayName("for the equation x^2-1 = 0 there are two roots of multiplicity 1 (x1=1, x2=-1)")
    void solveTest2() {
        //arrange
        double a = 1;
        double b = 0;
        double c = -1;
        //act
        double[] actual = mathService.solve(a, b, c);
        //assert
        assertThat(actual)
                .as("roots of equation")
                .containsExactly(1, -1);
    }

    @Test
    @DisplayName("for the equation x^2+2x+1 = 0 there is one root of multiplicity 2 (x1 = x2 = -1) or D < epsilon")
    void solveTest3() {
        //arrange
        double a = 1;
        double b = 2.0000001d;
        double c = 1;
        //act
        double[] actual = mathService.solve(a, b, c);
        //assert
        assertEquals(actual[0], actual[1], MathService.EPSILON, "only one root because of zero D");
    }

    @Test
    @DisplayName("should throw if a is strictly equals to zero")
    void solveTest4() {
        //arrange
        double a = 0;
        double b = 2;
        double c = 1;
        //act and assert
        assertThrows(IllegalArgumentException.class, () -> mathService.solve(a, b, c),
                "a can't be zero");
    }

    @Test
    @DisplayName("should not throw if a is not strictly equals to zero")
    void solveTest5() {
        //arrange
        double a = 0.0000012d;
        double b = 2;
        double c = 1;
        //act
        double[] actual = mathService.solve(a, b, c);
        //assert
        assertThat(actual)
                .as("roots of equation")
                .isNotEmpty();
    }

    @Test
    @DisplayName("should throw for NaN params")
    void solveTest6() {
        //arrange
        double a = Double.NaN;
        double b = Double.NaN;
        double c = Double.NaN;
        //act
        assertThrows(IllegalArgumentException.class, () -> mathService.solve(a, b, c),
                "params can't be NaN");
    }

    @Test
    @DisplayName("should throw if params not real numbers")
    void solveTest7() {
        //arrange
        double a = Double.POSITIVE_INFINITY;
        double b = Double.NEGATIVE_INFINITY;
        double c = Double.POSITIVE_INFINITY;
        //act
        assertThrows(IllegalArgumentException.class, () -> mathService.solve(a, b, c),
                "params should be real numbers");
    }
}
