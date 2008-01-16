package thewebsemantic;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.hp.hpl.jena.datatypes.xsd.XSDDateTime;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.ibm.icu.math.BigDecimal;

public class JenaHelper {

	public static Object convertLiteral(RDFNode node, Class<?> c) {
		return convertLiteral((Literal)node.as(Literal.class), c);
	}

	public static Object convertLiteral(Literal l, Class<?> c) {
		if (c.equals(Date.class)) {
			return date(l);
		} else if ( c.equals(Calendar.class)) {
			return calendar(l);
		} else if (c.equals(BigDecimal.class)) {
			return bigDecimal(l);
		} else
			return l.getValue();
	}
	
	public static Date date(Literal l) {
		XSDDateTime date = (XSDDateTime) l.getValue();
		return date.asCalendar().getTime();
	}
	
	public static Object bigDecimal(Literal l) {
		Object o = l.getValue();
		System.out.println(o.getClass());
		return null;
	}
	
	public static Calendar calendar(Literal l) {
		XSDDateTime date = (XSDDateTime) l.getValue();
		return date.asCalendar();
	}
	
	@SuppressWarnings("unchecked")
	public static List<Individual> listAllIndividuals(OntClass c) {
		return (c == null) ? new LinkedList<Individual>()
				: (List<Individual>) c.listInstances().toList();
	}

	public static Literal asLiteral(RDFNode n) {
		return (Literal) n.as(Literal.class);
	}

	public static Individual asInd(RDFNode n) {
		return (Individual) n.as(Individual.class);
	}
}
