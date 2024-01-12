/*******************************************************************************
  Turnstone Biologics Confidential
  
  2018 Turnstone Biologics
  All Rights Reserved.
  
  This file is subject to the terms and conditions defined in
  file 'license.txt', which is part of this source code package.
   
  Contributors :
        Turnstone Biologics - General Release
 ******************************************************************************/
package com.occulue.validator;

import org.springframework.util.Assert;

import com.occulue.api.*;

public class ReportDescriptorValidator {
		
	/**
	 * default constructor
	 */
	protected ReportDescriptorValidator() {	
	}
	
	/**
	 * factory method
	 */
	static public ReportDescriptorValidator getInstance() {
		return new ReportDescriptorValidator();
	}
		
	/**
	 * handles creation validation for a ReportDescriptor
	 */
	public void validate( CreateReportDescriptorCommand reportDescriptor )throws Exception {
		Assert.notNull( reportDescriptor, "CreateReportDescriptorCommand should not be null" );
//		Assert.isNull( reportDescriptor.getReportDescriptorId(), "CreateReportDescriptorCommand identifier should be null" );
		Assert.notNull( reportDescriptor.getPayloadType(), "Field CreateReportDescriptorCommand.payloadType should not be null" );
		Assert.notNull( reportDescriptor.getReadingType(), "Field CreateReportDescriptorCommand.readingType should not be null" );
		Assert.notNull( reportDescriptor.getUnits(), "Field CreateReportDescriptorCommand.units should not be null" );
		Assert.notNull( reportDescriptor.getStartInterval(), "Field CreateReportDescriptorCommand.startInterval should not be null" );
		Assert.notNull( reportDescriptor.getNumIntervals(), "Field CreateReportDescriptorCommand.numIntervals should not be null" );
		Assert.notNull( reportDescriptor.getFrequency(), "Field CreateReportDescriptorCommand.frequency should not be null" );
		Assert.notNull( reportDescriptor.getRepeat(), "Field CreateReportDescriptorCommand.repeat should not be null" );
	}

	/**
	 * handles update validation for a ReportDescriptor
	 */
	public void validate( UpdateReportDescriptorCommand reportDescriptor ) throws Exception {
		Assert.notNull( reportDescriptor, "UpdateReportDescriptorCommand should not be null" );
		Assert.notNull( reportDescriptor.getReportDescriptorId(), "UpdateReportDescriptorCommand identifier should not be null" );
		Assert.notNull( reportDescriptor.getPayloadType(), "Field UpdateReportDescriptorCommand.payloadType should not be null" );
		Assert.notNull( reportDescriptor.getReadingType(), "Field UpdateReportDescriptorCommand.readingType should not be null" );
		Assert.notNull( reportDescriptor.getUnits(), "Field UpdateReportDescriptorCommand.units should not be null" );
		Assert.notNull( reportDescriptor.getStartInterval(), "Field UpdateReportDescriptorCommand.startInterval should not be null" );
		Assert.notNull( reportDescriptor.getNumIntervals(), "Field UpdateReportDescriptorCommand.numIntervals should not be null" );
		Assert.notNull( reportDescriptor.getFrequency(), "Field UpdateReportDescriptorCommand.frequency should not be null" );
		Assert.notNull( reportDescriptor.getRepeat(), "Field UpdateReportDescriptorCommand.repeat should not be null" );
    }

	/**
	 * handles delete validation for a ReportDescriptor
	 */
    public void validate( DeleteReportDescriptorCommand reportDescriptor ) throws Exception {
		Assert.notNull( reportDescriptor, "{commandAlias} should not be null" );
		Assert.notNull( reportDescriptor.getReportDescriptorId(), "DeleteReportDescriptorCommand identifier should not be null" );
	}
	
	/**
	 * handles fetchOne validation for a ReportDescriptor
	 */
	public void validate( ReportDescriptorFetchOneSummary summary ) throws Exception {
		Assert.notNull( summary, "ReportDescriptorFetchOneSummary should not be null" );
		Assert.notNull( summary.getReportDescriptorId(), "ReportDescriptorFetchOneSummary identifier should not be null" );
	}



}
