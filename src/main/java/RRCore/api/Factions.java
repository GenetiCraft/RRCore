package RRCore.api;

import RRCore.Main;

import cn.nukkit.utils.TextFormat;

import java.nio.file.*;

public class Factions {
	
	private Main plugin;
	
	private String facsPath;
	
	public Factions(Main plugin) {
		this.plugin = plugin;
		this.facsPath = this.plugin.getFacsFolder();
		//this.initFacs();
	}
	
	private void initFacs() {
		// To come...
	}
	
}