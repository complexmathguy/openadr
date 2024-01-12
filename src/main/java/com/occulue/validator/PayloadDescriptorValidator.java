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

public class PayloadDescriptorValidator {
		
	/**
	 * default constructor
	 */
	protected PayloadDescriptorValidator() {	
	}
	
	/**
	 * factory method
	 */
	static public PayloadDescriptorValidator getInstance() {
		return new PayloadDescriptorValidator();
	}
		
	/**
	 * handles creation validation for a PayloadDescriptor
	 */
	public void validate( CreatePayloadDescriptorCommand payloadDescriptor )throws Exception {
		Assert.notNull( payloadDescriptor, "CreatePayloadDescriptorCommand should not be null" );
//		Assert.isNull( payloadDescriptor.getPayloadDescriptorId(), "CreatePayloadDescriptorCommand identifier should be null" );
		Assert.notNull( payloadDescriptor.getPayloadType(), "Field CreatePayloadDescriptorCommand.payloadType should not be null" );
	}

	/**
	 * handles update validation for a PayloadDescriptor
	 */
	public void validate( UpdatePayloadDescriptorCommand payloadDescriptor ) throws Exception {
		Assert.notNull( payloadDescriptor, "UpdatePayloadDescriptorCommand should not be null" );
		Assert.notNull( payloadDescriptor.getPayloadDescriptorId(), "UpdatePayloadDescriptorCommand identifier should not be null" );
		Assert.notNull( payloadDescriptor.getPayloadType(), "Field UpdatePayloadDescriptorCommand.payloadType should not be null" );
    }

	/**
	 * handles delete validation for a PayloadDescriptor
	 */
    public void validate( DeletePayloadDescriptorCommand payloadDescriptor ) throws Exception {
		Assert.notNull( payloadDescriptor, "{commandAlias} should not be null" );
		Assert.notNull( payloadDescriptor.getPayloadDescriptorId(), "DeletePayloadDescriptorCommand identifier should not be null" );
	}
	
	/**
	 * handles fetchOne validation for a PayloadDescriptor
	 */
	public void validate( PayloadDescriptorFetchOneSummary summary ) throws Exception {
		Assert.notNull( summary, "PayloadDescriptorFetchOneSummary should not be null" );
		Assert.notNull( summary.getPayloadDescriptorId(), "PayloadDescriptorFetchOneSummary identifier should not be null" );
	}



}
