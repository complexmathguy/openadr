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
 * Aggregate handler for ReportPayloadDescriptor as outlined for the CQRS pattern, all write responsibilities 
 * related to ReportPayloadDescriptor are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class ReportPayloadDescriptorAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public ReportPayloadDescriptorAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public ReportPayloadDescriptorAggregate(CreateReportPayloadDescriptorCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateReportPayloadDescriptorCommand" );
    	CreateReportPayloadDescriptorEvent event = new CreateReportPayloadDescriptorEvent(command.getReportPayloadDescriptorId(), command.getReadingType(), command.getUnits(), command.getAccuracy(), command.getConfidence());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdateReportPayloadDescriptorCommand command) throws Exception {
    	LOGGER.info( "handling command UpdateReportPayloadDescriptorCommand" );
    	UpdateReportPayloadDescriptorEvent event = new UpdateReportPayloadDescriptorEvent(command.getReportPayloadDescriptorId(), command.getPayloadType(), command.getObjectType(), command.getReadingType(), command.getUnits(), command.getAccuracy(), command.getConfidence());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeleteReportPayloadDescriptorCommand command) throws Exception {
    	LOGGER.info( "Handling command DeleteReportPayloadDescriptorCommand" );
        apply(new DeleteReportPayloadDescriptorEvent(command.getReportPayloadDescriptorId()));
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
    void on(CreateReportPayloadDescriptorEvent event) {	
    	LOGGER.info( "Event sourcing CreateReportPayloadDescriptorEvent" );
    	this.reportPayloadDescriptorId = event.getReportPayloadDescriptorId();
        this.readingType = event.getReadingType();
        this.units = event.getUnits();
        this.accuracy = event.getAccuracy();
        this.confidence = event.getConfidence();
    }
    
    @EventSourcingHandler
    void on(UpdateReportPayloadDescriptorEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.payloadType = event.getPayloadType();
        this.objectType = event.getObjectType();
        this.readingType = event.getReadingType();
        this.units = event.getUnits();
        this.accuracy = event.getAccuracy();
        this.confidence = event.getConfidence();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------


    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID reportPayloadDescriptorId;
    
    private String readingType;
    private String units;
    private Float accuracy;
    private Integer confidence;

    private static final Logger LOGGER 	= Logger.getLogger(ReportPayloadDescriptorAggregate.class.getName());
}
