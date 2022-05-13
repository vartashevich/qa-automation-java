package com.tcs.edu;

import com.tcs.edu.domain.Message;
import com.tcs.edu.helper.Doubling;
import com.tcs.edu.helper.MessageOrder;

/**
 * Created on 06.05.2022
 *
 * @author Viktor Artashevich
 */
public interface MessageService {
    Message[] sortMessages(final MessageOrder order, final Message... processedMessages);

    Message[] processMessages(Doubling doubling, Message... allMessages);


    void logMessage(Message message, Message... messages);


    void logMessage(MessageOrder order, Message message, Message... messages);

    void logMessage(MessageOrder order, Doubling doubling, Message message, Message... messages);
}
