Reading beans created with `Bean2RDF` is very easy.  When writing your bean to the model, jenabean left an annotation that indicated the class it was created from.  To read out RDF as objects use `thewebsemantic.RDF2Bean`.
```
RDF2Bean reader = new RDF2Bean(myModel);
```

There are 3 ways to access a bean.  First we can as `RDF2Bean` to give us all instances that are asserted as being instances of `Customer`.

```
Collection<Customer> customers = reader.load(Customer.class);
```

This returns a typed collection of 0 to n customer instances.  It will never return null.  It's important to note that the return type of `java.util.Collection` is intentional and meaningful.  There is no guaranteed ordering.  You may of course use standard java programming techniques to sort the collection as needed.  Second, you may load a particular bean by specifying it's class and id.

```
Customer cust = reader.load(Customer.class, 0);
```

This method will throw `thewebsemantic.NotFoundException` if a bean of the specified class and id is not found.  Finally, you may execute a SPARQL query against the model using `thewebsemantic.Sparql`.

```
String query = "...";
List customers = Sparql.exec(myModel, Customer.class, query);
```

In contrast, `Sparql.exec(...)` returns an ordered list, governed by the SPARQL query you provided.  Your SPARQL cannot be arbitrary and must be garaunteed to select instances of the correct type.  To learn more see SparqlQueries.



&lt;script&gt;

testXSS features

Unknown end tag for &lt;/scriptsdf&gt;

