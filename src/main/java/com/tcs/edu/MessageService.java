package com.tcs.edu;

import com.tcs.edu.domain.Message;
import com.tcs.edu.helper.Doubling;
import com.tcs.edu.helper.MessageOrder;
import com.tcs.edu.helper.Severity;

import java.util.Collection;
import java.util.UUID;

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
     *
     * @param message  сообщение
     * @param messages список сообщений
     */
    void logMessage(Message message, Message... messages) throws LogException;

    /**
     * Метод обработки сообщений
     *
     * @param order    порядок
     * @param message  сообщение
     * @param messages список сообщений
     */
    void logMessage(MessageOrder order, Message message, Message... messages) throws LogException;

    /**
     * Метод обработки сообщений
     *
     * @param order    порядок
     * @param doubling вариант обработки
     * @param message  сообщение
     * @param messages список сообщений
     */
    void logMessage(MessageOrder order, Doubling doubling, Message message, Message... messages) throws LogException;

    /**
     * Метод поиска записи по идентификатору. Если сообщения с таким ключем нет, то вернем null.
     */
    Message findByPrimaryKey(UUID key);

    /**
     * Метод поиска всех записей в памяти
     */
    Collection<Message> findAll();

    /**
     * Метод поиска записей по уровню важности. Если сообщения с такой важностью нет, то вернем пустую коллекцию.
     */
    Collection<Message> findBySeverity(Severity by);
}
