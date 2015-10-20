OWL/RDF has classes.  Supposedly the ontology author gave the classnames some thought, like foaf:Person.  The easiest thing to do is to name your JavaBeans according to the ontology class they are bound to and use the `@Namespace` annotation...

```
@Namespace("http://xmlns.com/foaf/0.1/") 
public class Person {
        // abreviated
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
```

This will cause your bean to be saved as an instance of foaf:Person.  If your bean and ontology class names are mismatched, you can use @RdfType along with @Namespace:

```
@Namespace("http://xmlns.com/foaf/0.1/")
@RdfType("Person")
public class Fellow {
        // abreviated
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
```





@Namespace applies to the entire class, therefore, the name property in the example will also go under the foaf namespace as foaf:name, a valid foaf property.  The idea here is brevity.  We control our classes and the names of our properties, so assuming we name things according to the ontology, we can save some key strokes.  If your methods are not named according to the ontology, or you want to assert properties from a different namespace, you can explicity map any property to any URI correspoding to the ontology.  In this example, the bean property "children" is mapped to the semantic bible ontology's "parentOf" property.

```
	@RdfProperty("http://semanticbible.org/ns/2006/NTNames#parentOf")
	public Collection<Human> getChildren() {
		return children;
	}
```

Note that the same thing may also be done via the bean's fields.

```
	@RdfProperty("http://semanticbible.org/ns/2006/NTNames#parentOf")
	private Collection<Human> children;

```

You may use `@Id` and `@RdfProperty` on property getter methods or fields.  You should choose either one, don't annotate both fields and getter methods or mix the two.  Jenabean will assume after the first field annotation that it's a field based bean and ignore method annotations.