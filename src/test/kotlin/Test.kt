import io.sketchware.models.collections.MoreblockCollectionItem
import io.sketchware.models.projects.BlockModel
import io.sketchware.utils.internal.deserialize
import io.sketchware.utils.internal.json
import io.sketchware.utils.internal.serialize
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.serializer

private val value = "{\"data\":\"{\\\"color\\\":-10701022,\\\"id\\\":\\\"10\\\",\\\"nextBlock\\\":-1,\\\"opCode\\\":\\\"addSourceDirectly\\\",\\\"parameters\\\":[\\\"android.support.design.widget.Snackbar.make(_view, _label.toString(), android.support.design.widget.Snackbar.LENGTH_SHORT).show();\\\"],\\\"spec\\\":\\\"add source directly %s.inputOnly\\\",\\\"subStack1\\\":-1,\\\"subStack2\\\":-1,\\\"type\\\":\\\" \\\",\\\"typeName\\\":\\\"\\\"}\\n\",\"name\":\"test\",\"reserved1\":\"Snackbar on %m.view.view with label %s.label\"}"
fun main() {
    println(value.serialize<MoreblockCollectionItem>())
}