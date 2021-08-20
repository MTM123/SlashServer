package lv.mtm123.slashserver.util

import org.spongepowered.configurate.objectmapping.ConfigSerializable
import org.spongepowered.configurate.objectmapping.meta.Setting
import org.spongepowered.configurate.yaml.NodeStyle
import org.spongepowered.configurate.yaml.YamlConfigurationLoader
import java.io.File
import java.nio.file.Paths

@ConfigSerializable
class Config {

    @Setting
    val servers = listOf(ServerEntry())

    companion object {
        fun loadConfig(pluginFolder: File): Config {

            if (!pluginFolder.exists()) {
                pluginFolder.mkdirs()
            }

            val path = Paths.get(pluginFolder.path, "config.yml")

            val loader = YamlConfigurationLoader.builder()
                .path(path)
                .nodeStyle(NodeStyle.BLOCK)
                .build()

            val node = loader.load()
            val config = node.get(Config::class.java)!!

            node.set(Config::class.java, config)
            loader.save(node)

            return config
        }
    }

}
