package io.sketchware.exceptions

class MoreblocksNotFoundException(activityName: String) :
    Exception("Moreblocks not found in activity $activityName. Does it activity exist?")