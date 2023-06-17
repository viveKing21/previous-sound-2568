package com.ballot_box.utility;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import com.ballot_box.exceptions.SomethingWentWrongException;
import com.ballot_box.ui.MainUI;


public class InputTaking{
    String dtmFormat;
    DateTimeFormatter dtm;

    Scanner sc = new Scanner(System.in).useDelimiter("\n");

    public Integer integer(boolean cancelable){
        String output = sc.next().trim();

        if(cancelable && (output.charAt(0) == 'c' || output.charAt(0) == 'C')){
            Print.printLine(1);
            return null;
        }
        try{
            return Integer.parseInt(output);
        }
        catch(Exception e){
            MainUI.inputError(cancelable);
            return integer(cancelable);
        }
    }
    public Long longInt(boolean cancelable){
        String output = sc.next().trim();

        if(cancelable && (output.charAt(0) == 'c' || output.charAt(0) == 'C')){
            Print.printLine(1);
            return null;
        }
        try{
            return Long.parseLong(output);
        }
        catch(Exception e){
            MainUI.inputError(cancelable);
            return longInt(cancelable);
        }
    }
    public Boolean bool(boolean cancelable){
        String output = sc.next().trim();

        if(cancelable && (output.charAt(0) == 'c' || output.charAt(0) == 'C')){
            Print.printLine(1);
            return null;
        }
        try{
            if(output.equalsIgnoreCase("y")) return true;
            if(output.equalsIgnoreCase("n")) return false;
            throw new SomethingWentWrongException("Invalid input");
        }
        catch(Exception e){
            MainUI.inputError(cancelable);
            return bool(cancelable);
        }
    }
    public String string(){
        String output = sc.next().trim();
        if(output.charAt(0) == 'c' || output.charAt(0) == 'C'){
            Print.printLine(1);
            return null;
        }
        return output;
    }
    public LocalDate date(String format){
        String output = sc.next().trim();

        if(output.charAt(0) == 'c' || output.charAt(0) == 'C'){
            Print.printLine(1);
            return null;
        }
        try{
            if(format == null) throw new DateTimeException("Invalid Format");

            if(dtmFormat != format){
                dtmFormat = format;
                dtm = DateTimeFormatter.ofPattern(format);
            }
            return LocalDate.parse(output, dtm);
        }
        catch(Exception e){
            MainUI.inputError(true);
            return date(format);
        }
    }
}
