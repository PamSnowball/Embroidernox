package components;

import audio.Sound;
import blueprints.Blueprint;
import componentArchitecture.Action;
import componentArchitecture.Component;
import componentArchitecture.ComponentBlueprint;
import componentArchitecture.ComponentBundle;
import componentArchitecture.ComponentLoader;
import componentArchitecture.ComponentParams;
import componentArchitecture.ComponentType;
import componentArchitecture.ParamsBundle;
import componentArchitecture.Requirement;
import entityInfoGui.PopUpInfoGui;
import gameManaging.GameManager;
import instances.Entity;
import instances.EntityGetRequest;
import instances.EntityListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import languages.GameText;
import org.lwjgl.util.vector.ReadableVector3f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import resourceManagement.SoundCache;
import speciesInformation.SpeciesInfoLine;
import speciesInformation.SpeciesInfoType;
import textures.Texture;
import toolbox.Maths;
import toolbox.Transformation;
import utils.BinaryReader;
import utils.BinaryWriter;
import utils.CSVReader;
import world.GridIterator;
import world.GridSection;

public class InformationComponent extends Component {
  private Entity entity;
  
  private Entity parent;
  
  private Transformation transform;
  
  private InformationCompBlueprint blueprint;
  
  private Vector3f basePosition;
  
  private List<EntityListener> basePosChangeListeners = new ArrayList<>();
  
  private InformationComponent(InformationCompBlueprint blueprint) {
    super(blueprint);
    this.blueprint = blueprint;
  }
  
  public void create(ComponentBundle bundle) {
    this.transform = (Transformation)bundle.getComponent(ComponentType.TRANSFORM);
    this.entity = bundle.getEntity();
    InformationParams params = (InformationParams)bundle.getParameters(ComponentType.INFO);
    if (params != null) {
      this.basePosition = params.basePosition;
    } else {
      this.basePosition = generateBasePosition(this.transform.getPosition());
    } 
  }
  
  public void load(ComponentBundle bundle, BinaryReader reader) throws Exception {
    this.transform = (Transformation)bundle.getComponent(ComponentType.TRANSFORM);
    this.entity = bundle.getEntity();
    this.basePosition = reader.readVector();
    int parentId = reader.readInt();
    if (parentId >= 0)
      bundle.requestEntity(new EntityGetRequest(parentId) {
            public void provideEntity(Entity entity) {
              InformationComponent.this.parent = entity;
            }
          }); 
  }
  
  public void addBasePositionListener(EntityListener listener) {
    this.basePosChangeListeners.add(listener);
  }
  
  public void updateBasePosition() {
    Vector3f pos = this.transform.getPosition();
    this.basePosition.set((ReadableVector3f)generateBasePosition(pos));
    notifyListeners();
  }
  
  public void setBasePosition(Vector3f pos) {
    this.basePosition.set((ReadableVector3f)pos);
  }
  
  public void setParent(Entity entity) {
    this.parent = entity;
  }
  
  public Entity getParent() {
    return this.parent;
  }
  
  public void getActions(List<Action> actions) {}
  
  public String getName() {
    return this.blueprint.name;
  }
  
  public int getDpBonus() {
    return this.blueprint.dpCost;
  }
  
  public int getBaseDpPerMin() {
    return this.blueprint.baseDpPerMin;
  }
  
  public int getRoamingRange() {
    return this.blueprint.roamingRange;
  }
  
  @Override
  public boolean reproduce(ParamsBundle params, boolean boosted, Entity entity) {
    params.addParams(new InformationParams(new Vector3f((ReadableVector3f)this.basePosition)));
    return true;
  }
  
  public void getStatusInfo(List<PopUpInfoGui> info) {}
  
  public Vector3f getBasePosition() {
    return this.basePosition;
  }
  
  private void notifyListeners() {
    for (EntityListener listener : this.basePosChangeListeners)
      listener.execute(); 
  }
  
  private Vector3f generateBasePosition(Vector3f pos) {
    Vector3f position = getAwayFromWorldEdges(pos);
    if (this.blueprint.roamingRange % 2 == 0) {
      float f1 = clampToGridCorners(position.x);
      float f2 = clampToGridCorners(position.z);
      float f3 = GameManager.getWorld().getHeightOfTerrain(f1, f2);
      return new Vector3f(f1, f3, f2);
    } 
    float x = clampToGridCenter(position.x);
    float z = clampToGridCenter(position.z);
    float height = GameManager.getWorld().getHeightOfTerrain(x, z);
    return new Vector3f(x, height, z);
  }
  
  public Vector3f getAwayFromWorldEdges(Vector3f originalPosition) {
    float dis = this.blueprint.roamingRange * 0.5F * 2.5F;
    float x = Maths.clamp(originalPosition.x, dis, 100.0F - dis);
    float z = Maths.clamp(originalPosition.z, dis, 100.0F - dis);
    return new Vector3f(x, 0.0F, z);
  }
  
  private float clampToGridCorners(float pos) {
    float n = pos / 2.5F;
    n = Math.round(n);
    return n * 2.5F;
  }
  
  private float clampToGridCenter(float pos) {
    int n = (int)(pos / 2.5F);
    float gridCorner = n * 2.5F;
    return gridCorner + 1.25F;
  }
  
  public Vector3f getRandomInRangePoint() {
    float range = 2.5F * this.blueprint.roamingRange;
    float halfRange = range * 0.5F;
    float xOffset = Maths.RANDOM.nextFloat() * range - halfRange;
    float zOffset = Maths.RANDOM.nextFloat() * range - halfRange;
    return new Vector3f(this.basePosition.x + xOffset, 0.0F, this.basePosition.z + zOffset);
  }
  
  public Vector3f getRandomInRangePointInWorld() {
    float range = 2.5F * this.blueprint.roamingRange;
    float halfRange = range * 0.5F;
    float xOffset = Maths.RANDOM.nextFloat() * range - halfRange;
    float zOffset = Maths.RANDOM.nextFloat() * range - halfRange;
    Vector3f newPos = new Vector3f(this.basePosition.x + xOffset, 0.0F, this.basePosition.z + zOffset);
    if (newPos.x < 0.0F) {
      newPos.x *= -1.0F;
    } else if (newPos.x > 100.0F) {
      float overshoot = newPos.x - 100.0F;
      newPos.x = 100.0F - overshoot;
    } 
    if (newPos.z < 0.0F) {
      newPos.z *= -1.0F;
    } else if (newPos.z > 100.0F) {
      float overshoot = newPos.z - 100.0F;
      newPos.z = 100.0F - overshoot;
    } 
    return newPos;
  }
  
  public int getLocalPopulation() {
    return GameManager.getWorld().getPopulation(this.entity.getBlueprint().getSpeciesClassification(), this.blueprint.roamingRange, 
        this.basePosition.x, this.basePosition.z);
  }
  
  public Vector2f generateInRadiusRangePoint(float theta) {
    Vector2f point = genRadiusSpawn(theta, 0.2F, 2.0F);
    Vector3f position = this.transform.getPosition();
    Vector2f.add(point, new Vector2f(position.x, position.z), point);
    if (isPointInHomeArea(point.x, point.y))
      return point; 
    return null;
  }
  
  private Vector2f genRadiusSpawn(float theta, float innerRange, float outerRange) {
    float xVal = (float)Math.sin(theta);
    float zVal = (float)Math.cos(theta);
    float distance = Maths.randomNumberBetween(innerRange, outerRange);
    return new Vector2f(xVal * distance, zVal * distance);
  }
  
  public Vector3f getEvenInRangePoint() {
    GridIterator iterator = GameManager.getWorld().getIterator(this.basePosition.x, this.basePosition.z, 
        this.blueprint.roamingRange);
    List<GridSection> emptiestSections = new ArrayList<>();
    int smallest = Integer.MAX_VALUE;
    while (iterator.hasNext()) {
      GridSection gridSection = iterator.next();
      int entityPop = gridSection.getEntityCount(this.entity.getBlueprint().getSpeciesClassification());
      if (entityPop < smallest) {
        smallest = entityPop;
        emptiestSections.clear();
        emptiestSections.add(gridSection);
        continue;
      } 
      if (entityPop == smallest)
        emptiestSections.add(gridSection); 
    } 
    GridSection section = emptiestSections.get(Maths.RANDOM.nextInt(emptiestSections.size()));
    float xOffset = Maths.RANDOM.nextFloat() * 2.5F + (section.getTopLeftPosition()).x;
    float zOffset = Maths.RANDOM.nextFloat() * 2.5F + (section.getTopLeftPosition()).z;
    return new Vector3f(xOffset, 0.0F, zOffset);
  }
  
  public boolean isInBaseRange() {
    Vector3f pos = this.transform.getPosition();
    return isPointInHomeArea(pos.x, pos.z);
  }
  
  public boolean isPointInHomeArea(float posX, float posZ) {
    float range = 2.5F * this.blueprint.roamingRange;
    float halfRange = range * 0.5F;
    return (posX < this.basePosition.x + halfRange && posX > this.basePosition.x - halfRange && 
      posZ < this.basePosition.z + halfRange && posZ > this.basePosition.z - halfRange);
  }
  
  public Vector3f getRandomInRangePoint(int range) {
    float fullRange = 2.5F * range;
    float halfRange = fullRange * 0.5F;
    float xOffset = Maths.RANDOM.nextFloat() * fullRange - halfRange;
    float zOffset = Maths.RANDOM.nextFloat() * fullRange - halfRange;
    return new Vector3f(this.basePosition.x + xOffset, 0.0F, this.basePosition.z + zOffset);
  }
  
  public void clampToHomeArea(Vector3f position) {
    float range = 2.5F * this.blueprint.roamingRange;
    float halfRange = range * 0.5F;
    position.x = Maths.clamp(position.x, this.basePosition.x - halfRange, this.basePosition.x + halfRange);
    position.z = Maths.clamp(position.z, this.basePosition.z - halfRange, this.basePosition.z + halfRange);
  }
  
  public void export(BinaryWriter writer) throws IOException {
    writer.writeVector(this.basePosition);
    if (this.parent == null) {
      writer.writeInt(-1);
    } else {
      writer.writeInt(this.parent.getId());
    } 
  }
  
  public static class InformationCompBlueprint extends ComponentBlueprint {
    String name;
    
    String description;
    
    Texture icon;
    
    boolean flipTexture = false;
    
    int dpCost;
    
    int baseDpPerMin;
    
    int roamingRange;
    
    Sound placementSound;
    
    public InformationCompBlueprint() {
      super(ComponentType.INFO);
    }
    
    public Component createInstance() {
      return new InformationComponent(this);
    }
    
    public Texture getIcon() {
      return this.icon;
    }
    
    public String getName() {
      return this.name;
    }
    
    public String getDescription() {
      return this.description;
    }
    
    public Sound getPlacementSound() {
      return this.placementSound;
    }
    
    public int getBaseDppm() {
      return this.baseDpPerMin;
    }
    
    public void getInfo(Map<SpeciesInfoType, List<SpeciesInfoLine>> info) {}
    
    public int getPrice() {
      return this.dpCost;
    }
    
    public void delete() {
      SoundCache.CACHE.releaseSound(this.placementSound);
    }
    
    public void setIcon(Texture texture) {
      this.icon = texture;
      this.flipTexture = true;
    }
    
    public boolean isFlipTexture() {
      return this.flipTexture;
    }
    
    public int getRange() {
      return this.roamingRange;
    }
  }
  
  public static class InformationCompLoader implements ComponentLoader {
    public ComponentBlueprint load(CSVReader reader, Blueprint actualBlueprint) {
      InformationComponent.InformationCompBlueprint blueprint = new InformationComponent.InformationCompBlueprint();
      blueprint.name = GameText.getText(reader.getNextLabelInt());
      blueprint.description = GameText.getText(reader.getNextLabelInt());
      blueprint.dpCost = reader.getNextLabelInt();
      blueprint.baseDpPerMin = reader.getNextLabelInt();
      blueprint.roamingRange = reader.getNextLabelInt();
      blueprint.placementSound = SoundCache.CACHE.requestSound(reader.getNextLabelString(), true);
      return blueprint;
    }
    
    public Requirement loadRequirement(CSVReader reader) {
      return null;
    }
  }
  
  public static class InformationParams extends ComponentParams {
    private final Vector3f basePosition;
    
    public InformationParams(Vector3f basePosition) {
      super(ComponentType.INFO);
      this.basePosition = basePosition;
    }
  }
}
