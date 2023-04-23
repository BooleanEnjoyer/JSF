package org.example.reader;

import org.example.exceptions.IncorrectArgumentNumberException;

public class ArgumentsValidator {

    private static final int EXPECTED_ARGUMENTS_COUNT = 2;

    protected void validateArguments(String[] argumentsArray)
            throws IncorrectArgumentNumberException {

        Integer argumentsArraySize = argumentsArray.length;

        if(!argumentsArraySize.equals(EXPECTED_ARGUMENTS_COUNT)){
            throw new IncorrectArgumentNumberException();
        }
    }
}
