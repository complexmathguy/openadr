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
 * Aggregate handler for PayloadDescriptor as outlined for the CQRS pattern, all write responsibilities 
 * related to PayloadDescriptor are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class PayloadDescriptorAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public PayloadDescriptorAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public PayloadDescriptorAggregate(CreatePayloadDescriptorCommand command) throws Exception {
    	LOGGER.info( "Handling command CreatePayloadDescriptorCommand" );
    	CreatePayloadDescriptorEvent event = new CreatePayloadDescriptorEvent(command.getPayloadDescriptorId(), command.getPayloadType(), command.getObjectType());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdatePayloadDescriptorCommand command) throws Exception {
    	LOGGER.info( "handling command UpdatePayloadDescriptorCommand" );
    	UpdatePayloadDescriptorEvent event = new UpdatePayloadDescriptorEvent(command.getPayloadDescriptorId(), command.getPayloadType(), command.getObjectType());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeletePayloadDescriptorCommand command) throws Exception {
    	LOGGER.info( "Handling command DeletePayloadDescriptorCommand" );
        apply(new DeletePayloadDescriptorEvent(command.getPayloadDescriptorId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands

    // multiple association commands

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreatePayloadDescriptorEvent event) {	
    	LOGGER.info( "Event sourcing CreatePayloadDescriptorEvent" );
    	this.payloadDescriptorId = event.getPayloadDescriptorId();
        this.payloadType = event.getPayloadType();
        this.objectType = event.getObjectType();
    }
    
    @EventSourcingHandler
    void on(UpdatePayloadDescriptorEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.payloadType = event.getPayloadType();
        this.objectType = event.getObjectType();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------


    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID payloadDescriptorId;
    
    private String payloadType;
    private ObjectType objectType;

    private static final Logger LOGGER 	= Logger.getLogger(PayloadDescriptorAggregate.class.getName());
}
