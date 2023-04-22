package org.example.exceptions;

import org.example.Printer;

public class IncorrectArgumentNumberException extends Exception {

    Printer printer = new Printer();

    public IncorrectArgumentNumberException() {
        printer.printWrongArgumentAmount();
    }
}
