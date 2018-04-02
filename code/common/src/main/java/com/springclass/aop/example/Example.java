package com.springclass.aop.example;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by mickknutson on 3/26/16.
 */
public interface Example {

    String findFileOperation() throws FileNotFoundException;
    String networkOperation() throws IOException;
    Long formatNumberOperation() throws NumberFormatException;

    String exampleOperation() throws Exception;

} // The End...
