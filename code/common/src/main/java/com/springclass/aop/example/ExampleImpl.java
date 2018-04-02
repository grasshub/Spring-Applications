package com.springclass.aop.example;

import java.io.FileNotFoundException;
import java.io.IOException;

public class ExampleImpl implements Example{

    public String findFileOperation() throws FileNotFoundException {
        return "return findFileOperation";
    }
    public String networkOperation() throws IOException {
        return "return networkOperation";
    }
    public Long formatNumberOperation() throws NumberFormatException {
        return 12345L;
    }

    public String exampleOperation() throws Exception {
        return "return exampleOperation";
    }

} // The End...
