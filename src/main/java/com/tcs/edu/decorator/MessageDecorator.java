package com.tcs.edu.decorator;

import com.tcs.edu.domain.Message;

/**
 * Created on 04.05.2022
 *
 * @author Viktor Artashevich
 */
public interface MessageDecorator {
    Message decorate(Message message);
}
