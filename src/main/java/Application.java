import static com.tcs.edu.printer.ConsolePrinter.*;
import static com.tcs.edu.decorator.TimeStampMessageDecorator.decorate;

class Application {
    public static void main(String[] args) {
        print("Hello world!");
        print(decorate("My new message"));
    }
}