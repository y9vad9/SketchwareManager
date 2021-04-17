package io.sketchware.exception

class MoreblockNotFoundException(activityName: String, moreblockName: String) :
    Exception("Moreblock for name $moreblockName not found in $activityName.")