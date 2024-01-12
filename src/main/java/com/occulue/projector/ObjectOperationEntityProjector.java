/*******************************************************************************
  Turnstone Biologics Confidential
  
  2018 Turnstone Biologics
  All Rights Reserved.
  
  This file is subject to the terms and conditions defined in
  file 'license.txt', which is part of this source code package.
   
  Contributors :
        Turnstone Biologics - General Release
 ******************************************************************************/
package com.occulue.projector;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


import com.occulue.api.*;
import com.occulue.entity.*;
import com.occulue.exception.*;
import com.occulue.repository.*;

/**
 * Projector for ObjectOperation as outlined for the CQRS pattern.
 * 
 * Commands are handled by ObjectOperationAggregate
 * 
 * @author your_name_here
 *
 */
@Component("objectOperation-entity-projector")
public class ObjectOperationEntityProjector {
		
	// core constructor
	public ObjectOperationEntityProjector(ObjectOperationRepository repository ) {
        this.repository = repository;
    }	

	/*
	 * Insert a ObjectOperation
	 * 
     * @param	entity ObjectOperation
     */
    public ObjectOperation create( ObjectOperation entity) {
	    LOGGER.info("creating " + entity.toString() );
	   
    	// ------------------------------------------
    	// persist a new one
    	// ------------------------------------------ 
	    return repository.save(entity);
        
    }

	/*
	 * Update a ObjectOperation
	 * 
     * @param	entity ObjectOperation
     */
    public ObjectOperation update( ObjectOperation entity) {
	    LOGGER.info("updating " + entity.toString() );

	    // ------------------------------------------
    	// save with an existing instance
    	// ------------------------------------------ 
		return repository.save(entity);

    }
    
	/*
	 * Delete a ObjectOperation
	 * 
     * @param	id		UUID
     */
    public ObjectOperation delete( UUID id ) {
	    LOGGER.info("deleting " + id.toString() );

    	// ------------------------------------------
    	// locate the entity by the provided id
    	// ------------------------------------------
	    ObjectOperation entity = repository.findById(id).get();
	    
    	// ------------------------------------------
    	// delete what is discovered 
    	// ------------------------------------------
    	repository.delete( entity );
    	
    	return entity;
    }    


    /*
     * Add to the Operations
     * 
     * @param	parentID	UUID
     * @param	addTo		childType
     * @return	ObjectOperation
     */
    public ObjectOperation addToOperations( UUID parentId, Operations addTo ) {
	    LOGGER.info("handling AssignOperationsToObjectOperationEvent - " );
	    
	    ObjectOperation parentEntity = repository.findById(parentId).get();
	    Operations child = OperationsProjector.find(addTo.getOperationsId());
	    
	    parentEntity.getOperations().add( child );

	    // ------------------------------------------
    	// save 
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

    /*
     * Remove from the Operations
     * 
     * @param	parentID	UUID
     * @param	removeFrom	childType
     * @return	ObjectOperation
     */
	public ObjectOperation removeFromOperations( UUID parentId, Operations removeFrom ) {
	    LOGGER.info("handling RemoveOperationsFromObjectOperationEvent " );
	
	    ObjectOperation parentEntity = repository.findById(parentId).get();
	    Operations child = OperationsProjector.find(removeFrom.getOperationsId());
	    
	    parentEntity.getOperations().remove( child );
	
	    // ------------------------------------------
		// save
		// ------------------------------------------ 
	    update(parentEntity);
	    
	    return parentEntity;
	}



    /**
     * Method to retrieve the ObjectOperation via an FindObjectOperationQuery
     * @return 	query	FindObjectOperationQuery
     */
    @SuppressWarnings("unused")
    public ObjectOperation find( UUID id ) {
    	LOGGER.info("handling find using " + id.toString() );
    	try {
    		return repository.findById(id).get();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find a ObjectOperation - {0}", exc.getMessage() );
    	}
    	return null;
    }
    
    /**
     * Method to retrieve a collection of all ObjectOperations
     *
     * @param	query	FindAllObjectOperationQuery 
     * @return 	List<ObjectOperation> 
     */
    @SuppressWarnings("unused")
    public List<ObjectOperation> findAll( FindAllObjectOperationQuery query ) {
    	LOGGER.info("handling findAll using " + query.toString() );
    	try {
    		return repository.findAll();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find all ObjectOperation - {0}", exc.getMessage() );
    	}
    	return null;
    }

    //--------------------------------------------------
    // attributes
    // --------------------------------------------------
	@Autowired
    protected final ObjectOperationRepository repository;
    @Autowired
	@Qualifier(value = "operations-entity-projector")
	OperationsEntityProjector OperationsProjector;

    private static final Logger LOGGER 	= Logger.getLogger(ObjectOperationEntityProjector.class.getName());

}
