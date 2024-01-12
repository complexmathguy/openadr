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
 * Projector for ReportDescriptor as outlined for the CQRS pattern.
 * 
 * Commands are handled by ReportDescriptorAggregate
 * 
 * @author your_name_here
 *
 */
@Component("reportDescriptor-entity-projector")
public class ReportDescriptorEntityProjector {
		
	// core constructor
	public ReportDescriptorEntityProjector(ReportDescriptorRepository repository ) {
        this.repository = repository;
    }	

	/*
	 * Insert a ReportDescriptor
	 * 
     * @param	entity ReportDescriptor
     */
    public ReportDescriptor create( ReportDescriptor entity) {
	    LOGGER.info("creating " + entity.toString() );
	   
    	// ------------------------------------------
    	// persist a new one
    	// ------------------------------------------ 
	    return repository.save(entity);
        
    }

	/*
	 * Update a ReportDescriptor
	 * 
     * @param	entity ReportDescriptor
     */
    public ReportDescriptor update( ReportDescriptor entity) {
	    LOGGER.info("updating " + entity.toString() );

	    // ------------------------------------------
    	// save with an existing instance
    	// ------------------------------------------ 
		return repository.save(entity);

    }
    
	/*
	 * Delete a ReportDescriptor
	 * 
     * @param	id		UUID
     */
    public ReportDescriptor delete( UUID id ) {
	    LOGGER.info("deleting " + id.toString() );

    	// ------------------------------------------
    	// locate the entity by the provided id
    	// ------------------------------------------
	    ReportDescriptor entity = repository.findById(id).get();
	    
    	// ------------------------------------------
    	// delete what is discovered 
    	// ------------------------------------------
    	repository.delete( entity );
    	
    	return entity;
    }    




    /**
     * Method to retrieve the ReportDescriptor via an FindReportDescriptorQuery
     * @return 	query	FindReportDescriptorQuery
     */
    @SuppressWarnings("unused")
    public ReportDescriptor find( UUID id ) {
    	LOGGER.info("handling find using " + id.toString() );
    	try {
    		return repository.findById(id).get();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find a ReportDescriptor - {0}", exc.getMessage() );
    	}
    	return null;
    }
    
    /**
     * Method to retrieve a collection of all ReportDescriptors
     *
     * @param	query	FindAllReportDescriptorQuery 
     * @return 	List<ReportDescriptor> 
     */
    @SuppressWarnings("unused")
    public List<ReportDescriptor> findAll( FindAllReportDescriptorQuery query ) {
    	LOGGER.info("handling findAll using " + query.toString() );
    	try {
    		return repository.findAll();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find all ReportDescriptor - {0}", exc.getMessage() );
    	}
    	return null;
    }

    //--------------------------------------------------
    // attributes
    // --------------------------------------------------
	@Autowired
    protected final ReportDescriptorRepository repository;

    private static final Logger LOGGER 	= Logger.getLogger(ReportDescriptorEntityProjector.class.getName());

}
