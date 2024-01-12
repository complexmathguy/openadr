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
 * Aggregate handler for Program as outlined for the CQRS pattern, all write responsibilities 
 * related to Program are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class ProgramAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public ProgramAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public ProgramAggregate(CreateProgramCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateProgramCommand" );
    	CreateProgramEvent event = new CreateProgramEvent(command.getProgramId(), command.getCreatedDateTime(), command.getModificationDateTime(), command.getProgramName(), command.getProgramLongName(), command.getRetailerName(), command.getRetailerLongName(), command.getProgramType(), command.getCountry(), command.getPrincipalSubdivision(), command.getTimeZoneOffset(), command.getIntervalPeriod(), command.getProgramDescriptions(), command.getBindingEvents(), command.getLocalPrice(), command.getObjectType());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(UpdateProgramCommand command) throws Exception {
    	LOGGER.info( "handling command UpdateProgramCommand" );
    	UpdateProgramEvent event = new UpdateProgramEvent(command.getProgramId(), command.getCreatedDateTime(), command.getModificationDateTime(), command.getProgramName(), command.getProgramLongName(), command.getRetailerName(), command.getRetailerLongName(), command.getProgramType(), command.getCountry(), command.getPrincipalSubdivision(), command.getTimeZoneOffset(), command.getIntervalPeriod(), command.getProgramDescriptions(), command.getBindingEvents(), command.getLocalPrice(), command.getPayloadDescriptors(), command.getTargets(), command.getObjectType());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(DeleteProgramCommand command) throws Exception {
    	LOGGER.info( "Handling command DeleteProgramCommand" );
        apply(new DeleteProgramEvent(command.getProgramId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands

    // multiple association commands
    @CommandHandler
    public void handle(AssignPayloadDescriptorsToProgramCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignPayloadDescriptorsToProgramCommand" );
    	
    	if ( payloadDescriptors.contains( command.getAddTo() ) )
    		throw new ProcessingException( "PayloadDescriptors already contains an entity with id " + command.getAddTo().getPayloadDescriptorId() );

    	apply(new AssignPayloadDescriptorsToProgramEvent(command.getProgramId(), command.getAddTo()));
    }

    @CommandHandler
    public void handle(RemovePayloadDescriptorsFromProgramCommand command) throws Exception {
    	LOGGER.info( "Handling command RemovePayloadDescriptorsFromProgramCommand" );
    	
    	if ( !payloadDescriptors.contains( command.getRemoveFrom() ) )
    		throw new ProcessingException( "PayloadDescriptors does not contain an entity with id " + command.getRemoveFrom().getPayloadDescriptorId() );

        apply(new RemovePayloadDescriptorsFromProgramEvent(command.getProgramId(), command.getRemoveFrom()));
    }
    @CommandHandler
    public void handle(AssignTargetsToProgramCommand command) throws Exception {
    	LOGGER.info( "Handling command AssignTargetsToProgramCommand" );
    	
    	if ( targets.contains( command.getAddTo() ) )
    		throw new ProcessingException( "Targets already contains an entity with id " + command.getAddTo().getValueMapId() );

    	apply(new AssignTargetsToProgramEvent(command.getProgramId(), command.getAddTo()));
    }

    @CommandHandler
    public void handle(RemoveTargetsFromProgramCommand command) throws Exception {
    	LOGGER.info( "Handling command RemoveTargetsFromProgramCommand" );
    	
    	if ( !targets.contains( command.getRemoveFrom() ) )
    		throw new ProcessingException( "Targets does not contain an entity with id " + command.getRemoveFrom().getValueMapId() );

        apply(new RemoveTargetsFromProgramEvent(command.getProgramId(), command.getRemoveFrom()));
    }

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreateProgramEvent event) {	
    	LOGGER.info( "Event sourcing CreateProgramEvent" );
    	this.programId = event.getProgramId();
        this.createdDateTime = event.getCreatedDateTime();
        this.modificationDateTime = event.getModificationDateTime();
        this.programName = event.getProgramName();
        this.programLongName = event.getProgramLongName();
        this.retailerName = event.getRetailerName();
        this.retailerLongName = event.getRetailerLongName();
        this.programType = event.getProgramType();
        this.country = event.getCountry();
        this.principalSubdivision = event.getPrincipalSubdivision();
        this.timeZoneOffset = event.getTimeZoneOffset();
        this.intervalPeriod = event.getIntervalPeriod();
        this.programDescriptions = event.getProgramDescriptions();
        this.bindingEvents = event.getBindingEvents();
        this.localPrice = event.getLocalPrice();
        this.objectType = event.getObjectType();
    }
    
    @EventSourcingHandler
    void on(UpdateProgramEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.createdDateTime = event.getCreatedDateTime();
        this.modificationDateTime = event.getModificationDateTime();
        this.programName = event.getProgramName();
        this.programLongName = event.getProgramLongName();
        this.retailerName = event.getRetailerName();
        this.retailerLongName = event.getRetailerLongName();
        this.programType = event.getProgramType();
        this.country = event.getCountry();
        this.principalSubdivision = event.getPrincipalSubdivision();
        this.timeZoneOffset = event.getTimeZoneOffset();
        this.intervalPeriod = event.getIntervalPeriod();
        this.programDescriptions = event.getProgramDescriptions();
        this.bindingEvents = event.getBindingEvents();
        this.localPrice = event.getLocalPrice();
        this.payloadDescriptors = event.getPayloadDescriptors();
        this.targets = event.getTargets();
        this.objectType = event.getObjectType();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------

	// multiple associations
    @EventSourcingHandler
    void on(AssignPayloadDescriptorsToProgramEvent event ) {
    	LOGGER.info( "Event sourcing AssignPayloadDescriptorsToProgramEvent" );
    	this.payloadDescriptors.add( event.getAddTo() );
    }

	@EventSourcingHandler
	void on(RemovePayloadDescriptorsFromProgramEvent event ) {	
		LOGGER.info( "Event sourcing RemovePayloadDescriptorsFromProgramEvent" );
		this.payloadDescriptors.remove( event.getRemoveFrom() );
	}
	
	// multiple associations
    @EventSourcingHandler
    void on(AssignTargetsToProgramEvent event ) {
    	LOGGER.info( "Event sourcing AssignTargetsToProgramEvent" );
    	this.targets.add( event.getAddTo() );
    }

	@EventSourcingHandler
	void on(RemoveTargetsFromProgramEvent event ) {	
		LOGGER.info( "Event sourcing RemoveTargetsFromProgramEvent" );
		this.targets.remove( event.getRemoveFrom() );
	}
	

    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID programId;
    
    private Date createdDateTime;
    private Date modificationDateTime;
    private String programName;
    private String programLongName;
    private String retailerName;
    private String retailerLongName;
    private String programType;
    private String country;
    private String principalSubdivision;
    private Duration timeZoneOffset;
    private IntervalPeriod intervalPeriod;
    private String[] programDescriptions;
    private Boolean bindingEvents;
    private Boolean localPrice;
    private ObjectType objectType;
    private Set<PayloadDescriptor> payloadDescriptors = new HashSet<>();
    private Set<ValueMap> targets = new HashSet<>();

    private static final Logger LOGGER 	= Logger.getLogger(ProgramAggregate.class.getName());
}
