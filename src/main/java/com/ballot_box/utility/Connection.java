package com.ballot_box.utility;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Connection {
    static EntityManagerFactory entityManagerFactory;

    static {
        entityManagerFactory = Persistence.createEntityManagerFactory("ballotbox_cw");
    }

    static EntityManager getEntityManager(){
        return entityManagerFactory.createEntityManager();
    }
}
