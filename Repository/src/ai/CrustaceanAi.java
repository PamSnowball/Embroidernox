package ai;

import aiBasics.AgeDependentAI;
import aiBasics.AiRoutine;
import aiComponent.Ai;
import aiComponent.AiProgramBlueprint;
import baseMovement.MovementComp;
import componentArchitecture.ComponentBundle;
import componentArchitecture.ComponentType;
import components.InformationComponent;
import growth.GrowthComponent;
import toolbox.Transformation;
import utils.CSVReader;

public class CrustaceanAi implements AiProgramBlueprint {
	private float minIdleTime = 5.0F;

	private float maxIdleTime = 10.0F;

	public Ai createInstance(ComponentBundle bundle) {
		MovementComp movementComp = (MovementComp)bundle.getComponent(ComponentType.MOVEMENT);
		GrowthComponent grower = (GrowthComponent)bundle.getComponent(ComponentType.GROWTH);
		InformationComponent info = (InformationComponent)bundle.getComponent(ComponentType.INFO);
		Transformation transform = (Transformation)bundle.getComponent(ComponentType.TRANSFORM);
	    SwimAi youngAi = new SwimAi(info, movementComp, transform);
		StopStartAi grownAi = new WaterWalkAi(movementComp, transform, info);
		grownAi.setIdleTimes(this.minIdleTime, this.maxIdleTime);
		return new AgeDependentAI(grower, new AiRoutine[] { (AiRoutine)youngAi, grownAi });
	}

	public void loadSettings(CSVReader reader) {
		if (!reader.isEndOfLine()) {
			this.minIdleTime = reader.getNextLabelFloat();
			this.maxIdleTime = reader.getNextLabelFloat();
		}
	}
}
