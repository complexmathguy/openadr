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

public class NotificationValidator {
		
	/**
	 * default constructor
	 */
	protected NotificationValidator() {	
	}
	
	/**
	 * factory method
	 */
	static public NotificationValidator getInstance() {
		return new NotificationValidator();
	}
		
	/**
	 * handles creation validation for a Notification
	 */
	public void validate( CreateNotificationCommand notification )throws Exception {
		Assert.notNull( notification, "CreateNotificationCommand should not be null" );
//		Assert.isNull( notification.getNotificationId(), "CreateNotificationCommand identifier should be null" );
	}

	/**
	 * handles update validation for a Notification
	 */
	public void validate( UpdateNotificationCommand notification ) throws Exception {
		Assert.notNull( notification, "UpdateNotificationCommand should not be null" );
		Assert.notNull( notification.getNotificationId(), "UpdateNotificationCommand identifier should not be null" );
    }

	/**
	 * handles delete validation for a Notification
	 */
    public void validate( DeleteNotificationCommand notification ) throws Exception {
		Assert.notNull( notification, "{commandAlias} should not be null" );
		Assert.notNull( notification.getNotificationId(), "DeleteNotificationCommand identifier should not be null" );
	}
	
	/**
	 * handles fetchOne validation for a Notification
	 */
	public void validate( NotificationFetchOneSummary summary ) throws Exception {
		Assert.notNull( summary, "NotificationFetchOneSummary should not be null" );
		Assert.notNull( summary.getNotificationId(), "NotificationFetchOneSummary identifier should not be null" );
	}

	/**
	 * handles assign Notifier validation for a Notification
	 * 
	 * @param	command AssignNotifierToNotificationCommand
	 */	
	public void validate( AssignNotifierToNotificationCommand command ) throws Exception {
		Assert.notNull( command, "AssignNotifierToNotificationCommand should not be null" );
		Assert.notNull( command.getNotificationId(), "AssignNotifierToNotificationCommand identifier should not be null" );
		Assert.notNull( command.getAssignment(), "AssignNotifierToNotificationCommand assignment should not be null" );
	}

	/**
	 * handles unassign Notifier validation for a Notification
	 * 
	 * @param	command UnAssignNotifierFromNotificationCommand
	 */	
	public void validate( UnAssignNotifierFromNotificationCommand command ) throws Exception {
		Assert.notNull( command, "UnAssignNotifierFromNotificationCommand should not be null" );
		Assert.notNull( command.getNotificationId(), "UnAssignNotifierFromNotificationCommand identifier should not be null" );
	}


}
