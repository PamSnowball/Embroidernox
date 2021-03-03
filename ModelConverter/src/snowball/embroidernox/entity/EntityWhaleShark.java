package snowball.embroidernox.entity;

import snowball.embroidernox.component.Information;
import snowball.embroidernox.enumerator.EnumComponent;

public class EntityWhaleShark extends Entity {
	public EntityWhaleShark() {
		super("2004_Shark_Whale", "afb", true, 6);
		this.setHasCustomIcon(true);
		this.setNameIndex(-1);
		this.setMainStage(-1);
		this.setIconSize(1);
		this.setIconY(2.4F);
		this.setSwimHeight(-0.5F);
		System.out.println("Loaded Whale Shark Informations");
		
		this.componentLoader(new Information(1195, 273000, 775, 6, EnumComponent.EnumPlaceSound.SPLASH));
		
		EntityLoader.ENTITIES.add(this);
	}
}
