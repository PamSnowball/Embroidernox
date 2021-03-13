package components;

import blueprints.Blueprint;
import componentArchitecture.ComponentBlueprint;
import componentArchitecture.ComponentLoader;
import componentArchitecture.Requirement;
import resourceManagement.SoundCache;
import utils.CSVReader;

public class CustomInformationCompLoader extends InformationComponent.InformationCompLoader implements ComponentLoader {
	public ComponentBlueprint load(CSVReader reader, Blueprint actualBlueprint) {
		InformationComponent.InformationCompBlueprint blueprint = new InformationComponent.InformationCompBlueprint();
		blueprint.name = reader.getNextLabelString();
		blueprint.description = reader.getNextLabelString();
		blueprint.dpCost = reader.getNextLabelInt();
		blueprint.baseDpPerMin = reader.getNextLabelInt();
		blueprint.roamingRange = reader.getNextLabelInt();
		blueprint.placementSound = SoundCache.CACHE.requestSound(reader.getNextLabelString(), true);
		return blueprint;
	}
    
	public Requirement loadRequirement() {
		return null;
	}
}
