package RRCore;

import RRCore.commands.*;
import RRCore.lang.*;
import RRCore.api.*;

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
import java.nio.file.*;

public class Main extends PluginBase {

	public Config config;
	
	public Lang currentLang;
	
	private String langFolder;
	private String facsFolder;

	@Override
	public void onEnable() {
		this.getDataFolder().mkdirs();

		if(this.getFileStatus(this.getDataFolder() + "/config.yml") != 1) {
			saveResource("config.yml");
		}

		if(this.getFileStatus(this.getLangFolder() + "/eng.yml") != 1) {
			saveResource("/lang/eng.yml");
		}
		
		this.config = getConfig();
		
		this.currentLang = new Lang(this, this.config.getString("lang"));
		
		new Factions(this);

		this.getLogger().info(TextFormat.GREEN + this.currentLang.translate("plugin.enabled"));
		
		Set<Integer> lvls = this.getServer().getLevels().keySet();
		for(int lvl : lvls) {
			this.getServer().getLevel(lvl).setTime(0);
			this.getServer().getLevel(lvl).stopTime();
		}
		
		this.registerCommands();
	}
	
	public String getLangFolder() {
		return this.getDataFolder() + "/lang/";
	}
	
	public String getFacsFolder() {
		return this.getDataFolder() + "/facs/";
	}
	
	private void registerCommands() {
		this.getServer().getCommandMap().register("gmc", new GmcCommand(this));
		this.getServer().getCommandMap().register("gms", new GmsCommand(this));
		this.getServer().getCommandMap().register("gmsp", new GmspCommand(this));
	}
	
	public int getFileStatus(String file) { // Can also check if a folder exists
		Path p = Paths.get(file);
		boolean exists = Files.exists(p);
		boolean notExists = Files.notExists(p);

		if (exists) {
			return 1; //File exists
		} else if (notExists) {
			return 0; //File does not exist
		} else {
			return 2; //Unknown status
		}
	}

}