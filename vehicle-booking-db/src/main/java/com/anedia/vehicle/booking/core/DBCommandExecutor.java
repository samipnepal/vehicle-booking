package com.anedia.vehicle.booking.core;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class DBCommandExecutor {

    @Inject
    private EntityManagerProvider entityManagerProvider;

    public <T> T executeCommand(final DBCommand<T> dbCommand) {
        final EntityManager entityManager = getEntityManager();
        try {
            return dbCommand.execute(entityManager);
        } catch (final Exception exception) {
            throw new IllegalStateException(exception);
        }
    }

    private EntityManager getEntityManager() {
        return entityManagerProvider.getEntityManager();
    }
}
