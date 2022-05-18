package com.tcs.edu.printer;

import com.tcs.edu.LogException;
import com.tcs.edu.Printer;
import com.tcs.edu.domain.Message;
import com.tcs.edu.processor.ValidatedService;

/**
 * Класс содержит метод для печати сообщения в консоль
 *
 * @author Artashevich Viktor
 */
public class ConsolePrinter extends ValidatedService implements Printer {
    /**
     * Метод печатает сообщение
     * Нет побочных эффектов
     *
     * @param message Сообщение, которое необходимо напечатать
     * @throws выбрасывает исключение типа LogException при IAE
     */
    public void print(Message message) throws LogException {
        try {
            super.checkAgsValid(message);
            System.out.println(message.getBody());
        } catch (IllegalArgumentException e) {
            throw new LogException("Невалидный параметр", e);
        }
    }
}