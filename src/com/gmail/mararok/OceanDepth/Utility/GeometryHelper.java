/**
 * OceanDepth
 * The MIT License
 * Copyright (C) 2013 Mararok <mararok@gmail.com>
 */
package com.gmail.mararok.OceanDepth.Utility;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

/**
 *
 */
public class GeometryHelper {
	public static void genCircle(Location center, int radius, Material blockType) {
		World world = center.getWorld();
		int centerY = center.getBlockY();
		int locX = center.getBlockX(),locZ = center.getBlockZ();
		if (radius == 1) {
			world.getBlockAt(locX,centerY,locZ).setType(blockType);
			world.getBlockAt(locX+1,centerY,locZ).setType(blockType);
			world.getBlockAt(locX-1,centerY,locZ).setType(blockType);
			world.getBlockAt(locX,centerY,locZ+1).setType(blockType);
			world.getBlockAt(locX,centerY,locZ-1).setType(blockType);
		} else {
			int radiusSquared = radius * radius;
			for(int x = -radius; x <= radius; x++) {
			    for(int z = -radius; z <= radius; z++) {
			        if( (x*x) + (z*z) <= radiusSquared) {
			            locX = center.getBlockX() + x;
			            locZ = center.getBlockZ() + z;
			            world.getBlockAt(locX, centerY, locZ).setType(blockType);
			        }
			    }
			}
		}
	}
	
	public static void setBlockType(Location location, Material blockType) {
		location.getBlock().setType(blockType);
	}
}
