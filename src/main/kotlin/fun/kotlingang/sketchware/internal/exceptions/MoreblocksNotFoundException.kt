package `fun`.kotlingang.sketchware.internal.exceptions

class MoreblocksNotFoundException internal constructor(activityName: String) :
    Exception("Moreblocks not found in activity $activityName. Does activity exist?")