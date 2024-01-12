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
 * Subscriber for ObjectOperation related events.  .
 * 
 * @author your_name_here
 *
 */
@Component("objectOperation-subscriber")
public class ObjectOperationSubscriber extends BaseSubscriber {

	public ObjectOperationSubscriber() {
		queryGateway = applicationContext.getBean(QueryGateway.class);
	}
	
    public SubscriptionQueryResult<List<ObjectOperation>, ObjectOperation> objectOperationSubscribe() {
        return queryGateway
                .subscriptionQuery(new FindAllObjectOperationQuery(), 
                		ResponseTypes.multipleInstancesOf(ObjectOperation.class),
                		ResponseTypes.instanceOf(ObjectOperation.class));
    }

    public SubscriptionQueryResult<ObjectOperation, ObjectOperation> objectOperationSubscribe(@DestinationVariable UUID objectOperationId) {
        return queryGateway
                .subscriptionQuery(new FindObjectOperationQuery(new LoadObjectOperationFilter(objectOperationId)), 
                		ResponseTypes.instanceOf(ObjectOperation.class),
                		ResponseTypes.instanceOf(ObjectOperation.class));
    }




    // -------------------------------------------------
    // attributes
    // -------------------------------------------------
	@Autowired
    private final QueryGateway queryGateway;
}

