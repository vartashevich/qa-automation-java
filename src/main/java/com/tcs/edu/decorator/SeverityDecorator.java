package com.tcs.edu.decorator;

import com.tcs.edu.helper.Severity;

/**
 * Created on 13.04.2022
 *
 * @author Viktor Artashevich
 * Класс для преобразования Severity в строковое представление
 */
public class SeverityDecorator {
    /**
     * Метод преобразует полученную Enum важность в строковое представление
     *
     * @param severity уровень важности, который берем из Enum
     * @return важность преобразованная  в строку
     */
    public static String decorate(Severity severity) {
        String decoratedSeverity;
        switch (severity) {
            case MINOR: {
                decoratedSeverity = "()";
                break;
            }
            case REGULAR: {
                decoratedSeverity = "(!)";
                break;
            }
            case MAJOR: {
                decoratedSeverity = "(!!!)";
                break;
            }
            default: {
                decoratedSeverity="UNKNOWN SEVERITY!!!";
            }
        }
        return decoratedSeverity;
    }
}
