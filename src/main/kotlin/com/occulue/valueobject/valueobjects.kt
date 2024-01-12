/*******************************************************************************
  Turnstone Biologics Confidential
  
  2018 Turnstone Biologics
  All Rights Reserved.
  
  This file is subject to the terms and conditions defined in
  file 'license.txt', which is part of this source code package.
   
  Contributors :
        Turnstone Biologics - General Release
 ******************************************************************************/
package com.occulue.api;

import java.util.*


// --------------------------------------------
// Valueobject Definitions
// --------------------------------------------

data class DateTime(
    var value: String? = null
)

data class Duration(
    var value: String? = null
)

data class Interval(
         @AttributeOverrides(
      AttributeOverride( name = "intervalPeriod", column = Column(name = "intervalPeriod_intervalPeriod"))
    )
    var intervalPeriod:  String? = null
)

data class IntervalPeriod(
    var start:  Date? = null,
         @AttributeOverrides(
      AttributeOverride( name = "start", column = Column(name = "duration_start")),
      AttributeOverride( name = "duration", column = Column(name = "duration_duration")),
      AttributeOverride( name = "randomizeStart", column = Column(name = "duration_randomizeStart"))
    )
    var duration:  String? = null,
         @AttributeOverrides(
      AttributeOverride( name = "start", column = Column(name = "randomizeStart_start")),
      AttributeOverride( name = "duration", column = Column(name = "randomizeStart_duration")),
      AttributeOverride( name = "randomizeStart", column = Column(name = "randomizeStart_randomizeStart"))
    )
    var randomizeStart:  String? = null
)

data class Point(
    var x: Float? = null,
    var y: Float? = null
)

data class ValuesMap(
    var type: String? = null,
    var values: String? = null
)

data class ValuesMap(
    var type: String? = null,
    var values: String? = null
)





