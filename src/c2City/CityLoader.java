package c2City;

import java.io.BufferedReader;
import java.io.FileInputStream;

import java.io.InputStreamReader;
import java.util.ArrayList;


public class CityLoader {
	
	private final String filename;
	private ArrayList<City> cities;
	
	
	public CityLoader(String filename)
	{
		this.filename = filename;
		this.cities = new ArrayList<City>();
		
		try {
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(
							new FileInputStream(this.filename), "UTF8"));
			String line;
			while ((line = reader.readLine()) != null) {
				
				String[] elems = line.split("\t");
				City a = new City(elems[1], elems[8], Float.parseFloat(elems[4]), Float.parseFloat(elems[5]));
				cities.add(a);
			}
			reader.close();

		} catch (Exception e) {
			System.err.format("Exception occurred trying to read '%s'.",
					filename);
			e.printStackTrace();
		}
		
	}

	public final ArrayList<City> getCities() {
		return cities;
	}

	public final String getFilename() {
		return filename;
	}

	public static void main(String[] args) {
		CityLoader cL = new CityLoader("res/cities1000.txt");
		System.out.println("Cities loaded: " + cL.getCities().size());
		
	}

}
