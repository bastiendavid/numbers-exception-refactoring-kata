package com.dojo.number;

import com.dojo.number.exception.BadLuckException;
import com.dojo.number.exception.OddException;
import com.dojo.number.exception.TheDevilException;
import com.dojo.number.exception.TooLowException;

public class NumberController {

    private Logger logger;
    private NumberService numberService;

    public NumberController(Logger logger, NumberService numberService) {
        this.logger = logger;
        this.numberService = numberService;
    }

    public int doTheMagic(int number) {
        int result = -1;
        try {
            result = numberService.doTheMagic(number);
        } catch (TheDevilException e) {
            logger.log("The devil's message: " + e.getMessage());
        } catch (BadLuckException e) {
            logger.log("Bad luck message: " + e.getMessage());
        } catch (TooLowException e) {
            logger.log("Too low: " + e.getMessage());
        } catch (OddException e) {
            logger.log("Weird case: " + e.getMessage());
        } catch (RuntimeException e) {
            logger.log("Just in case: " + e.getMessage());
        } finally {
            numberService.close();
        }
        return result;
    }
}
