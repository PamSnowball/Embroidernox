package snowball.mod.load;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import snowball.embroidernox.entity.Entity;
import snowball.mod.Mod;

public class PreInitializer {
	private ArrayList<Class<?>> classes;
	
	private ArrayList<String> mods;
		
	private File equilinoxFolder;
	private File equilinoxJarFile;
	private File modsFolder;

	public Map<List<Entity>, Mod> entities;
	
	public PreInitializer(File equilinoxFolder, File equilinoxJarFile, File modsFolder, List<Mod> mods) {
		this.equilinoxFolder = equilinoxFolder;
		this.equilinoxJarFile = equilinoxJarFile;
		this.modsFolder = modsFolder;
		this.mods = new ArrayList<>();
		
		for(Mod mod : mods) this.mods.add(mod.getModInfo().id());
		
		classes = new ArrayList<>();
		
		Embroider.addEntities(entities);
	}
	
	public void setEntities(List<Entity> entities, Mod mod) {
		this.entities.put(entities, mod);
	}
	
	public void addClass(Class<?> clazz) {
		classes.add(clazz);
	}
	
	public File getEquilinoxFolder() {
		return equilinoxFolder;
	}
	
	public File getEquilinoxJarFile() {
		return equilinoxJarFile;
	}
	
	public File getModsFolder() {
		return modsFolder;
	}
	
	public boolean isModPresent( String id ) {
		return mods.contains( id );
	}
	
	public List<Class<?>> getClasses() {
		return classes;
	}
}
