package com.tcs.edu.processor;

import com.tcs.edu.domain.Message;

/**
 * Created on 13.05.2022
 *
 * @author Viktor Artashevich
 */
public abstract class ValidatedService {
public boolean isAgsValid(Message message){
    if (message == null) return false;
    if (message.toString().isEmpty()) return false;
    return true;
}
}
