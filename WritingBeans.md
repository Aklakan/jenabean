Java developers have objects.  Jena has triples.  We need something to translate an object with one or more properties into a set of triples that loosely say the same thing.  The first step is to create an instance of `thewebsemantic.Bean2RDF`:

```
Bean2RDF writer = new Bean2RDF(myModel);
```

Jenabean attempts to be as non-intrusive as possible, however, it requires your bean to have an id field or method marked with the `@Id` annotation.

```
package example;
import thewebsemantic.Id;
public class Customer {
  @Id private int customerId;
  // class abbreviated
}
```

or using a method

```
public class Book {
  private String isbn;

  @Id public String getIsbn() {
    return isbn;
  }
  // class abbreviated
}
```

Saving or persisting a bean within a Jena model is a simple method call.

```
Customer cust = new Customer();
cust.setId(0);
writer.save(cust);
```

The results are a set of triples, which printed in N3 format using `m.write(System.out, "N3");` look like this...

```
:Customer
      a       rdfs:Class ;
      <http://thewebsemantic.com/javaclass>
              "example.Customer" .

<http://example/Customer/0>
      a       :Customer ;
      :customerId "0"^^xsd:int ;
      :name   "1st National"^^xsd:string .

```

By default the bean's package becomes the name space or domain for creating the entity and property URI's.  The class's simple name becomes the rdfs class name, and the bean's properties become the rdf property names.  This is the simplest example that can possibly work regarding saving beans via jenabean.  If you want to specifically map your class to a particular name space see NameSpaces.  Jenabean accepts several types for id fields, read more about IdProperties...