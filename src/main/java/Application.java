import com.tcs.edu.processor.MessageService;
import com.tcs.edu.helper.Severity;


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

        for (int i = 0; i < 7; i++) {

            MessageService.logMessage(Severity.MAJOR, "My major message!");

        }

        MessageService.logMessage(Severity.REGULAR, "My regular message!");
        MessageService.logMessage(Severity.MINOR, "My minor message!");

    }
}