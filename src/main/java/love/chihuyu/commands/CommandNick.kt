package love.chihuyu.commands

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.arguments.GreedyStringArgument
import dev.jorel.commandapi.executors.PlayerCommandExecutor
import love.chihuyu.utils.NickUtil

object CommandNick {

    val main = CommandAPICommand("nick")
        .withPermission("nickname.nick")
        .withArguments(GreedyStringArgument("nickname"))
        .executesPlayer(PlayerCommandExecutor { sender, args ->
            val name = args[0] as String
            NickUtil.setDisplayNick(sender, name)

            sender.sendMessage("Nickname set to: $name")
        })
}