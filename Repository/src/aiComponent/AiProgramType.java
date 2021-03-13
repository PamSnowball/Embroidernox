package aiComponent;

import ai.BeeAiBlueprint;
import ai.CrustaceanAi;
import ai.DolphinAiBlueprint;
import ai.MeerkatAi;
import ai.PatrolAiBlueprint;
import ai.PatrolWithSwimAi;
import ai.SwimAiBlueprint;
import ai.TortoiseAi;
import ai.WalkingBirdAiBlueprint;
import birds.BirdAiBlueprint;
import utils.CSVReader;

public enum AiProgramType {
	PATROL {
		protected AiProgramBlueprint createProgramBlueprint() {
			return new PatrolAiBlueprint();
		}
	},
	SWIM {
		protected AiProgramBlueprint createProgramBlueprint() {
			return new SwimAiBlueprint();
		}
	},
	BIRD {
		protected AiProgramBlueprint createProgramBlueprint() {
			return new BirdAiBlueprint();
		}
	},
	WALKING_BIRD {
		protected AiProgramBlueprint createProgramBlueprint() {
			return new WalkingBirdAiBlueprint();
		}
	},
	BEE {
		protected AiProgramBlueprint createProgramBlueprint() {
			return new BeeAiBlueprint();
		}
	},
	PATROL_WITH_SWIM {
		protected AiProgramBlueprint createProgramBlueprint() {
			return new PatrolWithSwimAi();
		}
	},
	TORTOISE {
		protected AiProgramBlueprint createProgramBlueprint() {
			return new TortoiseAi();
		}
	},
	MEERKAT {
		protected AiProgramBlueprint createProgramBlueprint() {
			return new MeerkatAi();
		}
	},
	DOLPHIN {
		protected AiProgramBlueprint createProgramBlueprint() {
			return new DolphinAiBlueprint();
		}	
	},
	CRUSTACEAN {
		protected AiProgramBlueprint createProgramBlueprint() {
			return new CrustaceanAi();
		}	
	};
  
	public AiProgramBlueprint loadProgramBlueprint(CSVReader reader) {
		AiProgramBlueprint program = createProgramBlueprint();
		program.loadSettings(reader);
		return program;
	}
  
	protected abstract AiProgramBlueprint createProgramBlueprint();
}
