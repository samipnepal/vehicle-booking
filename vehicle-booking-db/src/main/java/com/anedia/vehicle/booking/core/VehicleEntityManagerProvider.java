package com.anedia.vehicle.booking.core;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


public class VehicleEntityManagerProvider {


    @PersistenceContext(name = "jcg-JPA")
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

}
