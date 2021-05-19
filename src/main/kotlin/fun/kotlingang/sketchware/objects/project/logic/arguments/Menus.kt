package `fun`.kotlingang.sketchware.objects.project.logic.arguments

import `fun`.kotlingang.sketchware.interfaces.objects.MutableValueContainer
import `fun`.kotlingang.sketchware.objects.project.logic.menu.components.calendar.CalendarField
import `fun`.kotlingang.sketchware.objects.project.logic.menu.components.file.PublicDirectoryType
import `fun`.kotlingang.sketchware.objects.project.logic.menu.components.intent.IntentAction
import `fun`.kotlingang.sketchware.objects.project.logic.menu.components.intent.IntentFlag
import `fun`.kotlingang.sketchware.objects.project.logic.menu.components.locationmanager.ProviderType
import `fun`.kotlingang.sketchware.objects.project.logic.menu.components.objectanimator.Interpolator
import `fun`.kotlingang.sketchware.objects.project.logic.menu.components.objectanimator.RepeatMode
import `fun`.kotlingang.sketchware.objects.project.logic.menu.components.objectanimator.TargetProperty
import `fun`.kotlingang.sketchware.objects.project.logic.menu.components.requestnetwork.RequestMethod
import `fun`.kotlingang.sketchware.objects.project.logic.menu.components.requestnetwork.RequestType
import `fun`.kotlingang.sketchware.objects.project.logic.menu.widgets.mapview.MapType
import `fun`.kotlingang.sketchware.objects.project.logic.menu.widgets.textview.TextStyle
import `fun`.kotlingang.sketchware.objects.project.logic.menu.widgets.view.Visibility
import `fun`.kotlingang.sketchware.objects.project.logic.menu.widgets.webview.WebViewCacheMode

sealed interface EnumMenuArgument<T : Enum<T>> : MenuArgument, MutableValueContainer<T>

sealed interface ComponentEnumMenuArgument<T : Enum<T>> : EnumMenuArgument<T>

sealed interface CalendarEnumMenuArgument<T : Enum<T>> : ComponentEnumMenuArgument<T>

class CalendarFieldContainer(override var value: CalendarField?) : CalendarEnumMenuArgument<CalendarField>

sealed interface FileEnumMenuArgument<T : Enum<T>> : ComponentEnumMenuArgument<T>

class PublicDirectoryTypeContainer(override var value: PublicDirectoryType?) :
    FileEnumMenuArgument<PublicDirectoryType>

sealed interface LocationManagerEnumMenuArgument<T : Enum<T>> : ComponentEnumMenuArgument<T>

class ProviderTypeContainer(override var value: ProviderType?) : LocationManagerEnumMenuArgument<ProviderType>

sealed interface ObjectAnimatorEnumMenuArgument<T : Enum<T>> : ComponentEnumMenuArgument<T>

class InterpolatorContainer(override var value: Interpolator?) : ObjectAnimatorEnumMenuArgument<Interpolator>

class RepeatModeContainer(override var value: RepeatMode?) : ObjectAnimatorEnumMenuArgument<RepeatMode>

class TargetPropertyContainer(override var value: TargetProperty?) : ObjectAnimatorEnumMenuArgument<TargetProperty>

sealed interface RequestNetworkEnumMenuArgument<T : Enum<T>> : ComponentEnumMenuArgument<T>

class RequestMethodContainer(override var value: RequestMethod?) : RequestNetworkEnumMenuArgument<RequestMethod>

class RequestTypeContainer(override var value: RequestType?) : RequestNetworkEnumMenuArgument<RequestType>

sealed interface IntentEnumMenuArgument<T : Enum<T>> : ComponentEnumMenuArgument<T>

class IntentActionContainer(override var value: IntentAction?) : IntentEnumMenuArgument<IntentAction>

class IntentFlagContainer(override var value: IntentFlag?) : IntentEnumMenuArgument<IntentFlag>

sealed interface ViewEnumMenuArgument<T : Enum<T>> : EnumMenuArgument<T>

sealed interface TextViewEnumMenuArgument<T : Enum<T>> : ViewEnumMenuArgument<T>

class TextStyleContainer(override var value: TextStyle?) : TextViewEnumMenuArgument<TextStyle>

sealed interface MapViewEnumMenuArgument<T : Enum<T>> : ViewEnumMenuArgument<T>

class MapTypeContainer(override var value: MapType?) : MapViewEnumMenuArgument<MapType>

class VisibilityContainer(override var value: Visibility?) : ViewEnumMenuArgument<Visibility>

sealed interface WebViewEnumMenuArgument<T : Enum<T>> : ViewEnumMenuArgument<T>

class WebViewCacheModeContainer(override var value: WebViewCacheMode?) : WebViewEnumMenuArgument<WebViewCacheMode>