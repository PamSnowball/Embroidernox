package fishHunt;

import baseMovement.MovementComp;
import classification.Classification;
import classification.Classifier;
import componentArchitecture.Action;
import componentArchitecture.Component;
import componentArchitecture.ComponentBlueprint;
import componentArchitecture.ComponentBundle;
import componentArchitecture.ComponentType;
import entityBundle.EntityBundle;
import entityInfoGui.PopUpInfoGui;
import gameManaging.GameManager;
import hunting.PreyComp;
import instances.Entity;
import interpolation.Timer;
import java.util.List;
import org.lwjgl.util.vector.Vector3f;
import toolbox.Maths;
import utils.BinaryReader;
import utils.BinaryWriter;

public class FishHuntComponent extends Component {
	private String[] prey;
	  
	//Close Factor is 0.6
	//Alert Range is 2
	
	private final Timer timer = Timer.createLoopingTimer(0.2F, true);
	  
	private Entity entity;
	  
	private MovementComp mover;
	  
	protected FishHuntComponent(ComponentBlueprint blueprint, String[] preys) {
		super(blueprint);
		this.prey = preys;
	}
	  
	public void getStatusInfo(List<PopUpInfoGui> info) {}
	  
	public void getActions(List<Action> actions) {}
	  
	@Override
	public void update() {
	    super.update();
	    if (this.timer.check())
	    	alertPrey(); 
	}
	  
	private void alertPrey() {
		for (int i = 0; i < prey.length; i++) {
			final Classification c = Classifier.getClassification(prey[i]);
			Vector3f pos = this.mover.getTransform().getPosition();
			EntityBundle bundle = GameManager.getWorld().getListOfSpecies(c, 2, pos.x, pos.z);
			for (Entity bundleEntity : bundle) {
				PreyComp fleeComp = (PreyComp)bundleEntity.getComponent(ComponentType.FLEE);
				if (fleeComp == null) continue; 
				if (preyInRange(pos, bundleEntity.getTransform().getPosition(), fleeComp.getSafeRangeSquared()))
					fleeComp.alertToDanger(this.entity); 
	    	}
		}
	}
	  
	private boolean preyInRange(Vector3f predatorPos, Vector3f preyPos, float dangerRadius) {
		Vector3f.sub(preyPos, predatorPos, Maths.VEC3);
		return (Maths.VEC3.lengthSquared() < dangerRadius * 0.6F);
	}
	  
	public void export(BinaryWriter writer) {}
	  
	public void create(ComponentBundle bundle) {
		this.entity = bundle.getEntity();
	    this.mover = (MovementComp)bundle.getComponent(ComponentType.MOVEMENT);
	}
	  
	public void load(ComponentBundle bundle, BinaryReader reader) {
	    create(bundle);
	}
}