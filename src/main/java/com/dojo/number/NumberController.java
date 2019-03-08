package com.dojo.number;

import com.dojo.number.exception.BadLuckException;
import com.dojo.number.exception.MaybeNotAfterAllException;
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
            e.printStackTrace();
        } catch (BadLuckException e) {
            logger.log("Bad luck message: " + e.getMessage());
            e.printStackTrace();
        } catch (TooLowException e) {
            logger.log("Too low: " + e.getMessage());
            e.printStackTrace();
        } catch (MaybeNotAfterAllException e) {
            logger.log("Weid case: " + e.getMessage());
            e.printStackTrace();
        } catch (RuntimeException e) {
            logger.log("Just in case: " + e.getMessage());
            e.printStackTrace();
        } finally {
            numberService.close();
        }
        return result;
    }
}
