package snowball.embroidernox;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import snowball.embroidernox.entity.EntityLoader;
import snowball.utils.Utils;
import snowball.embroidernox.entity.Entity;

public class ModelConverter {
	public static File log = new File("log/");
	
	public static List<String> output;
	
	private static String o = "_0.obj";
	private static Entity currentlyConverting;
	
	public static void main(String[] args) throws IOException {
		log.mkdirs();
		
		Utils.checkForEquilinoxDir();
		Utils.checkForEquilinoxJar();
		
		for (Entity entity : EntityLoader.ENTITIES) {
    		File objFile = new File("raw/" + entity.getName() + o);
    		ModelConverter.currentlyConverting = entity;
    		System.out.println("Looking for " + objFile);
    		if (objFile.exists()) {
	    		System.out.println("Trying to convert " + objFile.getName().replace(o, ""));
	    		try {
	    			convert(objFile, entity);
	    		} catch (IOException e) {
	    			System.out.println("Failed to convert");
	    		}
    	    } else {
    	    	output.add("Couldn't file file with name" + entity.getName());
    	    }
    	}
		
		if (output != null) {
			File outputFile = new File("log/" + LocalDate.now() + "_" +  LocalTime.now().toString().replace(':', '-').replace('.', '-') + ".txt");
			
			try (FileWriter writer = new FileWriter(outputFile)) {
				writer.write(output.toString().replace(",", "").replace(" ", "").replace("[", "").replace("]", ""));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private static void convert(File objFile, Entity entity) throws IOException {
    	int stage = 0; int i = 0;
		String name = objFile.getName().replace(o, "");
		new File("converted/").mkdirs();
		int index = objFile.toString().lastIndexOf('.') - 1;
	    String objPath = objFile.toPath().toString();
	    
		File[] objFiles = new File[entity.getGrowthStages()];
		File[] mtlFiles = new File[entity.getGrowthStages()];
		
		for (int l = 0; l < entity.getGrowthStages(); l++) {
			File file = new File(objPath.substring(0, index) + stage + ".obj");
	    	
			if (file.exists()) {
    			stage++;
    			objFiles[l] = file;
    			mtlFiles[l] = new File(file.toString().replace(".obj", ".mtl"));
    			System.out.println("Got file: " + file);
    		}
    	}
		
    	System.out.println("Got all " + stage + " files");
		
		List<EquiliModel> models = new ArrayList<>();
		
		for (int j = 0; j < stage; j++) {
			List<Integer> pointer = new ArrayList<>();
			
			List<String> faces = new ArrayList<>();
			
			List<Float> vertex = new ArrayList<>();
			List<Float> normal = new ArrayList<>();
			List<Float> colour = new ArrayList<>();
			
			List<String> objLines = new ArrayList<>(Files.readAllLines(objFiles[j].toPath()));
			System.out.println("Getting all lines of " + objFiles[j]);
			
			List<String> mtlLines = new ArrayList<>(Files.readAllLines(mtlFiles[j].toPath()));

			mtlFiles[j] = new File(objFiles[j].toPath().toString().replace(".obj", ".mtl"));
			
			System.out.println("Converting " + objFiles[j].toString());
			System.out.println("Converting " + mtlFiles[j].toString());
			
			List<List<String>> stringList = new ArrayList<>();
			stringList.add(faces);
			stringList.add(mtlLines);
			
			List<List<Float>> floatList = new ArrayList<>();
			floatList.add(vertex);
			floatList.add(normal);
			floatList.add(colour);
			
			for (String objLine : objLines) {
				lineReader(stringList, pointer, floatList, i, objLine);
			}
			
			pointer.add(i);

			if (entity.hasMaterial()) {
				colour.set(0, -1F);
				colour.set(1, 0F);
				colour.set(2, 0F);
			}
			
			models.add(new EquiliModel(pointer, vertex, normal, colour, faces));
		}
		
		File txtFile = new File("converted/" + name + ".txt");
		new Model(models, txtFile, entity);
	}
	
	private static void lineReader(List<List<String>> stringList, List<Integer> pointer, List<List<Float>> floatList, int i, String objLine) {
		List<String> faces = stringList.get(0);
		List<String> mtlLines = stringList.get(1);
		
		List<Float> vertex = floatList.get(0);
		List<Float> normal = floatList.get(1);
		List<Float> colour = floatList.get(2);
 		
		String[] objSplit = objLine.split(" ");
		
		if (objSplit[0].equals("v")) {
			vertex.add(Float.parseFloat(objSplit[1]));
			vertex.add(Float.parseFloat(objSplit[2]));
			vertex.add(Float.parseFloat(objSplit[3]));
		}
	
		if (objSplit[0].equals("vn")) {
			normal.add(Float.parseFloat(objSplit[1]));
			normal.add(Float.parseFloat(objSplit[2]));
			normal.add(Float.parseFloat(objSplit[3]));
		}
		
		if (objSplit[0].equals("usemtl")) {
			faces.add("pointer");
			pointer.add(i);
			for (String mtlLine : mtlLines) {
				String[] mtlSplit = mtlLine.split(" ");
				if (mtlSplit[0].equals("Kd")) {
					colour.add(Float.parseFloat(mtlSplit[1]));
					colour.add(Float.parseFloat(mtlSplit[2]));
					colour.add(Float.parseFloat(mtlSplit[3]));
				}
			}
		}
	
		if (objSplit[0].equals("f")) {
			faces.add(objSplit[1]); 
			faces.add(objSplit[2]); 
			faces.add(objSplit[3]);
			i += 3;
		} 
	}

	public static Entity getCurrentlyConvertingEntity() {
		return currentlyConverting;
	}

	static class EquiliModel {
		private List<Integer> pointer;
		
		private List<Float> vertex;
		private List<Float> normal;
		private List<Float> colour;
	
		private List<String> faces;
		
		EquiliModel(List<Integer> pointer, List<Float> vertex, List<Float> normal, List<Float> colour, List<String> faces) {
			this.pointer = pointer;
			this.vertex = vertex;
			this.normal = normal;
			this.colour = colour;
			this.faces = faces;
		}

		public List<Integer> getPointer() { return pointer; }
		
		public List<Float> getVertex() { return vertex; }
		public List<Float> getNormal() { return normal; }
		public List<Float> getColour() { return colour; }
		
		public List<String> getFaces() { return faces; }
	}
}
