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
 * Projector for Program as outlined for the CQRS pattern.
 * 
 * Commands are handled by ProgramAggregate
 * 
 * @author your_name_here
 *
 */
@Component("program-entity-projector")
public class ProgramEntityProjector {
		
	// core constructor
	public ProgramEntityProjector(ProgramRepository repository ) {
        this.repository = repository;
    }	

	/*
	 * Insert a Program
	 * 
     * @param	entity Program
     */
    public Program create( Program entity) {
	    LOGGER.info("creating " + entity.toString() );
	   
    	// ------------------------------------------
    	// persist a new one
    	// ------------------------------------------ 
	    return repository.save(entity);
        
    }

	/*
	 * Update a Program
	 * 
     * @param	entity Program
     */
    public Program update( Program entity) {
	    LOGGER.info("updating " + entity.toString() );

	    // ------------------------------------------
    	// save with an existing instance
    	// ------------------------------------------ 
		return repository.save(entity);

    }
    
	/*
	 * Delete a Program
	 * 
     * @param	id		UUID
     */
    public Program delete( UUID id ) {
	    LOGGER.info("deleting " + id.toString() );

    	// ------------------------------------------
    	// locate the entity by the provided id
    	// ------------------------------------------
	    Program entity = repository.findById(id).get();
	    
    	// ------------------------------------------
    	// delete what is discovered 
    	// ------------------------------------------
    	repository.delete( entity );
    	
    	return entity;
    }    


    /*
     * Add to the PayloadDescriptors
     * 
     * @param	parentID	UUID
     * @param	addTo		childType
     * @return	Program
     */
    public Program addToPayloadDescriptors( UUID parentId, PayloadDescriptor addTo ) {
	    LOGGER.info("handling AssignPayloadDescriptorsToProgramEvent - " );
	    
	    Program parentEntity = repository.findById(parentId).get();
	    PayloadDescriptor child = PayloadDescriptorProjector.find(addTo.getPayloadDescriptorId());
	    
	    parentEntity.getPayloadDescriptors().add( child );

	    // ------------------------------------------
    	// save 
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

    /*
     * Remove from the PayloadDescriptors
     * 
     * @param	parentID	UUID
     * @param	removeFrom	childType
     * @return	Program
     */
	public Program removeFromPayloadDescriptors( UUID parentId, PayloadDescriptor removeFrom ) {
	    LOGGER.info("handling RemovePayloadDescriptorsFromProgramEvent " );
	
	    Program parentEntity = repository.findById(parentId).get();
	    PayloadDescriptor child = PayloadDescriptorProjector.find(removeFrom.getPayloadDescriptorId());
	    
	    parentEntity.getPayloadDescriptors().remove( child );
	
	    // ------------------------------------------
		// save
		// ------------------------------------------ 
	    update(parentEntity);
	    
	    return parentEntity;
	}

    /*
     * Add to the Targets
     * 
     * @param	parentID	UUID
     * @param	addTo		childType
     * @return	Program
     */
    public Program addToTargets( UUID parentId, ValueMap addTo ) {
	    LOGGER.info("handling AssignTargetsToProgramEvent - " );
	    
	    Program parentEntity = repository.findById(parentId).get();
	    ValueMap child = ValueMapProjector.find(addTo.getValueMapId());
	    
	    parentEntity.getTargets().add( child );

	    // ------------------------------------------
    	// save 
    	// ------------------------------------------ 
	    repository.save(parentEntity);
        
	    return parentEntity;
    }
    

    /*
     * Remove from the Targets
     * 
     * @param	parentID	UUID
     * @param	removeFrom	childType
     * @return	Program
     */
	public Program removeFromTargets( UUID parentId, ValueMap removeFrom ) {
	    LOGGER.info("handling RemoveTargetsFromProgramEvent " );
	
	    Program parentEntity = repository.findById(parentId).get();
	    ValueMap child = ValueMapProjector.find(removeFrom.getValueMapId());
	    
	    parentEntity.getTargets().remove( child );
	
	    // ------------------------------------------
		// save
		// ------------------------------------------ 
	    update(parentEntity);
	    
	    return parentEntity;
	}



    /**
     * Method to retrieve the Program via an FindProgramQuery
     * @return 	query	FindProgramQuery
     */
    @SuppressWarnings("unused")
    public Program find( UUID id ) {
    	LOGGER.info("handling find using " + id.toString() );
    	try {
    		return repository.findById(id).get();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find a Program - {0}", exc.getMessage() );
    	}
    	return null;
    }
    
    /**
     * Method to retrieve a collection of all Programs
     *
     * @param	query	FindAllProgramQuery 
     * @return 	List<Program> 
     */
    @SuppressWarnings("unused")
    public List<Program> findAll( FindAllProgramQuery query ) {
    	LOGGER.info("handling findAll using " + query.toString() );
    	try {
    		return repository.findAll();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find all Program - {0}", exc.getMessage() );
    	}
    	return null;
    }

    //--------------------------------------------------
    // attributes
    // --------------------------------------------------
	@Autowired
    protected final ProgramRepository repository;
    @Autowired
	@Qualifier(value = "payloadDescriptor-entity-projector")
	PayloadDescriptorEntityProjector PayloadDescriptorProjector;
    @Autowired
	@Qualifier(value = "valueMap-entity-projector")
	ValueMapEntityProjector ValueMapProjector;

    private static final Logger LOGGER 	= Logger.getLogger(ProgramEntityProjector.class.getName());

}
