package travel.agent;

import java.lang.management.CompilationMXBean;

import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import travel.TravelOntology;
import travel.behaviours.WaitForTravelRequest;
import travel.gui.TravelAgencyGUI;

public class TravelAgencyAgent extends Agent{
	
	private TravelOntology ontology;
		
	@Override
	protected void setup() {
		
		ontology = new TravelOntology();
		new TravelAgencyGUI(this);
		
		DFAgentDescription ad = new DFAgentDescription();
		ServiceDescription sd = new ServiceDescription();
		ad.setName(getAID());
	
		sd.setType("travel");
		sd.setName("FMI Travel Agency");
		
		ad.addServices(sd);
		
		try {
			DFService.register(this, ad);
			
		} catch (FIPAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		addBehaviour(new WaitForTravelRequest(
				ontology, this));
	
	}
	
	public void addDestination(String name) {
		ontology.addDestination(name);
	}
	
	public void deleteDestination(String name) {
		ontology.deleteDestination(name);
	}
	
	public void renameDestination(String oldName, String newName) {
		ontology.renameDestination(oldName, newName);
	}
	
	public void addActivity(String destination, String activity) {
		ontology.addPropertyToDestination(destination, activity, 
				"hasActivity");
	}
	
	public void removeActivity(String destination, String activity) {
		ontology.removePropertyFromDestination(destination, activity, 
				"hasActivity");
	}
	
	public void addAccomedation(String destination, String acc) {
		ontology.addPropertyToDestination(destination, acc, 
				"hasAccomodation");
	}
	
	public void removeAccomedation(String destination, String acc) {
		ontology.removePropertyFromDestination(destination, acc,
				"hasAccomodation");
	}

}
