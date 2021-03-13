package snowball.embroidernox.util.requirement;

import java.util.List;

import snowball.embroidernox.util.component.IRequirement;

public class ReqFruitFall implements IRequirement {
	float target;

	public ReqFruitFall(float target) {
		this.target = target;
	}
	
	public void requirement(List<String> breed) {
		breed.add("FRUIT_FALL;" + target + ';');
	}
}
