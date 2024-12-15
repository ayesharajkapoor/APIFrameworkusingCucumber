package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.GoogleMAP;
import pojo.Location;

public class TesstDataBuild {
	public GoogleMAP addPlacePayload(String name, String address, String language) {
		GoogleMAP gm = new GoogleMAP();
		gm.setAccuracy(50);
		gm.setName(name);
		gm.setPhone_number("phone_number");
		gm.setAddress(address);
		gm.setWebsite("http://google.com");
		gm.setLanguage(language);
		Location l = new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		gm.setLocation(l);

		List<String> typesValue = new ArrayList<String>();
		typesValue.add("shoe park");
		typesValue.add("shop");

		gm.setTypes(typesValue);
		return gm;
	}
	
	public String deletePlaceAPI(String place_id) {
		
	return "{\r\n"
			+ "  \"place_id\": \""+place_id+"\"\r\n"
			+ "}";
		
	}

}
