import com.tcs.edu.domain.Message;
import com.tcs.edu.helper.Doubling;
import com.tcs.edu.helper.MessageOrder;
import com.tcs.edu.helper.Severity;
import com.tcs.edu.processor.MessageService;

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
      /*  for (int i = 0; i < 6; i++) {
            MessageService.logMessage(Severity.MAJOR, "My major message! ");
        }
        MessageService.logMessage(null, MessageOrder.DESC, "111", "222", "333");
        MessageService.logMessage(Severity.REGULAR, MessageOrder.ASC, "aaa", "bbb", "ccc");
        MessageService.logMessage(Severity.REGULAR, MessageOrder.ASC, Doubling.DISTINCT, "!!!", "@@@", "###", "###", "@@@", "!!!");
        MessageService.logMessage(Severity.REGULAR, MessageOrder.ASC, (Doubling) null, "!!!", "@@@", "###", "###", "@@@", "!!!");
        Message message1= new Message("Hello!", Severity.REGULAR);

       */
        Message m1 = new Message("Hello!", Severity.REGULAR);
        MessageService.logMessage(m1);
        Message m2 = new Message("Hello2!", Severity.MAJOR);
        MessageService.logMessage(MessageOrder.ASC,m2);
       MessageService.logMessage(MessageOrder.ASC, m1,m2);
       MessageService.logMessage(MessageOrder.DESC,Doubling.DOUBLES,m1,m2);
    }
}