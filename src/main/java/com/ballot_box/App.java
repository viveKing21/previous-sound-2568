package com.ballot_box;

import com.ballot_box.utility.AppInfo;
import com.ballot_box.utility.Design;
import com.ballot_box.utility.InputTaking;
import com.ballot_box.utility.Print;

import java.util.ResourceBundle;
import java.util.Scanner;

import com.ballot_box.ui.MainUI;

public class App 
{
    public static void main( String[] args )
    {
        InputTaking it = new InputTaking();

        MAIN_LOOP:
        while(true){
            MainUI.landingPage();
            MainUI.createOption(MainUI.LANDING_PAGE_OPTION_DEFAULT, false);
            Print.printLine(1);
            MainUI.welcomeMsg(null);
            MainUI.optionInput();

            int option = it.integer(false);
            Print.printLine(1);
            
            try{
                switch(option){
                    case 1:{
                        ResourceBundle resourceBundle = ResourceBundle.getBundle("auth");
                        final String ADMIN_USERNAME = resourceBundle.getString("ADMIN_USER");
                        final String ADMIN_PASSWORD = resourceBundle.getString("ADMIN_PASS");

                        MainUI.selectedOption(MainUI.LANDING_PAGE_OPTION_DEFAULT[option-1]);
                        Print.printLine(1);
                        MainUI.optionsTitle("PLEASE ENTER ACCOUNT DETAILS", true);

                        MainUI.takeInput("USERNAME", "String");
                        String username = it.string();
                        if(username == null) continue MAIN_LOOP;

                        Print.printLine(1);

                        MainUI.takeInput("PASSWORD", "String");
                        String password = it.string();
                        if(password == null) continue MAIN_LOOP;

                        if(ADMIN_USERNAME.equals(username)  && ADMIN_PASSWORD.equals(password)){
                            System.out.println("Login Success");
                        }
                        break;
                    }
                    case 2:{

                    }
                    case 3:{

                    }
                    case 4:{

                    }
                }
            }
            catch(Exception e){
                // error
            }
        }
    }
}
