package example;

import thewebsemantic.RdfProperty;
import thewebsemantic.Uri;

public class Human {
	private String name;
	private String description;
	private String uri;
	
	public Human(String uri) {
		this.uri = uri;
	}
	
	@Uri
	public String uri() {
		return uri;
	}
	
	@RdfProperty(NTNames.name_en_PROPERTY)
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@RdfProperty(NTNames.description_PROPERTY)
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		else if (obj instanceof Human)
			return equals((Human)obj);
		else
			return false;
	}
	
	public boolean equals(Human h) {
		return uri.equals(h.uri);
	}
	
	
	
}
