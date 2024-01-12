/*******************************************************************************
  Turnstone Biologics Confidential
  
  2018 Turnstone Biologics
  All Rights Reserved.
  
  This file is subject to the terms and conditions defined in
  file 'license.txt', which is part of this source code package.
   
  Contributors :
        Turnstone Biologics - General Release
 ******************************************************************************/
package com.occulue.controller.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.occulue.api.*;
import com.occulue.delegate.*;
import com.occulue.entity.*;
import com.occulue.exception.*;
import com.occulue.projector.*;

import com.occulue.controller.*;

/** 
 * Implements Spring Controller query CQRS processing for entity Resource.
 *
 * @author your_name_here
 */
@CrossOrigin
@RestController
@RequestMapping("/Resource")
public class ResourceQueryRestController extends BaseSpringRestController {

	
    /**
     * Handles loading a Resource using a UUID
     * @param		UUID resourceId
     * @return		Resource
     */    
    @GetMapping("/load")
    public Resource load( @RequestParam(required=true) UUID resourceId ) {
    	Resource entity = null;

    	try {  
    		entity = ResourceBusinessDelegate.getResourceInstance().getResource( new ResourceFetchOneSummary( resourceId ) );
        }
        catch( Throwable exc ) {
            LOGGER.log( Level.WARNING, "failed to load Resource using Id " + resourceId );
            return null;
        }

        return entity;
    }

    /**
     * Handles loading all Resource business objects
     * @return		Set<Resource>
     */
    @GetMapping("/")
    public List<Resource> loadAll() {                
    	List<Resource> resourceList = null;
        
    	try {
            // load the Resource
            resourceList = ResourceBusinessDelegate.getResourceInstance().getAllResource();
            
            if ( resourceList != null )
                LOGGER.log( Level.INFO,  "successfully loaded all Resources" );
        }
        catch( Throwable exc ) {
            LOGGER.log( Level.WARNING,  "failed to load all Resources ", exc );
        	return null;
        }

        return resourceList;
                            
    }



//************************************************************************    
// Attributes
//************************************************************************
    protected Resource resource = null;
    private static final Logger LOGGER = Logger.getLogger(ResourceQueryRestController.class.getName());
    
}
