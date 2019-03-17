package com.dojo.number;

import com.dojo.number.exception.BadLuckException;
import com.dojo.number.exception.OddException;
import com.dojo.number.exception.TheDevilException;
import com.dojo.number.exception.TooLowException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NumberControllerTest {

    @Mock
    Logger logger;

    @Mock
    NumberService numberService;

    @InjectMocks
    NumberController numberController;

    @Test
    void returns_the_number_given_by_number_service_and_close_the_service() throws Exception {
        // Given
        final int inputNumber = 32;
        final int outputNumber = 42;
        when(numberService.doTheMagic(inputNumber)).thenReturn(outputNumber);

        // When
        int result = numberController.doTheMagic(inputNumber);

        // Then
        assertThat(result).isEqualTo(outputNumber);
        verify(numberService).close();
    }

    @ParameterizedTest
    @MethodSource("exceptionsAndExpectedLogs")
    void logs_exceptions_raised_by_the_service_and_close_the_service(Exception exception, String expectedLoggedMessage) throws Exception {
        // Given
        final int inputNumber = 72;
        when(numberService.doTheMagic(inputNumber)).thenThrow(exception);

        // When
        numberController.doTheMagic(inputNumber);

        // Then
        verify(logger).log(expectedLoggedMessage);
        verify(numberService).close();
    }

    static Stream<Arguments> exceptionsAndExpectedLogs() {
        return Stream.of(
                arguments(new TheDevilException("Devil exception"), "The devil's message: Devil exception"),
                arguments(new BadLuckException("Bad luck exception"), "Bad luck message: Bad luck exception"),
                arguments(new TooLowException("Too low exception"), "Too low: Too low exception"),
                arguments(new OddException("Odd exception"), "Weird case: Odd exception"),
                arguments(new RuntimeException("Run time exception"), "Just in case: Run time exception")
        );
    }
}
