Jenabean can load a beans given a SPARQL query and a java type.  Your query must comply with a simple pattern by selecting a single subject "?s".

```
SELECT ?s WHERE { ... }
```

Jenabean expects that the single bound return variable be named "?s".  It's also important that your query intentionaly return resources that are mappable to the desired type.  The easiest way to do this is sparql is add a type assertion:

```
SELECT ?s WHERE { 
   ?s a <http://test.bean/Man> . 
   ...
}
```

Once you've crafted your SPARQL query use `thewebsemantic.Sparql` to run the query, passing it a jena model, a type, and your query.
```
String 	queryString = 
	"SELECT ?s WHERE { ?s a <http://test.bean/FatBean> }";
Collection<FatBean> result2 = Sparql.exec(m, FatBean.class, queryString);
```