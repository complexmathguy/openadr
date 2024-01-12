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
 * Subscriber for Program related events.  .
 * 
 * @author your_name_here
 *
 */
@Component("program-subscriber")
public class ProgramSubscriber extends BaseSubscriber {

	public ProgramSubscriber() {
		queryGateway = applicationContext.getBean(QueryGateway.class);
	}
	
    public SubscriptionQueryResult<List<Program>, Program> programSubscribe() {
        return queryGateway
                .subscriptionQuery(new FindAllProgramQuery(), 
                		ResponseTypes.multipleInstancesOf(Program.class),
                		ResponseTypes.instanceOf(Program.class));
    }

    public SubscriptionQueryResult<Program, Program> programSubscribe(@DestinationVariable UUID programId) {
        return queryGateway
                .subscriptionQuery(new FindProgramQuery(new LoadProgramFilter(programId)), 
                		ResponseTypes.instanceOf(Program.class),
                		ResponseTypes.instanceOf(Program.class));
    }




    // -------------------------------------------------
    // attributes
    // -------------------------------------------------
	@Autowired
    private final QueryGateway queryGateway;
}

