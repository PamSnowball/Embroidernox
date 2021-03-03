package snowball.embroidernox;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import snowball.embroidernox.ModelConverter.EquiliModel;

public class Model {
	private List<List<Integer>> pointer = new ArrayList<>();
	private List<List<Float>> vertex = new ArrayList<>();
	private List<List<Float>> normal = new ArrayList<>();
	private List<List<Float>> colour = new ArrayList<>();
	private List<List<String>> faces = new ArrayList<>();
	
	public Model(List<EquiliModel> models, File file, List<String> entity) throws IOException {
		for (int i = 0; i < models.size(); i++) {
			pointer.add(models.get(i).getPointer());
			vertex.add(models.get(i).getVertex());
			normal.add(models.get(i).getNormal());
			colour.add(models.get(i).getColour());
			faces.add(models.get(i).getFaces());
		}
		
		entity.add(String.valueOf(models.size()));
		entity.add("\n");
		
		convert(file, models.size(), entity);
	}

	private void convert(File file, int size, List<String> entity) throws IOException {
		int j = 1;
		for (int k = 0; k < size; k++) {
			int point = colour.get(k).size() / 3 - 1;
			
			entity.add(insert(String.format("%.4f", getMin(vertex.get(k), 0))) + ";");
			entity.add(insert(String.format("%.4f", getMin(vertex.get(k), 1))) + ";");
			entity.add(insert(String.format("%.4f", getMin(vertex.get(k), 2))) + ";");
			
			entity.add(insert(String.format("%.4f", getMax(vertex.get(k), 0))) + ";");
			entity.add(insert(String.format("%.4f", getMax(vertex.get(k), 1))) + ";");
			entity.add(insert(String.format("%.4f", getMax(vertex.get(k), 2))) + ";");
			
			entity.add("1");
			entity.add("\n");
			
			int pointerSize = pointer.get(k).size() - 1;
			String facesSize = String.valueOf(faces.get(k).size() - pointerSize) + ";";
			entity.add(facesSize  + String.valueOf(pointerSize));
			System.out.println(facesSize  + String.valueOf(pointerSize));
			
			for(int i = 0; i < faces.get(k).size(); i++) {	
				if (faces.get(k).get(i).contains("//")) {
					String[] convertedFaces = faces.get(k).get(i).split("//");
					int faces0 = Integer.parseInt(convertedFaces[0]) - 1;
					int faces1 = Integer.parseInt(convertedFaces[1]) - 1;
					entity.add(insert(String.format("%.4f", vertex.get(k).get(faces0 * 3))) + ";");
					entity.add(insert(String.format("%.4f", vertex.get(k).get(faces0 * 3 + 1))) + ";");
					entity.add(insert(String.format("%.4f", vertex.get(k).get(faces0 * 3 + 2))) + ";");
					entity.add(insert(String.format("%.4f", normal.get(k).get(faces1 * 3))) + ";");
					entity.add(insert(String.format("%.4f", normal.get(k).get(faces1 * 3 + 1))) + ";");
					entity.add(insert(String.format("%.4f", normal.get(k).get(faces1 * 3 + 2))));
					if (i + 1 < faces.get(k).size() && !faces.get(k).get(i + 1).contains("pointer"))
						entity.add(";");
					}	
			
				if (faces.get(k).get(i).contains("pointer")) {
					entity.add("\n");
					entity.add(String.valueOf(pointer.get(k).get(j) - pointer.get(k).get(j - 1) + ";"));
					entity.add(colour.get(k).get(point * 3).toString() + ";");
					entity.add(colour.get(k).get(point * 3 + 1).toString() + ";");
					entity.add(colour.get(k).get(point * 3 + 2).toString());
					entity.add("\n");
					point--;
					j++;
				}
			}
			
			j = 1;
			entity.add("\n");
		}
		
		FileWriter writer = new FileWriter(file);
		try {
			writer.write(":D Here is your Equilinox Model: \n" + entity.toString().replace(",", "").replace(" ", "").replace("[", "").replace("]", ""));
		} catch (Exception e) {
			writer.write("I Failed, sorry");
			e.printStackTrace();
		} finally {
			writer.close();
		}
	}

	public static Float getMax(List<Float> floatList, int axis) {
		Float max = floatList.get(axis); 
		for(int i = 0; i < floatList.size(); i += 3)
			if(floatList.get(i) > max) max = floatList.get(i);
		return max;
	}
	
	public static Float getMin(List<Float> floatList, int axis) {
		Float min = floatList.get(axis); 
		for(int i = 0; i < floatList.size(); i += 3)
			if(floatList.get(i) < min) min = floatList.get(i);
		return min;
	}

	public static String insert(String target) {
	    int position = 1;
	    if (target.contains("-")) position = 2;
		final int targetLen = target.length();
	    final char[] buffer = new char[targetLen + 1];
	    target.getChars(0, position, buffer, 0);
	    ".".getChars(0, 1, buffer, position);
	    target.getChars(position, targetLen, buffer, position + 1);
	    return new String(buffer);
	}
}
