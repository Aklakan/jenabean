package thewebsemantic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;

public class UriGen {
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		OntModel m = ModelFactory.createOntologyModel();	
		m.setNsPrefix("xsd", "http://www.w3.org/2001/XMLSchema#");
		FileInputStream file = new FileInputStream(args[0]);
		InputStreamReader r = new InputStreamReader(file,"UTF-8");
		m.read(r, null);
		List<RDFNode> props =  m.listOntProperties().toList();
		for (RDFNode node : props) {
			Property p = (Property)node.as(Property.class);
			System.out.println("public static final String " +
					p.getLocalName() + "_PROPERTY = \"" + 
					p.getURI() + "\";");
		}
		
		List<RDFNode> klasses =  m.listClasses().toList();
		for (RDFNode node : klasses) {
			OntClass p = (OntClass)node.as(OntClass.class);
			if (p.getLocalName() == null)
				continue;
			System.out.println("public static final String " +
					p.getLocalName() + "_CLASS = \"" + 
					p.getURI() + "\";");
		}
	}
}
