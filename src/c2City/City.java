package c2City;

public class City{
	private final String name;
	private final String country;
	private final float lat;
	private final float lng;
	
	public City(String name, String country, float lat, float lng){
		this.name = name;
		this.country = country;
		this.lat = lat;
		this.lng = lng;
	}

	public final String getName() {
		return name;
	}

	public final String getCountry() {
		return country;
	}

	public final float getLat() {
		return lat;
	}

	public final float getLng() {
		return lng;
	}

	
}