package travel;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Destination {

	private String name;
	private List<String> activities = new ArrayList<>();
	private List<String> accomodations = new ArrayList<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getActivities() {
		return activities;
	}

	public void setActivities(List<String> activities) {
		this.activities = activities;
	}

	public List<String> getAccomodations() {
		return accomodations;
	}

	public void setAccomodations(List<String> accomodations) {
		this.accomodations = accomodations;
	}

	@Override
	public String toString() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			
			return mapper.writeValueAsString(this);
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return "";
	}
}
