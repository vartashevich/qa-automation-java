package com.tcs.edu.printer;

import com.tcs.edu.Printer;
import com.tcs.edu.domain.Message;
import com.tcs.edu.processor.ValidatedService;

/**
 * Class contains method for printing message to console
 *
 * @author Artashevich Viktor
 */
public class ConsolePrinter extends ValidatedService implements Printer {
    /**
     * Prints the message
     * No side-effects
     *
     * @param message the message to be printed
     */
    public void print(Message message) {
        if (super.isAgsValid(message)) {
            System.out.println(message.getBody());
        }
    }
}