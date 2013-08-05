/**
 * OceanDepth
 * The MIT License
 * Copyright (C) 2013 Mararok <mararok@gmail.com>
 */
package com.gmail.mararok.OceanDepth.Enviroment;

import java.util.Arrays;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.generator.ChunkGenerator;

/**
 * 
 */
public class OceanDepthGenerator extends ChunkGenerator {
	private int PartsAmount;
	private int WaterPartsAmount;
	private byte[][] OceanChunk;

	private void generateOceanChunk(World oceanWorld) {
		byte[] waterPart = new byte[4096];
		Arrays.fill(waterPart, (byte)Material.STATIONARY_WATER.getId());
		PartsAmount = oceanWorld.getMaxHeight() / 16;
		OceanChunk = new byte[PartsAmount][];
		WaterPartsAmount = PartsAmount - 3;
		for (int partIndex = 0; partIndex < WaterPartsAmount;++partIndex) {
			OceanChunk[partIndex] = new byte[4096];
			System.arraycopy(waterPart,0,OceanChunk[partIndex],0,4096);
		}
		
		int x,z;
		int bedrockID = (byte)Material.BEDROCK.getId();
		int blockY = (0 & 0xF) << 8;
		for(x = 0; x < 16; x++) {
			for(z = 0; z < 16; z++) {
				OceanChunk[0][blockY | (z << 4) | x] = (byte) bedrockID;
			}
		}
	}
	
	@Override
	public Location getFixedSpawnLocation(World world, Random random) {
		return new Location(world,0,5,0);
	}
	@Override
	public byte[][] generateBlockSections(World world, Random random, int x,
			int z, BiomeGrid biomes) {
		setBiomes(biomes);
		if (OceanChunk == null) {
			generateOceanChunk(world);
		}
		
		byte[][] result = new byte[PartsAmount][];
		for (int partIndex = 0; partIndex < WaterPartsAmount;++partIndex) {
			result[partIndex] = new byte[4096];
			System.arraycopy(OceanChunk[partIndex],0,result[partIndex],0,4096);
		}
		return result;
		
	}
	
	void setBlock(byte[][] result, int x, int y, int z, byte blockID) {
		int shiftedY = y >> 4;
	    if (result[shiftedY] == null) {
	        result[shiftedY] = new byte[4096];
	    }
	    result[shiftedY][((y & 0xF) << 8) | (z << 4) | x] = blockID;
	}
	
	private void setBiomes(BiomeGrid biomes) {
		int x,z;
		for (x = 0; x < 15;++x) {
			for (z = 0; z < 15; ++z) {
				biomes.setBiome(x,z,Biome.OCEAN);
			}
		}
	}

}
