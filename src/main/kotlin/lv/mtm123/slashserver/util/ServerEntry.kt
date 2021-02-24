package lv.mtm123.slashserver.util

import ninja.leaping.configurate.objectmapping.Setting
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable

@ConfigSerializable
class ServerEntry {
    @Setting
    val server = "lobby"

    @Setting
    val commands = listOf("lobby", "hub")

}
