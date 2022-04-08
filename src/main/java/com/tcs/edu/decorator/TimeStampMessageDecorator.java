package com.tcs.edu.decorator;

import java.time.Instant;

/**
 * Created on 24.03.2022
 *
 * @author Viktor Artashevich
 */

public class TimeStampMessageDecorator {
    public static int messageCount = 0;
    private static final int PAGE_MESSAGE_COUNT = 3;

    /**
     * <p>Метод decorate украшает полученное в аргументе сообщение. Разделяем постранично сообщения в зависимости от
     * pageMessageCount.
     * decoratedMessage - константа состоящая из кол-ва выводов сообщений, текущей даты и сообщения<p/>
     *
     * @param message сообщение, передаваемое для декорирования в виде добавления даты
     * @return Возвращаем переменную decoratedMessage - результат декорирования сообщения message
     */


    public static String decorate(String message) {
        if (messageCount % PAGE_MESSAGE_COUNT == 0 && messageCount != 0) {
            System.out.println("----------------------------");
        }
        messageCount++;

        final var decoratedMessage = String.format("%d %s %s", messageCount, Instant.now().toString(), message);
        return decoratedMessage;
    }
}
