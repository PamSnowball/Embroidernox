package componentArchitecture;

import aiComponent.AiCompLoader;
import baseMovement.MovementCompLoader;
import beavers.BeaverCompLoader;
import beavers.WoodCompLoader;
import biomes.SpreaderCompLoader;
import birdHunt.BirdHuntCompLoader;
import blueprints.Blueprint;
import building.BuildCompLoader;
import building.BuilderCompLoader;
import building.DecomposeCompLoader;
import carnivorePlant.FlyTrapCompLoader;
import carnivorePlant.TongueShootCompLoader;
import components.LilyComponent;
import components.NameComponent;
import eating.EatingCompLoader;
import equipping.EquipCompLoader;
import equipping.ItemCompLoader;
import fighting.FightCompLoader;
import fighting.HostileCompLoader;
import fishHunt.FishHuntCompLoader;
import flinging.FlingingCompLoader;
import food.FoodCompLoader;
import fruit.DecayCompLoader;
import fruit.FruitFallLoader;
import fruit.FruiterCompLoader;
import growth.GrowthCompLoader;
import healer.HealerCompLoader;
import health.LifeCompLoader;
import hiveComponents.BeeCompLoader;
import hiveComponents.HiveCompLoader;
import hunting.FleeCompLoader;
import hunting.HuntCompLoader;
import loot.DropCompLoader;
import materials.MaterialComponent;
import meerkats.BurrowCompLoader;
import meerkats.TimeOutCompLoader;
import monkeys.ClingCompLoader;
import monkeys.TreeSwingCompLoader;
import nesting.NestingCompLoader;
import nightBloom.BloomCompLoader;
import panic.PanicCompLoader;
import particleComponent.ParticleCompLoader;
import peacock.PeacockCompLoader;
import perching.PerchCompLoader;
import perching.PercherCompLoader;
import simpleAnimations.AnimationCompLoader;
import sleeping.SleepCompLoader;
import sound.RandomSounderLoader;
import sound.SoundCompLoader;
import sound.SoundLooperLoader;
import spitting.ProjectileCompLoader;
import spitting.SpitCompLoader;
import stinging.StingingCompLoader;
import sunFacer.SunFaceCompLoader;
import toolbox.Transformation;
import treeCharging.TreeChargeCompLoader;
import components.InformationComponent;
import components.CustomInformationCompLoader;
import utils.CSVReader;

public enum ComponentType {
	TRANSFORM((ComponentLoader)new Transformation.TransformLoader(), false, false),
	MESH(null, false, false),
	MATERIAL((ComponentLoader)new MaterialComponent.MaterialCompLoader(), false, false),
	SOUND((ComponentLoader)new SoundCompLoader(), false, true),
	NAME((ComponentLoader)new NameComponent.NameCompLoader(), false, false),
	INFO((ComponentLoader)new InformationComponent.InformationCompLoader(), false, false),
	CUSTOM_INFO((ComponentLoader)new CustomInformationCompLoader(), false, false),
	LIFE((ComponentLoader)new LifeCompLoader(), false, true),
	ANIMATION((ComponentLoader)new AnimationCompLoader(), true, true),
	PARTICLES((ComponentLoader)new ParticleCompLoader(), false, true),
	HEALER((ComponentLoader)new HealerCompLoader(), false, false),
	SPREADER((ComponentLoader)new SpreaderCompLoader(), false, false),
	MOVEMENT((ComponentLoader)new MovementCompLoader(), true, true),
	AI((ComponentLoader)new AiCompLoader(), false, true),
	EATING((ComponentLoader)new EatingCompLoader(), false, true),
	FOOD((ComponentLoader)new FoodCompLoader(), false, false),
	FRUITER((ComponentLoader)new FruiterCompLoader(), false, true),
	GROWTH((ComponentLoader)new GrowthCompLoader(), false, true),
	LILY((ComponentLoader)new LilyComponent.LilyCompLoader(), true, true),
	RANDOM_SOUNDER((ComponentLoader)new RandomSounderLoader(), false, true),
	FLEE((ComponentLoader)new FleeCompLoader(), true, false),
	FRUIT_FALL((ComponentLoader)new FruitFallLoader(), false, true),
	DECAY((ComponentLoader)new DecayCompLoader(), true, true),
	BUILD((ComponentLoader)new BuildCompLoader(), false, false),
	BUILDER((ComponentLoader)new BuilderCompLoader(), false, true),
	HIVE((ComponentLoader)new HiveCompLoader(), false, true),
	PERCH((ComponentLoader)new PerchCompLoader(), false, false),
	BEAVER((ComponentLoader)new BeaverCompLoader(), true, true),
	EQUIP((ComponentLoader)new EquipCompLoader(), false, true),
	WOOD((ComponentLoader)new WoodCompLoader(), false, false),
	SLEEP((ComponentLoader)new SleepCompLoader(), true, true),
	CHARGE((ComponentLoader)new TreeChargeCompLoader(), true, true),
	PANIC((ComponentLoader)new PanicCompLoader(), true, true),
	HUNT((ComponentLoader)new HuntCompLoader(), true, true),
	FIGHT((ComponentLoader)new FightCompLoader(), true, false),
	DROP((ComponentLoader)new DropCompLoader(), false, false),
	ITEM((ComponentLoader)new ItemCompLoader(), true, true),
	NESTING((ComponentLoader)new NestingCompLoader(), false, false),
	DECOMPOSE((ComponentLoader)new DecomposeCompLoader(), false, true),
	PERCHER((ComponentLoader)new PercherCompLoader(), false, false),
	FLINGING((ComponentLoader)new FlingingCompLoader(), true, true),
	CLING((ComponentLoader)new ClingCompLoader(), true, true),
	TREE_SWING((ComponentLoader)new TreeSwingCompLoader(), true, true),
	HOSTILE((ComponentLoader)new HostileCompLoader(), true, true),
	BEE((ComponentLoader)new BeeCompLoader(), false, false),
	FISH_HUNT((ComponentLoader)new FishHuntCompLoader(), true, true),
	PEACOCK((ComponentLoader)new PeacockCompLoader(), true, true),
	SOUND_LOOPER((ComponentLoader)new SoundLooperLoader(), false, true),
	SUN_FACER((ComponentLoader)new SunFaceCompLoader(), true, true),
	SPITTING((ComponentLoader)new SpitCompLoader(), true, true),
	PROJECTILE((ComponentLoader)new ProjectileCompLoader(), true, true),
	STINGING((ComponentLoader)new StingingCompLoader(), true, true),
	BLOOM((ComponentLoader)new BloomCompLoader(), false, true),
	BIRD_HUNT((ComponentLoader)new BirdHuntCompLoader(), true, true),
	TONGUE_SHOOT((ComponentLoader)new TongueShootCompLoader(), true, true),
	FLY_TRAP((ComponentLoader)new FlyTrapCompLoader(), false, true),
	BURROW((ComponentLoader)new BurrowCompLoader(), true, true),
	TIME_OUT((ComponentLoader)new TimeOutCompLoader(), false, true);
  
	private ComponentLoader loader;
	  
	private boolean dynamic;
	  
	private boolean active;
	  
	ComponentType(ComponentLoader loader, boolean dynamic, boolean active) {
	    this.loader = loader;
	    this.dynamic = dynamic;
	    this.active = active;
	}
	  
	public ComponentBlueprint loadComponent(CSVReader reader, Blueprint blueprint) {
	    return this.loader.load(reader, blueprint);
	}
	  
	public Requirement loadRequirement(CSVReader reader) {
	    return this.loader.loadRequirement(reader);
	}
	  
	protected boolean isDynamic() {
	    return this.dynamic;
	}
	  
	public boolean isActive() {
	    return this.active;
	}
	  
	public static ComponentType getType(int id) {
		return values()[id];
	}
}
