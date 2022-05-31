package com.tcs.edu.domain;

import com.tcs.edu.helper.Severity;

import java.util.Objects;
import java.util.UUID;

/**
 * Created on 27.04.2022
 * Класс Message отвечает за поля severity и body (строковое сообщение). Содержит идентификатор записи
 * сообщения в памяти
 *
 * @author Viktor Artashevich
 */
public class Message {
    final private String body;
    final private Severity severity;
    private UUID id;

    public Message(String body) {
        this.body = body;
        this.severity = Severity.REGULAR;
    }

    /**
     * Конструктор, принимающий body и severity
     *
     * @param body     строковое сообщение
     * @param severity уровень важности сообщения
     */
    public Message(String body, Severity severity) {
        this.body = body;
        this.severity = severity;
    }

    public String getBody() {
        return body;
    }

    public Severity getSeverity() {
        return severity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(body, message.body) && severity == message.severity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(body, severity);
    }

    @Override
    public String toString() {
        return "Message{" +
                "body='" + body + '\'' +
                ", severity=" + severity +
                '}';
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
