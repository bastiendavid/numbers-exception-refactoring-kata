package com.dojo.number;

import com.dojo.number.exception.BadLuckException;
import com.dojo.number.exception.MaybeNotAfterAllException;
import com.dojo.number.exception.TheDevilException;
import com.dojo.number.exception.TooLowException;

import java.util.Random;

public class NumberService {

    public int doTheMagic(int number) throws TheDevilException, BadLuckException, TooLowException, MaybeNotAfterAllException {
        if (number == 666) {
            throw new TheDevilException("The devil exception");
        } else if (number == 13) {
            throw new BadLuckException("That's not my lucky number");
        } else if (number >= 10 && number <= 20) {
            throw new TooLowException("You can do better");
        } else if (number >= 100 && number <= 200) {
            if (new Random().nextBoolean()) {
                return number * 3;
            } else {
                throw new MaybeNotAfterAllException("Hmm... no");
            }
        } else if (number < 0) {
            throw new RuntimeException("Should not happen");
        }

        return number * 2;
    }

    public void close() {
        // TODO: do stuff here
    }
}
