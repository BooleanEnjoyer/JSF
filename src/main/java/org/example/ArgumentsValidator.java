package org.example;

import java.util.Arrays;

public class ArgumentsValidator {
    private static final long WANTED_ARGUMENTS_AMOUNT = 2;
    Printer printer = new Printer();
    public void validateArguments(String[] argumentsArray){
        Long argumentsArraySize = Arrays.stream(argumentsArray).count();
        boolean correctSize = argumentsArraySize.equals(WANTED_ARGUMENTS_AMOUNT) ;
        if(!correctSize){
            printer.printWrongArgumentAmount();
            System.exit(0);
        }
    }
}
