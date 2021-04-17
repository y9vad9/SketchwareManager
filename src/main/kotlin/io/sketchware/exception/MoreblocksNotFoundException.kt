package io.sketchware.exception

class MoreblocksNotFoundException(activityName: String) :
    Exception("Moreblocks not found in activity $activityName. Does activity exist?")