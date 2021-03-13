package snowball.mod;

import snowball.mod.load.PreInitializer;

public interface Mod {
	public void preInitializer(PreInitializer preInit);
	
	default ModInfo getModInfo() {
		return this.getClass().getDeclaredAnnotation(ModInfo.class);
	}
}
