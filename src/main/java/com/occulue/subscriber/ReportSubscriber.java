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
 * Subscriber for Report related events.  .
 * 
 * @author your_name_here
 *
 */
@Component("report-subscriber")
public class ReportSubscriber extends BaseSubscriber {

	public ReportSubscriber() {
		queryGateway = applicationContext.getBean(QueryGateway.class);
	}
	
    public SubscriptionQueryResult<List<Report>, Report> reportSubscribe() {
        return queryGateway
                .subscriptionQuery(new FindAllReportQuery(), 
                		ResponseTypes.multipleInstancesOf(Report.class),
                		ResponseTypes.instanceOf(Report.class));
    }

    public SubscriptionQueryResult<Report, Report> reportSubscribe(@DestinationVariable UUID reportId) {
        return queryGateway
                .subscriptionQuery(new FindReportQuery(new LoadReportFilter(reportId)), 
                		ResponseTypes.instanceOf(Report.class),
                		ResponseTypes.instanceOf(Report.class));
    }




    // -------------------------------------------------
    // attributes
    // -------------------------------------------------
	@Autowired
    private final QueryGateway queryGateway;
}

