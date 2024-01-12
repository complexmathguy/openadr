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
 * Subscriber for Subscription related events.  .
 * 
 * @author your_name_here
 *
 */
@Component("subscription-subscriber")
public class SubscriptionSubscriber extends BaseSubscriber {

	public SubscriptionSubscriber() {
		queryGateway = applicationContext.getBean(QueryGateway.class);
	}
	
    public SubscriptionQueryResult<List<Subscription>, Subscription> subscriptionSubscribe() {
        return queryGateway
                .subscriptionQuery(new FindAllSubscriptionQuery(), 
                		ResponseTypes.multipleInstancesOf(Subscription.class),
                		ResponseTypes.instanceOf(Subscription.class));
    }

    public SubscriptionQueryResult<Subscription, Subscription> subscriptionSubscribe(@DestinationVariable UUID subscriptionId) {
        return queryGateway
                .subscriptionQuery(new FindSubscriptionQuery(new LoadSubscriptionFilter(subscriptionId)), 
                		ResponseTypes.instanceOf(Subscription.class),
                		ResponseTypes.instanceOf(Subscription.class));
    }




    // -------------------------------------------------
    // attributes
    // -------------------------------------------------
	@Autowired
    private final QueryGateway queryGateway;
}

