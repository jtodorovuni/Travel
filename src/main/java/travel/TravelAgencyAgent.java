package travel;

import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import travel.behaviours.WaitForTravelRequest;

public class TravelAgencyAgent extends Agent{
	
	
	
	@Override
	protected void setup() {
		
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
				new TravelOntology(), this));
	
	}

}
