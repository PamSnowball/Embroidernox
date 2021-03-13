package snowball.embroidernox.component.special;

import java.util.List;

import snowball.embroidernox.component.utils.IComponent;
import snowball.embroidernox.enumerator.classification.IClassifier;
import snowball.utils.Utils;

public class Nesting implements IComponent {
	boolean model;
	
	IClassifier classification;
	
	int stage;
	
	public Nesting(IClassifier classification, int hatchingStage, boolean decreasesModel) {
		
	}
	
	@Override
	public void load(List<String> loader) {
		loader.add(Utils.value("NESTING;index", classification.getClassification(), "hatchStage", stage, "decreasesModel", model ? 1 : 0));
	}

	@Override
	public int getId() {
		return 10;
	}
}
