package travel.behaviours;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import travel.Destination;
import travel.TravelOntology;

public class WaitForTravelRequest extends CyclicBehaviour{

	private TravelOntology ontology;
	
	public WaitForTravelRequest(TravelOntology ontology, Agent agent) {
		this.ontology = ontology;
		myAgent = agent;
	}
	
	@Override
	public void action() {
		MessageTemplate mt = MessageTemplate
				.MatchPerformative(ACLMessage.CFP);
		
		ACLMessage msg = myAgent.receive(mt);
		
		if(msg != null) {
			
			String dest = msg.getContent();
			ACLMessage reply = msg.createReply();
			
			Destination foundDestination = 
					ontology.getDestination(dest);
			
			if(foundDestination != null) {
				System.out.println("We have the destination");
				
				reply.setPerformative(ACLMessage.PROPOSE);
				reply.setContent(foundDestination.toString());
				reply.setLanguage("JSON");			
			}else {
				System.out.println("The destination is not found");
				
				reply.setPerformative(ACLMessage.INFORM_REF);
				List<String> all = ontology.getAllDestinationsNames();
				reply.setContent(String.join(", ", all));
			}
			
			myAgent.send(reply);			
			
		}else {
			block();
		}
		
	}

}
