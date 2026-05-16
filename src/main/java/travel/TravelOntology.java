package travel;

import java.io.FileInputStream;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.ontology.OntResource;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.util.iterator.ExtendedIterator;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class TravelOntology {
	private static final String FILE_PATH = "travel.owl";
	private static final String BASE = "http://www.owl-ontologies.com/travel.owl";
	private static final String NS = BASE + "#";
	
	private OntModel model;
	
	public TravelOntology() {
		loadOntology();
	}

	private void loadOntology() {
		model = ModelFactory.createOntologyModel(
				OntModelSpec.OWL_MEM_MICRO_RULE_INF);
		
		try(InputStream in = new FileInputStream(FILE_PATH)){
			model.read(in, BASE);
			System.out.println("Loaded successfully");
		}catch(Exception ex) {
			System.out.println("Loading failed");
		}		
	}
	
	public Destination getDestination(String name) {
		Individual ind = model.getIndividual(NS + name);
		
		if(ind == null)
			return null;
		
		OntClass destCls = model.getOntClass(NS + "Destination");
		if (destCls != null && !ind.hasOntClass(destCls, false)) 
			return null;
		
		Destination d = new Destination();
		d.setName(name);
		
		Property hasActivity = 
				model.getProperty(NS + "hasActivity");
		Property hasAccomodation = 
				model.getProperty(NS + "hasAccomodation");	
		
		StmtIterator stm = ind.listProperties();
		
		while(stm.hasNext()) {
			Statement s = stm.next();
			
			if(!s.getObject().isResource())
				continue;
			
			String objName = localName(s.getObject().asResource());
			
			if(s.getPredicate().equals(hasActivity)) {
				d.getActivities().add(objName);
			}else if(s.getPredicate().equals(hasAccomodation)) {
				d.getAccomodations().add(objName);
			}			
		}
		
		return d;		
	}
	
	public List<String> getAllDestinationsNames(){
		
		List<String> result = new ArrayList<>();		
		OntClass cls = model.getOntClass(NS + "Destination");		
		
		if(cls == null) 
			return result;
		
		ExtendedIterator<? extends OntResource> itr = 
				cls.listInstances();
		
		while(itr.hasNext()) {
			OntResource r = itr.next();
			
			if(r.getURI() != null)
				result.add(localName(r));			
		}
		
		return result;		
	}
	
	public static String localName(Resource r) {
		
		if(r.getURI() == null)
			return "";
		
		int pos = r.getURI().lastIndexOf('#');
		
		if(pos >= 0) {
			return r.getURI().substring(pos + 1);
		}else {
			return r.getURI();
		}		
	}
	
}
