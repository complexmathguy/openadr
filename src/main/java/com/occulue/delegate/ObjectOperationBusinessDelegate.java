/*******************************************************************************
  Turnstone Biologics Confidential
  
  2018 Turnstone Biologics
  All Rights Reserved.
  
  This file is subject to the terms and conditions defined in
  file 'license.txt', which is part of this source code package.
   
  Contributors :
        Turnstone Biologics - General Release
 ******************************************************************************/
package com.occulue.delegate;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;
import java.util.concurrent.*;

import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.queryhandling.QueryUpdateEmitter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.occulue.api.*;
import com.occulue.entity.*;
import com.occulue.exception.*;
import com.occulue.validator.*;

/**
 * ObjectOperation business delegate class.
 * <p>
 * This class implements the Business Delegate design pattern for the purpose of:
 * <ol>
 * <li>Reducing coupling between the business tier and a client of the business tier by hiding all business-tier implementation details</li>
 * <li>Improving the available of ObjectOperation related services in the case of a ObjectOperation business related service failing.</li>
 * <li>Exposes a simpler, uniform ObjectOperation interface to the business tier, making it easy for clients to consume a simple Java object.</li>
 * <li>Hides the communication protocol that may be required to fulfill ObjectOperation business related services.</li>
 * </ol>
 * <p>
 * @author your_name_here
 */
public class ObjectOperationBusinessDelegate 
extends BaseBusinessDelegate {
//************************************************************************
// Public Methods
//************************************************************************
    /** 
     * Default Constructor 
     */
    public ObjectOperationBusinessDelegate()  {
    	queryGateway 		= applicationContext.getBean(QueryGateway.class);
    	commandGateway 		= applicationContext.getBean(CommandGateway.class);
    	queryUpdateEmitter  = applicationContext.getBean(QueryUpdateEmitter.class);
	}


   /**
	* ObjectOperation Business Delegate Factory Method
	*
	* All methods are expected to be self-sufficient.
	*
	* @return 	ObjectOperationBusinessDelegate
	*/
	public static ObjectOperationBusinessDelegate getObjectOperationInstance() {
		return( new ObjectOperationBusinessDelegate() );
	}
 
   /**
    * Creates the provided command.
    * 
    * @param		command ${class.getCreateCommandAlias()}
    * @exception    ProcessingException
    * @exception	IllegalArgumentException
    * @return		CompletableFuture<UUID>
    */
	public CompletableFuture<UUID> createObjectOperation( CreateObjectOperationCommand command )
    throws ProcessingException, IllegalArgumentException {

		CompletableFuture<UUID> completableFuture = null;
				
		try {
			// --------------------------------------
        	// assign identity now if none
        	// -------------------------------------- 
			if ( command.getObjectOperationId() == null )
				command.setObjectOperationId( UUID.randomUUID() );
				
			// --------------------------------------
        	// validate the command
        	// --------------------------------------    	
        	ObjectOperationValidator.getInstance().validate( command );    

    		// ---------------------------------------
    		// issue the CreateObjectOperationCommand - by convention the future return value for a create command
        	// that is handled by the constructor of an aggregate will return the UUID 
    		// ---------------------------------------
        	completableFuture = commandGateway.send( command );
        	
			LOGGER.log( Level.INFO, "return from Command Gateway for CreateObjectOperationCommand of ObjectOperation is " + command );
			
        }
        catch (Exception exc) {
            final String errMsg = "Unable to create ObjectOperation - " + exc;
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        finally {
        }        
        
        return completableFuture;
    }

   /**
    * Update the provided command.
    * @param		command UpdateObjectOperationCommand
    * @return		CompletableFuture<Void>
    * @exception    ProcessingException
    * @exception  	IllegalArgumentException
    */
    public CompletableFuture<Void> updateObjectOperation( UpdateObjectOperationCommand command ) 
    throws ProcessingException, IllegalArgumentException {
    	CompletableFuture<Void> completableFuture = null;
    	
    	try {       

			// --------------------------------------
        	// validate 
        	// --------------------------------------    	
        	ObjectOperationValidator.getInstance().validate( command );    

        	// --------------------------------------
        	// issue the UpdateObjectOperationCommand and return right away
        	// --------------------------------------    	
        	completableFuture = commandGateway.send( command );
    	}
        catch (Exception exc) {
            final String errMsg = "Unable to save ObjectOperation - " + exc;
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        
    	return completableFuture;
    }
   
   /**
    * Deletes the associatied value object
    * @param		command DeleteObjectOperationCommand
    * @return		CompletableFuture<Void>
    * @exception 	ProcessingException
    */
    public CompletableFuture<Void> delete( DeleteObjectOperationCommand command ) 
    throws ProcessingException, IllegalArgumentException {	
    	
    	CompletableFuture<Void> completableFuture = null;
    	
        try {  
			// --------------------------------------
        	// validate the command
        	// --------------------------------------    	
        	ObjectOperationValidator.getInstance().validate( command );    
        	
        	// --------------------------------------
        	// issue the DeleteObjectOperationCommand and return right away
        	// --------------------------------------    	
        	completableFuture = commandGateway.send( command );
        }
        catch (Exception exc) {
            final String errMsg = "Unable to delete ObjectOperation using Id = "  + command.getObjectOperationId();
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        finally {
        }
        
        return completableFuture;
    }

    /**
     * Method to retrieve the ObjectOperation via ObjectOperationFetchOneSummary
     * @param 	summary ObjectOperationFetchOneSummary 
     * @return 	ObjectOperationFetchOneResponse
     * @exception ProcessingException - Thrown if processing any related problems
     * @exception IllegalArgumentException 
     */
    public ObjectOperation getObjectOperation( ObjectOperationFetchOneSummary summary ) 
    throws ProcessingException, IllegalArgumentException {
    	
    	if( summary == null )
    		throw new IllegalArgumentException( "ObjectOperationFetchOneSummary arg cannot be null" );
    	
    	ObjectOperation entity = null;
    	
        try {
        	// --------------------------------------
        	// validate the fetch one summary
        	// --------------------------------------    	
        	ObjectOperationValidator.getInstance().validate( summary );    
        	
        	// --------------------------------------
        	// use queryGateway to send request to Find a ObjectOperation
        	// --------------------------------------
        	CompletableFuture<ObjectOperation> futureEntity = queryGateway.query(new FindObjectOperationQuery( new LoadObjectOperationFilter( summary.getObjectOperationId() ) ), ResponseTypes.instanceOf(ObjectOperation.class));
        	
        	entity = futureEntity.get();
        }
        catch( Exception exc ) {
            final String errMsg = "Unable to locate ObjectOperation with id " + summary.getObjectOperationId();
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        finally {
        }        
        
        return entity;
    }


    /**
     * Method to retrieve a collection of all ObjectOperations
     *
     * @return 	List<ObjectOperation> 
     * @exception ProcessingException Thrown if any problems
     */
    public List<ObjectOperation> getAllObjectOperation() 
    throws ProcessingException {
        List<ObjectOperation> list = null;

        try {
        	CompletableFuture<List<ObjectOperation>> futureList = queryGateway.query(new FindAllObjectOperationQuery(), ResponseTypes.multipleInstancesOf(ObjectOperation.class));
        	
        	list = futureList.get();
        }
        catch( Exception exc ) {
            String errMsg = "Failed to get all ObjectOperation";
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        finally {
        }        
        
        return list;
    }


    /**
     * add Operations to Operations 
     * @param		command AssignOperationsToObjectOperationCommand
     * @exception	ProcessingException
     */     
	public void addToOperations( AssignOperationsToObjectOperationCommand command ) throws ProcessingException {
		
		
		// -------------------------------------------
		// load the parent
		// -------------------------------------------
		load( command.getObjectOperationId() );

		OperationsBusinessDelegate childDelegate 	= OperationsBusinessDelegate.getOperationsInstance();
		ObjectOperationBusinessDelegate parentDelegate = ObjectOperationBusinessDelegate.getObjectOperationInstance();		
		UUID childId = command.getAddTo().getOperationsId();
		
		try {		
			// --------------------------------------
	    	// validate the command
	    	// --------------------------------------    
	    	ObjectOperationValidator.getInstance().validate( command );    

	    	// --------------------------------------
        	// issue the command
        	// --------------------------------------    	
    		commandGateway.sendAndWait( command );			
		}
		catch( Exception exc ) {
			final String msg = "Failed to add a Operations as Operations to ObjectOperation" ; 
			LOGGER.log( Level.WARNING, msg, exc );
			throw new ProcessingException( msg, exc );
		}

	}

    /**
     * remove Operations from Operations
     * @param		command RemoveOperationsFromObjectOperationCommand
     * @exception	ProcessingException
     */     	
	public void removeFromOperations( RemoveOperationsFromObjectOperationCommand command ) throws ProcessingException {		
		
		OperationsBusinessDelegate childDelegate 	= OperationsBusinessDelegate.getOperationsInstance();
		UUID childId = command.getRemoveFrom().getOperationsId();

		try {
			
			// --------------------------------------
	    	// validate the command
	    	// --------------------------------------    
	    	ObjectOperationValidator.getInstance().validate( command );    

	    	// --------------------------------------
	    	// issue the command
	    	// --------------------------------------    	
			commandGateway.sendAndWait( command );

		}
		catch( Exception exc ) {
			final String msg = "Failed to remove child using Id " + childId; 
			LOGGER.log( Level.WARNING, msg, exc );
			throw new ProcessingException( msg, exc );
		}
	}



	/**
	 * Internal helper method to load the root 
	 * 
	 * @param		id	UUID
	 * @return		ObjectOperation
	 */
	protected ObjectOperation load( UUID id ) throws ProcessingException {
		objectOperation = ObjectOperationBusinessDelegate.getObjectOperationInstance().getObjectOperation( new ObjectOperationFetchOneSummary(id) );	
		return objectOperation;
	}


//************************************************************************
// Attributes
//************************************************************************
	private final QueryGateway queryGateway;
	private final CommandGateway commandGateway;
	private final QueryUpdateEmitter queryUpdateEmitter;
	private ObjectOperation objectOperation 	= null;
    private static final Logger LOGGER 			= Logger.getLogger(ObjectOperationBusinessDelegate.class.getName());
    
}
