package love.chihuyu

import love.chihuyu.commands.CommandNick
import love.chihuyu.commands.CommandUnnick
import love.chihuyu.utils.NickUtil
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.plugin.java.JavaPlugin

class Plugin : JavaPlugin(), Listener {

    companion object {
        lateinit var plugin: JavaPlugin
    }

    init {
        plugin = this
    }

    override fun onEnable() {
        server.pluginManager.registerEvents(this, this)

        saveDefaultConfig()

        CommandNick.main.register()
        CommandUnnick.main.register()
    }

    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        val player = event.player
        NickUtil.setDisplayNick(player, config.getString(player.uniqueId.toString()) ?: player.name)
    }
}