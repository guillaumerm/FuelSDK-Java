package com.exacttarget.fuelsdk;

import com.google.gson.annotations.Expose;

import org.apache.commons.lang.ObjectUtils.Null;

import java.util.ArrayList;
import java.util.List;

import com.exacttarget.fuelsdk.annotations.ExternalName;
import com.exacttarget.fuelsdk.annotations.RestObject;
import com.exacttarget.fuelsdk.internal.Subscription;

@RestObject(path = "/platform/v1/ens-subscriptions", primaryKey = "subscriptionId", collection = "", totalCount = "")
public class ENSSubscription extends ETENSRestObject {

    public static enum NotificationEventCategory {
        TRANSACTIONAL_SEND_EVENTS("TransactionalSendEvents"),
        ENGAGEMENT_EVENTS("EngagementEvents");

        private final String value;

        private NotificationEventCategory(String value) {
            this.value = value;
        }

        public String toString() {
            return this.value;
        }
    }

    public static enum NotificationEventType {
        EMAIL_SENT("EmailSent", NotificationEventCategory.TRANSACTIONAL_SEND_EVENTS, "Indicates that the email was sent to the email provider."),
        EMAIL_NOT_SENT("EmailNotSent", NotificationEventCategory.TRANSACTIONAL_SEND_EVENTS, "Indicates that the email was not sent and includes the reason."),
        EMAIL_BOUNCED("EmailBounced", NotificationEventCategory.TRANSACTIONAL_SEND_EVENTS, "Indicates that the email bounced and includes the reason."),
        EMAIL_OPEN("EmailOpen", NotificationEventCategory.ENGAGEMENT_EVENTS, "Indicates that the email was opened."),
        EMAIL_CLICK("EmailClick", NotificationEventCategory.ENGAGEMENT_EVENTS, "Indicates that the recipient clicked a link in the email."),
        EMAIL_UNSUBSCRIBE("EmailUnsubscribe", NotificationEventCategory.ENGAGEMENT_EVENTS, "Indicates that the recipient clicked the unsubscribe link in the email."),
        SMS_SENT("SmsSent", NotificationEventCategory.TRANSACTIONAL_SEND_EVENTS, "Indicates that the SMS message was sent to the aggregator."),
        SMS_NOT_SENT("SmsNotSent", NotificationEventCategory.TRANSACTIONAL_SEND_EVENTS, "Indicates that the SMS message was not sent and includes the reason."),
        SMS_TRANSIENT("SmsTransient", NotificationEventCategory.TRANSACTIONAL_SEND_EVENTS, "Indicates the most recent transmission status between Salesforce and the mobile device. Not the final disposition. This information is provided by our delivery partners and mobile carriers but is not available in all locales. No actions to take."),
        SMS_BOUNCED("SmsBounced", NotificationEventCategory.TRANSACTIONAL_SEND_EVENTS, "Indicates that the SMS message bounced and includes the reason."),
        SMS_DELIVERED("SmsDelivered", NotificationEventCategory.TRANSACTIONAL_SEND_EVENTS, "Indicates that the SMS message was delivered.");

        private final String type;
        private final NotificationEventCategory category;
        private final String description;

        private NotificationEventType(String type, NotificationEventCategory category, String description) {
            this.type = type;
            this.category = category;
            this.description = description;
        }

        public String toString() {
            return this.category.toString() + "." + this.type;
        }

        public NotificationEventCategory gNotificationEventCategory() {
            return this.category;
        }

        public String getDescription() {
            return this.description;
        }
    }

    public static enum SubscriptionFilterDataItem {
        DEFINITION_KEY("definitionKey"),
        STATUS_CODE("statusCode"),
        BOUNCE_CODE("bounceCode"),
        SMTP_REASON("smtpReason");
        private final String dataItem;
        private SubscriptionFilterDataItem(String dataItem) {
            this.dataItem = dataItem;
        }
        public String toString() {
            return this.dataItem;
        }
    }

    @Expose
    @ExternalName("callbackId")
    private String callbackId = null;
    @Expose
    @ExternalName("callbackName")
    private String callbackName = null;
    @Expose
    @ExternalName("subscriptionName")
    private String subscriptionName = null;
    @Expose
    @ExternalName("eventCategoryTypes")
    private List<NotificationEventType> eventCategoryTypes = new ArrayList<NotificationEventType>();
    @Expose
    @ExternalName("subscriptionId")
    private String subscriptionId = null;
    @Expose
    @ExternalName("filters")
    private List<String> filters = new ArrayList<String>();
    @Expose
    @ExternalName("status")
    private String status = null;
    @Override
    public String getId() {
        return this.subscriptionId;
    }
    @Override
    public void setId(String id) {
        this.subscriptionId = id;
    }

    /**
     * 
     * @return The callback ID of the ENSSubscription object.
     */
    public String getCallbackId() {
        return this.callbackId;
    }

    /**
     * 
     * @param callbackId The callback ID of the ENSSubscription object.
     */
    public void setCallbackId(String callbackId) {
        this.callbackId = callbackId;
    }

    /**
     * 
     * @return The subscription name of the ENSSubscription object.
     */
    public String getSubscriptionName() {
        return this.subscriptionName;
    }
    
    /**
     * 
     * @param subscriptionName The subscription name of the ENSSubscription object.
     */
    public void setSubscriptionName(String subscriptionName) {
        this.subscriptionName = subscriptionName;
    }

    /**
     * 
     * @return The list of notification event types of the ENSSubscription object.
     */
    public List<NotificationEventType> getEventCategoryTypes() {
        return this.eventCategoryTypes;
    }

    /**
     * 
     * @param eventCategoryTypes The evemt categpry types of the ENSSubscription object.
     */
    public void setEventCategoryTypes(List<NotificationEventType> eventCategoryTypes) {
        this.eventCategoryTypes = eventCategoryTypes;
    }

    /**
     * 
     * @return The subscription ID of the ENSSubscription object.
     */
    public String getSubscriptionId() {
        return this.subscriptionId;
    }

    /**
     * 
     * @param subscriptionId The subscription ID of the ENSSubscription object.
     */
    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    /**
     * 
     * @return The filters of the ENSSubscription object
     */
    public List<String> getFilters() {
        return this.filters;
    }

    /**
     * 
     * @param filters The filters of the ENSSubscription object.
     */
    public void setFilters(List<String> filters) {
        this.filters = filters;
    }
}
