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
 * Aggregate handler for Event as outlined for the CQRS pattern, all write responsibilities 
 * related to Event are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class EventAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public EventAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public EventAggregate(CreateEventCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateEventCommand" );
    	CreateEventEvent event = new CreateEventEvent(command.getEventId(), command.getCreatedDateTime(), command.getModificationDateTime(), command.getEventName(), command.getPriority(), command.getIntervalPeriod(), command.getIntervals(), command.getObjectType());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdateEventCommand command) throws Exception {
    	LOGGER.info( "handling command UpdateEventCommand" );
    	UpdateEventEvent event = new UpdateEventEvent(command.getEventId(), command.getCreatedDateTime(), command.getModificationDateTime(), command.getEventName(), command.getPriority(), command.getIntervalPeriod(), command.getProgram(), command.getTargets(), command.getReportDescriptors(), command.getPayloadDescriptors(), command.getIntervals(), command.getObjectType());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeleteEventCommand command) throws Exception {
    	LOGGER.info( "Handling command DeleteEventCommand" );
        apply(new DeleteEventEvent(command.getEventId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands
    @CommandHandler
    public void handle(AssignProgramToEventCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignProgramToEventCommand" );
    	
    	if (  program != null && program.getProgramId() == command.getAssignment().getProgramId() )
    		throw new ProcessingException( "Program already assigned with id " + command.getAssignment().getProgramId() );  
    		
        apply(new AssignProgramToEventEvent(command.getEventId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignProgramFromEventCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignProgramFromEventCommand" );

    	if (  program == null )
    		throw new ProcessingException( "Program already has nothing assigned." );  

    	apply(new UnAssignProgramFromEventEvent(command.getEventId()));
    }

    // multiple association commands
    @CommandHandler
    public void handle(AssignReportDescriptorsToEventCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignReportDescriptorsToEventCommand" );
    	
    	if ( reportDescriptors.contains( command.getAddTo() ) )
    		throw new ProcessingException( "ReportDescriptors already contains an entity with id " + command.getAddTo().getReportDescriptorId() );

    	apply(new AssignReportDescriptorsToEventEvent(command.getEventId(), command.getAddTo()));
    }

    @CommandHandler
    public void handle(RemoveReportDescriptorsFromEventCommand command) throws Exception {
    	LOGGER.info( "Handling command RemoveReportDescriptorsFromEventCommand" );
    	
    	if ( !reportDescriptors.contains( command.getRemoveFrom() ) )
    		throw new ProcessingException( "ReportDescriptors does not contain an entity with id " + command.getRemoveFrom().getReportDescriptorId() );

        apply(new RemoveReportDescriptorsFromEventEvent(command.getEventId(), command.getRemoveFrom()));
    }
    @CommandHandler
    public void handle(AssignPayloadDescriptorsToEventCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignPayloadDescriptorsToEventCommand" );
    	
    	if ( payloadDescriptors.contains( command.getAddTo() ) )
    		throw new ProcessingException( "PayloadDescriptors already contains an entity with id " + command.getAddTo().getPayloadDescriptorId() );

    	apply(new AssignPayloadDescriptorsToEventEvent(command.getEventId(), command.getAddTo()));
    }

    @CommandHandler
    public void handle(RemovePayloadDescriptorsFromEventCommand command) throws Exception {
    	LOGGER.info( "Handling command RemovePayloadDescriptorsFromEventCommand" );
    	
    	if ( !payloadDescriptors.contains( command.getRemoveFrom() ) )
    		throw new ProcessingException( "PayloadDescriptors does not contain an entity with id " + command.getRemoveFrom().getPayloadDescriptorId() );

        apply(new RemovePayloadDescriptorsFromEventEvent(command.getEventId(), command.getRemoveFrom()));
    }
    @CommandHandler
    public void handle(AssignIntervalsToEventCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignIntervalsToEventCommand" );
    	
    	if ( intervals.contains( command.getAddTo() ) )
    		throw new ProcessingException( "Intervals already contains an entity with id " + command.getAddTo().getIntervalId() );

    	apply(new AssignIntervalsToEventEvent(command.getEventId(), command.getAddTo()));
    }

    @CommandHandler
    public void handle(RemoveIntervalsFromEventCommand command) throws Exception {
    	LOGGER.info( "Handling command RemoveIntervalsFromEventCommand" );
    	
    	if ( !intervals.contains( command.getRemoveFrom() ) )
    		throw new ProcessingException( "Intervals does not contain an entity with id " + command.getRemoveFrom().getIntervalId() );

        apply(new RemoveIntervalsFromEventEvent(command.getEventId(), command.getRemoveFrom()));
    }

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreateEventEvent event) {	
    	LOGGER.info( "Event sourcing CreateEventEvent" );
    	this.eventId = event.getEventId();
        this.createdDateTime = event.getCreatedDateTime();
        this.modificationDateTime = event.getModificationDateTime();
        this.eventName = event.getEventName();
        this.priority = event.getPriority();
        this.intervalPeriod = event.getIntervalPeriod();
        this.intervals = event.getIntervals();
        this.objectType = event.getObjectType();
    }
    
    @EventSourcingHandler
    void on(UpdateEventEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.createdDateTime = event.getCreatedDateTime();
        this.modificationDateTime = event.getModificationDateTime();
        this.eventName = event.getEventName();
        this.priority = event.getPriority();
        this.intervalPeriod = event.getIntervalPeriod();
        this.program = event.getProgram();
        this.targets = event.getTargets();
        this.reportDescriptors = event.getReportDescriptors();
        this.payloadDescriptors = event.getPayloadDescriptors();
        this.intervals = event.getIntervals();
        this.objectType = event.getObjectType();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------
	// single associations
    @EventSourcingHandler
    void on(AssignProgramToEventEvent event ) {	
    	LOGGER.info( "Event sourcing AssignProgramToEventEvent" );
    	this.program = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignProgramFromEventEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignProgramFromEventEvent" );
		this.program = null;
	}

	// multiple associations
    @EventSourcingHandler
    void on(AssignReportDescriptorsToEventEvent event ) {
    	LOGGER.info( "Event sourcing AssignReportDescriptorsToEventEvent" );
    	this.reportDescriptors.add( event.getAddTo() );
    }

	@EventSourcingHandler
	void on(RemoveReportDescriptorsFromEventEvent event ) {	
		LOGGER.info( "Event sourcing RemoveReportDescriptorsFromEventEvent" );
		this.reportDescriptors.remove( event.getRemoveFrom() );
	}
	
	// multiple associations
    @EventSourcingHandler
    void on(AssignPayloadDescriptorsToEventEvent event ) {
    	LOGGER.info( "Event sourcing AssignPayloadDescriptorsToEventEvent" );
    	this.payloadDescriptors.add( event.getAddTo() );
    }

	@EventSourcingHandler
	void on(RemovePayloadDescriptorsFromEventEvent event ) {	
		LOGGER.info( "Event sourcing RemovePayloadDescriptorsFromEventEvent" );
		this.payloadDescriptors.remove( event.getRemoveFrom() );
	}
	
	// multiple associations
    @EventSourcingHandler
    void on(AssignIntervalsToEventEvent event ) {
    	LOGGER.info( "Event sourcing AssignIntervalsToEventEvent" );
    	this.intervals.add( event.getAddTo() );
    }

	@EventSourcingHandler
	void on(RemoveIntervalsFromEventEvent event ) {	
		LOGGER.info( "Event sourcing RemoveIntervalsFromEventEvent" );
		this.intervals.remove( event.getRemoveFrom() );
	}
	

    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID eventId;
    
    private Date createdDateTime;
    private Date modificationDateTime;
    private String eventName;
    private Integer priority;
    private IntervalPeriod intervalPeriod;
    private Interval intervals;
    private ObjectType objectType;
    private Program program = null;
    private ValuesMape targets = null;
    private Set<ReportDescriptor> reportDescriptors = new HashSet<>();
    private Set<PayloadDescriptor> payloadDescriptors = new HashSet<>();

    private static final Logger LOGGER 	= Logger.getLogger(EventAggregate.class.getName());
}
