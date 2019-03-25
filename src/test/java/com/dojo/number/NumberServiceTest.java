package com.dojo.number;

import com.dojo.number.exception.BadLuckException;
import com.dojo.number.exception.OddException;
import com.dojo.number.exception.TheDevilException;
import com.dojo.number.exception.TooLowException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;


class NumberServiceTest {

    private NumberService numberService = new NumberService();

    @Test
    void throws_a_devil_exception_for_666() {
        assertThatExceptionOfType(TheDevilException.class)
                .isThrownBy(() -> numberService.doTheMagic(666))
                .withMessage("The devil exception");
    }

    @Test
    void throws_a_bad_luck_exception_for_13() {
        assertThatExceptionOfType(BadLuckException.class)
                .isThrownBy(() -> numberService.doTheMagic(13))
                .withMessage("That's not my lucky number");
    }

    @ParameterizedTest
    @MethodSource("numbersBetween10And20Except13")
    void throws_a_too_low_exception_for_numbers_between_10_and_20_except_13(int number) {
        assertThatExceptionOfType(TooLowException.class)
                .isThrownBy(() -> numberService.doTheMagic(number))
                .withMessage("You can do better");
    }

    static IntStream numbersBetween10And20Except13() {
        return IntStream.range(10, 21).filter(i -> i != 13);
    }

    @ParameterizedTest
    @MethodSource("evenNumbersBetween100And200")
    void return_3_times_the_number_for_even_numbers_between_100_and_200(int number) throws Exception {
        assertThat(numberService.doTheMagic(number)).isEqualTo(number * 3);
    }

    static IntStream evenNumbersBetween100And200() {
        return IntStream.range(100, 201).filter(i -> i % 2 == 0);
    }


    @ParameterizedTest
    @MethodSource("oddNumbersBetween100And200")
    void throws_an_odd_exception_for_odd_numbers_between_100_and_200(int number) {
        assertThatExceptionOfType(OddException.class)
                .isThrownBy(() -> numberService.doTheMagic(number))
                .withMessage("Hmm... no");
    }

    static IntStream oddNumbersBetween100And200() {
        return IntStream.range(100, 201).filter(i -> i % 2 == 1);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -2, -100, -21321})
    void throws_a_runtime_exception_for_negative_numbers(int number) {
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> numberService.doTheMagic(number))
                .withMessage("Should not happen");
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 34, 453, 1000})
    void return_2_times_the_number(int number) throws Exception {
        assertThat(numberService.doTheMagic(number)).isEqualTo(number * 2);
    }
}
