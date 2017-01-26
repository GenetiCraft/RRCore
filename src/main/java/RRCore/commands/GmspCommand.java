package RRCore.commands;

import RRCore.Main;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.data.CommandParameter;
import cn.nukkit.utils.TextFormat;
import cn.nukkit.command.CommandSender;

public class GmspCommand extends Command {
	
	private Main plugin;
	
	public GmspCommand(Main plugin) {
		super("gmsp", "Make yourself (or another player) spectator mode", "/gmsp <player>");

		this.plugin = plugin;
		
		this.setPermission("RRCore.permission.command.gmsp");
        this.commandParameters.clear();
        this.commandParameters.put("default",
            new CommandParameter[]{
                new CommandParameter("player", CommandParameter.ARG_TYPE_TARGET, true)
            }
		);

		this.plugin.getLogger().info(this.plugin.currentLang.translate("plugin.command.enabled", "n", "gmsp"));
	}

	@Override
	public boolean execute(CommandSender sender, String label, String[] args){
		if(args.length >= 1 && args[0] != null) {
			// Perform command on player
			Player player = sender.getServer().getPlayer(args[0]);
					
			if(player != null) {
				player.setGamemode(3);
				player.sendMessage(this.plugin.currentLang.translate("plugin.command.gmx.set-to", "p", sender.getName(), "gm", "spectator"));
				sender.sendMessage(this.plugin.currentLang.translate("plugin.command.gmx.set-from", "p", player.getName(), "gm", "spectator"));
				return true;
			} else {
				sender.sendMessage(this.plugin.currentLang.translate("plugin.command.gmx.failed-player"));
				return false;
			}
		} else {
			// Perform command on self
			if(sender instanceof Player) {
				((Player) sender).setGamemode(3);
				sender.sendMessage(this.plugin.currentLang.translate("plugin.command.gmx.set-own", "gm", "spectator"));
				return true;
			} else {
				sender.sendMessage(this.plugin.currentLang.translate("plugin.command.gmx.failed-own"));
				return false;
			}
		}
	}

}