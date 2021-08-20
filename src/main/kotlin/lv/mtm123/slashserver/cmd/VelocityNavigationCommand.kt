package lv.mtm123.slashserver.cmd

import com.velocitypowered.api.command.SimpleCommand
import com.velocitypowered.api.proxy.Player
import com.velocitypowered.api.proxy.ProxyServer
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor

class VelocityNavigationCommand(private val proxy: ProxyServer, private val target: String) : SimpleCommand {

    override fun execute(inv: SimpleCommand.Invocation) {
        if (inv.source() !is Player) {
            inv.source().sendMessage(Component.text("Only players can use this command!").color(NamedTextColor.RED))
            return
        }

        val player: Player = inv.source() as Player
        proxy.getServer(target).ifPresent { s -> player.createConnectionRequest(s).connect() }
    }

}
