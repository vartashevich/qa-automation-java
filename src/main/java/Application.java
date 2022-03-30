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
        ConsolePrinter.print("Hello world!");
        ConsolePrinter.print(
                TimeStampMessageDecorator.decorate("My new message")
        );
        ConsolePrinter.print(
                TimeStampMessageDecorator.decorate("My new message2")
        );
        ConsolePrinter.print(
                TimeStampMessageDecorator.decorate("My new message3")
        );
    }
}