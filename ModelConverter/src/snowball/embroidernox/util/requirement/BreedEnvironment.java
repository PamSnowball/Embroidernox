package snowball.embroidernox.util.requirement;

import java.util.List;

import snowball.embroidernox.enumerator.EnumComponent.EnumBiome;
import snowball.embroidernox.interfaces.IEnvironment;
import snowball.embroidernox.util.Utils;

public class BreedEnvironment {
	static class EnvironmentAltitude implements IEnvironment {
		float influence;
		
		int min;
		int max;
		
		public EnvironmentAltitude(int minimumAltitude, int maximumAltitude, int healthInfluence) {
			this.influence = healthInfluence;
			this.min = minimumAltitude;
			this.max = maximumAltitude;
		}
		
		@Override
		public void environment(List<String> enviro) {
			enviro.add(Utils.value(1, "min", min, "max", max, "influence", influence));
		}
	}
	
	static class EnvironmentLikedBiome implements IEnvironment {
		boolean barren;
		
		EnumBiome[] biomes;
		
		float ideal;
		float influence;

		public EnvironmentLikedBiome(boolean growsBarren, EnumBiome[] likedBiomes, float ideal, float influence) {
			this.influence = influence;
			this.barren = growsBarren;
			this.biomes = likedBiomes;
			this.ideal = ideal;
		}
		
		@Override
		public void environment(List<String> enviro) {
			if (biomes != null) { 
				enviro.add(Utils.value(2, "barren", barren ? 1 : 0, "likedBiomes", biomes.length));
				for (EnumBiome biome : biomes) enviro.add(biome.getId() + ";");
				enviro.add(Utils.value("idealFactor", ideal, "influence", influence));
			}
		}
	}
	
	static class EnvironmentUnsuitableBiome implements IEnvironment {
		EnumBiome[] biomes;
		
		float influence;
		
		public EnvironmentUnsuitableBiome(EnumBiome[] dislikedBiomes, float influence) {
			this.biomes = dislikedBiomes;
			this.influence = influence;
		}
		
		@Override
		public void environment(List<String> enviro) {
			enviro.add(3 + ";dislikedBiomes;" + biomes.length);
			for (EnumBiome biome : biomes) enviro.add(biome.getId() + ";");
			enviro.add("influence;" + influence);
		}
	}
	
	static class EnvironmentFavouriteBiome implements IEnvironment {
		EnumBiome biome;
		
		float influence;
		
		public EnvironmentFavouriteBiome(EnumBiome favouriteBiome, float influence) {
			this.biome = favouriteBiome;
			this.influence = influence;
		}
		
		@Override
		public void environment(List<String> enviro) {
			enviro.add(4 + ";faveBiomes;" + biome.getId() + ";influence" + influence);
		}
	}
	
	static class EnvironmentLikedSpecies implements IEnvironment {
		
		
		
		
		@Override
		public void environment(List<String> enviro) {
		}
	}
}
