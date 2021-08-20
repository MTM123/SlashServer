package lv.mtm123.slashserver.proxies

import lv.mtm123.slashserver.cmd.BungeeNavigationCommand
import lv.mtm123.slashserver.util.Config
import lv.mtm123.slashserver.util.ServerEntry
import net.md_5.bungee.api.plugin.Plugin
import org.bstats.bungeecord.Metrics

class BungeeSlashServer : Plugin() {

    private lateinit var config: Config

    override fun onEnable() {
        config = Config.loadConfig(dataFolder)
        registerCommands()

        Metrics(this, 12510)
    }

    override fun onDisable() {

    }

    private fun registerCommands() {
        config.servers.forEach { s: ServerEntry ->
            val list = s.commands.filter { alias -> s.server != alias }.toCollection(ArrayList())
            proxy.pluginManager.registerCommand(this, BungeeNavigationCommand(s.server, *list.toTypedArray()))
        }
    }

}
