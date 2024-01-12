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

public class ObjectOperationValidator {
		
	/**
	 * default constructor
	 */
	protected ObjectOperationValidator() {	
	}
	
	/**
	 * factory method
	 */
	static public ObjectOperationValidator getInstance() {
		return new ObjectOperationValidator();
	}
		
	/**
	 * handles creation validation for a ObjectOperation
	 */
	public void validate( CreateObjectOperationCommand objectOperation )throws Exception {
		Assert.notNull( objectOperation, "CreateObjectOperationCommand should not be null" );
//		Assert.isNull( objectOperation.getObjectOperationId(), "CreateObjectOperationCommand identifier should be null" );
		Assert.notNull( objectOperation.getCallbackUrl(), "Field CreateObjectOperationCommand.callbackUrl should not be null" );
		Assert.notNull( objectOperation.getBearerToken(), "Field CreateObjectOperationCommand.bearerToken should not be null" );
	}

	/**
	 * handles update validation for a ObjectOperation
	 */
	public void validate( UpdateObjectOperationCommand objectOperation ) throws Exception {
		Assert.notNull( objectOperation, "UpdateObjectOperationCommand should not be null" );
		Assert.notNull( objectOperation.getObjectOperationId(), "UpdateObjectOperationCommand identifier should not be null" );
		Assert.notNull( objectOperation.getCallbackUrl(), "Field UpdateObjectOperationCommand.callbackUrl should not be null" );
		Assert.notNull( objectOperation.getBearerToken(), "Field UpdateObjectOperationCommand.bearerToken should not be null" );
    }

	/**
	 * handles delete validation for a ObjectOperation
	 */
    public void validate( DeleteObjectOperationCommand objectOperation ) throws Exception {
		Assert.notNull( objectOperation, "{commandAlias} should not be null" );
		Assert.notNull( objectOperation.getObjectOperationId(), "DeleteObjectOperationCommand identifier should not be null" );
	}
	
	/**
	 * handles fetchOne validation for a ObjectOperation
	 */
	public void validate( ObjectOperationFetchOneSummary summary ) throws Exception {
		Assert.notNull( summary, "ObjectOperationFetchOneSummary should not be null" );
		Assert.notNull( summary.getObjectOperationId(), "ObjectOperationFetchOneSummary identifier should not be null" );
	}


	/**
	 * handles add to Operations validation for a ObjectOperation
	 * 
	 * @param	command AssignOperationsToObjectOperationCommand
	 */	
	public void validate( AssignOperationsToObjectOperationCommand command ) throws Exception {
		Assert.notNull( command, "AssignOperationsToObjectOperationCommand should not be null" );
		Assert.notNull( command.getObjectOperationId(), "AssignOperationsToObjectOperationCommand identifier should not be null" );
		Assert.notNull( command.getAddTo(), "AssignOperationsToObjectOperationCommand addTo attribute should not be null" );
	}

	/**
	 * handles remove from Operations validation for a ObjectOperation
	 * 
	 * @param	command RemoveOperationsFromObjectOperationCommand
	 */	
	public void validate( RemoveOperationsFromObjectOperationCommand command ) throws Exception {
		Assert.notNull( command, "RemoveOperationsFromObjectOperationCommand should not be null" );
		Assert.notNull( command.getObjectOperationId(), "RemoveOperationsFromObjectOperationCommand identifier should not be null" );
		Assert.notNull( command.getRemoveFrom(), "RemoveOperationsFromObjectOperationCommand removeFrom attribute should not be null" );
		Assert.notNull( command.getRemoveFrom().getOperationsId(), "RemoveOperationsFromObjectOperationCommand removeFrom attribubte identifier should not be null" );
	}
	

}
