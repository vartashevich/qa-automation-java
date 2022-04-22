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
        for (int i = 0; i < 6; i++) {
            MessageService.logMessage(Severity.MAJOR, "My major message! ");
        }
        MessageService.logMessage(Severity.MAJOR, MessageOrder.DESC, "111", "222", "333");
        MessageService.logMessage(Severity.REGULAR, MessageOrder.ASC, "aaa", "bbb", "ccc");
        MessageService.logMessage(Severity.REGULAR, MessageOrder.ASC, Doubling.DISTINCT, "!!!", "@@@", "###", "###", "@@@", "%%%");
    }
}