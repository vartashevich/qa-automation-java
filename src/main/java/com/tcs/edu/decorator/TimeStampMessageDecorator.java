package com.tcs.edu.decorator;

import com.tcs.edu.domain.Message;

import java.time.Instant;

/**
 * Created on 24.03.2022
 *
 * @author Viktor Artashevich
 */
public class TimeStampMessageDecorator implements MessageDecorator {
    public int messageCount = 0;
    private int pageMessageCount;

    /**
     * Конструктор, принимающий параметр количества сообщений необходимых для разделения
     * @param pageMessageCount
     */
    public TimeStampMessageDecorator(int pageMessageCount) {
        this.pageMessageCount = pageMessageCount;
    }

    /**
     * <p>Метод decorate украшает полученное в аргументе сообщение. Разделяем постранично сообщения в зависимости от
     * pageMessageCount.
     * decoratedMessage - константа состоящая из кол-ва выводов сообщений, текущей даты и сообщения<p/>
     *
     * @param message Экземпляр объекта типа Message, сообщение, передаваемое для декорирования в виде добавления даты
     * @return Возвращаем переменную result типа Message - результат декорирования сообщения message
     */
    public Message decorate(Message message) {
        if (messageCount % pageMessageCount == 0 && messageCount != 0) {
            System.out.println("----------------------------");
        }
        messageCount++;
        final String decoratedMessage = String.format("%d %s %s", messageCount, Instant.now().toString(), message.getBody());
        return new Message(decoratedMessage, message.getSeverity());
    }
}
