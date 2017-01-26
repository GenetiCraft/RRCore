package RRCore;

import RRCore.commands.*;
import RRCore.lang.*;

import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.TextFormat;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.Command;
import cn.nukkit.Player;
import cn.nukkit.level.Level;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Main extends PluginBase {

	public Config config;
	
	public Lang currentLang;
	
	private String langFolder;

	@Override
	public void onEnable() {
		this.getDataFolder().mkdirs();

		if(!(new File(this.getDataFolder(), "config.yml").exists())) {
			saveResource("config.yml");
		}
		
		if(!(new File(this.getLangFolder(), "eng.yml").exists())) {
			saveResource("/lang/eng.yml");
		}

		this.config = getConfig();
		
		this.currentLang = new Lang(this, this.config.getString("lang"));

		this.getLogger().info(TextFormat.GREEN + this.currentLang.translate("plugin.enabled"));
		
		Set<Integer> lvls = this.getServer().getLevels().keySet();
		for(int lvl : lvls) {
			this.getServer().getLevel(lvl).setTime(0);
			this.getServer().getLevel(lvl).stopTime();
		}
		
		this.registerCommands();
	}
	
	public String getLangFolder() {
		return this.getDataFolder() + "/lang";
	}
	
	private void registerCommands() {
		this.getServer().getCommandMap().register("gmc", new GmcCommand(this));
		this.getServer().getCommandMap().register("gms", new GmsCommand(this));
		this.getServer().getCommandMap().register("gmsp", new GmspCommand(this));
	}

}