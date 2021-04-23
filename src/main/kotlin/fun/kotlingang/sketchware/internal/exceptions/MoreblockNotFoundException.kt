package `fun`.kotlingang.sketchware.internal.exceptions

class MoreblockNotFoundException internal constructor(activityName: String, moreblockName: String) :
    Exception("Moreblock for name $moreblockName not found in $activityName.")