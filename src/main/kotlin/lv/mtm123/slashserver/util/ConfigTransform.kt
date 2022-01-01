package lv.mtm123.slashserver.util

import org.spongepowered.configurate.ConfigurateException
import org.spongepowered.configurate.ConfigurationNode
import org.spongepowered.configurate.NodePath
import org.spongepowered.configurate.transformation.ConfigurationTransformation
import kotlin.jvm.Throws

class ConfigTransform {

    companion object {
        private const val VERSION = 0

        @Throws(ConfigurateException::class)
        fun <N : ConfigurationNode> update(node: N): N {
            if (!node.virtual()) {
                val transformation = build()
                val start = transformation.version(node)
                transformation.apply(node)
                val end = transformation.version(node)

                if (start != end) {
                    println("Config was updated to v$end")
                }
            }

            return node
        }

        private fun addPerm(): ConfigurationTransformation {
            return ConfigurationTransformation.builder() // Move the node at `serverVersion` to the location <code>{"server", "version"}</code>
                .addAction(
                    NodePath.path("servers", ConfigurationTransformation.WILDCARD_OBJECT)
                ) { _, value ->
                    val perm = "slashserver.server.${value.node("server").string}"
                    value.node("permission").set(perm)
                    null
                }
                .build()
        }

        private fun build(): ConfigurationTransformation.Versioned {
            return ConfigurationTransformation.versionedBuilder()
                .addVersion(0, addPerm())
                .build()
        }

    }

}
