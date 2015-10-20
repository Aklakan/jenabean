### 1. Download jenabean.jar (jena too!) ###
jenabean uses [Jena](http://jena.sourceforge.net/downloads.html). [jenabean.jar](http://code.google.com/p/jenabean/) is featured on the front page.

### 2. Create your model classes ###
Create a java bean class and use the @Id annotation to indicate which method has the unique id for this "thing".  This example uses a convenience class, RdfBean, to tie some of the more common methods directly to your bean.

[Song.java](http://jenabean.googlecode.com/svn/trunk/src/example/java/example/Song.java)
```
public class Song extends RdfBean<Song> {
	private String composer;
	private Genre genre; 
	private String title;
	
	@Id
	public String getTitle() {
		return title;
	}
```

### 3. Create a jena model and bind with the jenabean singleton ###
Create a jena ontology model instance and make jenabean aware of it...

[SimpleExample.java](http://jenabean.googlecode.com/svn/trunk/src/example/java/example/SimpleExample.java)
```
	OntModel m = ModelFactory.createOntologyModel();
	Jenabean.instance().bind(m);
```

### 4. Save the bean ###
Create an instance of you bean and save it...

```
	Song s1 = new Song();
	s1.setTitle("Waters of March");
	s1.setComposer("Antonio Carlos Jobim");
	s1.setGenre(Genre.JAZZ);
	s1.save();
```

### 5. Read it back out and verify the RDF ###
Read it back out and let jena show us the RDF we've created...
```
        Collection<Song> songs = Song.load();
	for (Song song : songs)
		System.out.println(song.getTitle() + ":" + 
		song.getGenre().getName());	
        m.write(System.out);	
```