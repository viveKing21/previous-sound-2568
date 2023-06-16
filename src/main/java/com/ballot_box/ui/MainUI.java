package com.ballot_box.ui;

import com.ballot_box.utility.AppInfo;
import com.ballot_box.utility.Design;
import com.ballot_box.utility.Print;

final public class MainUI {
    final public static Design DESCRIPTION_DESIGN = new Design(101);
    final public static Design WELCOME_MESSAGE_DESIGN = new Design(53);

    static {
        DESCRIPTION_DESIGN.setBorder(2);
        DESCRIPTION_DESIGN.setBorderColor(Design.BLACK);
        DESCRIPTION_DESIGN.setBorderStyle("\u2588_", "\u2588");
        DESCRIPTION_DESIGN.setPadding(1);

        WELCOME_MESSAGE_DESIGN.setBorder(1);
        WELCOME_MESSAGE_DESIGN.setBorder(1, 0, 1, 0);
        WELCOME_MESSAGE_DESIGN.setBorderStyle("~");
        WELCOME_MESSAGE_DESIGN.setBorderColor(Design.BLUE);
        WELCOME_MESSAGE_DESIGN.setPadding(0, 1, 0, 1);
        WELCOME_MESSAGE_DESIGN.bold(true);
        WELCOME_MESSAGE_DESIGN.setTextColor(Design.BLUE);
    }

    public static String[] LANDING_PAGE_OPTION_DEFAULT = new String[]{
        "Login as Administrator",
        "Login as Candidate",
        "Login as Participant",
        "Register as a Candidate/Participant"
    };

    public static void landingPage(){
        Print.printStyle(AppInfo.LOGO, Design.GREEN);
        Print.printLine(1);
        Print.printDesign(AppInfo.DESCRIPTION, DESCRIPTION_DESIGN);
    }
    public static void welcomeMsg(String name){
        if(name == null) Print.printDesign("Welcome to the BallotBox (Online Voting System)", WELCOME_MESSAGE_DESIGN);
        else Print.printDesign("Welcome back '"+Print.wrapStyle(name, Design.GREEN, Design.ITALIC, Design.BOLD)+"' (Happy to see you again :)", WELCOME_MESSAGE_DESIGN);
    }
    static public void optionInput(){
        Print.printStyle("PLEASE SELECT AN OPTION (Enter the code) :" + Design.BLANK, Design.GREEN_BACKGROUND);
    }
    public static void optionsTitle(String title, boolean cancelable){
        if(cancelable){
            Print.printStyle(title + " (TYPE '"+Print.wrapStyle('C', Design.GREEN, Design.BOLD, Design.ITALIC, Design.UNDERLINE), Design.ITALIC, Design.UNDERLINE);
            System.out.print(" ");
            Print.printlnStyle("' FOR CANCELING ANYTIME):-\n", Design.ITALIC, Design.UNDERLINE);
        }
        else{
            Print.printlnStyle(title + " :-\n", Design.ITALIC, Design.UNDERLINE);
        }
    }
    public static void createOption(String[] options, boolean cancelable){
        optionsTitle("OPTIONS", false);

        int index = 1;
        for(String option : options){
            Print.printlnStyle("-" + option +" ("+Print.wrapStyle(index, Design.GREEN)+")", Design.ITALIC, Design.YELLOW);
            index++;
        }
    }
    static public void takeInput(String feild, String type){
        if(type != null){
            Print.printStyle(feild + " (" + type +") :" + Design.BLANK, Design.CYAN_BACKGROUND, Design.WHITE, Design.BOLD);
        }
        else{
            Print.printStyle(feild + " :" + Design.BLANK, Design.CYAN_BACKGROUND, Design.WHITE, Design.BOLD);
        }
    }
    static public void selectedOption(String title){
        Print.printlnStyle(title, Design.CYAN, Design.BOLD);
    }
    static public void inputError(boolean cancelable){
        Print.printLine(1);
        if(cancelable){
            Print.printStyle("PLEASE ENTER A VALID INPUT (PRESS 'C' FOR CANCEL) :"+ Design.BLANK, Design.RED_BACKGROUND, Design.WHITE, Design.BOLD);
        }
        else{
            Print.printStyle("PLEASE ENTER A VALID INPUT :"+ Design.BLANK, Design.RED_BACKGROUND, Design.WHITE, Design.BOLD);
        }
    }
    static public void error(Exception e){
        Print.printLine(2);
        Print.printlnStyle(" Something Went Wrong :( ", Design.RED_BACKGROUND, Design.WHITE);
        new Exception(Print.wrapStyle(e.getMessage(), Design.RED_BACKGROUND, Design.WHITE));
    }
    static public void error(String msg){
        Print.printlnStyle(msg, Design.RED, Design.RED_BACKGROUND, Design.WHITE);
    }
    static public void warning(String msg){
        Print.printlnStyle(msg, Design.RED, Design.YELLOW_BACKGROUND, Design.BOLD);
    }
    // success messages
    static public void success(String msg){
        Print.printlnStyle(msg, Design.BLACK, Design.CYAN_BACKGROUND, Design.BOLD);
    }
}
