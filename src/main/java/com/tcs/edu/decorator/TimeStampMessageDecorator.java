package com.tcs.edu.decorator;

import java.time.Instant;

/**
 * Created on 24.03.2022
 *
 * @author Viktor Artashevich
 */

public class TimeStampMessageDecorator {
    /**
     * @param message сообщение, передаваемое для декорирования в виде добавления даты
     * @return Возвращаем исходное сообщение дополненное текущей датой
     */
    public static String decorate(String message) {
        return Instant.now().toString() + " " + message;
    }
}
