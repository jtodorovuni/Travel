package travel;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jade.tools.gui.ACLTimeChooserDialog;

public class Destination {

	private String name;
	private List<String> activities = new ArrayList<>();
	private List<String> accomodations = new ArrayList<>();
	private List<String> inferredTypes = new ArrayList<>();
		
	public List<String> getInferredTypes() {
		return inferredTypes;
	}

	public void setInferredTypes(List<String> inferredTypes) {
		this.inferredTypes = inferredTypes;
	}

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
	
	public String toPrettyString() {
		StringBuilder sb = new StringBuilder();
		sb.append(name).append(" \n");
		
		if(!activities.isEmpty()) {
			sb.append("\nActivities: ");
			sb.append(String.join(", ", activities));
		}
		
		if(!accomodations.isEmpty()) {
			sb.append("\nAccomedations: ");
			sb.append(String.join(", ", accomodations));
		}
		
		if(!inferredTypes.isEmpty()) {
			sb.append("\nTypes: ");
			sb.append(String.join(", ", inferredTypes));
		}
		
		return sb.toString();
	}
}
