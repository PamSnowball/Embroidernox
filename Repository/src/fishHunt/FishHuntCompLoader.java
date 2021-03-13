package fishHunt;

import blueprints.Blueprint;
import componentArchitecture.ComponentBlueprint;
import componentArchitecture.ComponentLoader;
import componentArchitecture.Requirement;
import utils.CSVReader;

public class FishHuntCompLoader implements ComponentLoader {
	  public ComponentBlueprint load(CSVReader reader, Blueprint blueprint) {
		  if (!reader.isEndOfLine()) {
			  String[] preys = new String[reader.getNextInt()];
			  for (int i = 0; i < preys.length; i++) {
				  preys[i] = reader.getNextString();
			  }
			  return new FishHuntCompBlueprint(preys);
		  } else {
			  String[] afs = new String[]{ "afs" };
			  return new FishHuntCompBlueprint(afs);
		  }
	  }
	  
	  public Requirement loadRequirement(CSVReader reader) {
		  return null;
	  }
}