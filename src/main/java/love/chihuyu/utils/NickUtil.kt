package love.chihuyu.utils

import love.chihuyu.Plugin.Companion.plugin
import org.bukkit.entity.Player
import xyz.haoshoku.nick.api.NickAPI

object NickUtil {

    fun setDisplayNick(player: Player, name: String) {
        plugin.server.onlinePlayers.forEach { it.hidePlayer(plugin, player) }

        player.setDisplayName(name)
        player.setPlayerListName(name)

        player.customName = name
        player.isCustomNameVisible = true

        NickAPI.nick(player, name)
        NickAPI.setGameProfileName(player, name)

        plugin.config.set(player.uniqueId.toString(), name)
        plugin.saveConfig()
        plugin.server.onlinePlayers.forEach { it.showPlayer(plugin, player) }
    }
}