package com.aas.music.util;

import java.util.Collection;

import org.springframework.orm.ObjectRetrievalFailureException;

import com.aas.music.model.BaseEntity;


/**
 * Utility methods for handling entities. Separate from the BaseEntity class 
 * mainly because of dependency on the ORM-associated 
 * ObjectRetrievalFailureException.
 *
 * @author Amadeo Asco
 */
public abstract class EntityUtils {

    /**
     * Looks up the entity of the given class with the given id in the given collection.
     *
     * @param entities    the collection to search.
     * @param entityClass the entity class to look up.
     * @param entityId    the entity id to look up.
     * @param <T>		  the data class.
     * @return the found entity.
     * @throws ObjectRetrievalFailureException if the entity was not found.
     */
    public static <T extends BaseEntity> T getById(final Collection<T> entities, 
    		final Class<T> entityClass, final int entityId)
    		throws ObjectRetrievalFailureException {
        for (final T entity : entities) {
            if (entity.getId() == entityId && entityClass.isInstance(entity)) {
                return entity;
            }
        }

        throw new ObjectRetrievalFailureException(entityClass, entityId);
    }

}
