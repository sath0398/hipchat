# Atlassian Chat
This application that will take the input of a string, and provides the output as an json that describes the emoticons,
mentions, links and link titles found within that text. 

An example request with Links, Emotions and Mentions
```
curl -X POST -H "Content-type: text/plain" -d '@bob @john (success) such a cool feature; https://twitter.com/jdorfman/status/430511497475670016"
```

With an example response of
```
{"mentions":["@bob","@john"],"emotions":["(success)"],"links":[{"url":"https://twitter.com/jdorfman/status/430511497475670016","title":"Justin Dorfman on Twitter: \"nice @littlebigdetail from @HipChat (shows hex colors when pasted in chat). http://t.co/7cI6Gjy5pq\""}]}
```

## Structure
This application uses the following libraries.
  
  1. Spring Boot for Dependency Injection
  2. Jersey for JAX-RS
  3. Maven - build management
  

## How to Build?
mvn clean compile install
 
## How to Run?
Run the standalone java program - SpringBootJerseyApplication

#Design

Visitor Design Pattern is used on this application as distinct set of operations (UrlExtraction, Mention and Emotions can be performed on just creating a visitor class that implements all of the appropriate specializations of the virtual function.

```
