package com.tcs.edu.decorator;

import java.time.Instant;

/**
 * Created on 24.03.2022
 *
 * @author Viktor Artashevich
 */

public class TimeStampMessageDecorator {
    private static int messageCount = 0;

    /**
     * <p>Метод decorate украшает полученное в аргументе сообщение.
     * decoratedMessage - константа состоящая из кол-ва выводов сообщений, текущей даты и сообщения<p/>
     *
     * @param message сообщение, передаваемое для декорирования в виде добавления даты
     * @return Возвращаем переменную decoratedMessage - результат декорирования сообщения message
     */


    public static String decorate(String message) {
        messageCount++;
        final var decoratedMessage = messageCount + " " + Instant.now().toString() + " " + message;
        return decoratedMessage;
    }
}
