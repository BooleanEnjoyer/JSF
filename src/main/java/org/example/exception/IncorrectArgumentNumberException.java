package org.example.exception;

import org.example.service.Printer;

public class IncorrectArgumentNumberException extends Exception {

    Printer printer = new Printer();

    public IncorrectArgumentNumberException() {
        printer.printWrongArgumentAmount();
    }
}
