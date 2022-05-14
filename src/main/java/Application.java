import com.tcs.edu.MessageService;
import com.tcs.edu.decorator.SeverityDecorator;
import com.tcs.edu.decorator.TimeStampMessageDecorator;
import com.tcs.edu.domain.Message;
import com.tcs.edu.helper.Doubling;
import com.tcs.edu.helper.MessageOrder;
import com.tcs.edu.helper.Severity;
import com.tcs.edu.printer.ConsolePrinter;
import com.tcs.edu.processor.OrderedDistinctedMessageService;

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
        Message m3 = new Message("Hello!", Severity.REGULAR);
        MessageService service = new OrderedDistinctedMessageService(new ConsolePrinter(), new SeverityDecorator(), new TimeStampMessageDecorator(5));
        service.logMessage(m1);
        service.logMessage(MessageOrder.DESC, Doubling.DOUBLES, m1, m2);
        service.logMessage(MessageOrder.ASC, m2);
        service.logMessage(MessageOrder.ASC, m1, m2);
        service.logMessage(MessageOrder.DESC, Doubling.DOUBLES, m1, m2);
        service.logMessage(MessageOrder.DESC, Doubling.DISTINCT, m1, m2);
        service.logMessage(MessageOrder.ASC, Doubling.DEFAULT, m1, m2);
        System.out.println(m1.equals(m2));
        System.out.println(m1.equals(m3));
        System.out.println(m1.hashCode());
    }
}