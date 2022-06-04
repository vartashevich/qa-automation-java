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
     * Метод поиска всех записей в памяти. Если сообщения с таким ключем нет, то вернем null.
     */
    Collection<Message> findAll();

    /**
     * Метод поиска записей по уровню важности. Если сообщения с такой важностью нет, то вернем пустую коллекцию.
     *
     * @param by параметр важности сообщения
     * @return возвращает коллекцию записей соответствующих уровню важности
     */
    Collection<Message> findBySeverity(Severity by);
}
