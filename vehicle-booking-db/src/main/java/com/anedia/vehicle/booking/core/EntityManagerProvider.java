package com.anedia.vehicle.booking.core;

import javax.persistence.EntityManager;

/**
 * Interface to entityManager provider.
 *
 * @author yadab
 */
public interface EntityManagerProvider {

    EntityManager getEntityManager();
}
