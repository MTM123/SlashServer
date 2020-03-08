package lv.mtm123.slashserver.cmd

import com.velocitypowered.api.command.Command
import com.velocitypowered.api.command.CommandSource
import com.velocitypowered.api.proxy.Player
import com.velocitypowered.api.proxy.ProxyServer
import net.kyori.text.TextComponent
import net.kyori.text.format.TextColor

class VelocityNavigationCommand(private val proxy: ProxyServer, private val target: String) : Command {

    override fun execute(source: CommandSource?, args: Array<out String>) {
        if (source !is Player) {
            source?.sendMessage(TextComponent.of("Only players can use this command!").color(TextColor.RED))
            return
        }

        val player: Player = source
        proxy.getServer(target).ifPresent { s -> player.createConnectionRequest(s).connect() }
    }

}