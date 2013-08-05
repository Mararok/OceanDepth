/**
 * OceanDepth
 * The MIT License
 * Copyright (C) 2013 Mararok <mararok@gmail.com>
 */
package com.gmail.mararok.OceanDepth.Enviroment;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

/**
 *
 */
public class Vulcan {
	private World EruptionWorld;
	private Location StartPosition;
	private Location CurrentPosition;
	private int MaxHeight;
	
	public Vulcan(World world,Location startPosition, int maxHeight) {
		EruptionWorld = world;
		StartPosition = startPosition;
		CurrentPosition = startPosition.clone();
		MaxHeight = maxHeight;
	}
	public void update() {
		if (!isEruptionEnd()) {
			
			CurrentPosition.setY(CurrentPosition.getBlockY()+1);
			CurrentPosition.setX(CurrentPosition.getX()+1);
			EruptionWorld.getBlockAt(CurrentPosition).setType(Material.AIR);
			CurrentPosition.setX(CurrentPosition.getX()-2);
			EruptionWorld.getBlockAt(CurrentPosition).setType(Material.AIR);
			CurrentPosition.setX(CurrentPosition.getX()+1);
			EruptionWorld.getBlockAt(CurrentPosition).setType(Material.AIR);
			
			CurrentPosition.setZ(CurrentPosition.getZ()+1);
			EruptionWorld.getBlockAt(CurrentPosition).setType(Material.AIR);
			CurrentPosition.setZ(CurrentPosition.getZ()-2);
			EruptionWorld.getBlockAt(CurrentPosition).setType(Material.AIR);
			CurrentPosition.setZ(CurrentPosition.getZ()+1);
			EruptionWorld.getBlockAt(CurrentPosition).setType(Material.AIR);
			
			
			CurrentPosition.setY(CurrentPosition.getBlockY()+1);
			CurrentPosition.setX(CurrentPosition.getX()+1);
			EruptionWorld.getBlockAt(CurrentPosition).setType(Material.AIR);
			CurrentPosition.setX(CurrentPosition.getX()-2);
			EruptionWorld.getBlockAt(CurrentPosition).setType(Material.AIR);
			CurrentPosition.setX(CurrentPosition.getX()+1);
			EruptionWorld.getBlockAt(CurrentPosition).setType(Material.AIR);
			
			CurrentPosition.setZ(CurrentPosition.getZ()+1);
			EruptionWorld.getBlockAt(CurrentPosition).setType(Material.AIR);
			CurrentPosition.setZ(CurrentPosition.getZ()-2);
			EruptionWorld.getBlockAt(CurrentPosition).setType(Material.AIR);
			CurrentPosition.setZ(CurrentPosition.getZ()+1);
			EruptionWorld.getBlockAt(CurrentPosition).setType(Material.AIR);
			
			CurrentPosition.setY(CurrentPosition.getBlockY()-1);
			EruptionWorld.getBlockAt(CurrentPosition).setType(Material.STATIONARY_LAVA);
			
		}
	}
	
	public Material getRandomBlock() {
		Random r = new Random();
		int ore = r.nextInt(5);
		
		switch(ore) {
		case 1:
			return Material.IRON_ORE;
		case 2:
			return Material.GOLD_ORE;
		case 3:
		case 4:
			return Material.STONE;
		}
		
		return Material.COBBLESTONE;
		
	}
	public boolean isEruptionEnd() {
		return ( CurrentPosition.getBlockY() == (MaxHeight+StartPosition.getBlockY()) );
	}
}
