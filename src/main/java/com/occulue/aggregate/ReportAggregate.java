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
 * Aggregate handler for Report as outlined for the CQRS pattern, all write responsibilities 
 * related to Report are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class ReportAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public ReportAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public ReportAggregate(CreateReportCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateReportCommand" );
    	CreateReportEvent event = new CreateReportEvent(command.getReportId(), command.getCreatedDateTime(), command.getModificationDateTime(), command.getClientName(), command.getReportName(), command.getIntervalPeriod(), command.getIntervals(), command.getObjectType());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdateReportCommand command) throws Exception {
    	LOGGER.info( "handling command UpdateReportCommand" );
    	UpdateReportEvent event = new UpdateReportEvent(command.getReportId(), command.getCreatedDateTime(), command.getModificationDateTime(), command.getClientName(), command.getReportName(), command.getIntervalPeriod(), command.getProgram(), command.getEvent(), command.getPayloadDescriptors(), command.getResources(), command.getIntervals(), command.getObjectType());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeleteReportCommand command) throws Exception {
    	LOGGER.info( "Handling command DeleteReportCommand" );
        apply(new DeleteReportEvent(command.getReportId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands
    @CommandHandler
    public void handle(AssignProgramToReportCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignProgramToReportCommand" );
    	
    	if (  program != null && program.getProgramId() == command.getAssignment().getProgramId() )
    		throw new ProcessingException( "Program already assigned with id " + command.getAssignment().getProgramId() );  
    		
        apply(new AssignProgramToReportEvent(command.getReportId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignProgramFromReportCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignProgramFromReportCommand" );

    	if (  program == null )
    		throw new ProcessingException( "Program already has nothing assigned." );  

    	apply(new UnAssignProgramFromReportEvent(command.getReportId()));
    }
    @CommandHandler
    public void handle(AssignEventToReportCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignEventToReportCommand" );
    	
    	if (  event != null && event.getEventId() == command.getAssignment().getEventId() )
    		throw new ProcessingException( "Event already assigned with id " + command.getAssignment().getEventId() );  
    		
        apply(new AssignEventToReportEvent(command.getReportId(), command.getAssignment()));
    }

    @CommandHandler
    public void handle(UnAssignEventFromReportCommand command) throws Exception {
    	LOGGER.info( "Handlign command UnAssignEventFromReportCommand" );

    	if (  event == null )
    		throw new ProcessingException( "Event already has nothing assigned." );  

    	apply(new UnAssignEventFromReportEvent(command.getReportId()));
    }

    // multiple association commands
    @CommandHandler
    public void handle(AssignPayloadDescriptorsToReportCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignPayloadDescriptorsToReportCommand" );
    	
    	if ( payloadDescriptors.contains( command.getAddTo() ) )
    		throw new ProcessingException( "PayloadDescriptors already contains an entity with id " + command.getAddTo().getPayloadDescriptorId() );

    	apply(new AssignPayloadDescriptorsToReportEvent(command.getReportId(), command.getAddTo()));
    }

    @CommandHandler
    public void handle(RemovePayloadDescriptorsFromReportCommand command) throws Exception {
    	LOGGER.info( "Handling command RemovePayloadDescriptorsFromReportCommand" );
    	
    	if ( !payloadDescriptors.contains( command.getRemoveFrom() ) )
    		throw new ProcessingException( "PayloadDescriptors does not contain an entity with id " + command.getRemoveFrom().getPayloadDescriptorId() );

        apply(new RemovePayloadDescriptorsFromReportEvent(command.getReportId(), command.getRemoveFrom()));
    }
    @CommandHandler
    public void handle(AssignResourcesToReportCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignResourcesToReportCommand" );
    	
    	if ( resources.contains( command.getAddTo() ) )
    		throw new ProcessingException( "Resources already contains an entity with id " + command.getAddTo().getResourceId() );

    	apply(new AssignResourcesToReportEvent(command.getReportId(), command.getAddTo()));
    }

    @CommandHandler
    public void handle(RemoveResourcesFromReportCommand command) throws Exception {
    	LOGGER.info( "Handling command RemoveResourcesFromReportCommand" );
    	
    	if ( !resources.contains( command.getRemoveFrom() ) )
    		throw new ProcessingException( "Resources does not contain an entity with id " + command.getRemoveFrom().getResourceId() );

        apply(new RemoveResourcesFromReportEvent(command.getReportId(), command.getRemoveFrom()));
    }

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreateReportEvent event) {	
    	LOGGER.info( "Event sourcing CreateReportEvent" );
    	this.reportId = event.getReportId();
        this.createdDateTime = event.getCreatedDateTime();
        this.modificationDateTime = event.getModificationDateTime();
        this.clientName = event.getClientName();
        this.reportName = event.getReportName();
        this.intervalPeriod = event.getIntervalPeriod();
        this.intervals = event.getIntervals();
        this.objectType = event.getObjectType();
    }
    
    @EventSourcingHandler
    void on(UpdateReportEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.createdDateTime = event.getCreatedDateTime();
        this.modificationDateTime = event.getModificationDateTime();
        this.clientName = event.getClientName();
        this.reportName = event.getReportName();
        this.intervalPeriod = event.getIntervalPeriod();
        this.program = event.getProgram();
        this.event = event.getEvent();
        this.payloadDescriptors = event.getPayloadDescriptors();
        this.resources = event.getResources();
        this.intervals = event.getIntervals();
        this.objectType = event.getObjectType();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------
	// single associations
    @EventSourcingHandler
    void on(AssignProgramToReportEvent event ) {	
    	LOGGER.info( "Event sourcing AssignProgramToReportEvent" );
    	this.program = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignProgramFromReportEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignProgramFromReportEvent" );
		this.program = null;
	}
	// single associations
    @EventSourcingHandler
    void on(AssignEventToReportEvent event ) {	
    	LOGGER.info( "Event sourcing AssignEventToReportEvent" );
    	this.event = event.getAssignment();
    }

	@EventSourcingHandler
	void on(UnAssignEventFromReportEvent event ) {	
		LOGGER.info( "Event sourcing UnAssignEventFromReportEvent" );
		this.event = null;
	}

	// multiple associations
    @EventSourcingHandler
    void on(AssignPayloadDescriptorsToReportEvent event ) {
    	LOGGER.info( "Event sourcing AssignPayloadDescriptorsToReportEvent" );
    	this.payloadDescriptors.add( event.getAddTo() );
    }

	@EventSourcingHandler
	void on(RemovePayloadDescriptorsFromReportEvent event ) {	
		LOGGER.info( "Event sourcing RemovePayloadDescriptorsFromReportEvent" );
		this.payloadDescriptors.remove( event.getRemoveFrom() );
	}
	
	// multiple associations
    @EventSourcingHandler
    void on(AssignResourcesToReportEvent event ) {
    	LOGGER.info( "Event sourcing AssignResourcesToReportEvent" );
    	this.resources.add( event.getAddTo() );
    }

	@EventSourcingHandler
	void on(RemoveResourcesFromReportEvent event ) {	
		LOGGER.info( "Event sourcing RemoveResourcesFromReportEvent" );
		this.resources.remove( event.getRemoveFrom() );
	}
	

    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID reportId;
    
    private Date createdDateTime;
    private Date modificationDateTime;
    private String clientName;
    private String reportName;
    private IntervalPeriod intervalPeriod;
    private Interval intervals;
    private ObjectType objectType;
    private Program program = null;
    private Event event = null;
    private Set<PayloadDescriptor> payloadDescriptors = new HashSet<>();
    private Set<Resource> resources = new HashSet<>();

    private static final Logger LOGGER 	= Logger.getLogger(ReportAggregate.class.getName());
}
