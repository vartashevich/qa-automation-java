package com.tcs.edu.processor;

import com.tcs.edu.helper.Severity;
import com.tcs.edu.decorator.SeverityDecorator;
import com.tcs.edu.decorator.TimeStampMessageDecorator;

/**
 * Created on 13.04.2022
 * Класс для обработки и объединения важности сообщения и строкового сообщения.
 * @author Viktor Artashevich
 */
public class MessageService {
    /**
     * Метод обработки важности и сообщения
     * @param severity Важность сообщения, соответствует Enum
     * @param message Строковое сообщение, которое нужно обработать
     * @return Обработанное сообщение, результат работы двух методов - декорирования строки и преобразования важности в строку
     */
    public static String service(Severity severity, String message) {
        String processedMessage;
        processedMessage = TimeStampMessageDecorator.decorate(message) + " " + SeverityDecorator.decorate(severity);
        return processedMessage;
    }
}
