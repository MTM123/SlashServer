package lv.mtm123.slashserver.util

import ninja.leaping.configurate.objectmapping.Setting
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable
import java.util.*

@ConfigSerializable
class ServerEntry {
    @Setting
    val server = "lobby"
    @Setting
    val commands = Arrays.asList("lobby", "hub")

}