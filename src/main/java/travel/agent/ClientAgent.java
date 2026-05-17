package travel.agent;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import travel.Destination;
import travel.gui.ClientGUI;

public class ClientAgent extends Agent{

	private ClientGUI gui;
	private String searchedDestination;
	private AID agency;	
	
	@Override
	protected void setup() {
		gui = new ClientGUI(this);
	}
	
	public void searchDestination(String search) {
		this.searchedDestination = search;
		addBehaviour(travelQuest);
	}
	
	private OneShotBehaviour travelQuest = new OneShotBehaviour() {
		
		@Override
		public void action() {
			DFAgentDescription da = new DFAgentDescription();
			ServiceDescription sd = new ServiceDescription();
			sd.setType("travel");
			da.addServices(sd);
			
			try {
				DFAgentDescription[] descrs = 
						DFService.search(myAgent, da);
				
				if(descrs.length > 0) {
					agency = descrs[0].getName();
					addBehaviour(new SearchForDestination());
				}else {
					System.out.println("No agencies");
				}
				
			} catch (FIPAException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	};

	private class SearchForDestination extends Behaviour {

		int step = 0;
		MessageTemplate mt;
		
		@Override
		public void action() {
			switch(step) {
				case 0:
					ACLMessage cfp = new ACLMessage(ACLMessage.CFP);
					cfp.addReceiver(agency);
					cfp.setContent(searchedDestination);
					cfp.setConversationId("slunce-more");
					
					mt = MessageTemplate.MatchConversationId("slunce-more");
					
					send(cfp);
					step++;				
				break;
				case 1:
					ACLMessage reply = receive(mt);
					
					if(reply == null) {
						block();
						return;
					}
					
					if(reply.getPerformative() == ACLMessage.PROPOSE) {
						ObjectMapper mapper = new ObjectMapper();
						
						try {
							Destination result = mapper.readValue(
									reply.getContent(), 
									Destination.class);
							gui.showResult(result.toString());
													
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}else {
						gui.showResult("We do not have this destination but we "
								+ "have this to offer:"
								+ reply.getContent());
					}					
					step++;				
				break;
				
			}
			
		}

		@Override
		public boolean done() {
			if(step == 2) return true;
			
			return false;
		}
		
		
	}
	
}
