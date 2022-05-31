package com.tcs.edu.processor;

import com.tcs.edu.LogException;
import com.tcs.edu.MessageService;
import com.tcs.edu.domain.Message;
import com.tcs.edu.helper.Doubling;
import com.tcs.edu.helper.MessageOrder;
import com.tcs.edu.helper.Severity;
import com.tcs.edu.repository.HashMapMessageRepository;
import com.tcs.edu.repository.MessageRepository;

import java.util.Collection;
import java.util.UUID;

/**
 * Created on 13.04.2022
 * Класс для обработки сообщений.
 * Содержит перегруженные методы для обработки сообщений, методы сортировки и обработки сообщений,
 * а также сохранения в памяти.
 *
 * @author Viktor Artashevich
 */
public class OrderedDistinctedMessageService extends ValidatedService implements MessageService {
    public MessageRepository repository;

    public OrderedDistinctedMessageService(HashMapMessageRepository repository) {
        this.repository = repository;
    }

    /**
     * Метод обработки сообщений. Вызывает перегруженный метод logMessage.
     * В качестве порядка передаем ASC, который является порядком по умолчанию.
     * В результате обработанные сообщения добавляются в память.
     *
     * @param message  Экземпляр объекта типа Message
     * @param messages массив объектов Message
     */
    public void logMessage(Message message, Message... messages) throws LogException {
        logMessage(MessageOrder.ASC, message, messages);
    }

    /**
     * Метод обработки сообщений. Вызывает перегруженный метод logMessage.
     * В качестве варианта обработки передаем DEFAULT, который является обработкой по умолчанию.
     * В результате обработанные сообщения добавляются в память.
     *
     * @param order    порядок сообщений из массива дополнительных сообщений
     * @param message  Экземпляр объекта типа Message
     * @param messages массив объектов Message
     */
    public void logMessage(MessageOrder order, Message message, Message... messages) throws LogException {
        logMessage(order, Doubling.DEFAULT, message, messages);
    }

    /**
     * Метод обработки сообщений. Сначала обрабатываем сообщения в зависимости от варианта Doubling,
     * потом сортируем полученный массив. Затем добавляем в память уже отсортированный массив.
     *
     * @param order    enum порядка сообщений из массива дополнительных сообщений
     * @param doubling enum с вариантами обработки сообщений
     * @param message  Экземпляр объекта типа Message
     * @param messages массив объектов Message
     */
    public void logMessage(MessageOrder order, Doubling doubling, Message message, Message... messages) throws LogException {
        Message[] allMessages = new Message[messages.length + 1];
        allMessages[0] = message;
        System.arraycopy(messages, 0, allMessages, 1, messages.length);
        Message[] processedMessages = processMessages(doubling, allMessages);
        Message[] sortedMessages = sortMessages(order, processedMessages);
        for (Message current : sortedMessages) {
            repository.create(current);
        }
    }

    /**
     * Метод сортирует массив дополнительных сообщений в зависимости от порядка MessageOrder
     *
     * @param order             enum порядка вывода сообщений из массива дополнительных сообщений
     * @param processedMessages массив обработанных сообщений
     * @return массив отсортированных сообщений
     */
    public Message[] sortMessages(final MessageOrder order, final Message... processedMessages) {
        Message[] sortedMessages = null;
        switch (order) {
            case ASC: {
                sortedMessages = processedMessages;
                break;
            }
            case DESC: {
                sortedMessages = new Message[processedMessages.length];
                for (int i = processedMessages.length - 1, j = 0; i >= 0; i--, j++) {
                    sortedMessages[j] = processedMessages[i];
                }
            }
            break;
            default: {
                sortedMessages = processedMessages;
            }
        }
        return sortedMessages;
    }

    /**
     * Метод обрабатывает массив дополнительных сообщений, в зависимости от вариантов обработки Doubling
     *
     * @param doubling    enum с вариантами обработки сообщений
     * @param allMessages массив сообщений
     * @return возвращаем массив обработанных сообщений
     */
    public Message[] processMessages(Doubling doubling, Message... allMessages) {
        Message[] processedMessages = allMessages;
        if (doubling != null) {
            switch (doubling) {
                case DOUBLES: {
                    processedMessages = new Message[allMessages.length * 2];
                    for (int i = 0; i < allMessages.length; i++) {
                        processedMessages[i * 2] = allMessages[i];
                        processedMessages[i * 2 + 1] = allMessages[i];
                    }
                }
                break;
                case DISTINCT: {
                    processedMessages = new Message[allMessages.length];
                    for (int i = 0; i <= allMessages.length - 1; i++) {
                        if (!isArrayContainsMessage(allMessages[i], processedMessages)) {
                            processedMessages[i] = allMessages[i];
                        }
                    }
                }
                break;
            }
        }
        return processedMessages;
    }

    /**
     * Метод проверяющий наличие сообщения в массиве сообщений
     *
     * @param message Экземпляр объекта типа Message, которое проверяем на наличие в массиве
     * @param array   Массив объектов типа Message, с которым сравниваем
     * @return isArrayContainsMessage признак наличия или отсутствия элемента в массиве
     */
    private static boolean isArrayContainsMessage(Message message, Message... array) {
        boolean isArrayContainsMessage = false;
        if (array != null) {
            for (Message current : array) {
                if (current != null && current.equals(message)) {
                    isArrayContainsMessage = true;
                    break;
                }
            }
        }
        return isArrayContainsMessage;
    }

    /**
     * Метод поиска записи по идентификатору. Делегирует выполнение методу репозитория.
     *
     * @param key идентификатор записи
     * @return запись соответствующую ключу-идентификатору
     */
    public Message findByPrimaryKey(UUID key) {
        return repository.findByPrimaryKey(key);
    }

    /**
     * Метод поиска всех записей. Делегирует выполнение методу репозитория.
     *
     * @return возвращает все записи
     */
    public Collection<Message> findAll() {
        return repository.findAll();
    }

    /**
     * Метод поиска записи по уровню важности. Делегирует выполнение методу репозитория.
     *
     * @param by уровень важности сообщения
     * @return возвращает запись, соответствующую уровню важности
     */
    public Collection<Message> findBySeverity(Severity by) {
        return repository.findBySeverity(by);
    }
}