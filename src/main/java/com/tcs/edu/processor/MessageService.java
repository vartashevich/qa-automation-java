package com.tcs.edu.processor;

import com.tcs.edu.helper.Severity;
import com.tcs.edu.decorator.SeverityDecorator;
import com.tcs.edu.decorator.TimeStampMessageDecorator;
import com.tcs.edu.printer.ConsolePrinter;

/**
 * Created on 13.04.2022
 * Класс для обработки и объединения важности сообщения и строкового сообщения.
 *
 * @author Viktor Artashevich
 */
public class MessageService {
    /**
     * Метод обработки важности и сообщения. Вызывает метод print класа ConsolePrinter
     *
     * @param severity Важность сообщения, соответствует Enum
     * @param message  Строковое сообщение, которое нужно обработать
     * @param additionalMessages Массив строк, который нужно обработать
     */

    public static void logMessage(Severity severity, String message, String... additionalMessages) {
        ConsolePrinter.print(TimeStampMessageDecorator.decorate(message) + " " + SeverityDecorator.decorate(severity));
        for (String current : additionalMessages) {
            ConsolePrinter.print(TimeStampMessageDecorator.decorate(current) + " " + SeverityDecorator.decorate(severity));
        }
    }
}
