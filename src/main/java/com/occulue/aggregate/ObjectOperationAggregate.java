package com.occulue.aggregate;

import com.occulue.api.*;
import com.occulue.entity.*;
import com.occulue.exception.*;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.modelling.command.AggregateMember;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import static org.axonframework.modelling.command.AggregateLifecycle.apply;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.context.annotation.Profile;

/**
 * Aggregate handler for ObjectOperation as outlined for the CQRS pattern, all write responsibilities 
 * related to ObjectOperation are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class ObjectOperationAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public ObjectOperationAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public ObjectOperationAggregate(CreateObjectOperationCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateObjectOperationCommand" );
    	CreateObjectOperationEvent event = new CreateObjectOperationEvent(command.getObjectOperationId(), command.getCallbackUrl(), command.getBearerToken(), command.getObjects());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdateObjectOperationCommand command) throws Exception {
    	LOGGER.info( "handling command UpdateObjectOperationCommand" );
    	UpdateObjectOperationEvent event = new UpdateObjectOperationEvent(command.getObjectOperationId(), command.getCallbackUrl(), command.getBearerToken(), command.getObjects(), command.getOperations());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeleteObjectOperationCommand command) throws Exception {
    	LOGGER.info( "Handling command DeleteObjectOperationCommand" );
        apply(new DeleteObjectOperationEvent(command.getObjectOperationId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands

    // multiple association commands
    @CommandHandler
    public void handle(AssignOperationsToObjectOperationCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignOperationsToObjectOperationCommand" );
    	
    	if ( operations.contains( command.getAddTo() ) )
    		throw new ProcessingException( "Operations already contains an entity with id " + command.getAddTo().getOperationsId() );

    	apply(new AssignOperationsToObjectOperationEvent(command.getObjectOperationId(), command.getAddTo()));
    }

    @CommandHandler
    public void handle(RemoveOperationsFromObjectOperationCommand command) throws Exception {
    	LOGGER.info( "Handling command RemoveOperationsFromObjectOperationCommand" );
    	
    	if ( !operations.contains( command.getRemoveFrom() ) )
    		throw new ProcessingException( "Operations does not contain an entity with id " + command.getRemoveFrom().getOperationsId() );

        apply(new RemoveOperationsFromObjectOperationEvent(command.getObjectOperationId(), command.getRemoveFrom()));
    }

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreateObjectOperationEvent event) {	
    	LOGGER.info( "Event sourcing CreateObjectOperationEvent" );
    	this.objectOperationId = event.getObjectOperationId();
        this.callbackUrl = event.getCallbackUrl();
        this.bearerToken = event.getBearerToken();
        this.objects = event.getObjects();
    }
    
    @EventSourcingHandler
    void on(UpdateObjectOperationEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.callbackUrl = event.getCallbackUrl();
        this.bearerToken = event.getBearerToken();
        this.objects = event.getObjects();
        this.operations = event.getOperations();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------

	// multiple associations
    @EventSourcingHandler
    void on(AssignOperationsToObjectOperationEvent event ) {
    	LOGGER.info( "Event sourcing AssignOperationsToObjectOperationEvent" );
    	this.operations.add( event.getAddTo() );
    }

	@EventSourcingHandler
	void on(RemoveOperationsFromObjectOperationEvent event ) {	
		LOGGER.info( "Event sourcing RemoveOperationsFromObjectOperationEvent" );
		this.operations.remove( event.getRemoveFrom() );
	}
	

    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID objectOperationId;
    
    private String callbackUrl;
    private String bearerToken;
    private ObjectType objects;
    private Set<Operations> operations = new HashSet<>();

    private static final Logger LOGGER 	= Logger.getLogger(ObjectOperationAggregate.class.getName());
}
