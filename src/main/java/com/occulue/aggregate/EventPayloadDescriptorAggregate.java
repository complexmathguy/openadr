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
 * Aggregate handler for EventPayloadDescriptor as outlined for the CQRS pattern, all write responsibilities 
 * related to EventPayloadDescriptor are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class EventPayloadDescriptorAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public EventPayloadDescriptorAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public EventPayloadDescriptorAggregate(CreateEventPayloadDescriptorCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateEventPayloadDescriptorCommand" );
    	CreateEventPayloadDescriptorEvent event = new CreateEventPayloadDescriptorEvent(command.getEventPayloadDescriptorId(), command.getUnits(), command.getCurrency());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdateEventPayloadDescriptorCommand command) throws Exception {
    	LOGGER.info( "handling command UpdateEventPayloadDescriptorCommand" );
    	UpdateEventPayloadDescriptorEvent event = new UpdateEventPayloadDescriptorEvent(command.getEventPayloadDescriptorId(), command.getPayloadType(), command.getObjectType(), command.getUnits(), command.getCurrency());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeleteEventPayloadDescriptorCommand command) throws Exception {
    	LOGGER.info( "Handling command DeleteEventPayloadDescriptorCommand" );
        apply(new DeleteEventPayloadDescriptorEvent(command.getEventPayloadDescriptorId()));
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
    void on(CreateEventPayloadDescriptorEvent event) {	
    	LOGGER.info( "Event sourcing CreateEventPayloadDescriptorEvent" );
    	this.eventPayloadDescriptorId = event.getEventPayloadDescriptorId();
        this.units = event.getUnits();
        this.currency = event.getCurrency();
    }
    
    @EventSourcingHandler
    void on(UpdateEventPayloadDescriptorEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.payloadType = event.getPayloadType();
        this.objectType = event.getObjectType();
        this.units = event.getUnits();
        this.currency = event.getCurrency();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------


    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID eventPayloadDescriptorId;
    
    private String units;
    private String currency;

    private static final Logger LOGGER 	= Logger.getLogger(EventPayloadDescriptorAggregate.class.getName());
}
