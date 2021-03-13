package snowball.embroidernox.util.requirement;

import java.util.List;

import snowball.embroidernox.enumerator.classification.IClassifier;
import snowball.embroidernox.util.component.IRequirement;

public class ReqEating implements IRequirement {
	IClassifier classification;

	public ReqEating(IClassifier classification) {
		this.classification = classification;
	}
	
	public void requirement(List<String> breed) {
		breed.add("EATING;" + classification + ';');
	}
}