package io.github.derec4.fasterHappyGhast.commands

import io.github.derec4.fasterHappyGhast.FasterHappyGhast
import io.github.derec4.fasterHappyGhast.handlers.ConfigHandler
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class CommandManager(private val plugin: FasterHappyGhast) : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (args.isEmpty()) {
            sender.sendMessage("/${command.name} reload")
            return true
        }

        when (args[0].lowercase()) {
            "reload" -> {
                val perm = "${plugin.name.lowercase()}.reload"
                if (!sender.hasPermission(perm)) {
                    sender.sendMessage("You don't have permission to do that.")
                    return true
                }

                plugin.reloadConfig()
                val cfg = ConfigHandler()
                cfg.load(plugin)

                FasterHappyGhast.baseSpeed = cfg.baseSpeed
                FasterHappyGhast.defaultSpeed = cfg.defaultSpeed
                FasterHappyGhast.affectAll = cfg.affectAll

                sender.sendMessage("${plugin.name} configuration reloaded.")
                return true
            }

            else -> {
                sender.sendMessage("Unknown subcommand. Use /${command.name} reload")
                return true
            }
        }
    }
}
