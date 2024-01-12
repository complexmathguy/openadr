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
 * Subscriber for EventPayloadDescriptor related events.  .
 * 
 * @author your_name_here
 *
 */
@Component("eventPayloadDescriptor-subscriber")
public class EventPayloadDescriptorSubscriber extends BaseSubscriber {

	public EventPayloadDescriptorSubscriber() {
		queryGateway = applicationContext.getBean(QueryGateway.class);
	}
	
    public SubscriptionQueryResult<List<EventPayloadDescriptor>, EventPayloadDescriptor> eventPayloadDescriptorSubscribe() {
        return queryGateway
                .subscriptionQuery(new FindAllEventPayloadDescriptorQuery(), 
                		ResponseTypes.multipleInstancesOf(EventPayloadDescriptor.class),
                		ResponseTypes.instanceOf(EventPayloadDescriptor.class));
    }

    public SubscriptionQueryResult<EventPayloadDescriptor, EventPayloadDescriptor> eventPayloadDescriptorSubscribe(@DestinationVariable UUID eventPayloadDescriptorId) {
        return queryGateway
                .subscriptionQuery(new FindEventPayloadDescriptorQuery(new LoadEventPayloadDescriptorFilter(eventPayloadDescriptorId)), 
                		ResponseTypes.instanceOf(EventPayloadDescriptor.class),
                		ResponseTypes.instanceOf(EventPayloadDescriptor.class));
    }




    // -------------------------------------------------
    // attributes
    // -------------------------------------------------
	@Autowired
    private final QueryGateway queryGateway;
}

