/*******************************************************************************
  Turnstone Biologics Confidential
  
  2018 Turnstone Biologics
  All Rights Reserved.
  
  This file is subject to the terms and conditions defined in
  file 'license.txt', which is part of this source code package.
   
  Contributors :
        Turnstone Biologics - General Release
 ******************************************************************************/
package com.occulue.api

import org.axonframework.modelling.command.TargetAggregateIdentifier


import java.util.*
import javax.persistence.*

import com.occulue.entity.*;

//-----------------------------------------------------------
// Command definitions
//-----------------------------------------------------------

// Event Commands
data class CreateEventCommand(
    @TargetAggregateIdentifier var eventId: UUID? = null,
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

data class UpdateEventCommand(
    @TargetAggregateIdentifier var eventId: UUID? = null,
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

data class DeleteEventCommand(@TargetAggregateIdentifier  var eventId: UUID? = null)

// single association commands
data class AssignProgramToEventCommand(@TargetAggregateIdentifier  val eventId: UUID, val assignment: Program )
data class UnAssignProgramFromEventCommand(@TargetAggregateIdentifier  val eventId: UUID )

// multiple association commands
data class AssignReportDescriptorsToEventCommand(@TargetAggregateIdentifier  val eventId: UUID, val addTo: ReportDescriptor )
data class RemoveReportDescriptorsFromEventCommand(@TargetAggregateIdentifier  val eventId: UUID, val removeFrom: ReportDescriptor )
data class AssignPayloadDescriptorsToEventCommand(@TargetAggregateIdentifier  val eventId: UUID, val addTo: PayloadDescriptor )
data class RemovePayloadDescriptorsFromEventCommand(@TargetAggregateIdentifier  val eventId: UUID, val removeFrom: PayloadDescriptor )
data class AssignIntervalsToEventCommand(@TargetAggregateIdentifier  val eventId: UUID, val addTo: Interval )
data class RemoveIntervalsFromEventCommand(@TargetAggregateIdentifier  val eventId: UUID, val removeFrom: Interval )


// EventPayloadDescriptor Commands
data class CreateEventPayloadDescriptorCommand(
    @TargetAggregateIdentifier var eventPayloadDescriptorId: UUID? = null,
    var units: String? = null,
    var currency: String? = null
)

data class UpdateEventPayloadDescriptorCommand(
    @TargetAggregateIdentifier var eventPayloadDescriptorId: UUID? = null,
    var payloadType: String? = null,
    @Enumerated(EnumType.STRING) var objectType: ObjectType? = null,
    var units: String? = null,
    var currency: String? = null
)

data class DeleteEventPayloadDescriptorCommand(@TargetAggregateIdentifier  var eventPayloadDescriptorId: UUID? = null)

// single association commands

// multiple association commands


// Notification Commands
data class CreateNotificationCommand(
    @TargetAggregateIdentifier var notificationId: UUID? = null,
         @AttributeOverrides(
      AttributeOverride( name = "start", column = Column(name = "targets_start")),
      AttributeOverride( name = "duration", column = Column(name = "targets_duration")),
      AttributeOverride( name = "randomizeStart", column = Column(name = "targets_randomizeStart"))
    )
    var targets: ValuesMap? = null,
    @Enumerated(EnumType.STRING) var objectType: ObjectType? = null,
    @Enumerated(EnumType.STRING) var operation: Operation? = null
)

data class UpdateNotificationCommand(
    @TargetAggregateIdentifier var notificationId: UUID? = null,
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

data class DeleteNotificationCommand(@TargetAggregateIdentifier  var notificationId: UUID? = null)

// single association commands
data class AssignNotifierToNotificationCommand(@TargetAggregateIdentifier  val notificationId: UUID, val assignment: Notifier )
data class UnAssignNotifierFromNotificationCommand(@TargetAggregateIdentifier  val notificationId: UUID )

// multiple association commands


// Notifier Commands
data class CreateNotifierCommand(
    @TargetAggregateIdentifier  var notifierId: UUID? = null
)

data class UpdateNotifierCommand(
    @TargetAggregateIdentifier  var notifierId: UUID? = null
)

data class DeleteNotifierCommand(@TargetAggregateIdentifier  var notifierId: UUID? = null)

// single association commands

// multiple association commands


// ObjectOperation Commands
data class CreateObjectOperationCommand(
    @TargetAggregateIdentifier var objectOperationId: UUID? = null,
    var callbackUrl: String? = null,
    var bearerToken: String? = null,
    @Enumerated(EnumType.STRING) var objects: ObjectType? = null
)

data class UpdateObjectOperationCommand(
    @TargetAggregateIdentifier var objectOperationId: UUID? = null,
    var callbackUrl: String? = null,
    var bearerToken: String? = null,
    @Enumerated(EnumType.STRING) var objects: ObjectType? = null,
    var operations:  Set<Operations>? = null
)

data class DeleteObjectOperationCommand(@TargetAggregateIdentifier  var objectOperationId: UUID? = null)

// single association commands

// multiple association commands
data class AssignOperationsToObjectOperationCommand(@TargetAggregateIdentifier  val objectOperationId: UUID, val addTo: Operations )
data class RemoveOperationsFromObjectOperationCommand(@TargetAggregateIdentifier  val objectOperationId: UUID, val removeFrom: Operations )


// PayloadDescriptor Commands
data class CreatePayloadDescriptorCommand(
    @TargetAggregateIdentifier var payloadDescriptorId: UUID? = null,
    var payloadType: String? = null,
    @Enumerated(EnumType.STRING) var objectType: ObjectType? = null
)

data class UpdatePayloadDescriptorCommand(
    @TargetAggregateIdentifier var payloadDescriptorId: UUID? = null,
    var payloadType: String? = null,
    @Enumerated(EnumType.STRING) var objectType: ObjectType? = null
)

data class DeletePayloadDescriptorCommand(@TargetAggregateIdentifier  var payloadDescriptorId: UUID? = null)

// single association commands

// multiple association commands


// Problem Commands
data class CreateProblemCommand(
    @TargetAggregateIdentifier var problemId: UUID? = null,
    var type: String? = null,
    var title: String? = null,
    var status: Int? = null,
    var detail: String? = null,
    var instance: String? = null
)

data class UpdateProblemCommand(
    @TargetAggregateIdentifier var problemId: UUID? = null,
    var type: String? = null,
    var title: String? = null,
    var status: Int? = null,
    var detail: String? = null,
    var instance: String? = null
)

data class DeleteProblemCommand(@TargetAggregateIdentifier  var problemId: UUID? = null)

// single association commands

// multiple association commands


// Program Commands
data class CreateProgramCommand(
    @TargetAggregateIdentifier var programId: UUID? = null,
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

data class UpdateProgramCommand(
    @TargetAggregateIdentifier var programId: UUID? = null,
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

data class DeleteProgramCommand(@TargetAggregateIdentifier  var programId: UUID? = null)

// single association commands

// multiple association commands
data class AssignPayloadDescriptorsToProgramCommand(@TargetAggregateIdentifier  val programId: UUID, val addTo: PayloadDescriptor )
data class RemovePayloadDescriptorsFromProgramCommand(@TargetAggregateIdentifier  val programId: UUID, val removeFrom: PayloadDescriptor )
data class AssignTargetsToProgramCommand(@TargetAggregateIdentifier  val programId: UUID, val addTo: ValueMap )
data class RemoveTargetsFromProgramCommand(@TargetAggregateIdentifier  val programId: UUID, val removeFrom: ValueMap )


// Report Commands
data class CreateReportCommand(
    @TargetAggregateIdentifier var reportId: UUID? = null,
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

data class UpdateReportCommand(
    @TargetAggregateIdentifier var reportId: UUID? = null,
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

data class DeleteReportCommand(@TargetAggregateIdentifier  var reportId: UUID? = null)

// single association commands
data class AssignProgramToReportCommand(@TargetAggregateIdentifier  val reportId: UUID, val assignment: Program )
data class UnAssignProgramFromReportCommand(@TargetAggregateIdentifier  val reportId: UUID )
data class AssignEventToReportCommand(@TargetAggregateIdentifier  val reportId: UUID, val assignment: Event )
data class UnAssignEventFromReportCommand(@TargetAggregateIdentifier  val reportId: UUID )

// multiple association commands
data class AssignPayloadDescriptorsToReportCommand(@TargetAggregateIdentifier  val reportId: UUID, val addTo: PayloadDescriptor )
data class RemovePayloadDescriptorsFromReportCommand(@TargetAggregateIdentifier  val reportId: UUID, val removeFrom: PayloadDescriptor )
data class AssignResourcesToReportCommand(@TargetAggregateIdentifier  val reportId: UUID, val addTo: Resource )
data class RemoveResourcesFromReportCommand(@TargetAggregateIdentifier  val reportId: UUID, val removeFrom: Resource )


// ReportDescriptor Commands
data class CreateReportDescriptorCommand(
    @TargetAggregateIdentifier var reportDescriptorId: UUID? = null,
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

data class UpdateReportDescriptorCommand(
    @TargetAggregateIdentifier var reportDescriptorId: UUID? = null,
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

data class DeleteReportDescriptorCommand(@TargetAggregateIdentifier  var reportDescriptorId: UUID? = null)

// single association commands

// multiple association commands


// ReportPayloadDescriptor Commands
data class CreateReportPayloadDescriptorCommand(
    @TargetAggregateIdentifier var reportPayloadDescriptorId: UUID? = null,
    var readingType: String? = null,
    var units: String? = null,
    var accuracy: Float? = null,
    var confidence: Int? = null
)

data class UpdateReportPayloadDescriptorCommand(
    @TargetAggregateIdentifier var reportPayloadDescriptorId: UUID? = null,
    var payloadType: String? = null,
    @Enumerated(EnumType.STRING) var objectType: ObjectType? = null,
    var readingType: String? = null,
    var units: String? = null,
    var accuracy: Float? = null,
    var confidence: Int? = null
)

data class DeleteReportPayloadDescriptorCommand(@TargetAggregateIdentifier  var reportPayloadDescriptorId: UUID? = null)

// single association commands

// multiple association commands


// Resource Commands
data class CreateResourceCommand(
    @TargetAggregateIdentifier var resourceId: UUID? = null,
    var createdDateTime:  Date? = null,
    var modificationDateTime:  Date? = null,
    var resourceName: String? = null,
    @Enumerated(EnumType.STRING) var objectType: ObjectType? = null
)

data class UpdateResourceCommand(
    @TargetAggregateIdentifier var resourceId: UUID? = null,
    var createdDateTime:  Date? = null,
    var modificationDateTime:  Date? = null,
    var resourceName: String? = null,
    var ven: Ven? = null,
    var attributes:  Set<ValuesMape>? = null,
    var targets:  Set<ValuesMape>? = null,
    @Enumerated(EnumType.STRING) var objectType: ObjectType? = null
)

data class DeleteResourceCommand(@TargetAggregateIdentifier  var resourceId: UUID? = null)

// single association commands
data class AssignVenToResourceCommand(@TargetAggregateIdentifier  val resourceId: UUID, val assignment: Ven )
data class UnAssignVenFromResourceCommand(@TargetAggregateIdentifier  val resourceId: UUID )

// multiple association commands
data class AssignAttributesToResourceCommand(@TargetAggregateIdentifier  val resourceId: UUID, val addTo: ValuesMape )
data class RemoveAttributesFromResourceCommand(@TargetAggregateIdentifier  val resourceId: UUID, val removeFrom: ValuesMape )
data class AssignTargetsToResourceCommand(@TargetAggregateIdentifier  val resourceId: UUID, val addTo: ValuesMape )
data class RemoveTargetsFromResourceCommand(@TargetAggregateIdentifier  val resourceId: UUID, val removeFrom: ValuesMape )


// Subscription Commands
data class CreateSubscriptionCommand(
    @TargetAggregateIdentifier var subscriptionId: UUID? = null,
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

data class UpdateSubscriptionCommand(
    @TargetAggregateIdentifier var subscriptionId: UUID? = null,
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

data class DeleteSubscriptionCommand(@TargetAggregateIdentifier  var subscriptionId: UUID? = null)

// single association commands
data class AssignProgramToSubscriptionCommand(@TargetAggregateIdentifier  val subscriptionId: UUID, val assignment: Program )
data class UnAssignProgramFromSubscriptionCommand(@TargetAggregateIdentifier  val subscriptionId: UUID )

// multiple association commands
data class AssignObjectOperationsToSubscriptionCommand(@TargetAggregateIdentifier  val subscriptionId: UUID, val addTo: ObjectOperation )
data class RemoveObjectOperationsFromSubscriptionCommand(@TargetAggregateIdentifier  val subscriptionId: UUID, val removeFrom: ObjectOperation )


// Ven Commands
data class CreateVenCommand(
    @TargetAggregateIdentifier var venId: UUID? = null,
    var createdDateTime:  Date? = null,
    var modificationDateTime:  Date? = null,
    var venName: String? = null,
    @Enumerated(EnumType.STRING) var objectType: ObjectType? = null
)

data class UpdateVenCommand(
    @TargetAggregateIdentifier var venId: UUID? = null,
    var createdDateTime:  Date? = null,
    var modificationDateTime:  Date? = null,
    var venName: String? = null,
    var attributes:  Set<ValueMap>? = null,
    var targets:  Set<ValueMap>? = null,
    var resources:  Set<Resource>? = null,
    @Enumerated(EnumType.STRING) var objectType: ObjectType? = null
)

data class DeleteVenCommand(@TargetAggregateIdentifier  var venId: UUID? = null)

// single association commands

// multiple association commands
data class AssignAttributesToVenCommand(@TargetAggregateIdentifier  val venId: UUID, val addTo: ValueMap )
data class RemoveAttributesFromVenCommand(@TargetAggregateIdentifier  val venId: UUID, val removeFrom: ValueMap )
data class AssignTargetsToVenCommand(@TargetAggregateIdentifier  val venId: UUID, val addTo: ValueMap )
data class RemoveTargetsFromVenCommand(@TargetAggregateIdentifier  val venId: UUID, val removeFrom: ValueMap )
data class AssignResourcesToVenCommand(@TargetAggregateIdentifier  val venId: UUID, val addTo: Resource )
data class RemoveResourcesFromVenCommand(@TargetAggregateIdentifier  val venId: UUID, val removeFrom: Resource )



