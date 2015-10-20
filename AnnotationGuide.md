# Introduction #
RDF/OWL individuals are identified by a unique URI. **jenabean** consults code annotations to contruct an URI for your beans.  Here's a simple example of a basic bean annotated correctly:
```
@Namespace("http://example.org/")
public class Book {
  private String isbn;
  private String title;

  public Book(String isbn) {
    this.isbn = isbn;
  }

  @Id
  public String isbn() {return isbn;}
  
  public void setTitle(String s) {title = s;}
  public String getTitle() { return title;}
}
```

For a book the natural choice for an id is its ISBN number.  jenabean merely looks for any method marked with @Id.  The contract requires that your id method return a unique id within scope of the class and namespace you supplied.


# Details #
There are three primary annotations:
  * @Namespace - gives a namespace to the java class.
  * @Id - indicates the primary key field of the bean.
  * @RdfProperty - allows you to override the default property naming convention of jenabean.

## thewebsemantic.Namespace( namespace ) ##
The @Namespace annotation is required and takes one argument.  Any object other than a primitive, primitive wrapper, or `java.util.Date` will be ignored by **jenabean** unless it has this annotation.  A good candidate for your namespace would be your domain name.  For practical purposes, you can consider the namespace value as similar to a java package.

```
import thewebsematic.Namespace;

@Namespace("http://example.org#")
public class Foo...

@Namespace("http://my.domain.com/")
public class Bar...

```

## thewebsemantic.Id ##
@Id is optional but recommended.  If ommited jenabean will use the object's `hashCode()` method to derive it's unique id.  `hashCode()` doesn't guarantee unique id's, and the generated ids will be difficult to recreate should you need to find your Individual once persisted.  It is therefore preferable that you mark one method as returning the beans Id.  This should be the most natural key property, for example, a User bean's id would be the screen name or even better, email address.  A book's id would be it's isbn number...

```
@Id
public String getIsbn() ...
```

jenabean does not support the old "multipart" key bondings like EJB.  Just create one method that returns the multipart key as a concatenated string.  The @Id method may also return an integer if you like.

## thewebsemantic.RdfProperty( uri ) ##
@RdfProperty is optional and applied to getter methods.  It's usefull when you do not want the default rdf property bindings (name...hasName...age...hasAge).  The argumnent supplied to @RdfProperty is a URI, an RDF property URI.  If the property exists in the model, it'll be reused, otherwise jena will create it.