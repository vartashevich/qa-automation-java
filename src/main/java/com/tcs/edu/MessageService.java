package com.tcs.edu;

import com.tcs.edu.domain.Message;
import com.tcs.edu.helper.Doubling;
import com.tcs.edu.helper.MessageOrder;

/**
 * Created on 06.05.2022
 *
 * @author Viktor Artashevich
 */
public interface MessageService {
    /**
     * Метод сортировки
     *
     * @param order             порядок
     * @param processedMessages сообщения
     */
    Message[] sortMessages(final MessageOrder order, final Message... processedMessages);

    /**
     * Метод обработки
     *
     * @param doubling    варианты обработки сообщений
     * @param allMessages сообщения
     */
    Message[] processMessages(Doubling doubling, Message... allMessages);

    /**
     * Метод обработки сообщений
     * @param message сообщение
     * @param messages список сообщений
     */
    void logMessage(Message message, Message... messages);

    /**
     * Метод обработки сообщений
     * @param order порядок
     * @param message сообщение
     * @param messages список сообщений
     */
    void logMessage(MessageOrder order, Message message, Message... messages);

    /**
     * Метод обработки сообщений
     * @param order порядок
     * @param doubling вариант обработки
     * @param message сообщение
     * @param messages список сообщений
     */
    void logMessage(MessageOrder order, Doubling doubling, Message message, Message... messages);
}
