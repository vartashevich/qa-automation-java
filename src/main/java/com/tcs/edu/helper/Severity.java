package com.tcs.edu.helper;

/**
 * Created on 13.04.2022
 *
 * @author Viktor Artashevich
 * Содержит уровни важности сообщений
 */
public enum Severity {
    MINOR("()"), REGULAR("(!)"), MAJOR("(!!!)");
    private final String value;

    Severity(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
