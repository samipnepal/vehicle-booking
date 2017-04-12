package com.anedia.vehicle.booking.core;

import javax.persistence.EntityManager;

public interface DBCommand<T> {

    T execute(EntityManager em);

}
