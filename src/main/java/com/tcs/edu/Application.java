package com.tcs.edu;

import static com.tcs.edu.printer.ConsolePrinter.print;
import static com.tcs.edu.decorator.TimeStampMessageDecorator.decorate;

class Application {
    public static void main(String[] args) {
       print(decorate("My new message"));

    }
}