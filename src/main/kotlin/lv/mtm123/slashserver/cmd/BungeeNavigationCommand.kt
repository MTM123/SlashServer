package lv.mtm123.slashserver.cmd

import net.md_5.bungee.api.ChatColor
import net.md_5.bungee.api.CommandSender
import net.md_5.bungee.api.ProxyServer
import net.md_5.bungee.api.chat.ComponentBuilder
import net.md_5.bungee.api.connection.ProxiedPlayer
import net.md_5.bungee.api.plugin.Command

class BungeeNavigationCommand(private val target: String?, vararg aliases: String?) :
    Command(target, null /*this is not here*/, *aliases) {

    override fun execute(sender: CommandSender?, args: Array<out String>?) {
        if (sender !is ProxiedPlayer) {
            sender?.sendMessage(*ComponentBuilder("Only players can use this command!").color(ChatColor.RED).create())
            return
        }

        val proxyPlayer: ProxiedPlayer = sender
        val server = ProxyServer.getInstance().getServerInfo(target)
        if (server != null) {
            proxyPlayer.connect(server)
        }
    }

}