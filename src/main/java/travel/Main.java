package travel;

import jade.core.ProfileImpl;
import jade.core.Profile;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;
import travel.agent.ClientAgent;
import travel.agent.TravelAgencyAgent;

public class Main {

	public static void main(String[] args) {
		Runtime runtime = Runtime.instance();
		
		Profile profile = new ProfileImpl();
		profile.setParameter(Profile.MAIN_HOST, "localhost");
		profile.setParameter(Profile.MAIN_PORT, "9898");
		profile.setParameter(Profile.GUI, "true");
		
		AgentContainer mainContainer = 
				runtime.createMainContainer(profile);
		
		try {
			
			AgentController agency = mainContainer.createNewAgent(
					"Agency", 
					TravelAgencyAgent.class.getName(),
					null);
			AgentController client = mainContainer.createNewAgent(
					"Customer", ClientAgent.class.getName(), null);
			
			agency.start();
			client.start();
			
		} catch (StaleProxyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
