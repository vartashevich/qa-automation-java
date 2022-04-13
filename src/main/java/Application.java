import com.tcs.edu.processor.MessageService;
import com.tcs.edu.helper.Severity;
import com.tcs.edu.printer.ConsolePrinter;


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
            ConsolePrinter.print(
                    // TimeStampMessageDecorator.decorate("My new message")
                    MessageService.service(Severity.MAJOR, "My major message!")
            );
        }
        ConsolePrinter.print((MessageService.service(Severity.REGULAR, "My regular message!")));
        ConsolePrinter.print((MessageService.service(Severity.MINOR, "My minor message!")));

    }
}