package RRCore.commands;

import RRCore.Main;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.data.CommandParameter;
import cn.nukkit.utils.TextFormat;
import cn.nukkit.command.CommandSender;

public class GmsCommand extends Command {
	
	private Main plugin;
	
	public GmsCommand(Main plugin) {
		super("gms", "Make yourself (or another player) survival mode", "/gms <player>");

		this.plugin = plugin;
		
		this.setPermission("RRCore.permission.command.gms");
        this.commandParameters.clear();
        this.commandParameters.put("default",
            new CommandParameter[]{
                new CommandParameter("player", CommandParameter.ARG_TYPE_TARGET, true)
            }
		);

		this.plugin.getLogger().info(this.plugin.currentLang.translate("plugin.command.enabled", "n", "gms"));
	}

	@Override
	public boolean execute(CommandSender sender, String label, String[] args){
		if(args.length >= 1 && args[0] != null) {
			// Perform command on player
			Player player = sender.getServer().getPlayer(args[0]);
					
			if(player != null) {
				player.setGamemode(0);
				player.sendMessage(this.plugin.currentLang.translate("plugin.command.gmx.set-to", "p", sender.getName(), "gm", "survival"));
				sender.sendMessage(this.plugin.currentLang.translate("plugin.command.gmx.set-from", "p", player.getName(), "gm", "survival"));
				return true;
			} else {
				sender.sendMessage(this.plugin.currentLang.translate("plugin.command.gmx.failed-player"));
				return false;
			}
		} else {
			// Perform command on self
			if(sender instanceof Player) {
				((Player) sender).setGamemode(0);
				sender.sendMessage(this.plugin.currentLang.translate("plugin.command.gmx.set-own", "gm", "survival"));
				return true;
			} else {
				sender.sendMessage(this.plugin.currentLang.translate("plugin.command.gmx.failed-own"));
				return false;
			}
		}
	}

}