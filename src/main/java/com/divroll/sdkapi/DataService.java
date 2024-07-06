/*
 * Copyright (C) 2024 Divroll
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.divroll.sdkapi;

import com.divroll.core.EntityNotExistsException;
import com.divroll.core.EntityStoreException;
import com.divroll.core.InvalidIdException;
import com.divroll.core.InvalidRelationException;
import com.divroll.core.RelationNotExistsException;
import com.divroll.core.RelationTypeDoesNotExistsException;

import java.util.List;
import java.util.Set;

public interface DataService {

    /**
     * Creates an entity.
     * @param toCreate the entity to create
     * @return the id of the created entity
     * @throws EntityStoreException if there is an error creating the entity
     */
    EntityId createEntity(Entity toCreate) 
        throws EntityStoreException;

    /**
     * Create an entity with a relationship to another existing entity.
     * @param toCreate the entity to create
     * @param relation the relationship to create between the entities
     * @param relatedId the id of the related entity
     * @return the id of the created entity
     * @throws EntityNotExistsException if the related entity does not exist
     * @throws InvalidRelationException if the relationship is invalid, e.g. the relationship name is reserved for internal use
     * @throws EntityStoreException if there is an error creating the entity
     */
    EntityId createEntity(Entity toCreate, Relationship relation, EntityId relatedId)
        throws EntityNotExistsException, InvalidRelationException, EntityStoreException;    
    
    /**
     * Create an entity with a relationship to another entity.
     * @param toCreate the entity to create
     * @param relation the relationship to create between the entities
     * @param relatedEntity the related entity to create
     * @return the id of the created entity
     * @throws EntityStoreException if there is an error creating the entity
     */        
    EntityId createRelatedEntities(Entity toCreate, Relationship relation, Entity relatedEntity)
        throws EntityStoreException; 

    /**
     * Create an entity with a relationship to other entities.
     * @param toCreate the entity to create
     * @param relation the relationship to create between the entities
     * @param relatedEntities the list of related entities to create
     * @return the id of the created entity
     * @throws EntityStoreException if there is an error creating the entity
     */        
    EntityId createRelatedEntities(Entity toCreate, Relationship relation, List<Entity> relatedEntities) 
        throws EntityStoreException;
     
    /**
     * Add a relationship between the entity and another existing entity.
     * @param toUpdate the entity to add relation into
     * @param relation the relationship to create between the entities 
     * @param relatedId the id of related entity
     * @return the updated entity
     * @throws EntityNotExistsException if the related entity does not exist
     * @throws EntityStoreException if there is an error creating the entity
     */    
    Entity addRelation(EntityId toUpdate, Relationship relation, EntityId relatedId) 
        throws EntityNotExistsException, EntityStoreException;

    /**
     * Add a relationship between the entity and other existing entities.
     * @param toUpdate the entity to add relation into
     * @param relation the relationship to create between the entities 
     * @param relatedIds the list of related entity ids
     * @return the updated entity
     * @throws EntityNotExistsException if any of the related entities not exist
     * @throws EntityStoreException if there is an error creating the entity
     */     
    Entity addRelation(EntityId toUpdate, Relationship relation, List<EntityId> relatedIds) 
        throws EntityNotExistsException, EntityStoreException;
  
    /**
     * Reads an entity.
     * @param toRead the id of the entity to read
     * @return the entity
     * @throws EntityNotExistsException if the entity does not exist
     * @throws EntityStoreException if there is an error reading the entity
     */        
    Entity readEntity(EntityId toRead)
        throws EntityNotExistsException, EntityStoreException;    

    /**
     * Reads the first entity with a property equal to value.
     * @param type the type of entity to read
     * @param property the property to match
     * @param value the value to match
     * @return the entity with the property equal to value
     * @throws EntityNotExistsException if the entity does not exist
     * @throws EntityStoreException if there is an error reading the entity
     */ 
    Entity readEntity(String type, String property, EntityProperty value)
        throws EntityNotExistsException, EntityStoreException;

    /**
     * Reads a list of entities.
     * @param toRead the list of ids of the entities to read
     * @return the list of entities
     * @throws EntityNotExistsException if any of the entities does not exist
     * @throws EntityStoreException if there is an error reading the entities
     */        
    List<Entity> readEntities(List<EntityId> toRead)
        throws EntityNotExistsException, EntityStoreException;  
    
    /**
     * Reads a list of entities with relation to another existing entity.
     * @param type the type of entity to read
     * @param relation the relation of the entity to the related entity
     * @param relatedEntity the related entity
     * @return the list of entities with relation to the related entity
     * @throws EntityNotExistsException if the related entity does not exists
     * @throws EntityStoreException if there is an error reading the entities
     */
    List<Entity> readEntities(String type, Relationship relation, Entity relatedEntity)
        throws EntityNotExistsException, EntityStoreException;

    /**
     * Reads a list of entities with relation to another existing entity.
     * @param type the type of entity to read
     * @param relation the relation of the entity to the related entity
     * @param relatedId the id of the related entity
     * @return the list of entities with relation to the related entity
     * @throws EntityNotExistsException if the related entity does not exists
     * @throws EntityStoreException if there is an error reading the entities
     */
    List<Entity> readEntities(String type, Relationship relation, EntityId relatedId)
        throws EntityNotExistsException, EntityStoreException;    

    /**
     * Reads related entities.
     * @param toRead the id of the entity to read related entities from
     * @param relation the relationship to read
     * @return the list of related entities
     * @throws EntityNotExistsException if the entity does not exist
     * @throws EntityStoreException if there is an error reading the related entities
     */        
    List<Entity> readRelatedEntities(EntityId toRead, Relationship relation)
        throws EntityNotExistsException, EntityStoreException;

    /**
     * Reads related entities with a condition.
     * @param toRead the id of the entity to read related entities from
     * @param relation the relationship to read
     * @param condition the condition to filter the related entities
     * @return the list of related entities
     * @throws EntityNotExistsException if the entity does not exist
     * @throws EntityStoreException if there is an error reading the related entities
     */        
    List<Entity> readRelatedEntities(EntityId toRead, Relationship relation, Condition condition)
        throws EntityNotExistsException, EntityStoreException;

    /**
     * Reads entities with a condition.
     * @param condition the condition to filter the entities
     * @param max the maximum number of entities to return
     * @param skip the number of entities to skip
     * @return the list of entities
     * @throws EntityStoreException if there is an error reading the entities
     */        
    List<Entity> readEntities(String type, Condition condition, Integer max, Long skip)
        throws EntityStoreException;


    /**
     * Reads entities with a relationship to the entity type.
     * @param relation the relationship of entities to the type
     * @param relatedType the type of the entity that the entities have relationship to
     * @return the list of entities having relationship to the entity type
     * @throws RelationNotExistsException if the relationship does not exist
     * @throws EntityStoreException if there is an error reading the entities
     */    
    List<Entity> readEntities(Relationship relation, String relatedType)
        throws RelationNotExistsException, EntityStoreException;

    /**
     * Reads entities of a specific type.
     * @param type the type of the entities to read
     * @param max the maximum number of entities to return
     * @param skip the number of entities to skip
     * @return the list of entities
     * @throws EntityStoreException if there is an error reading the entities
     */        
    List<Entity> readEntities(String type, Integer max, Long skip)
        throws EntityStoreException;    

    /**
     * Updates an entity.
     * @param toUpdate the id of the entity to update
     * @param updates the list of updates to apply
     * @return the updated entity
     * @throws EntityNotExistsException if the entity does not exist
     * @throws EntityStoreException if there is an error updating the entity
     */        
    Entity updateEntity(EntityId toUpdate, List<Update> updates) 
        throws EntityNotExistsException, EntityStoreException;

    /**
     * Updates the list of entities with the list of updates.
     * @throws InvalidIdException if the any of the entity id is not in valid format
     * @throws EntityNotExistsException if any of the the entities does not exist
     * @throws EntityStoreException if there is an error updating the entities
     */
    List<Entity> updateEntities(List<EntityId> toUpdate, List<Update> updates) 
        throws InvalidIdException, EntityNotExistsException, EntityStoreException;     

    /**
     * Updates all entities matching type with the updates.
     * @param type the type of the entities to update
     * @param updates the updates to apply to the entities
     * @throws EntityStoreException if there is an error updating the entities
     */
    List<Entity> updateEntities(String type, List<Update> updates)
        throws EntityStoreException;

    /**
     * Delete an entity by id.
     * @param toDelete the id of the entity to delete
     * @return true if the entity is deleted, false otherwise
     * @throws EntityStoreException if there is an error deleting the entity
     */
    boolean deleteEntity(EntityId toDelete)
        throws EntityStoreException;

    /**
     * Delete the list of entities.
     * @param toDelete the list of entities to delete
     * @return true if the entities are deleted, false otherwise
     * @throws EntityNotExistsException if any of the entities does not exist
     * @throws EntityStoreException if there is an error deleting the entities 
     */    
    boolean deleteEntities(List<EntityId> toDelete)
        throws EntityNotExistsException, EntityStoreException;
        
    /** 
     * Delete from the list of entities that match the condition.
     * @param toDelete the list of entities to delete
     * @param condition the condition to match the entities to delete
     * @return the number of entities deleted
     * @throws EntityStoreException if there is an error deleting the entities  
     */    
    long deleteEntities(List<EntityId> toDelete, Condition condition) 
        throws EntityStoreException;     

    /**
     * Deletes all entities matching the condition.
     * @param condition the condition to match the entities to delete
     * @return the number of entities deleted
     * @throws EntityStoreException if there is an error deleting the entities
     */    
    long deleteEntities(Condition condition)
        throws EntityStoreException;

    /**
     * Removes related entities in a relationhip. May also delete related entities.
     * @param toDeleteFrom the id of the entity to remove the related entities from
     * @param relation the relationship to remove the related entities from
     * @param relatedIds the ids of the related entities to remove
     * @param cascadeDelete if true, the related entities will be deleted
     * @throws InvalidIdException if the entity id is not in valid format
     * @throws EntityNotExistsException if the entity does not exist
     * @throws RelationNotExistsException if the relationship does not exist
     * @throws RelationTypeDoesNotExistsException if the relationship type for the relation does not exist
     * @throws EntityStoreException if there is an error removing the related entities
     */
    void removeRelatedEntities(EntityId toDeleteFrom, Relationship relation, List<EntityId> relatedIds, boolean cascadeDelete)
        throws InvalidIdException, EntityNotExistsException, RelationNotExistsException, RelationTypeDoesNotExistsException, EntityStoreException;

    /**
     * Count entities that match the entity type.
     * @param type the type of the entities to count
     * @return the number of entities that match the type
     * @throws EntityStoreException if there is an error counting the entities
     */    
    long countEntities(String type) 
        throws EntityStoreException;
    
    /**
     * Get the list of existing entity types.
     * @return the set of entity types
     * @throws EntityStoreException if there is an error getting the entity types
     */         
    Set<String> getEntityTypes() 
        throws EntityStoreException;
}
