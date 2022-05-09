package com.tcs.edu.decorator;

import com.tcs.edu.domain.Message;
import com.tcs.edu.helper.Severity;

/**
 * Created on 13.04.2022
 *
 * @author Viktor Artashevich
 * Класс для преобразования Severity в строковое представление
 */
public class SeverityDecorator implements MessageDecorator {
    /**
     * Метод преобразует полученную Enum важность в строковое представление
     *
     * @param severity уровень важности, который берем из Enum
     * @return важность преобразованная  в строку
     */
    @Override
    public Message decorate(Message message) {
        Message result;
        Severity severity = message.getSeverity();
        if (severity != null) {
            String body = message.getBody() + " " + severity.getValue();
            result = new Message(body, severity);
        } else {
            result = message;
        }
        return result;
    }
}
