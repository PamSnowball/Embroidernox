package snowball.embroidernox.util.requirement;

import java.util.List;

import snowball.embroidernox.enumerator.EnumBiome;
import snowball.embroidernox.util.component.IRequirement;
import snowball.utils.Utils;

public class ReqNotBiome implements IRequirement {
	EnumBiome biome;
		
	float amount = 0.1F;
	
	public ReqNotBiome(EnumBiome biome) {
		this.biome = biome;
	}
		
	@Override
	public void requirement(List<String> req) {
		req.add(Utils.value("LIFE;type;2;enviroType;4;biome", biome.name(), "amount", amount));
	}
}
