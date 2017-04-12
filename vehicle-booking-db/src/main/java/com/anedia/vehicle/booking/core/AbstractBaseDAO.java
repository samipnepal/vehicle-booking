package com.anedia.vehicle.booking.core;

import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

import static com.anedia.vehicle.booking.util.DBConstants.ORDINAL_PARAMETER;
import static com.anedia.vehicle.booking.util.DBConstants.SELECT_FROM_CLAUSE;
import static com.anedia.vehicle.booking.util.DBConstants.WHERE_CLAUSE;

public abstract class AbstractBaseDAO<ID, T> {

    @Inject
    protected DBCommandExecutor dbCommandExecutor;

    @Inject
    private Logger logger;

    protected abstract Class<T> getPersistentClass();

    protected T add(final T entity) {
        return dbCommandExecutor.executeCommand(new DBCommand<T>() {

            public T execute(final EntityManager em) {
                em.persist(entity);
                return entity;
            }
        });
    }

    protected void update(final T entity) {
        dbCommandExecutor.executeCommand(new DBCommand<Void>() {

            public Void execute(final EntityManager em) {
                em.merge(entity);
                return null;
            }

        });
    }

    protected List<T> findAll(final String orderField) {
        return dbCommandExecutor.executeCommand(new DBCommand<List<T>>() {

            @SuppressWarnings("unchecked")
            public List<T> execute(final EntityManager em) {
                final StringBuilder query = new StringBuilder(SELECT_FROM_CLAUSE);
                query.append(getPersistentClass().getSimpleName());
                query.append(" e");
                if (orderField != null) {
                    query.append(" Order by e.").append(orderField);
                }
                return em.createQuery(query.toString()).getResultList();
            }
        });
    }

    protected List<T> findByQuery(final CustomQueryBuild customQueryBuild) {
        return dbCommandExecutor.executeCommand(new DBCommand<List<T>>() {

            @SuppressWarnings("unchecked")
            public List<T> execute(final EntityManager em) {
                return buildFindQuery(customQueryBuild).getResultList();
            }
        });
    }

    protected Boolean delete(final ID id) {
        return dbCommandExecutor.executeCommand(new DBCommand<Boolean>() {

            public Boolean execute(final EntityManager em) {
                final T existingData = em.find(getPersistentClass(), id);
                if (existingData == null) {
                    return false;
                }
                em.remove(existingData);
                return true;
            }
        });
    }

    protected int delete(final List<CustomQueryBuild> customQueryBuildList) {
        return dbCommandExecutor.executeCommand(new DBCommand<Integer>() {
            public Integer execute(final EntityManager em) {
                //TODO : going with jpql/native as CriteriaDelete is only supported in jpa 2.1. JBoss EAP 6.x support jpa 2.0
                final Query query = buildDeleteQuery(em, customQueryBuildList);
                final int noOfRecords = query.executeUpdate();
                logger.info("no of records deleted "+ noOfRecords);
                return noOfRecords;
            }
        });
    }

    protected int countAll() {
        return dbCommandExecutor.executeCommand(new DBCommand<Integer>() {

            public Integer execute(final EntityManager em) {
                return ((Long) em.createQuery("Select Count(e) From " + getPersistentClass().getSimpleName() + " e").getSingleResult()).intValue();
            }
        });
    }

    protected List<T> findList(final String columnName, final String pattern) {
        return dbCommandExecutor.executeCommand(new DBCommand<List<T>>() {

            @SuppressWarnings("unchecked")
            public List<T> execute(final EntityManager em) {
                return em.createQuery(SELECT_FROM_CLAUSE + getPersistentClass().getSimpleName() + WHERE_CLAUSE +columnName+ " LIKE "+ pattern).getResultList();
            }
        });
    }

    protected Query buildDeleteQuery(final EntityManager em, final List<CustomQueryBuild> customQueryBuildList) {
        final StringBuffer queryBuilder = new StringBuffer();
        if (customQueryBuildList != null && !customQueryBuildList.isEmpty()) {
            queryBuilder.append(" e where");
            for (final CustomQueryBuild criteria : customQueryBuildList) {
                queryBuilder.append(" e.")
                        .append(criteria.getExpression())
                        .append(" AND");
            }
            queryBuilder.delete(queryBuilder.lastIndexOf(" AND"), queryBuilder.length());
        }
        final Query query = em.createQuery("Delete From " + getPersistentClass().getSimpleName() + queryBuilder);
        final StringBuilder queryValues = new StringBuilder();
        if (customQueryBuildList != null) {
            int ordinalParameter = ORDINAL_PARAMETER;
            for (final CustomQueryBuild criteria : customQueryBuildList) {
                final Object value = criteria.getValues().get(0);
                query.setParameter(Character.toString((char)ordinalParameter++), value);
                queryValues.append(value).append("#");
            }
        }
        logger.info("Query for delete and values: "+ queryBuilder+" "+ queryValues);
        return query;
    }

    protected Query buildFindQuery(final CustomQueryBuild customQueryBuild) {
        return dbCommandExecutor.executeCommand(new DBCommand<Query>() {
            public Query execute(final EntityManager em) {
                final String whereClause = customQueryBuild.getExpression();
                final Query query = em.createQuery(SELECT_FROM_CLAUSE + getPersistentClass().getSimpleName() + WHERE_CLAUSE + whereClause);
                for (int iterator = 1; iterator <= customQueryBuild.getValues().size(); iterator++) {
                    query.setParameter(iterator, customQueryBuild.getValues().get(iterator - 1));
                }
                query.setFirstResult(customQueryBuild.getOffset());
                query.setMaxResults(customQueryBuild.getLimit());
                return query;
            }
        });
    }

}
