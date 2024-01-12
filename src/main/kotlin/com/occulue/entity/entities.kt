/*******************************************************************************
  Turnstone Biologics Confidential
  
  2018 Turnstone Biologics
  All Rights Reserved.
  
  This file is subject to the terms and conditions defined in
  file 'license.txt', which is part of this source code package.
   
  Contributors :
        Turnstone Biologics - General Release
 ******************************************************************************/
package com.occulue.entity;

import java.util.UUID

import javax.persistence.*
import javax.persistence.NamedQueries
import javax.persistence.NamedQuery

import com.occulue.api.*;

// --------------------------------------------
// Entity Definitions
// --------------------------------------------
@Entity
data class Event(
    @Id var eventId: UUID? = null,
    var createdDateTime:  Date? = null,
    var modificationDateTime:  Date? = null,
    var eventName: String? = null,
    var priority: Int? = null,
     @Embedded     @AttributeOverrides(
    )
    var intervalPeriod:  String? = null,
    @OneToOne(fetch = FetchType.EAGER) @JoinColumn(name = "program") var program: Program? = null,
    @OneToOne(fetch = FetchType.EAGER) @JoinColumn(name = "targets") var targets: ValuesMape? = null,
    @OneToMany(fetch = FetchType.EAGER) @JoinColumn(name = "reportDescriptors") var reportDescriptors:  Set<ReportDescriptor>? = null,
    @OneToMany(fetch = FetchType.EAGER) @JoinColumn(name = "payloadDescriptors") var payloadDescriptors:  Set<PayloadDescriptor>? = null,
     @Embedded     @AttributeOverrides(
    )
    var intervals:  Set<Interval>? = null,
    @Enumerated(EnumType.STRING) var objectType: ObjectType? = null
)

@Entity
data class EventPayloadDescriptor(
    @Id var eventPayloadDescriptorId: UUID? = null,
    var payloadType: String? = null,
    @Enumerated(EnumType.STRING) var objectType: ObjectType? = null,
    var units: String? = null,
    var currency: String? = null
)

@Entity
data class Notification(
    @Id var notificationId: UUID? = null,
     @Embedded     @AttributeOverrides(
    )
    var targets: ValuesMap? = null,
    @Enumerated(EnumType.STRING) var objectType: ObjectType? = null,
    @Enumerated(EnumType.STRING) var operation: Operation? = null,
    @OneToOne(fetch = FetchType.EAGER) @JoinColumn(name = "notifier") var notifier: Notifier? = null
)

@Entity
data class Notifier(
     var notifierId: UUID? = null
)

@Entity
data class ObjectOperation(
    @Id var objectOperationId: UUID? = null,
    var callbackUrl: String? = null,
    var bearerToken: String? = null,
    @Enumerated(EnumType.STRING) var objects: ObjectType? = null,
    @ElementCollection (fetch = FetchType.EAGER)var operations:  Set<Operations>? = null
)

@Entity
data class PayloadDescriptor(
    @Id var payloadDescriptorId: UUID? = null,
    var payloadType: String? = null,
    @Enumerated(EnumType.STRING) var objectType: ObjectType? = null
)

@Entity
data class Problem(
    @Id var problemId: UUID? = null,
    var type: String? = null,
    var title: String? = null,
    var status: Int? = null,
    var detail: String? = null,
    var instance: String? = null
)

@Entity
data class Program(
    @Id var programId: UUID? = null,
    var createdDateTime:  Date? = null,
    var modificationDateTime:  Date? = null,
    var programName: String? = null,
    var programLongName: String? = null,
    var retailerName: String? = null,
    var retailerLongName: String? = null,
    var programType: String? = null,
    var country: String? = null,
    var principalSubdivision: String? = null,
     @Embedded     @AttributeOverrides(
    )
    var timeZoneOffset:  String? = null,
     @Embedded     @AttributeOverrides(
    )
    var intervalPeriod:  String? = null,
    var programDescriptions: String? = null,
    var bindingEvents: Boolean? = null,
    var localPrice: Boolean? = null,
    @OneToMany(fetch = FetchType.EAGER) @JoinColumn(name = "payloadDescriptors") var payloadDescriptors:  Set<PayloadDescriptor>? = null,
    @ElementCollection (fetch = FetchType.EAGER)var targets:  Set<ValueMap>? = null,
    @Enumerated(EnumType.STRING) var objectType: ObjectType? = null
)

@Entity
data class Report(
    @Id var reportId: UUID? = null,
    var createdDateTime:  Date? = null,
    var modificationDateTime:  Date? = null,
    var clientName: String? = null,
    var reportName: String? = null,
     @Embedded     @AttributeOverrides(
    )
    var intervalPeriod:  String? = null,
    @OneToOne(fetch = FetchType.EAGER) @JoinColumn(name = "program") var program: Program? = null,
    @OneToOne(fetch = FetchType.EAGER) @JoinColumn(name = "event") var event: Event? = null,
    @OneToMany(fetch = FetchType.EAGER) @JoinColumn(name = "payloadDescriptors") var payloadDescriptors:  Set<PayloadDescriptor>? = null,
    @OneToMany(fetch = FetchType.EAGER) @JoinColumn(name = "resources") var resources:  Set<Resource>? = null,
     @Embedded     @AttributeOverrides(
    )
    var intervals: Interval? = null,
    @Enumerated(EnumType.STRING) var objectType: ObjectType? = null
)

@Entity
data class ReportDescriptor(
    @Id var reportDescriptorId: UUID? = null,
    var payloadType: String? = null,
    var readingType: String? = null,
    var units: String? = null,
    var aggregate: Boolean? = null,
    var startInterval: Int? = null,
    var numIntervals: Int? = null,
    var historical: Boolean? = null,
    var frequency: Int? = null,
    var repeat: Int? = null,
     @Embedded     @AttributeOverrides(
    )
    var targets: ValuesMap? = null
)

@Entity
data class ReportPayloadDescriptor(
    @Id var reportPayloadDescriptorId: UUID? = null,
    var payloadType: String? = null,
    @Enumerated(EnumType.STRING) var objectType: ObjectType? = null,
    var readingType: String? = null,
    var units: String? = null,
    var accuracy: Float? = null,
    var confidence: Int? = null
)

@Entity
data class Resource(
    @Id var resourceId: UUID? = null,
    var createdDateTime:  Date? = null,
    var modificationDateTime:  Date? = null,
    var resourceName: String? = null,
    @OneToOne(fetch = FetchType.EAGER) @JoinColumn(name = "ven") var ven: Ven? = null,
    @ElementCollection (fetch = FetchType.EAGER)var attributes:  Set<ValuesMape>? = null,
    @ElementCollection (fetch = FetchType.EAGER)var targets:  Set<ValuesMape>? = null,
    @Enumerated(EnumType.STRING) var objectType: ObjectType? = null
)

@Entity
data class Subscription(
    @Id var subscriptionId: UUID? = null,
    var createdDateTime:  Date? = null,
    var modificationDateTime:  Date? = null,
    var clientName: String? = null,
    @OneToOne(fetch = FetchType.EAGER) @JoinColumn(name = "program") var program: Program? = null,
    @OneToMany(fetch = FetchType.EAGER) @JoinColumn(name = "objectOperations") var objectOperations:  Set<ObjectOperation>? = null,
     @Embedded     @AttributeOverrides(
    )
    var targets: ValuesMap? = null,
    @Enumerated(EnumType.STRING) var objectType: ObjectType? = null
)

@Entity
data class Ven(
    @Id var venId: UUID? = null,
    var createdDateTime:  Date? = null,
    var modificationDateTime:  Date? = null,
    var venName: String? = null,
    @ElementCollection (fetch = FetchType.EAGER)var attributes:  Set<ValueMap>? = null,
    @ElementCollection (fetch = FetchType.EAGER)var targets:  Set<ValueMap>? = null,
    @OneToMany(fetch = FetchType.EAGER) @JoinColumn(name = "resources") var resources:  Set<Resource>? = null,
    @Enumerated(EnumType.STRING) var objectType: ObjectType? = null
)

