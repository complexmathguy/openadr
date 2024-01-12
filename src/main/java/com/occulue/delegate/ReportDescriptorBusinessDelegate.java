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
 * ReportDescriptor business delegate class.
 * <p>
 * This class implements the Business Delegate design pattern for the purpose of:
 * <ol>
 * <li>Reducing coupling between the business tier and a client of the business tier by hiding all business-tier implementation details</li>
 * <li>Improving the available of ReportDescriptor related services in the case of a ReportDescriptor business related service failing.</li>
 * <li>Exposes a simpler, uniform ReportDescriptor interface to the business tier, making it easy for clients to consume a simple Java object.</li>
 * <li>Hides the communication protocol that may be required to fulfill ReportDescriptor business related services.</li>
 * </ol>
 * <p>
 * @author your_name_here
 */
public class ReportDescriptorBusinessDelegate 
extends BaseBusinessDelegate {
//************************************************************************
// Public Methods
//************************************************************************
    /** 
     * Default Constructor 
     */
    public ReportDescriptorBusinessDelegate()  {
    	queryGateway 		= applicationContext.getBean(QueryGateway.class);
    	commandGateway 		= applicationContext.getBean(CommandGateway.class);
    	queryUpdateEmitter  = applicationContext.getBean(QueryUpdateEmitter.class);
	}


   /**
	* ReportDescriptor Business Delegate Factory Method
	*
	* All methods are expected to be self-sufficient.
	*
	* @return 	ReportDescriptorBusinessDelegate
	*/
	public static ReportDescriptorBusinessDelegate getReportDescriptorInstance() {
		return( new ReportDescriptorBusinessDelegate() );
	}
 
   /**
    * Creates the provided command.
    * 
    * @param		command ${class.getCreateCommandAlias()}
    * @exception    ProcessingException
    * @exception	IllegalArgumentException
    * @return		CompletableFuture<UUID>
    */
	public CompletableFuture<UUID> createReportDescriptor( CreateReportDescriptorCommand command )
    throws ProcessingException, IllegalArgumentException {

		CompletableFuture<UUID> completableFuture = null;
				
		try {
			// --------------------------------------
        	// assign identity now if none
        	// -------------------------------------- 
			if ( command.getReportDescriptorId() == null )
				command.setReportDescriptorId( UUID.randomUUID() );
				
			// --------------------------------------
        	// validate the command
        	// --------------------------------------    	
        	ReportDescriptorValidator.getInstance().validate( command );    

    		// ---------------------------------------
    		// issue the CreateReportDescriptorCommand - by convention the future return value for a create command
        	// that is handled by the constructor of an aggregate will return the UUID 
    		// ---------------------------------------
        	completableFuture = commandGateway.send( command );
        	
			LOGGER.log( Level.INFO, "return from Command Gateway for CreateReportDescriptorCommand of ReportDescriptor is " + command );
			
        }
        catch (Exception exc) {
            final String errMsg = "Unable to create ReportDescriptor - " + exc;
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        finally {
        }        
        
        return completableFuture;
    }

   /**
    * Update the provided command.
    * @param		command UpdateReportDescriptorCommand
    * @return		CompletableFuture<Void>
    * @exception    ProcessingException
    * @exception  	IllegalArgumentException
    */
    public CompletableFuture<Void> updateReportDescriptor( UpdateReportDescriptorCommand command ) 
    throws ProcessingException, IllegalArgumentException {
    	CompletableFuture<Void> completableFuture = null;
    	
    	try {       

			// --------------------------------------
        	// validate 
        	// --------------------------------------    	
        	ReportDescriptorValidator.getInstance().validate( command );    

        	// --------------------------------------
        	// issue the UpdateReportDescriptorCommand and return right away
        	// --------------------------------------    	
        	completableFuture = commandGateway.send( command );
    	}
        catch (Exception exc) {
            final String errMsg = "Unable to save ReportDescriptor - " + exc;
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        
    	return completableFuture;
    }
   
   /**
    * Deletes the associatied value object
    * @param		command DeleteReportDescriptorCommand
    * @return		CompletableFuture<Void>
    * @exception 	ProcessingException
    */
    public CompletableFuture<Void> delete( DeleteReportDescriptorCommand command ) 
    throws ProcessingException, IllegalArgumentException {	
    	
    	CompletableFuture<Void> completableFuture = null;
    	
        try {  
			// --------------------------------------
        	// validate the command
        	// --------------------------------------    	
        	ReportDescriptorValidator.getInstance().validate( command );    
        	
        	// --------------------------------------
        	// issue the DeleteReportDescriptorCommand and return right away
        	// --------------------------------------    	
        	completableFuture = commandGateway.send( command );
        }
        catch (Exception exc) {
            final String errMsg = "Unable to delete ReportDescriptor using Id = "  + command.getReportDescriptorId();
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        finally {
        }
        
        return completableFuture;
    }

    /**
     * Method to retrieve the ReportDescriptor via ReportDescriptorFetchOneSummary
     * @param 	summary ReportDescriptorFetchOneSummary 
     * @return 	ReportDescriptorFetchOneResponse
     * @exception ProcessingException - Thrown if processing any related problems
     * @exception IllegalArgumentException 
     */
    public ReportDescriptor getReportDescriptor( ReportDescriptorFetchOneSummary summary ) 
    throws ProcessingException, IllegalArgumentException {
    	
    	if( summary == null )
    		throw new IllegalArgumentException( "ReportDescriptorFetchOneSummary arg cannot be null" );
    	
    	ReportDescriptor entity = null;
    	
        try {
        	// --------------------------------------
        	// validate the fetch one summary
        	// --------------------------------------    	
        	ReportDescriptorValidator.getInstance().validate( summary );    
        	
        	// --------------------------------------
        	// use queryGateway to send request to Find a ReportDescriptor
        	// --------------------------------------
        	CompletableFuture<ReportDescriptor> futureEntity = queryGateway.query(new FindReportDescriptorQuery( new LoadReportDescriptorFilter( summary.getReportDescriptorId() ) ), ResponseTypes.instanceOf(ReportDescriptor.class));
        	
        	entity = futureEntity.get();
        }
        catch( Exception exc ) {
            final String errMsg = "Unable to locate ReportDescriptor with id " + summary.getReportDescriptorId();
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        finally {
        }        
        
        return entity;
    }


    /**
     * Method to retrieve a collection of all ReportDescriptors
     *
     * @return 	List<ReportDescriptor> 
     * @exception ProcessingException Thrown if any problems
     */
    public List<ReportDescriptor> getAllReportDescriptor() 
    throws ProcessingException {
        List<ReportDescriptor> list = null;

        try {
        	CompletableFuture<List<ReportDescriptor>> futureList = queryGateway.query(new FindAllReportDescriptorQuery(), ResponseTypes.multipleInstancesOf(ReportDescriptor.class));
        	
        	list = futureList.get();
        }
        catch( Exception exc ) {
            String errMsg = "Failed to get all ReportDescriptor";
            LOGGER.log( Level.WARNING, errMsg, exc );
            throw new ProcessingException( errMsg, exc );
        }
        finally {
        }        
        
        return list;
    }




	/**
	 * Internal helper method to load the root 
	 * 
	 * @param		id	UUID
	 * @return		ReportDescriptor
	 */
	protected ReportDescriptor load( UUID id ) throws ProcessingException {
		reportDescriptor = ReportDescriptorBusinessDelegate.getReportDescriptorInstance().getReportDescriptor( new ReportDescriptorFetchOneSummary(id) );	
		return reportDescriptor;
	}


//************************************************************************
// Attributes
//************************************************************************
	private final QueryGateway queryGateway;
	private final CommandGateway commandGateway;
	private final QueryUpdateEmitter queryUpdateEmitter;
	private ReportDescriptor reportDescriptor 	= null;
    private static final Logger LOGGER 			= Logger.getLogger(ReportDescriptorBusinessDelegate.class.getName());
    
}
