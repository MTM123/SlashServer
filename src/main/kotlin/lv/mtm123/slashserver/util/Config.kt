package lv.mtm123.slashserver.util

import ninja.leaping.configurate.objectmapping.ObjectMapper
import ninja.leaping.configurate.objectmapping.Setting
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable
import ninja.leaping.configurate.yaml.YAMLConfigurationLoader
import org.yaml.snakeyaml.DumperOptions
import java.io.File

@ConfigSerializable
class Config {

    @Setting
    val servers = listOf(ServerEntry())

    companion object {
        fun loadConfig(pluginFolder : File) : Config {

            if (!pluginFolder.exists()) {
                pluginFolder.mkdirs()
            }

            val cfgFile = File(pluginFolder, "config.yml")

            val instance: ObjectMapper<Config>.BoundInstance =
                ObjectMapper.forClass(Config::class.java)
                    .bindToNew()
            val loader = YAMLConfigurationLoader.builder()
                .setFile(cfgFile).setFlowStyle(DumperOptions.FlowStyle.BLOCK).build()

            val node = loader.load()
            instance.populate(node)

            //Pretty sure I'm doing this part wrong
            if (!cfgFile.exists()) {
                instance.serialize(node)
                loader.save(node)
            }

            //instance.populate(loader.load());
            return instance.instance
        }
    }

}