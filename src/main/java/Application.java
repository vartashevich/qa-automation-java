import com.tcs.edu.LogException;
import com.tcs.edu.domain.Message;
import com.tcs.edu.helper.Doubling;
import com.tcs.edu.helper.MessageOrder;
import com.tcs.edu.helper.Severity;
import com.tcs.edu.processor.OrderedDistinctedMessageService;
import com.tcs.edu.repository.HashMapMessageRepository;

import java.util.UUID;

/**
 * Класс, выступающий входной точкой для тестового приложения
 */
class Application {
    /**
     * Тестовое приложения для выполнения домашних заданий по курсу
     *
     * @param args аргументы командной строки передаваемые строками
     */
    public static void main(String[] args) {
        Message m1 = new Message("Hello!", Severity.REGULAR);
        Message m2 = new Message("Hello2!", Severity.MAJOR);
        Message m3 = new Message("Hello3!", Severity.MAJOR);
        Message m4 = null;
        OrderedDistinctedMessageService service = new OrderedDistinctedMessageService(
                new HashMapMessageRepository()
        );
        try {
            service.logMessage(m1);
            service.logMessage(MessageOrder.DESC, Doubling.DOUBLES, m2);
            service.logMessage(m3);
            service.logMessage(m4);
        } catch (LogException e) {
            e.printStackTrace();
        }
        final UUID key = m2.getId();
        System.out.println("________________________");
        System.out.println(service.findAll());
        System.out.println(key);
        System.out.println(service.findByPrimaryKey(key));
        System.out.println("________________");
        System.out.println(service.findBySeverity(Severity.MAJOR));
    }
}