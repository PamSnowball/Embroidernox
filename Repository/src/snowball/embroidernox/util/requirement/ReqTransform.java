package snowball.embroidernox.util.requirement;

import java.util.List;

import snowball.embroidernox.util.component.IRequirement;

public class ReqTransform implements IRequirement {
	float size;

	public ReqTransform(float targetSize) {
		this.size = targetSize;
	}
	
	public void requirement(List<String> breed) {
		breed.add("TRANSFORM;" + size + ';');
	}
}
