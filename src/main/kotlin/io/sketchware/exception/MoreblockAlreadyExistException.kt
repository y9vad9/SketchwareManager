package io.sketchware.exception

class MoreblockAlreadyExistException(activity: String, moreblockName: String) :
    Exception("Moreblock for name $moreblockName in $activity already exist.")