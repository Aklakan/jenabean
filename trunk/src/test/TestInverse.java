package test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;

import org.junit.Test;
import static org.junit.Assert.*;

import thewebsemantic.Bean2RDF;
import thewebsemantic.RDF2Bean;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;

public class TestInverse {
	
	@Test
	public void testExtended() {
		OntModel m = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM_MINI_RULE_INF);	
		Bean2RDF writer = new Bean2RDF(m);
		
		Tag fun = new Tag("fun");
		Tag run = new Tag("run");
		Post p1 = new Post();
		p1.setTitle("i like OWL");
		p1.addTag(fun);
		p1.addTag(run);
		writer.save(p1); 
		//m.writeAll(System.out, "RDF/XML-ABBREV", "http://foo/");
		RDF2Bean reader = new RDF2Bean(m);
		reader.loadDeep(Post.class, p1.hashCode());
		Post test = reader.loadDeep(Post.class, p1.hashCode());
		assertEquals(2, test.getTags().size());
		
		Tag funLoaded = reader.loadDeep(Tag.class, "fun");
		Collection<Taggable> items = funLoaded.getItems();
		for (Object o : items) {
			assertTrue(o instanceof Post);
			Post p = (Post)o;
			assertEquals("i like OWL", p.getTitle());
			assertEquals(p.getTags().size(), 2);
		}
		
		
	}
	
	@Test
	public void testBasic() throws IOException {
		OntModel m = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM_MINI_RULE_INF);	
		Bean2RDF writer = new Bean2RDF(m);
		Apple a = new Apple();
		Apple aprime = new Apple();
		
		a.addOrange(new Orange());
		a.addOrange(new Orange());
		
		Orange o = new Orange();
		int rememberedId = o.hashCode();
		a.addOrange(o);
		aprime.addOrange(o);
		
		writer.save(a);
		writer.save(o);

		//m.write(System.out);
		RDF2Bean reader = new RDF2Bean(m);
		Collection<Orange> oranges = reader.loadDeep(Orange.class);

		for (Orange orange : oranges)
			assertEquals(1, orange.getApples().size());

		writer.save(aprime);
		m.write(new FileWriter("tmp.rdf"));
		m = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM_MINI_RULE_INF);
		reader = new RDF2Bean(m);
		m.read("file:tmp.rdf");
		o = reader.loadDeep(Orange.class, rememberedId);
		assertEquals(2, o.getApples().size());
		
		//without reasoner, it's back to none
		m = ModelFactory.createOntologyModel();
		reader = new RDF2Bean(m);
		m.read("file:tmp.rdf");
		o = reader.loadDeep(Orange.class, rememberedId);
		assertEquals(0, o.getApples().size());
		
	}
}
