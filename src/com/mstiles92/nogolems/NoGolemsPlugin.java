package com.mstiles92.nogolems;

import java.util.logging.Logger;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class NoGolemsPlugin extends JavaPlugin {
	
	private PluginDescriptionFile pdf;
	private Logger log;
	
	public void onEnable() {
		this.pdf = this.getDescription();
		this.log = this.getLogger();
		log.info(pdf.getName() + " by " + pdf.getAuthors().get(0) + " version " + pdf.getVersion() + " enabled!");
		this.getServer().getPluginManager().registerEvents(new NoGolemsEventListener(), this);
	}
	
	public void onDisable() {
		log.info(pdf.getName() + " by " + pdf.getAuthors().get(0) + " version " + pdf.getVersion() + " disabled!");
	}
}
