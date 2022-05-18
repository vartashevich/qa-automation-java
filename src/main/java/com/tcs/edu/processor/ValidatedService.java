package com.tcs.edu.processor;

import com.tcs.edu.domain.Message;

/**
 * Created on 13.05.2022
 *
 * @author Viktor Artashevich
 */
public abstract class ValidatedService {
    /**
     * Метод проверяет параметры на валидность
     *
     * @param message Сообщение
     */
    public void checkAgsValid(Message message) {
        if (message == null) {
            throw new IllegalArgumentException("arg message is null");
        }
        if (message.getBody() == null) {
            throw new IllegalArgumentException("some arg is empty");
        }
    }
}
