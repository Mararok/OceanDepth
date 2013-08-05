/**
 * OceanDepth
 * The MIT License
 * Copyright (C) 2013 Mararok <mararok@gmail.com>
 */
package com.gmail.mararok.OceanDepth;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldInitEvent;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.gmail.mararok.OceanDepth.Enviroment.OceanDepthGenerator;
import com.gmail.mararok.OceanDepth.Enviroment.Vulcan;

public class OceanDepthPlugin extends JavaPlugin implements Listener {
	private OceanDepthGenerator DepthGenerator;
	 List<Vulcan> vulcans;
	private SimpleTask t;
	@Override public void onEnable() {
		vulcans =new LinkedList<Vulcan>();
		DepthGenerator = new OceanDepthGenerator();
		this.getServer().getPluginManager().registerEvents(this,this);
	}
	public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
		return DepthGenerator;
	}
	
	void addVulcan() {
		
		if (vulcans.size() < 32) {
			Random r = new Random();
			
			vulcans.add(new Vulcan(new Location(getServer().getWorld("test"),r.nextInt(100),0,r.nextInt(100)),20) );
		}
	}
	@EventHandler
	public void onWorldInit(WorldInitEvent event) {
		getLogger().info("init world");
		t = new SimpleTask();
		t.plugin = this;
		t.runTaskTimer(this,100,10);
	}
}

class SimpleTask extends BukkitRunnable {
	OceanDepthPlugin plugin;
	@Override
	public void run() {
		plugin.addVulcan();
		for (Vulcan v : plugin.vulcans) {
			v.update();
		}
	}
	
}
