package snowball.embroidernox.component;

import java.util.List;

import snowball.embroidernox.interfaces.IComponent;
import snowball.embroidernox.util.Utils;

public class Ai {
	protected static class BaseAi implements IComponent {
		String id;
		
		BaseAi(String id) {
			this.id = id;
		}
		
		public void load(List<String> brain) {
			brain.add("AI;" + id); 
		}
	}
	
	static class PatrolAi extends BaseAi {
		float minIdleTime = 5.0F;
		float maxIdleTime = 10.0F;
		
		public PatrolAi() { super("PATROL"); }
		
		public PatrolAi(float minIdleTime, float maxIdleTime) {
			super("PATROL");
			this.minIdleTime = minIdleTime;
			this.maxIdleTime = maxIdleTime;
		}
		
		@Override
		public void load(List<String> brain) {
			super.load(brain);
			if (minIdleTime != 5.0F || maxIdleTime != 10.0F) brain.add(Utils.value(";minIdleTime", minIdleTime, "maxIdleTime") + maxIdleTime);
		}
	}
	
	static class SwimAi extends BaseAi { SwimAi() { super("SWIM"); } }
	
	static class BirdAi extends BaseAi {
		float circleRot = 50.0F;
		float circleTime = 2.0F;
		
		public BirdAi() { super("BIRD"); }

		public BirdAi(float circleRot, float circleMinTime) {
			super("BIRD");
			this.circleRot = circleRot;
			this.circleTime = circleMinTime;
		}

		@Override
		public void load(List<String> brain) {
			super.load(brain);
			if (circleRot != 50.0F || circleTime != 2.0F) brain.add(Utils.value(";circleRot", circleRot, "circleTime") + circleTime);
		}
	}
	
	static class WalkingBirdAi extends BaseAi {
		float minIdleTime;
		float maxIdleTime;
		boolean stayOnLand;
		
		public WalkingBirdAi() { super("WALKING_BIRD"); }
		
		public WalkingBirdAi(float minIdle, float maxIdle, boolean stayOnLand) {
			super("WALKING_BIRD");
			this.minIdleTime = minIdle;
			this.maxIdleTime = maxIdle;
			this.stayOnLand = stayOnLand;
		}

		@Override
		public void load(List<String> brain) {
			super.load(brain);
			brain.add(Utils.value("minIdle", minIdleTime, "maxIdle", maxIdleTime, "stayOnLand", (stayOnLand ? 1 : 0)));
		}
	}
	
	static class BeeAi extends BaseAi { BeeAi() { super("BEE"); } }
	
	static class PatrolWithSwimAi extends PatrolAi {
		public PatrolWithSwimAi() {
			this.id = "PATROL_WITH_SWIM";
		}
		
		public PatrolWithSwimAi(float minIdleTime, float maxIdleTime) {
			super(minIdleTime, maxIdleTime);
			this.id = "PATROL_WITH_SWIM";
		}
	}
	
	static class TortoiseAi extends BaseAi { TortoiseAi() { super("TORTOISE"); } }
	
	static class MeerkatAi extends BaseAi {
		float minIdleTime = 7.0F;
		float maxIdleTime = 15.0F;
		
		public MeerkatAi() { super("MEERKAT"); }
		
		public MeerkatAi(float minIdleTime, float maxIdleTime) {
			super("MEERKAT");
			this.minIdleTime = minIdleTime;
			this.maxIdleTime = maxIdleTime;
		}

		@Override
		public void load(List<String> brain) {
			super.load(brain);
			if (minIdleTime != 7.0F || maxIdleTime != 15.0F) brain.add(Utils.value(";minIdleTime", minIdleTime, "maxIdleTime") + maxIdleTime);	
		}
	}
	
	static class DolphinAi extends BaseAi { DolphinAi() { super("DOLPHIN"); } }
}
