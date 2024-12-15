package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;


public class Hooks {
	@Before
	public void beforeScenario() throws IOException {
		if (placeAPIstepDefintion.placeid == null)
		{
			
			placeAPIstepDefintion step = new placeAPIstepDefintion();
			
			step.addplace_api_p_ayload_with("ayesha", "Chennai across center", "Arabic");
			
			step.user_calls_the_using_http_method("AddPlaceAPI", "POST");
			
			step.verify_the_place_id_from_created_maps_and_verify_by_using("ayesha", "getPlaceAPI");
			
		}

	}

}
