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
 * Subscriber for Resource related events.  .
 * 
 * @author your_name_here
 *
 */
@Component("resource-subscriber")
public class ResourceSubscriber extends BaseSubscriber {

	public ResourceSubscriber() {
		queryGateway = applicationContext.getBean(QueryGateway.class);
	}
	
    public SubscriptionQueryResult<List<Resource>, Resource> resourceSubscribe() {
        return queryGateway
                .subscriptionQuery(new FindAllResourceQuery(), 
                		ResponseTypes.multipleInstancesOf(Resource.class),
                		ResponseTypes.instanceOf(Resource.class));
    }

    public SubscriptionQueryResult<Resource, Resource> resourceSubscribe(@DestinationVariable UUID resourceId) {
        return queryGateway
                .subscriptionQuery(new FindResourceQuery(new LoadResourceFilter(resourceId)), 
                		ResponseTypes.instanceOf(Resource.class),
                		ResponseTypes.instanceOf(Resource.class));
    }




    // -------------------------------------------------
    // attributes
    // -------------------------------------------------
	@Autowired
    private final QueryGateway queryGateway;
}

