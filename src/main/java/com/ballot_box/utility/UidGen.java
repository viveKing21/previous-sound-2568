package com.ballot_box.utility;

import java.util.UUID;

public class UidGen {
    static public String generate(){
        return UUID.randomUUID().toString();
    }
}
