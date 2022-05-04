package com.tcs.edu.processor;

import com.tcs.edu.decorator.SeverityDecorator;
import com.tcs.edu.decorator.TimeStampMessageDecorator;
import com.tcs.edu.domain.Message;
import com.tcs.edu.helper.Doubling;
import com.tcs.edu.helper.MessageOrder;
import com.tcs.edu.printer.ConsolePrinter;

/**
 * Created on 13.04.2022
 * Класс для обработки сообщений.
 * Содержит перегруженные методы для обработки сообщений, методы сортировки и обработки сообщений,
 * а также вывода на экран.
 *
 * @author Viktor Artashevich
 */
public class MessageService {
    /**
     * Метод обработки сообщений. Вызывает перегруженный метод logMessage.
     * В качестве порядка передаем ASC, который является порядком по умолчанию.
     * В результате печатаются обработанные сообщения.
     *
     * @param severity           Важность сообщения, соответствует Enum
     * @param message            Основное строковое сообщение, которое нужно обработать
     * @param additionalMessages массив дополнительных сообщений
     */
    public static void logMessage(Message message, Message... messages) {
        logMessage(MessageOrder.ASC, message, messages);
    }

    /**
     * Метод обработки сообщений. Вызывает перегруженный метод logMessage.
     * В качестве варианта обработки передаем DEFAULT, который является обработкой по умолчанию.
     * В результате печатаются обработанные сообщения.
     *
     * @param severity           Важность сообщения, соответствует Enum
     * @param order              enum порядка вывода сообщений из массива дополнительных сообщений
     * @param message            Основное строковое сообщение, которое нужно обработать
     * @param additionalMessages массив дополнительных сообщений
     */
    public static void logMessage(MessageOrder order, Message message, Message... messages) {
        logMessage(order, Doubling.DEFAULT, message, messages);
    }

    /**
     * Метод обработки сообщений. Сначала обрабатываем сообщения в зависимости от ваианта Doubling,
     * потом сортируем полученный массив. Затем печатаем уже отсортированный массив.
     * В результате печатаются обработанные сообщения.
     *
     * @param severity           Важность сообщения, соответствует Enum
     * @param order              enum порядка вывода сообщений из массива дополнительных сообщений
     * @param doubling           enum с вариантами обработки сообщений
     * @param message            Основное строковое сообщение, которое нужно обработать
     * @param additionalMessages массив дополнительных сообщений
     */
    @SuppressWarnings("ConstantConditions")
    public static void logMessage(MessageOrder order, Doubling doubling, Message message, Message... messages) {
        Message[] allMessages = new Message[messages.length + 1];
        allMessages[0] = message;
        if (messages != null) {
            System.arraycopy(messages, 0, allMessages, 1, messages.length);
        }
        Message[] processedMessages = processMessages(doubling, allMessages);
        Message[] sortedMessages = sortMessages(order, processedMessages);
        printAdditionalMessages(sortedMessages);
    }

    /**
     * Метод сортирует массив дополнительных сообщений в зависимости от порядка MessageOrder
     *
     * @param order              enum порядка вывода сообщений из массива дополнительных сообщений
     * @param additionalMessages массив дополнительных сообщений
     * @return массив отсортированных сообщений
     */
    private static Message[] sortMessages(MessageOrder order, Message... processedMessages) {
        if (processedMessages != null) {
            Message[] sortedMessages = new Message[processedMessages.length];
            switch (order) {
                case ASC: {
                    System.arraycopy(processedMessages, 0, sortedMessages, 0, processedMessages.length - 1 + 1);
                    break;
                }
                case DESC: {
                    for (int i = processedMessages.length - 1, j = 0; i >= 0; i--, j++) {
                        sortedMessages[j] = processedMessages[i];
                    }
                }
                break;
                default: {
                    System.arraycopy(processedMessages, 0, sortedMessages, 0, processedMessages.length - 1 + 1);
                }
            }
            return sortedMessages;
        } else {
            return null;
        }
    }

    /**
     * Метод обрабатывает массив дополнительных сообщений, в зависимости от вариантов обработки Doubling
     *
     * @param doubling           enum с вариантами обработки сообщений
     * @param additionalMessages массив дополнительных сообщений
     * @return возвращаем массив обработанных сообщений
     */
    private static Message[] processMessages(Doubling doubling, Message... allMessages) {
        if (allMessages != null) {
            Message[] processedMessages;
            if (doubling != null) {
                switch (doubling) {
                    case DOUBLES: {
                        processedMessages = new Message[allMessages.length * 2];
                        for (int i = 0, j = 0; i <= allMessages.length - 1; i++, j = j + 2) {
                            processedMessages[j] = allMessages[i];
                            processedMessages[j + 1] = allMessages[i];
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
                    default: {
                        processedMessages = new Message[allMessages.length];
                        System.arraycopy(allMessages, 0, processedMessages, 0, allMessages.length - 1 + 1);
                    }
                    break;
                }
                return processedMessages;
            } else {
                processedMessages = new Message[allMessages.length];
                System.arraycopy(allMessages, 0, processedMessages, 0, allMessages.length - 1 + 1);
                return processedMessages;
            }
        }
        return null;
    }

    /**
     * Метод печатает дополнительны сообщения, которые были переданы в массиве
     *
     * @param severity           Уровень важности сообщения
     * @param additionalMessages массив дополнительных сообщений
     */
    private static void printAdditionalMessages(Message... additionalMessages) {
        if (additionalMessages != null) {
            for (Message current : additionalMessages) {
                printMessage(current);
            }
        }
    }

    /**
     * Метод печатает обработанное сообщение
     *
     * @param severity Уровень важности сообщения
     * @param message  Основное троковое сообщение, которое нужно обработать
     */
    private static void printMessage(Message message) {
        if (message != null) {
            if (message.getSeverity() != null) {
                ConsolePrinter.print(TimeStampMessageDecorator.decorate(message.getBody()) + " " + SeverityDecorator.decorate(message.getSeverity()));
            } else ConsolePrinter.print(TimeStampMessageDecorator.decorate(message.getBody()));
        }
    }

    /**
     * Метод проверяющий наличие сообщения в массиве сообщений
     *
     * @param message Сообщение, которое проверяем на наличие в массиве
     * @param array   Массив сообщений, с которым сравниваем
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
}