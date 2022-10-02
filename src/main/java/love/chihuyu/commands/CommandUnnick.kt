package love.chihuyu.commands

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.executors.PlayerCommandExecutor
import love.chihuyu.Plugin.Companion.plugin
import xyz.haoshoku.nick.api.NickAPI

object CommandUnnick {

    val main = CommandAPICommand("unnick")
        .withPermission("nickname.unnick")
        .executesPlayer(PlayerCommandExecutor { sender, args ->
            val originalName = NickAPI.getOriginalGameProfileName(sender)
            plugin.server.onlinePlayers.forEach { it.hidePlayer(plugin, sender) }

            sender.setPlayerListName(originalName)
            sender.setDisplayName(originalName)

            sender.customName = originalName
            sender.isCustomNameVisible = true

            NickAPI.resetNick(sender)

            plugin.config.set(sender.uniqueId.toString(), originalName)
            plugin.saveConfig()
            plugin.server.onlinePlayers.forEach { it.showPlayer(plugin, sender) }

            sender.sendMessage("Nickname reset.")
        })
}