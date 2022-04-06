import com.tcs.edu.decorator.TimeStampMessageDecorator;
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

        for (int i = 0; i < 10; i++) {
            ConsolePrinter.print(
                    TimeStampMessageDecorator.decorate("My new message")
            );
        }
    }
}