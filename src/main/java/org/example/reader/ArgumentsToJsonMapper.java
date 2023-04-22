package org.example.reader;

import com.google.gson.stream.JsonReader;
import org.example.Printer;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class ArgumentsToJsonMapper {

    Printer printer = new Printer();

    public List<JsonReader> mapArgumentsToJson(String[] args){
        List<JsonReader> readiedJsonFiles = new ArrayList<>();
        for (String argument : args ){
            try{
                readiedJsonFiles.add(
                        new JsonReader(new FileReader(argument)));
            }
            catch(FileNotFoundException e){
                printer.printFileNotFound();
                System.exit(0);
            }
        }
        return readiedJsonFiles;
    }
}
