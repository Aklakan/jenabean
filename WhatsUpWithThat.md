**Q:** _Why do I need to annotate my beans with an Id?_

Jenabean will create a URI for you, but it needs to know what makes your bean instance unique.  Its class path and class name are useful, but it's still necessary to distinguish between multiple instances of the same class.  In the absence of an @Id annotated field or method, Jenabean will use the object's hash code value (via the hashCode() method of java.lang.Object) but be warned, this is no unique, and will change when the bean is reconstituted from the triple store.

**Q:** _How does jenabean create URI's for me?_

Sans any annotations except @Id, jenabean uses the class's package as the URL domain and concatenates that with the classe's simple name, using '/' as the path separator.  Finally, it retrieves the object's id from the @Id annotated field or method.  The bean class "com.example.Product" with id value of 1 would have URI "http://com.example/Product/1".  The domain can be customized via the @Namespace annotation.  The rest of the URI is customizable by naming your classes appropriately and providing id's.

**Q:** _Why can't I use `java.util.ArrayList` or `java.utio.HashSet` as bean properties?_

Jenabean deals with the raw collection interfaces `Collection`, `List`, and `Set`.  In order to support lazy loading, and to detect when the collection is modified, Jenabean uses it's own special implementations of these interfaces.  This is in keeping with common practice regarding object to relational binding tools (hibernate, jpa).  In most common cases, you're encouraged to use Collection or Set.  If you need an ordered aggregate property, you may use List or an array type.

**Q:** _What type of concurrency does Jenabean support?_

Jenabean uses Jena style READ or WRITE locks when it saves or loads data.  Other than that its up to you to avoid stale writes (the model has changed by 3rd party, you save a bean with old data an overwrite their update).