package com.tcs.edu.repository;

import com.tcs.edu.LogException;
import com.tcs.edu.domain.Message;
import com.tcs.edu.helper.Severity;

import java.util.Collection;
import java.util.UUID;

/**
 * Created on 24.05.2022
 *
 * @author Viktor Artashevich
 */
public interface MessageRepository {
    /**
     * Метод по добавлению Сообщения в память
     */
    UUID create(Message message) throws LogException;

    /**
     * Метод поиска записи по идентификатору
     */
    Message findByPrimaryKey(UUID key);

    /**
     * Метод поиска всех записей в памяти
     */
    Collection<Message> findAll();

    /**
     * Метод поиска записей по уровню важности
     *
     * @param by параметр важности сообщения
     * @return возвращает записи соответствующие уровню важности
     */
    Collection<Message> findBySeverity(Severity by);
}
