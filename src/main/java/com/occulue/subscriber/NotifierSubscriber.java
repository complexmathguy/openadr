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
 * Subscriber for Notifier related events.  .
 * 
 * @author your_name_here
 *
 */
@Component("notifier-subscriber")
public class NotifierSubscriber extends BaseSubscriber {

	public NotifierSubscriber() {
		queryGateway = applicationContext.getBean(QueryGateway.class);
	}
	
    public SubscriptionQueryResult<List<Notifier>, Notifier> notifierSubscribe() {
        return queryGateway
                .subscriptionQuery(new FindAllNotifierQuery(), 
                		ResponseTypes.multipleInstancesOf(Notifier.class),
                		ResponseTypes.instanceOf(Notifier.class));
    }

    public SubscriptionQueryResult<Notifier, Notifier> notifierSubscribe(@DestinationVariable UUID notifierId) {
        return queryGateway
                .subscriptionQuery(new FindNotifierQuery(new LoadNotifierFilter(notifierId)), 
                		ResponseTypes.instanceOf(Notifier.class),
                		ResponseTypes.instanceOf(Notifier.class));
    }




    // -------------------------------------------------
    // attributes
    // -------------------------------------------------
	@Autowired
    private final QueryGateway queryGateway;
}

