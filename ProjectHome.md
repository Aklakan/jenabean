<table border='0'>
<tr>
<td valign='top'><img src='http://farm4.static.flickr.com/3538/3357219707_716e58b5a7_o.png' />
</td>
<td>
<b>jenabean</b> uses <a href='http://jena.sourceforge.net/'>Jena's</a> flexible RDF/OWL api to persist java beans.  It takes an unconventional approach to binding that is driven by the java object model rather than an OWL or RDF schema.  <b>jenabean</b> is annotation based and does not place any interface or extension requirements on your java object model.  By default <b>jenabean</b> uses typical java bean conventions to derive RDF property URI’s, for example, the java bean property “name” would become RDF property “:name”.  <b>jenabean</b> allows for explicit binding between an object property and a particular RDF property.  (See <a href='http://jenabean.googlecode.com/svn/trunk/src/example/java/example/Book.java'>Book.java</a> for an example of binding arbitrary bean properties to the Dublin Core schema.)<br>
</td>

</tr>
</table>



### Code Examples ###
To get a quick idea of how using jenabean might look see [the junit test class](http://jenabean.googlecode.com/svn/trunk/basic/src/test/java/test/bean/TestBean2Rdf.java).  There is also a very simple [stripes application](http://jenabean.googlecode.com/svn/trunk/example/) that provides an example of using jenabean within an app.

```
package test;
import thewebsemantic.Id;

public class KeepItSimple {
	@Id
	private String id;
	private int value;
	public int getValue() {return value;}
	public void setValue(int i) {value = i;}
	public String getId() {return id;}
	public void setId(String id) {this.id = id;}	
}
```

```
@prefix :        <http://test.bean/> .

<http://thewebsemantic.com/javaclass>
      a       owl:AnnotationProperty .

<http://test.bean/KeepItSimple/kisv1.1>
      a       :KeepItSimple ;
      :id     "kisv1.1"^^xsd:string ;
      :value  "444"^^xsd:int .

:KeepItSimple
      a       rdfs:Class ;
      <http://thewebsemantic.com/javaclass>
              "test.bean.KeepItSimple" .

:id   a       rdf:Property .

:value
      a       rdf:Property .
```
