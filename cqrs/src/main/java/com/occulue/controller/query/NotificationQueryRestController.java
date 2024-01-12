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
 * Implements Spring Controller query CQRS processing for entity Notification.
 *
 * @author your_name_here
 */
@CrossOrigin
@RestController
@RequestMapping("/Notification")
public class NotificationQueryRestController extends BaseSpringRestController {

	
    /**
     * Handles loading a Notification using a UUID
     * @param		UUID notificationId
     * @return		Notification
     */    
    @GetMapping("/load")
    public Notification load( @RequestParam(required=true) UUID notificationId ) {
    	Notification entity = null;

    	try {  
    		entity = NotificationBusinessDelegate.getNotificationInstance().getNotification( new NotificationFetchOneSummary( notificationId ) );
        }
        catch( Throwable exc ) {
            LOGGER.log( Level.WARNING, "failed to load Notification using Id " + notificationId );
            return null;
        }

        return entity;
    }

    /**
     * Handles loading all Notification business objects
     * @return		Set<Notification>
     */
    @GetMapping("/")
    public List<Notification> loadAll() {                
    	List<Notification> notificationList = null;
        
    	try {
            // load the Notification
            notificationList = NotificationBusinessDelegate.getNotificationInstance().getAllNotification();
            
            if ( notificationList != null )
                LOGGER.log( Level.INFO,  "successfully loaded all Notifications" );
        }
        catch( Throwable exc ) {
            LOGGER.log( Level.WARNING,  "failed to load all Notifications ", exc );
        	return null;
        }

        return notificationList;
                            
    }



//************************************************************************    
// Attributes
//************************************************************************
    protected Notification notification = null;
    private static final Logger LOGGER = Logger.getLogger(NotificationQueryRestController.class.getName());
    
}
