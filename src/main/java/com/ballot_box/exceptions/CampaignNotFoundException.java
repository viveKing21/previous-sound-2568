package com.ballot_box.exceptions;

public class CampaignNotFoundException extends Exception{
    public CampaignNotFoundException(String msg){
        super(msg);
    }
}