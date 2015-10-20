The new maven repository is now up and running, it is available at this url: http://jenabean.googlecode.com/svn/repo/
Please make sure to change your old pom configuration, the old repository will stay alive for a while but will not longer be maintained

## Step 1: add the repository path to your pom.xml file ##
```
<repositories> 
  <repository> 
    <id>Jenabean</id> 
    <url>http://jenabean.googlecode.com/svn/repo</url> 
  </repository>
</repositories> 
```
## Step 2: add the jenabean/jpa4jena dependency ##
```
<dependency> 
   <groupId>thewebsemantic</groupId> 
   <artifactId>jenabean</artifactId> 
   <version>1.0.6</version> 
</dependency>
```

```
<dependency> 
   <groupId>thewebsemantic</groupId> 
   <artifactId>jpa4jena</artifactId> 
   <version>0.7</version> 
</dependency>
```