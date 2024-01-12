/*******************************************************************************
  Turnstone Biologics Confidential
  
  2018 Turnstone Biologics
  All Rights Reserved.
  
  This file is subject to the terms and conditions defined in
  file 'license.txt', which is part of this source code package.
   
  Contributors :
        Turnstone Biologics - General Release
 ******************************************************************************/
package com.occulue.subscriber;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.queryhandling.SubscriptionQueryResult;
import org.axonframework.messaging.responsetypes.ResponseTypes;

import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.stereotype.Component;


import com.occulue.api.*;
import com.occulue.entity.*;
import com.occulue.exception.*;

/**
 * Subscriber for Notification related events.  .
 * 
 * @author your_name_here
 *
 */
@Component("notification-subscriber")
public class NotificationSubscriber extends BaseSubscriber {

	public NotificationSubscriber() {
		queryGateway = applicationContext.getBean(QueryGateway.class);
	}
	
    public SubscriptionQueryResult<List<Notification>, Notification> notificationSubscribe() {
        return queryGateway
                .subscriptionQuery(new FindAllNotificationQuery(), 
                		ResponseTypes.multipleInstancesOf(Notification.class),
                		ResponseTypes.instanceOf(Notification.class));
    }

    public SubscriptionQueryResult<Notification, Notification> notificationSubscribe(@DestinationVariable UUID notificationId) {
        return queryGateway
                .subscriptionQuery(new FindNotificationQuery(new LoadNotificationFilter(notificationId)), 
                		ResponseTypes.instanceOf(Notification.class),
                		ResponseTypes.instanceOf(Notification.class));
    }




    // -------------------------------------------------
    // attributes
    // -------------------------------------------------
	@Autowired
    private final QueryGateway queryGateway;
}

