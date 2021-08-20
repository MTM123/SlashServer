package lv.mtm123.slashserver.util

import org.spongepowered.configurate.objectmapping.ConfigSerializable
import org.spongepowered.configurate.objectmapping.meta.Setting

@ConfigSerializable
class ServerEntry {

    @Setting
    val server = "lobby"

    @Setting
    val commands = listOf("hub", "main")

}
