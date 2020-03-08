package lv.mtm123.slashserver.proxies

import com.google.inject.Inject
import com.velocitypowered.api.event.Subscribe
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent
import com.velocitypowered.api.plugin.Plugin
import com.velocitypowered.api.proxy.ProxyServer
import lv.mtm123.slashserver.cmd.VelocityNavigationCommand
import lv.mtm123.slashserver.util.Config
import ninja.leaping.configurate.objectmapping.ObjectMapper
import ninja.leaping.configurate.yaml.YAMLConfigurationLoader
import org.yaml.snakeyaml.DumperOptions
import java.io.File
import java.util.logging.Logger

@Plugin(
    id = "slashserver",
    name = "SlashServer",
    version = "1.0-SNAPSHOT",
    description = "Allows to use /<servername>",
    authors = ["MTM123"]
)
class VelocitySlashServer {

    @Inject
    private lateinit var proxy: ProxyServer

    @Inject
    private lateinit var logger: Logger

    @Subscribe
    fun onInit(event: ProxyInitializeEvent) {

        val mainDir = File("./plugins/SlashServer/")
        if (!mainDir.exists()) {
            mainDir.mkdirs()
        }

        Config.loadConfig(mainDir).servers.forEach { s ->
            proxy.commandManager.register(
                VelocityNavigationCommand(proxy, s.server),
                *s.commands.toTypedArray()
            )
        }

    }

}