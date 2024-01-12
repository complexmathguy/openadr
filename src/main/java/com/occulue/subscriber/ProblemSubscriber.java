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
 * Subscriber for Problem related events.  .
 * 
 * @author your_name_here
 *
 */
@Component("problem-subscriber")
public class ProblemSubscriber extends BaseSubscriber {

	public ProblemSubscriber() {
		queryGateway = applicationContext.getBean(QueryGateway.class);
	}
	
    public SubscriptionQueryResult<List<Problem>, Problem> problemSubscribe() {
        return queryGateway
                .subscriptionQuery(new FindAllProblemQuery(), 
                		ResponseTypes.multipleInstancesOf(Problem.class),
                		ResponseTypes.instanceOf(Problem.class));
    }

    public SubscriptionQueryResult<Problem, Problem> problemSubscribe(@DestinationVariable UUID problemId) {
        return queryGateway
                .subscriptionQuery(new FindProblemQuery(new LoadProblemFilter(problemId)), 
                		ResponseTypes.instanceOf(Problem.class),
                		ResponseTypes.instanceOf(Problem.class));
    }




    // -------------------------------------------------
    // attributes
    // -------------------------------------------------
	@Autowired
    private final QueryGateway queryGateway;
}

