package RRCore.lang;

import RRCore.Main;

import cn.nukkit.utils.Config;
import cn.nukkit.utils.TextFormat;

import java.io.File;
import java.util.Map;
import java.util.HashMap;

public class Lang extends Config {
	
	private Main plugin;
	private String shortname;
	private String name;
	
	private static String DEF_LANG = "eng";
	
	public Lang(Main plugin, String shortname) {
		super(2);
		this.plugin = plugin;
		this.shortname = shortname;
		if(this.plugin.getFileStatus(this.plugin.getLangFolder() + "/" + this.shortname + ".yml") != 1) {
			this.plugin.getLogger().info(TextFormat.RED + "Language config for '" + this.shortname + "' not found, using default.");
			if(this.plugin.getFileStatus(this.plugin.getLangFolder() + "/" + this.DEF_LANG + ".yml") != 1) {
				this.plugin.getLogger().info(TextFormat.RED + "Default language configuration not found! Please reinstall the plugin.");
				this.plugin.setEnabled(false);
			} else {
				this.load(this.plugin.getLangFolder() + "/" + DEF_LANG + ".yml");
			}
		} else {
			this.load(this.plugin.getLangFolder() + "/" + this.shortname + ".yml");	
		}
		
		this.name = this.getString("lang.name");
	}
	
	public String translate(String grabber) {
		HashMap<String, String> replaces = new HashMap<String, String>();
		return this.translate(grabber, replaces);
	}
	
	public String translate(String grabber, String replace, String with) {
		HashMap<String, String> replaces = new HashMap<String, String>();
		replaces.put(replace, with);
		return this.translate(grabber, replaces);
	}
	
	public String translate(String grabber, String replace1, String with1, String replace2, String with2) {
		HashMap<String, String> replaces = new HashMap<String, String>();
		replaces.put(replace1, with1);
		replaces.put(replace2, with2);
		return this.translate(grabber, replaces);
	}
	
	public String translate(String grabber, HashMap<String, String> args) {
		String string = this.getString(grabber).replace("&", "\u00A7");
		//String k = "";
		//Object v = new Object();
		for(HashMap.Entry<String, String> arg : args.entrySet())  {
			String k = arg.getKey();
			Object v = arg.getValue();
			string = string.replace("{" + k + "}", v.toString());
		}
		return string;
	}
	
}