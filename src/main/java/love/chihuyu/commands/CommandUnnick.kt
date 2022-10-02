package love.chihuyu.commands

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.executors.PlayerCommandExecutor
import love.chihuyu.Plugin.Companion.plugin
import love.chihuyu.utils.NickUtil
import xyz.haoshoku.nick.api.NickAPI

object CommandUnnick {

    val main = CommandAPICommand("unnick")
        .withPermission("nickname.unnick")
        .executesPlayer(PlayerCommandExecutor { sender, args ->
            val originalName = NickAPI.getOriginalGameProfileName(sender)
            NickUtil.setDisplayNick(sender, originalName)

            sender.sendMessage("Nickname reset.")
        })
}