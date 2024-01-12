/*******************************************************************************
  Turnstone Biologics Confidential
  
  2018 Turnstone Biologics
  All Rights Reserved.
  
  This file is subject to the terms and conditions defined in
  file 'license.txt', which is part of this source code package.
   
  Contributors :
        Turnstone Biologics - General Release
 ******************************************************************************/
package com.occulue.validator;

import org.springframework.util.Assert;

import com.occulue.api.*;

public class EventPayloadDescriptorValidator {
		
	/**
	 * default constructor
	 */
	protected EventPayloadDescriptorValidator() {	
	}
	
	/**
	 * factory method
	 */
	static public EventPayloadDescriptorValidator getInstance() {
		return new EventPayloadDescriptorValidator();
	}
		
	/**
	 * handles creation validation for a EventPayloadDescriptor
	 */
	public void validate( CreateEventPayloadDescriptorCommand eventPayloadDescriptor )throws Exception {
		Assert.notNull( eventPayloadDescriptor, "CreateEventPayloadDescriptorCommand should not be null" );
//		Assert.isNull( eventPayloadDescriptor.getEventPayloadDescriptorId(), "CreateEventPayloadDescriptorCommand identifier should be null" );
		Assert.notNull( eventPayloadDescriptor.getUnits(), "Field CreateEventPayloadDescriptorCommand.units should not be null" );
		Assert.notNull( eventPayloadDescriptor.getCurrency(), "Field CreateEventPayloadDescriptorCommand.currency should not be null" );
	}

	/**
	 * handles update validation for a EventPayloadDescriptor
	 */
	public void validate( UpdateEventPayloadDescriptorCommand eventPayloadDescriptor ) throws Exception {
		Assert.notNull( eventPayloadDescriptor, "UpdateEventPayloadDescriptorCommand should not be null" );
		Assert.notNull( eventPayloadDescriptor.getEventPayloadDescriptorId(), "UpdateEventPayloadDescriptorCommand identifier should not be null" );
		Assert.notNull( eventPayloadDescriptor.getUnits(), "Field UpdateEventPayloadDescriptorCommand.units should not be null" );
		Assert.notNull( eventPayloadDescriptor.getCurrency(), "Field UpdateEventPayloadDescriptorCommand.currency should not be null" );
    }

	/**
	 * handles delete validation for a EventPayloadDescriptor
	 */
    public void validate( DeleteEventPayloadDescriptorCommand eventPayloadDescriptor ) throws Exception {
		Assert.notNull( eventPayloadDescriptor, "{commandAlias} should not be null" );
		Assert.notNull( eventPayloadDescriptor.getEventPayloadDescriptorId(), "DeleteEventPayloadDescriptorCommand identifier should not be null" );
	}
	
	/**
	 * handles fetchOne validation for a EventPayloadDescriptor
	 */
	public void validate( EventPayloadDescriptorFetchOneSummary summary ) throws Exception {
		Assert.notNull( summary, "EventPayloadDescriptorFetchOneSummary should not be null" );
		Assert.notNull( summary.getEventPayloadDescriptorId(), "EventPayloadDescriptorFetchOneSummary identifier should not be null" );
	}



}
