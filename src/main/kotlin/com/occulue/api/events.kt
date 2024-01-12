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

import java.time.Instant
import java.util.*

import javax.persistence.*

import com.occulue.entity.*;


//-----------------------------------------------------------
// Event definitions
//-----------------------------------------------------------

// Event Events

data class CreateEventEvent(
    @Id var eventId: UUID? = null,
    var createdDateTime:  Date? = null,
    var modificationDateTime:  Date? = null,
    var eventName: String? = null,
    var priority: Int? = null,
         @AttributeOverrides(
      AttributeOverride( name = "start", column = Column(name = "intervalPeriod_start")),
      AttributeOverride( name = "duration", column = Column(name = "intervalPeriod_duration")),
      AttributeOverride( name = "randomizeStart", column = Column(name = "intervalPeriod_randomizeStart"))
    )
    var intervalPeriod:  String? = null,
         @AttributeOverrides(
      AttributeOverride( name = "start", column = Column(name = "intervals_start")),
      AttributeOverride( name = "duration", column = Column(name = "intervals_duration")),
      AttributeOverride( name = "randomizeStart", column = Column(name = "intervals_randomizeStart"))
    )
    var intervals:  Set<Interval>? = null,
    @Enumerated(EnumType.STRING) var objectType: ObjectType? = null
)

data class UpdateEventEvent(
    @Id var eventId: UUID? = null,
    var createdDateTime:  Date? = null,
    var modificationDateTime:  Date? = null,
    var eventName: String? = null,
    var priority: Int? = null,
         @AttributeOverrides(
      AttributeOverride( name = "start", column = Column(name = "intervalPeriod_start")),
      AttributeOverride( name = "duration", column = Column(name = "intervalPeriod_duration")),
      AttributeOverride( name = "randomizeStart", column = Column(name = "intervalPeriod_randomizeStart"))
    )
    var intervalPeriod:  String? = null,
    var program: Program? = null,
    var targets: ValuesMape? = null,
    var reportDescriptors:  Set<ReportDescriptor>? = null,
    var payloadDescriptors:  Set<PayloadDescriptor>? = null,
         @AttributeOverrides(
      AttributeOverride( name = "start", column = Column(name = "intervals_start")),
      AttributeOverride( name = "duration", column = Column(name = "intervals_duration")),
      AttributeOverride( name = "randomizeStart", column = Column(name = "intervals_randomizeStart"))
    )
    var intervals:  Set<Interval>? = null,
    @Enumerated(EnumType.STRING) var objectType: ObjectType? = null
)

data class DeleteEventEvent(@Id val eventId: UUID? = null)

// single association events
data class AssignProgramToEventEvent(@Id val eventId: UUID, val assignment: Program )
data class UnAssignProgramFromEventEvent(@Id val eventId: UUID? = null )

// multiple association events
data class AssignReportDescriptorsToEventEvent(@Id val eventId: UUID, val addTo: ReportDescriptor )
data class RemoveReportDescriptorsFromEventEvent(@Id val eventId: UUID, val removeFrom: ReportDescriptor )
data class AssignPayloadDescriptorsToEventEvent(@Id val eventId: UUID, val addTo: PayloadDescriptor )
data class RemovePayloadDescriptorsFromEventEvent(@Id val eventId: UUID, val removeFrom: PayloadDescriptor )
data class AssignIntervalsToEventEvent(@Id val eventId: UUID, val addTo: Interval )
data class RemoveIntervalsFromEventEvent(@Id val eventId: UUID, val removeFrom: Interval )


// EventPayloadDescriptor Events

data class CreateEventPayloadDescriptorEvent(
    @Id var eventPayloadDescriptorId: UUID? = null,
    var units: String? = null,
    var currency: String? = null
)

data class UpdateEventPayloadDescriptorEvent(
    @Id var eventPayloadDescriptorId: UUID? = null,
    var payloadType: String? = null,
    @Enumerated(EnumType.STRING) var objectType: ObjectType? = null,
    var units: String? = null,
    var currency: String? = null
)

data class DeleteEventPayloadDescriptorEvent(@Id val eventPayloadDescriptorId: UUID? = null)

// single association events

// multiple association events


// Notification Events

data class CreateNotificationEvent(
    @Id var notificationId: UUID? = null,
         @AttributeOverrides(
      AttributeOverride( name = "start", column = Column(name = "targets_start")),
      AttributeOverride( name = "duration", column = Column(name = "targets_duration")),
      AttributeOverride( name = "randomizeStart", column = Column(name = "targets_randomizeStart"))
    )
    var targets: ValuesMap? = null,
    @Enumerated(EnumType.STRING) var objectType: ObjectType? = null,
    @Enumerated(EnumType.STRING) var operation: Operation? = null
)

data class UpdateNotificationEvent(
    @Id var notificationId: UUID? = null,
         @AttributeOverrides(
      AttributeOverride( name = "start", column = Column(name = "targets_start")),
      AttributeOverride( name = "duration", column = Column(name = "targets_duration")),
      AttributeOverride( name = "randomizeStart", column = Column(name = "targets_randomizeStart"))
    )
    var targets: ValuesMap? = null,
    @Enumerated(EnumType.STRING) var objectType: ObjectType? = null,
    @Enumerated(EnumType.STRING) var operation: Operation? = null,
    var notifier: Notifier? = null
)

data class DeleteNotificationEvent(@Id val notificationId: UUID? = null)

// single association events
data class AssignNotifierToNotificationEvent(@Id val notificationId: UUID, val assignment: Notifier )
data class UnAssignNotifierFromNotificationEvent(@Id val notificationId: UUID? = null )

// multiple association events


// Notifier Events

data class CreateNotifierEvent(
     var notifierId: UUID? = null
)

data class UpdateNotifierEvent(
     var notifierId: UUID? = null
)

data class DeleteNotifierEvent(@Id val notifierId: UUID? = null)

// single association events

// multiple association events


// ObjectOperation Events

data class CreateObjectOperationEvent(
    @Id var objectOperationId: UUID? = null,
    var callbackUrl: String? = null,
    var bearerToken: String? = null,
    @Enumerated(EnumType.STRING) var objects: ObjectType? = null
)

data class UpdateObjectOperationEvent(
    @Id var objectOperationId: UUID? = null,
    var callbackUrl: String? = null,
    var bearerToken: String? = null,
    @Enumerated(EnumType.STRING) var objects: ObjectType? = null,
    var operations:  Set<Operations>? = null
)

data class DeleteObjectOperationEvent(@Id val objectOperationId: UUID? = null)

// single association events

// multiple association events
data class AssignOperationsToObjectOperationEvent(@Id val objectOperationId: UUID, val addTo: Operations )
data class RemoveOperationsFromObjectOperationEvent(@Id val objectOperationId: UUID, val removeFrom: Operations )


// PayloadDescriptor Events

data class CreatePayloadDescriptorEvent(
    @Id var payloadDescriptorId: UUID? = null,
    var payloadType: String? = null,
    @Enumerated(EnumType.STRING) var objectType: ObjectType? = null
)

data class UpdatePayloadDescriptorEvent(
    @Id var payloadDescriptorId: UUID? = null,
    var payloadType: String? = null,
    @Enumerated(EnumType.STRING) var objectType: ObjectType? = null
)

data class DeletePayloadDescriptorEvent(@Id val payloadDescriptorId: UUID? = null)

// single association events

// multiple association events


// Problem Events

data class CreateProblemEvent(
    @Id var problemId: UUID? = null,
    var type: String? = null,
    var title: String? = null,
    var status: Int? = null,
    var detail: String? = null,
    var instance: String? = null
)

data class UpdateProblemEvent(
    @Id var problemId: UUID? = null,
    var type: String? = null,
    var title: String? = null,
    var status: Int? = null,
    var detail: String? = null,
    var instance: String? = null
)

data class DeleteProblemEvent(@Id val problemId: UUID? = null)

// single association events

// multiple association events


// Program Events

data class CreateProgramEvent(
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
         @AttributeOverrides(
      AttributeOverride( name = "start", column = Column(name = "timeZoneOffset_start")),
      AttributeOverride( name = "duration", column = Column(name = "timeZoneOffset_duration")),
      AttributeOverride( name = "randomizeStart", column = Column(name = "timeZoneOffset_randomizeStart"))
    )
    var timeZoneOffset:  String? = null,
         @AttributeOverrides(
      AttributeOverride( name = "start", column = Column(name = "intervalPeriod_start")),
      AttributeOverride( name = "duration", column = Column(name = "intervalPeriod_duration")),
      AttributeOverride( name = "randomizeStart", column = Column(name = "intervalPeriod_randomizeStart"))
    )
    var intervalPeriod:  String? = null,
    var programDescriptions: String? = null,
    var bindingEvents: Boolean? = null,
    var localPrice: Boolean? = null,
    @Enumerated(EnumType.STRING) var objectType: ObjectType? = null
)

data class UpdateProgramEvent(
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
         @AttributeOverrides(
      AttributeOverride( name = "start", column = Column(name = "timeZoneOffset_start")),
      AttributeOverride( name = "duration", column = Column(name = "timeZoneOffset_duration")),
      AttributeOverride( name = "randomizeStart", column = Column(name = "timeZoneOffset_randomizeStart"))
    )
    var timeZoneOffset:  String? = null,
         @AttributeOverrides(
      AttributeOverride( name = "start", column = Column(name = "intervalPeriod_start")),
      AttributeOverride( name = "duration", column = Column(name = "intervalPeriod_duration")),
      AttributeOverride( name = "randomizeStart", column = Column(name = "intervalPeriod_randomizeStart"))
    )
    var intervalPeriod:  String? = null,
    var programDescriptions: String? = null,
    var bindingEvents: Boolean? = null,
    var localPrice: Boolean? = null,
    var payloadDescriptors:  Set<PayloadDescriptor>? = null,
    var targets:  Set<ValueMap>? = null,
    @Enumerated(EnumType.STRING) var objectType: ObjectType? = null
)

data class DeleteProgramEvent(@Id val programId: UUID? = null)

// single association events

// multiple association events
data class AssignPayloadDescriptorsToProgramEvent(@Id val programId: UUID, val addTo: PayloadDescriptor )
data class RemovePayloadDescriptorsFromProgramEvent(@Id val programId: UUID, val removeFrom: PayloadDescriptor )
data class AssignTargetsToProgramEvent(@Id val programId: UUID, val addTo: ValueMap )
data class RemoveTargetsFromProgramEvent(@Id val programId: UUID, val removeFrom: ValueMap )


// Report Events

data class CreateReportEvent(
    @Id var reportId: UUID? = null,
    var createdDateTime:  Date? = null,
    var modificationDateTime:  Date? = null,
    var clientName: String? = null,
    var reportName: String? = null,
         @AttributeOverrides(
      AttributeOverride( name = "start", column = Column(name = "intervalPeriod_start")),
      AttributeOverride( name = "duration", column = Column(name = "intervalPeriod_duration")),
      AttributeOverride( name = "randomizeStart", column = Column(name = "intervalPeriod_randomizeStart"))
    )
    var intervalPeriod:  String? = null,
         @AttributeOverrides(
      AttributeOverride( name = "start", column = Column(name = "intervals_start")),
      AttributeOverride( name = "duration", column = Column(name = "intervals_duration")),
      AttributeOverride( name = "randomizeStart", column = Column(name = "intervals_randomizeStart"))
    )
    var intervals: Interval? = null,
    @Enumerated(EnumType.STRING) var objectType: ObjectType? = null
)

data class UpdateReportEvent(
    @Id var reportId: UUID? = null,
    var createdDateTime:  Date? = null,
    var modificationDateTime:  Date? = null,
    var clientName: String? = null,
    var reportName: String? = null,
         @AttributeOverrides(
      AttributeOverride( name = "start", column = Column(name = "intervalPeriod_start")),
      AttributeOverride( name = "duration", column = Column(name = "intervalPeriod_duration")),
      AttributeOverride( name = "randomizeStart", column = Column(name = "intervalPeriod_randomizeStart"))
    )
    var intervalPeriod:  String? = null,
    var program: Program? = null,
    var event: Event? = null,
    var payloadDescriptors:  Set<PayloadDescriptor>? = null,
    var resources:  Set<Resource>? = null,
         @AttributeOverrides(
      AttributeOverride( name = "start", column = Column(name = "intervals_start")),
      AttributeOverride( name = "duration", column = Column(name = "intervals_duration")),
      AttributeOverride( name = "randomizeStart", column = Column(name = "intervals_randomizeStart"))
    )
    var intervals: Interval? = null,
    @Enumerated(EnumType.STRING) var objectType: ObjectType? = null
)

data class DeleteReportEvent(@Id val reportId: UUID? = null)

// single association events
data class AssignProgramToReportEvent(@Id val reportId: UUID, val assignment: Program )
data class UnAssignProgramFromReportEvent(@Id val reportId: UUID? = null )
data class AssignEventToReportEvent(@Id val reportId: UUID, val assignment: Event )
data class UnAssignEventFromReportEvent(@Id val reportId: UUID? = null )

// multiple association events
data class AssignPayloadDescriptorsToReportEvent(@Id val reportId: UUID, val addTo: PayloadDescriptor )
data class RemovePayloadDescriptorsFromReportEvent(@Id val reportId: UUID, val removeFrom: PayloadDescriptor )
data class AssignResourcesToReportEvent(@Id val reportId: UUID, val addTo: Resource )
data class RemoveResourcesFromReportEvent(@Id val reportId: UUID, val removeFrom: Resource )


// ReportDescriptor Events

data class CreateReportDescriptorEvent(
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
         @AttributeOverrides(
      AttributeOverride( name = "start", column = Column(name = "targets_start")),
      AttributeOverride( name = "duration", column = Column(name = "targets_duration")),
      AttributeOverride( name = "randomizeStart", column = Column(name = "targets_randomizeStart"))
    )
    var targets: ValuesMap? = null
)

data class UpdateReportDescriptorEvent(
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
         @AttributeOverrides(
      AttributeOverride( name = "start", column = Column(name = "targets_start")),
      AttributeOverride( name = "duration", column = Column(name = "targets_duration")),
      AttributeOverride( name = "randomizeStart", column = Column(name = "targets_randomizeStart"))
    )
    var targets: ValuesMap? = null
)

data class DeleteReportDescriptorEvent(@Id val reportDescriptorId: UUID? = null)

// single association events

// multiple association events


// ReportPayloadDescriptor Events

data class CreateReportPayloadDescriptorEvent(
    @Id var reportPayloadDescriptorId: UUID? = null,
    var readingType: String? = null,
    var units: String? = null,
    var accuracy: Float? = null,
    var confidence: Int? = null
)

data class UpdateReportPayloadDescriptorEvent(
    @Id var reportPayloadDescriptorId: UUID? = null,
    var payloadType: String? = null,
    @Enumerated(EnumType.STRING) var objectType: ObjectType? = null,
    var readingType: String? = null,
    var units: String? = null,
    var accuracy: Float? = null,
    var confidence: Int? = null
)

data class DeleteReportPayloadDescriptorEvent(@Id val reportPayloadDescriptorId: UUID? = null)

// single association events

// multiple association events


// Resource Events

data class CreateResourceEvent(
    @Id var resourceId: UUID? = null,
    var createdDateTime:  Date? = null,
    var modificationDateTime:  Date? = null,
    var resourceName: String? = null,
    @Enumerated(EnumType.STRING) var objectType: ObjectType? = null
)

data class UpdateResourceEvent(
    @Id var resourceId: UUID? = null,
    var createdDateTime:  Date? = null,
    var modificationDateTime:  Date? = null,
    var resourceName: String? = null,
    var ven: Ven? = null,
    var attributes:  Set<ValuesMape>? = null,
    var targets:  Set<ValuesMape>? = null,
    @Enumerated(EnumType.STRING) var objectType: ObjectType? = null
)

data class DeleteResourceEvent(@Id val resourceId: UUID? = null)

// single association events
data class AssignVenToResourceEvent(@Id val resourceId: UUID, val assignment: Ven )
data class UnAssignVenFromResourceEvent(@Id val resourceId: UUID? = null )

// multiple association events
data class AssignAttributesToResourceEvent(@Id val resourceId: UUID, val addTo: ValuesMape )
data class RemoveAttributesFromResourceEvent(@Id val resourceId: UUID, val removeFrom: ValuesMape )
data class AssignTargetsToResourceEvent(@Id val resourceId: UUID, val addTo: ValuesMape )
data class RemoveTargetsFromResourceEvent(@Id val resourceId: UUID, val removeFrom: ValuesMape )


// Subscription Events

data class CreateSubscriptionEvent(
    @Id var subscriptionId: UUID? = null,
    var createdDateTime:  Date? = null,
    var modificationDateTime:  Date? = null,
    var clientName: String? = null,
         @AttributeOverrides(
      AttributeOverride( name = "start", column = Column(name = "targets_start")),
      AttributeOverride( name = "duration", column = Column(name = "targets_duration")),
      AttributeOverride( name = "randomizeStart", column = Column(name = "targets_randomizeStart"))
    )
    var targets: ValuesMap? = null,
    @Enumerated(EnumType.STRING) var objectType: ObjectType? = null
)

data class UpdateSubscriptionEvent(
    @Id var subscriptionId: UUID? = null,
    var createdDateTime:  Date? = null,
    var modificationDateTime:  Date? = null,
    var clientName: String? = null,
    var program: Program? = null,
    var objectOperations:  Set<ObjectOperation>? = null,
         @AttributeOverrides(
      AttributeOverride( name = "start", column = Column(name = "targets_start")),
      AttributeOverride( name = "duration", column = Column(name = "targets_duration")),
      AttributeOverride( name = "randomizeStart", column = Column(name = "targets_randomizeStart"))
    )
    var targets: ValuesMap? = null,
    @Enumerated(EnumType.STRING) var objectType: ObjectType? = null
)

data class DeleteSubscriptionEvent(@Id val subscriptionId: UUID? = null)

// single association events
data class AssignProgramToSubscriptionEvent(@Id val subscriptionId: UUID, val assignment: Program )
data class UnAssignProgramFromSubscriptionEvent(@Id val subscriptionId: UUID? = null )

// multiple association events
data class AssignObjectOperationsToSubscriptionEvent(@Id val subscriptionId: UUID, val addTo: ObjectOperation )
data class RemoveObjectOperationsFromSubscriptionEvent(@Id val subscriptionId: UUID, val removeFrom: ObjectOperation )


// Ven Events

data class CreateVenEvent(
    @Id var venId: UUID? = null,
    var createdDateTime:  Date? = null,
    var modificationDateTime:  Date? = null,
    var venName: String? = null,
    @Enumerated(EnumType.STRING) var objectType: ObjectType? = null
)

data class UpdateVenEvent(
    @Id var venId: UUID? = null,
    var createdDateTime:  Date? = null,
    var modificationDateTime:  Date? = null,
    var venName: String? = null,
    var attributes:  Set<ValueMap>? = null,
    var targets:  Set<ValueMap>? = null,
    var resources:  Set<Resource>? = null,
    @Enumerated(EnumType.STRING) var objectType: ObjectType? = null
)

data class DeleteVenEvent(@Id val venId: UUID? = null)

// single association events

// multiple association events
data class AssignAttributesToVenEvent(@Id val venId: UUID, val addTo: ValueMap )
data class RemoveAttributesFromVenEvent(@Id val venId: UUID, val removeFrom: ValueMap )
data class AssignTargetsToVenEvent(@Id val venId: UUID, val addTo: ValueMap )
data class RemoveTargetsFromVenEvent(@Id val venId: UUID, val removeFrom: ValueMap )
data class AssignResourcesToVenEvent(@Id val venId: UUID, val addTo: Resource )
data class RemoveResourcesFromVenEvent(@Id val venId: UUID, val removeFrom: Resource )



