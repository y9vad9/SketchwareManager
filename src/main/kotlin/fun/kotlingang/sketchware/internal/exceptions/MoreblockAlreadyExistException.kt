package `fun`.kotlingang.sketchware.internal.exceptions

class MoreblockAlreadyExistException internal constructor(activity: String, moreblockName: String) :
    Exception("Moreblock for name $moreblockName in $activity already exist.")