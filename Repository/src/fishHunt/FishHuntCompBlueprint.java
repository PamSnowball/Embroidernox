package fishHunt;

import componentArchitecture.Component;
import componentArchitecture.ComponentBlueprint;
import componentArchitecture.ComponentType;
import java.util.List;
import java.util.Map;
import speciesInformation.SpeciesInfoLine;
import speciesInformation.SpeciesInfoType;

public class FishHuntCompBlueprint extends ComponentBlueprint {
	private final String[] prey;  
	
	protected FishHuntCompBlueprint(String[] preys) {
		super(ComponentType.FISH_HUNT);
		this.prey = preys;
	}
	  
	public Component createInstance() {
		return new FishHuntComponent(this, prey);
	}
	  
	public void delete() {}
	  
	public void getInfo(Map<SpeciesInfoType, List<SpeciesInfoLine>> info) {}
}

