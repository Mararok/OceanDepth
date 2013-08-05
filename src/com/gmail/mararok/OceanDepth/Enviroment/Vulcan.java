/**
 * OceanDepth
 * The MIT License
 * Copyright (C) 2013 Mararok <mararok@gmail.com>
 */
package com.gmail.mararok.OceanDepth.Enviroment;


import org.bukkit.Location;
import org.bukkit.Material;

import com.gmail.mararok.OceanDepth.Utility.GeometryHelper;

/**
 *
 */
public class Vulcan {
	private Location StartPosition;
	private Location CurrentPosition;
	
	private int CurrentHeight;
	private boolean EruptionEnded; 
	private boolean CoolingEnded;
	private int MaxHeight;
	private int StartHeight;
	public Vulcan(Location startPosition, int maxHeight) {
		StartPosition = startPosition;
		CurrentPosition = startPosition.clone();
		CurrentHeight = StartHeight = startPosition.getBlockY();
		MaxHeight = maxHeight+startPosition.getBlockY();
	}
	public void update() {
		if (EruptionEnded) {
			if (!CoolingEnded)
				updateCoolingEruption();
		} else {
			updateEruption();
		}
	}
	
	private void updateEruption() {
		CurrentPosition.setY(++CurrentHeight);
		GeometryHelper.genCircle(CurrentPosition,2,Material.STONE);
			
		CurrentPosition.setY(CurrentPosition.getY()+1);
		GeometryHelper.genCircle(CurrentPosition,1,Material.STONE);
			
		CurrentPosition.setY(CurrentPosition.getY()-1);
		GeometryHelper.genCircle(CurrentPosition,1,Material.STATIONARY_LAVA);
		
		if (CurrentHeight == MaxHeight) {
			EruptionEnded = true;
		}
	}
	
	private void updateCoolingEruption() {
		if (CurrentHeight > StartHeight) {
			GeometryHelper.genCircle(CurrentPosition,MaxHeight-CurrentHeight+1,Material.COBBLESTONE);
			int lava = MaxHeight-CurrentHeight-MaxHeight/3;
			GeometryHelper.genCircle(CurrentPosition,(lava > 0)?lava:1,Material.STATIONARY_LAVA);
			CurrentPosition.setY(CurrentPosition.getY()-1);
			--CurrentHeight;
		} else {
			CoolingEnded = true;
		}
	}
}
