package com.tcs.edu.repository;

import com.tcs.edu.LogException;
import com.tcs.edu.domain.Message;
import com.tcs.edu.helper.Severity;
import com.tcs.edu.processor.ValidatedService;

import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created on 24.05.2022
 * Класс отвечающий за хранение Сообщений в памяти
 *
 * @author Viktor Artashevich
 */
public class HashMapMessageRepository extends ValidatedService implements MessageRepository {
    HashMap<UUID, Message> hashMapMessages = new HashMap<>();

    /**
     * Метод по добавлению Сообщения в память
     *
     * @param message сообщение, которое надо сохранить в памяти
     * @return возвращает идентификатор записи
     */
    public UUID create(Message message) throws LogException {
        UUID uuid = UUID.randomUUID();
        try {
            super.checkAgsValid(message);
            message.setId(uuid);
            hashMapMessages.put(uuid, message);
            System.out.println(hashMapMessages.values());
            return uuid;
        } catch (IllegalArgumentException e) {
            throw new LogException("Невалидный параметр", e);
        }
    }

    @Override
    /**
     * Метод поиска записи по идентификатору
     */
    public Message findByPrimaryKey(UUID key) {
        return hashMapMessages.get(key);
    }

    @Override
    /**
     * Метод поиска всех записей в памяти
     */
    public Collection<Message> findAll() {
        return hashMapMessages.values();
    }

    /**
     * Метод поиска записей по уровню важности
     *
     * @param by параметр важности сообщения
     * @return возвращает записи соответствующие уровню важности
     */
    @Override
    public Collection<Message> findBySeverity(Severity by) {
        return hashMapMessages.values().stream().filter(message -> message.getSeverity() == by).collect(Collectors.toList());
    }
}
