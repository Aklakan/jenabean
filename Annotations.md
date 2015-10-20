## `@Id` ##
The only annotation required by Jenabean.  Place it on a field or getter method to indicate which property of your bean type is to be used as the beans identity.  If you are working with pre-existing OWL/RDF and are not in control of the URI's, your id type should be `java.net.URI`.  Jenabean supports the following id types (and their primitive equivalent)

  1. `java.lang.String`
  1. `java.lang.Integer`
  1. `java.lang.Long`
  1. `java.lang.Double`
  1. `java.lang.Float`
  1. `java.net.URI`

You should only annotate one field or one getter method with @Id.  Never mix and match method annotations with field annotations.  Use either field annotations or method annotations.

```
public class ExampleBean {
  @Id private int myid;
}
```

```
public class ExampleBean {
  private int myid;

  @Id
  public int getMyid() {return myid;}
  public void setMyid(int i) { myid=i;}
}
```


## `@Namespace` ##
Jenabean will create a default namespace for you bean based on its package.  Use this annotation when you want to override the default behavior.  After your bean has been namespaced, all of it's properties will inherit the same namespace.  If your namespace is "http://example.org/ontology#" and your bean has a name property, it's URI will be `http://example.org/ontology#name`.  `@Namespace` is a class level annotation.

```
@Namespace("http://example.org/ontology#")
public class ExampleBean {

```

## `@RdfProperty` ##