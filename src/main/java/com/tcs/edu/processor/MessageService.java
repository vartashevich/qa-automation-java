package com.tcs.edu.processor;

import com.tcs.edu.decorator.SeverityDecorator;
import com.tcs.edu.decorator.TimeStampMessageDecorator;
import com.tcs.edu.helper.Doubling;
import com.tcs.edu.helper.MessageOrder;
import com.tcs.edu.helper.Severity;
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
    public static void logMessage(Severity severity, String message, String... additionalMessages) {
        logMessage(severity, MessageOrder.ASC, message, additionalMessages);
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
    public static void logMessage(Severity severity, MessageOrder order, String message, String... additionalMessages) {
        logMessage(severity, order, Doubling.DEFAULT, message, additionalMessages);
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
    public static void logMessage(Severity severity, MessageOrder order, Doubling doubling, String message, String... additionalMessages) {
        printMessage(severity, message);
        String[] processedMessages = processMessages(doubling, additionalMessages);
        String[] sortedMessages = sortMessages(order, processedMessages);
        printAdditionalMessages(severity, sortedMessages);
    }

    /**
     * Метод сортирует массив дополнительных сообщений в зависимости от порядка MessageOrder
     *
     * @param order              enum порядка вывода сообщений из массива дополнительных сообщений
     * @param additionalMessages массив дополнительных сообщений
     * @return массив отсортированных сообщений
     */
    private static String[] sortMessages(MessageOrder order, String... additionalMessages) {
        if (additionalMessages != null) {
            String[] sortedMessages = new String[additionalMessages.length];
            switch (order) {
                case ASC: {
                    for (int i = 0; i <= additionalMessages.length - 1; i++) {
                        sortedMessages[i] = additionalMessages[i];
                    }
                    break;
                }
                case DESC: {
                    for (int i = additionalMessages.length - 1, j = 0; i >= 0; i--, j++) {
                        sortedMessages[j] = additionalMessages[i];
                    }
                }
                break;
                default: {
                    for (int i = 0; i <= additionalMessages.length - 1; i++) {
                        sortedMessages[i] = additionalMessages[i];
                    }
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
    private static String[] processMessages(Doubling doubling, String... additionalMessages) {
        if (additionalMessages != null) {
            String[] processedMessages;
            switch (doubling) {
                case DOUBLES: {
                    processedMessages = new String[additionalMessages.length * 2];
                    for (int i = 0, j = 0; i <= additionalMessages.length - 1; i++, j = j + 2) {
                        processedMessages[j] = additionalMessages[i];
                        processedMessages[j + 1] = additionalMessages[i];
                    }
                }
                break;
                case DISTINCT: {
                    processedMessages = new String[additionalMessages.length];
                    for (int i = 0; i <= additionalMessages.length - 1; i++) {
                        if (!isArrayContainsMessage(additionalMessages[i], processedMessages)) {
                            processedMessages[i] = additionalMessages[i];
                        }
                    }
                }
                break;
                default: {
                    processedMessages = new String[additionalMessages.length];
                    for (int i = 0; i <= additionalMessages.length - 1; i++) {
                        processedMessages[i] = additionalMessages[i];
                    }
                }
                break;
            }
            return processedMessages;
        }
        return null;
    }

    /**
     * Метод печатает дополнительны сообщения, которые были переданы в массиве
     *
     * @param severity           Уровень важности сообщения
     * @param additionalMessages массив дополнительных сообщений
     */
    private static void printAdditionalMessages(Severity severity, String... additionalMessages) {
        if (additionalMessages != null) {
            for (String current : additionalMessages) {
                printMessage(severity, current);
            }
        }
    }

    /**
     * Метод печатает обработанное сообщение
     *
     * @param severity Уровень важности сообщения
     * @param message  Основное троковое сообщение, которое нужно обработать
     */
    private static void printMessage(Severity severity, String message) {
        if (message != null) {
            ConsolePrinter.print(TimeStampMessageDecorator.decorate(message) + " " + SeverityDecorator.decorate(severity));
        }
    }

    /**
     * Метод проверяющий наличие сообщения в массиве сообщений
     *
     * @param message Сообщение, которое проверяем на наличие в массиве
     * @param array   Массив сообщений, с которым сравниваем
     * @return isArrayContainsMessage признак наличия или отсутствия элемента в массиве
     */
    private static boolean isArrayContainsMessage(String message, String... array) {
        boolean isArrayContainsMessage = false;
        if (array != null) {
            for (String current : array) {
                if (current != null && current.equals(message)) {
                    isArrayContainsMessage = true;
                    break;
                }
            }
        }
        return isArrayContainsMessage;
    }
}