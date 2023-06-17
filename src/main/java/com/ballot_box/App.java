package com.ballot_box;

import com.ballot_box.utility.AppInfo;
import com.ballot_box.utility.Design;
import com.ballot_box.utility.InputTaking;
import com.ballot_box.utility.Print;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.Set;
import java.util.List;

import com.ballot_box.entities.Address;
import com.ballot_box.entities.Campaign;
import com.ballot_box.entities.Candidate;
import com.ballot_box.entities.Election;
import com.ballot_box.entities.Profile;
import com.ballot_box.entities.ProposedAgenda;
import com.ballot_box.entities.User;
import com.ballot_box.exceptions.CampaignNotFoundException;
import com.ballot_box.exceptions.NotFoundException;
import com.ballot_box.exceptions.SomethingWentWrongException;
import com.ballot_box.exceptions.UserNotFoundException;
import com.ballot_box.services.CampaignService;
import com.ballot_box.services.CampaignServiceImp;
import com.ballot_box.services.CandidateService;
import com.ballot_box.services.CandidateServiceImp;
import com.ballot_box.services.ElectionService;
import com.ballot_box.services.ElectionServiceImp;
import com.ballot_box.services.UserService;
import com.ballot_box.services.UserServiceImp;
import com.ballot_box.ui.MainUI;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;
import org.hibernate.internal.SessionFactoryImpl;

public class App 
{
    static{
        Configurator.setLevel(SessionFactoryImpl.class.getName(), Level.ERROR);
    }
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

            Integer option = it.integer(false);
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
                            ADMIN_LOOP:
                            while(true){
                                Print.printLine(1);
                                MainUI.welcomeMsg("admin");
                                MainUI.createOption(MainUI.ADMIN_PAGE_OPTION, false);
                                Print.printLine(1);
                                MainUI.optionInput();

                                Integer adminOption = it.integer(false);                                
                                
                                Print.printLine(1);

                                switch(adminOption){
                                    case 1:{
                                        MainUI.selectedOption(MainUI.ADMIN_PAGE_OPTION[adminOption-1]);
                                        Print.printLine(1);
                                        ElectionService electionService = new ElectionServiceImp();
                                        
                                        try{
                                            Election election = electionService.getCurrentElection();
                                            if(election == null) throw new NotFoundException("No Election found");
                                            MainUI.success("Election is already live");
                                            Thread.sleep(1000);
                                            continue ADMIN_LOOP;
                                        }
                                        catch(NotFoundException e){
                                            Election election = new Election(new HashSet<>());
                                            electionService.addElection(election);
                                            MainUI.success("Election is live now");
                                            Thread.sleep(1000);
                                        }
                                        catch(Exception e){
                                            MainUI.error(e.getMessage());
                                        }
                                        break;
                                    }
                                    case 2:{
                                        MainUI.selectedOption(MainUI.ADMIN_PAGE_OPTION[adminOption-1]);
                                        Print.printLine(1);
                                        ElectionService electionService = new ElectionServiceImp();
                                        
                                        try{
                                            electionService.removeCurrentElection();
                                            MainUI.success("Election Terminated Successfully");
                                            Thread.sleep(1000);
                                        }
                                        catch(Exception e){
                                            MainUI.error(e.getMessage());
                                        }
                                        break;
                                    }
                                    case 3:{
                                        MainUI.error("Pending...");
                                        break;
                                    }
                                    case 4:{
                                        MainUI.error("Pending...");
                                        break;
                                    }
                                    case 5:{
                                        MainUI.error("Pending...");
                                        break;
                                    }
                                    case 6:{
                                        MainUI.error("Pending...");
                                        break;
                                    }
                                    case 7:{
                                        continue MAIN_LOOP;
                                    }
                                }
                            }
                        }
                        Print.printLine(1);
                        MainUI.error("Invalid Credentials");
                        Thread.sleep(1000);
                        break;
                    }
                    case 2:{
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

                        UserService userService = new UserServiceImp();

                        User user;

                        try{
                            user = userService.getUserByUsername(username);

                            if(user.getCandidate() == null || user.getPassword().equals(password) == false){
                                throw new UserNotFoundException("User not found");
                            }
                        }
                        catch(UserNotFoundException e){
                            Print.printLine(1);
                            MainUI.warning("Invalid Credentials!");
                            Thread.sleep(1000);
                            continue MAIN_LOOP;
                        }
                        catch(SomethingWentWrongException e){
                            MainUI.error(e);
                            continue MAIN_LOOP;
                        }
                        
                        MainUI.welcomeMsg(user.getProfile().getFirstName() + " " + user.getProfile().getLastName());

                        USER_LOOP:
                        while(true){
                            MainUI.createOption(MainUI.CANDIDATE_PAGE_OPTION, false);
                            Print.printLine(1);
                            MainUI.optionInput();

                            Integer candidateOption = it.integer(false);
                            Print.printLine(1);

                            MainUI.selectedOption(MainUI.CANDIDATE_PAGE_OPTION[candidateOption-1]);

                            switch(candidateOption){
                                case 1:{
                                    Print.printLine(1);
                                    MainUI.optionsTitle("PLEASE ENTER CAMPAIGN DETAILS", true);

                                    MainUI.takeInput("Campaign Title", "String");
                                    String campaignTitle = it.string();
                                    if(campaignTitle == null) continue USER_LOOP;
                                    
                                    Print.printLine(1);
                                    
                                    MainUI.takeInput("Campaign Slogan", "String");
                                    String campaignSlogan = it.string();
                                    if(campaignSlogan == null) continue USER_LOOP;

                                    ProposedAgenda proposedAgenda = new ProposedAgenda();

                                    Set<String> policies = new HashSet<>();

                                    do{
                                        Print.printLine(1);
                                        MainUI.takeInput("Proposed Agenda " + (policies.size()+1), "String");
                                        String pa = it.string();
                                        if(pa == null) continue USER_LOOP;

                                        policies.add(pa);

                                        Print.printLine(1);

                                        MainUI.choiceInput("Continue adding more policies?");
                                    }
                                    while(it.bool(false));

                                    proposedAgenda.setPolicyPriorities(policies);

                                    Campaign campaign = new Campaign(campaignTitle, campaignSlogan, proposedAgenda);
                                    
                                    campaign.setCandidate(user.getCandidate());

                                    CampaignService campaignService = new CampaignServiceImp();
                                    campaignService.addCampaign(campaign);

                                    Print.printLine(1);

                                    MainUI.success("Campaign is setuped, Now you can live your campaign");

                                    Thread.sleep(1000);
                                    break;
                                }
                                case 2: {
                                    Print.printLine(1);
                                    ElectionService electionService = new ElectionServiceImp();
                                    
                                    try{
                                        electionService.addCampaign(user.getCandidate().getCampaign());
                                        MainUI.success("Your Campaign is live now");
                                    }
                                    catch(Exception e){
                                        MainUI.error(e.getMessage());
                                    }
                                }
                            }
                        }

                        // break;
                    }
                    case 3:{
                        MainUI.selectedOption(MainUI.LANDING_PAGE_OPTION_DEFAULT[option-1]);
                        Print.printLine(1);
                    }
                    case 4:{
                        MainUI.selectedOption(MainUI.LANDING_PAGE_OPTION_DEFAULT[option-1]);
                        Print.printLine(1);

                        User user;
                        Profile profile;

                        MainUI.optionsTitle("ACCOUNT INFORMATION", true);

                        MainUI.takeInput("First Name", "String");
                        String firstName = it.string();
                        if(firstName == null) continue MAIN_LOOP;

                        Print.printLine(1);

                        MainUI.takeInput("Last Name", "String");
                        String lastName = it.string();
                        if(lastName == null) continue MAIN_LOOP;

                        Print.printLine(1);

                        MainUI.takeInput("Email", "String");
                        String email = it.string();
                        if(email == null) continue MAIN_LOOP;

                        Print.printLine(1);

                        MainUI.takeInput("Date Of Birth (YYYY-MM-DD)", null);
                        LocalDate dateOfBirth = it.date("yyyy-MM-dd");
                        if(dateOfBirth == null) continue MAIN_LOOP;
                        
                        Print.printLine(1);

                        // profile input ends here

                        profile = new Profile(firstName, lastName, email, dateOfBirth, null);

                        // if want to add address details right now
                        MainUI.choiceInput("Want to add Address details right now?");
                        Boolean hasAddress = it.bool(true);
                        if(hasAddress == null) continue MAIN_LOOP;

                        if(hasAddress){
                            Print.printLine(1);
                            MainUI.takeInput("House No.", "String");
                            String houseNo = it.string();
                            if(houseNo == null) continue MAIN_LOOP;

                            Print.printLine(1);

                            MainUI.takeInput("State", "String");
                            String state = it.string();
                            if(state == null) continue MAIN_LOOP;

                            Print.printLine(1);

                            MainUI.takeInput("Country", "String");
                            String country = it.string();
                            if(country == null) continue MAIN_LOOP;

                            Print.printLine(1);

                            MainUI.takeInput("Zipcode", "Integer");
                            Integer zipcode = it.integer(true);
                            if(zipcode == null) continue MAIN_LOOP;

                            profile.setAddress(new Address(houseNo, zipcode, state, country));
                        }

                        // address input ends here

                        Print.printLine(1);

                        // user input starts

                        MainUI.takeInput("Username", "String");
                        String username = it.string();
                        if(username == null) continue MAIN_LOOP;

                        UserService userService = new UserServiceImp();

                        try{
                            while(userService.userExist(username)){
                                Print.printLine(1);
                                MainUI.error("Username already exist");
                                Print.printLine(1);
                                MainUI.takeInput("Username", "String");
                                username = it.string();
                                if(username == null) continue MAIN_LOOP;
                            }
                        }
                        catch(Exception e){
                            // do nothing (username not found exception)
                        }
                        
                        
                        Print.printLine(1);

                        MainUI.takeInput("Password", "String");
                        String password = it.string();
                        if(password == null) continue MAIN_LOOP;
                        
                        Print.printLine(1);

                        // user input ends here

                        user = new User(username, password, profile);

                        // if want to register as a candidate
                        MainUI.choiceInput("Register as a Candidate?");
                        Boolean isCandidate = it.bool(true);
                        if(isCandidate == null) continue MAIN_LOOP;

                        if(isCandidate){
                            Print.printLine(1);
                            MainUI.takeInput("Biography", "String");
                            String biography = it.string();
                            if(biography == null) continue MAIN_LOOP;
                        
                            Print.printLine(1);

                            MainUI.takeInput("Year of Experience", "Integer");
                            Integer experience = it.integer(true);
                            if(experience == null) continue MAIN_LOOP;
                        
                            Print.printLine(1);

                            MainUI.takeInput("Qualification", "String");
                            String qualification = it.string();
                            if(qualification == null) continue MAIN_LOOP;
                        
                            Print.printLine(1);

                            MainUI.takeInput("Contact No (10-digit)", null);
                            Long contactNo = it.longInt(true);
                            if(contactNo == null) continue MAIN_LOOP;

                            profile.setBiography(biography); // addition profile info for candidate

                            Candidate candidate = new Candidate(experience.byteValue(), qualification, contactNo, user);
                            CandidateService candidateService = new CandidateServiceImp();
                            candidateService.addCandidate(candidate);
                        }
                        else{
                            userService.addUser(user);
                        }
                        Print.printLine(1);
                        MainUI.success("Your account has been created successfully!");
                        Print.printLine(1);
                        Thread.sleep(1000);
                    }
                }
            }
            catch(Exception e){
                // error
                MainUI.error(e);
            }
        }
    }
}
