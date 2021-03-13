package ai;

import baseMovement.MovementComp;
import components.InformationComponent;
import toolbox.Transformation;

public class WaterWalkAi extends StopStartAi {
	private final MovementComp mover;
	
	public WaterWalkAi(MovementComp mover, Transformation transform, InformationComponent info) {
		super(mover, transform, info, false);
		this.mover = mover;
		switchToWander();
	}
	
	@Override
	protected void updateWander() {
		boolean reached = mover.goToTarget(getTarget(), false, 0.3F);
		if (reached)
			if (mover.isSwimming()) switchToIdle();
			else switchToWander();
	}
}
