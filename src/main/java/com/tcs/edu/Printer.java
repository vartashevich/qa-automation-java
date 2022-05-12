package com.tcs.edu;

import com.tcs.edu.domain.Message;

/**
 * Created on 04.05.2022
 *
 * @author Viktor Artashevich
 */
public interface Printer {
    void print(Message decoratedMessage);
}
