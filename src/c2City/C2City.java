package c2City;

import java.util.ArrayList;
import java.util.Random;

import net.sf.javaml.core.kdtree.KDTree;

public class C2City {
	
	private CityLoader cityLoader = null;
	private KDTree kDTree = null;
	
	public C2City(CityLoader cityLoader){
		this.cityLoader = cityLoader;
		this.kDTree = new KDTree(2);
		
		ArrayList<City> cities = this.cityLoader.getCities();
		for (City city : cities){
			this.kDTree.insert(new double[]{city.getLat(), city.getLng()},  (Object)city);
		}
	}
	
	public int getCityCount()
	{
		return this.cityLoader.getCities().size();
	}
	
	public String getFilename()
	{
		return this.cityLoader.getFilename();
	}
	
	public City getCity(float lat, float lng){
		return (City) this.kDTree.nearest(new double[]{lat,lng}); 
	}

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		C2City c2City = new C2City(new CityLoader("res/cities1000.txt"));
		long end  = System.currentTimeMillis();
		System.out.println(c2City.getCityCount() +" cities are read from \"" + c2City.getFilename() + "\" and inserted into KDTree: in " 
				+ (end-start) + " ms." );
		
		
		System.out.println("Nearest city to: 41.0136° N, 28.9550° E (Istanbul) is : " +  c2City.getCity(41.0136f, 28.9550f).getName() );
		System.out.println("Nearest city to: 37.3382° N, 121.8863° W (San Jose) is : " +  c2City.getCity(37.3382f, -121.8863f).getName() );
		System.out.println("Nearest city to: 33.8650° S, 151.2094° E (Sydney) is : " +  c2City.getCity(-33.8650f, -151.2094f).getName() );
		System.out.println("Nearest city to: 22.9068° S, 43.1729° W (Rio de Janerio) is : " +  c2City.getCity(-22.9068f, -43.1729f).getName() );
		
		System.out.println("Nearest city to: 40.8778° N, 29.3356° E (Tuzla, Istanbul) is : " +  c2City.getCity(40.8778f, 29.3356f).getName() );
		System.out.println("Nearest city to: 36.7068° N, 28.0938° E (Selimiye, Marmaris) is : " +  c2City.getCity(36.7068f, 28.0938f).getName() );
		
		Random rand = new Random();
		
		final long TEST_SIZE = 10000;
		start = System.currentTimeMillis();
		for ( int i = 0; i<TEST_SIZE; i++)
		{
			float lat = rand.nextFloat() * 180 - 90; 
            float lng = rand.nextFloat() * 180 - 90;
            
            c2City.getCity(lat, lng);
		}
		end  = System.currentTimeMillis();
		
		System.out.println("Average time for query: " + (end - start)/(float)TEST_SIZE + " ms");
		
	}
}
