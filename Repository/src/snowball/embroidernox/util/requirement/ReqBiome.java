package snowball.embroidernox.util.requirement;

import java.util.List;

import snowball.embroidernox.enumerator.EnumBiome;
import snowball.embroidernox.util.component.IRequirement;
import snowball.utils.Utils;

public class ReqBiome implements IRequirement {
	EnumBiome biome;
		
	float amount;
		
	public ReqBiome(EnumBiome biome, float amount) {
		this.biome = biome;
		this.amount = amount;
	}
		
	@Override
	public void requirement(List<String> req) {
		req.add(Utils.value("LIFE;type;2;enviroType;1;biome", biome.name(), "amount", amount));
	}
}