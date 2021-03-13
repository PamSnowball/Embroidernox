package snowball.embroidernox.util.requirement;

import java.util.List;

import snowball.embroidernox.enumerator.EnumColours;
import snowball.embroidernox.util.component.IRequirement;

public class ReqMaterial implements IRequirement {
	EnumColours colour;
	
	public ReqMaterial(EnumColours colour) {
		this.colour = colour;
	}
	
	public void requirement(List<String> component) {
		component.add("MATERIAL;col;" + colour);
	}
}
