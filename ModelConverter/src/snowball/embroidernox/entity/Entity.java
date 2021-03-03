package snowball.embroidernox.entity;

import java.util.ArrayList;
import java.util.List;

import snowball.embroidernox.enumerator.*;
import snowball.embroidernox.interfaces.IComponent;
import snowball.embroidernox.util.Utils;
import snowball.embroidernox.util.Vector;

public class Entity {
	public static String check(Vector colour) {
		for (EnumColours e : EnumColours.values()) if (e.getColour() == colour) return e.name();
		return "CUSTOM";
	}
	
	public List<String> newEntity = new ArrayList<>();
	public List<String> component = new ArrayList<>();
	
	private final String name;
	
	private boolean hasExtraName;
	private boolean hasMainStage;
	private boolean randomize;
	private boolean alwaysVisible;
	
	private boolean hasCustomIcon;
	
	private float swimHeight;
	private int nameIndex;
	private int mainStage;
	private float iconSize;
	private float iconY;
	
	public Entity(String name, String classification, boolean isAquatic, float size) {
		pastLines(classification, isAquatic);
		firstLine(size);
		this.name = name;
	}
	
	protected final void firstLine(float size) {
		newEntity.add(Utils.value(size));

		if (hasExtraName && nameIndex > -1) newEntity.add(Utils.value("name", nameIndex));
		else newEntity.add("name;-1;");
		
		
		if (hasMainStage && mainStage >= 0) newEntity.add(Utils.value("id", mainStage));
		else newEntity.add("id;-1;");
		
		if (randomize) newEntity.add("randomize;1;"); else newEntity.add("randomize;0;");
		if (alwaysVisible) newEntity.add("alwaysVis;1"); else newEntity.add("alwaysVis;0");
		
		if (hasCustomIcon) newEntity.add(Utils.value(";size", iconSize) + "y;" + iconY);
		newEntity.add("\n");
	
		System.out.println("First Line of " + getName());
	}
	
	protected final void pastLines(String classification, boolean isAquatic) {
		newEntity.add(classification);
		newEntity.add("\n");
		
		if (isAquatic) newEntity.add("1;0;" + swimHeight); else newEntity.add("0;1");
		newEntity.add("\n");
		
		System.out.println("Past Lines of " + getName());
	}
	
	public List<String> load() {
		return newEntity;
	}	
	
	public String getName() { return name; }
	
	protected final void componentLoader(IComponent comp) {
		comp.load(component);
	}
	
	public List<String> loadComponents() {
		return component;
	}
	
	public void setHasCustomIcon(boolean hasCustomIcon) {
		this.hasCustomIcon = hasCustomIcon;
	}

	public void setAlwaysVisible(boolean alwaysVisible) {
		this.alwaysVisible = alwaysVisible;
	}

	public void setHasMainStage(boolean hasMainStage) {
		this.hasMainStage = hasMainStage;
	}
	
	public void setHasExtraName(boolean hasExtraName) {
		this.hasExtraName = hasExtraName;
	}
	
	public void setRandomize(boolean randomize) {
		this.randomize = randomize;
	}

	public void setSwimHeight(float swimHeight) {
		this.swimHeight = swimHeight;
	}

	public void setIconSize(float iconSize) {
		this.iconSize = iconSize;
	}

	public void setIconY(float iconY) {
		this.iconY = iconY;
	}

	public void setMainStage(int mainStage) {
		this.mainStage = mainStage;
	}

	public void setNameIndex(int nameIndex) {
		this.nameIndex = nameIndex;
	}
}
