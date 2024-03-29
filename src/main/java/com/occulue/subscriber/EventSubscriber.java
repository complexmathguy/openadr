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
 * Subscriber for Event related events.  .
 * 
 * @author your_name_here
 *
 */
@Component("event-subscriber")
public class EventSubscriber extends BaseSubscriber {

	public EventSubscriber() {
		queryGateway = applicationContext.getBean(QueryGateway.class);
	}
	
    public SubscriptionQueryResult<List<Event>, Event> eventSubscribe() {
        return queryGateway
                .subscriptionQuery(new FindAllEventQuery(), 
                		ResponseTypes.multipleInstancesOf(Event.class),
                		ResponseTypes.instanceOf(Event.class));
    }

    public SubscriptionQueryResult<Event, Event> eventSubscribe(@DestinationVariable UUID eventId) {
        return queryGateway
                .subscriptionQuery(new FindEventQuery(new LoadEventFilter(eventId)), 
                		ResponseTypes.instanceOf(Event.class),
                		ResponseTypes.instanceOf(Event.class));
    }




    // -------------------------------------------------
    // attributes
    // -------------------------------------------------
	@Autowired
    private final QueryGateway queryGateway;
}

