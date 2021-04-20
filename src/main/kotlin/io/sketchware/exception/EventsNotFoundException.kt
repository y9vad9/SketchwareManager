package io.sketchware.exception

class EventsNotFoundException(activity: String) :
    Exception(
        "Events does not exist for activity $activity. " +
            "Make sure that you have activity with name $activity or there is contains any event."
    )