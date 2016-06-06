# Spring Music Application

By Amadeo Ascó, June 2016.

This is an implementation of a simple web app using Java and Spring that allows 
to view, modify and delete instruments.


Licensed under the Apache License, Version 2.0, see more in the [license](license.txt) file.


## Running music locally
>cd spring-music

>./mvnw tomcat7:run

You can then access music [here](http://localhost:9966/music/): ``http://localhost:9966/music/``

If the web app is started from within eclipse then access music 
[here](http://localhost:8080/music/): ``http://localhost:8080/music/``


## Running unit-tests

To run all the test execute:

>./mvnw test

To run an specific unit-test execute: ./mvnw -Dtest=TestsClassName test, example:

>./mvnw -Dtest=InstrumentServiceJpaTests test


## How to use music

There are two views accessible directly through the web app menu, which it is 
located at the top right hand side. The views are:

1) [Home](#home-view)

2) [Instruments](#instruments-view)

If an exception is thrown when processing a request by one of the controller 
then an internal error page is displayed on the browser.

All the pages have at the footer, left hand side, a list of the languages 
supported represented by the flags of the countries the language originates 
from. The first of this flags corresponds to the language displayed in the 
shown page and subsequent pages. The next flags to this one are the other 
supported languages, any of which can be clicked to change the language.

### Home view
The 'Home' view gives an introduction to the web app.

### Instruments view
The 'Instruments' view lists all the instrument in the database. Currently 
only two type of instruments are supported; instruments with EQ settings and 
Vocal Compressor. The instruments can be sorted by any of the shown columns by 
clicking on the corresponding header. The actions applicable to each entry are 
accessible from the last column. The available action per instrument are:

- View details
- Edit details
- Delete instrument

Also at the bottom of the table can be seen the global actions, which they are 
currently only the addition of a new instrument.


## Overview
View: JSP with custom tags + bootstrap (CSS), [Webjars](http://www.webjars.org/) and [Dandelion](http://dandelion.github.io/).

Controller: Sprint and MVC annotations with bean validation.

Service: @Cacheable and @Transactional.

Repository: JPA.

---

The Web Layer: Sprint MVC and third-party web libraries (Dandelion and Webjars).

	Dandelion: datatables (based on JQuery datatables and Bootstrap) with functionality to sort and filter
	
	Webjars: Allows CSS and JS libraries to be imported as Maven libraries (http://www.webjar.org/).

This implementation uses the Model View Controller (MVC) pattern. The different components can be found at:
	<ul>
		<li>Model <a href="/src/main/java/com/aas/samples/music/model">here</a></li>
		<li>Views <a href="/src/main/webapp/WEB-INF/jsp">here</a></li>
		<li>Controllers <a href="/src/main/java/com/aas/samples/music/web">here</a></li>
	</ul>

### Flow Diagram

<p align="center">
  <img alt="FD" src="docs/music_flowDiagram.png" width="350" title="Flow Diagram."/>
</p>


## Database

<p align="center">
  <img alt="ERD" src="docs/music_erd.png" width="200" title="Entity Relation Diagram (ERD)."/>
</p>

### Database configuration

In its default configuration, Music uses an in-memory database 
(HSQLDB) which gets populated at startup with data. A similar setup is provided 
for MySql in case a persistent database configuration is needed. Note that 
whenever the database type is changed, the data-access.properties file needs to 
be updated and the mysql-connector-java artifact from the pom.xml needs to be 
uncommented.

You may start a MySql database with docker:

>docker run -e MYSQL_ROOT_PASSWORD=music -e MYSQL_DATABASE=music -p 3306:3306 mysql:5.7.8

## Working with Music in Eclipse/STS

### Prerequisites
The following items should be installed in your system:
* Maven 3

Note: when m2e is available, there is an m2 icon in Help -> About dialog.
If m2e is not there, just follow the install process [here](http://www.eclipse.org/m2e/).


### Steps:

1) In the command line
``copy project into your local storage``

2) Inside Eclipse
``File -> Import -> Maven -> Existing Maven project``


## Security

There is not any security implemented on this code except on the use of GET and 
POST, where POST hides the direct view of the transmitted values.

A common used approach to security is the use of security tokens:

	Since the HTTP protocol is stateless, this means that if we authenticate a 
	user with a username and password, then on the next request, our 
	application won’t know who we are. We would have to authenticate again.

Token based authentication is stateless, e.g. JSON Web Tokens. No information 
about a user is being stored on the server or in a session, so the application 
can scale.

The step followed are:
- User requests access with Username and Password
- Application validates credentials
- Application provides a signed token to the client, which me contain extra data
- Client stores that token and sends it along with every request
- Server verifies token and responds accordingly to the request, e.g. data


## Looking for something in particular?

<table border="1">
  <tr>
    <th width="300px">Java Config</th><th width="510px">Comments</th>
  </tr>
  <tr>
    <td>Java Config branch</td>
    <td>
      Music uses XML configuration by default. In case you'd like to 
      use Java Config instead.
    </td>
  </tr>
  <tr>
    <th width="300px">Inside the 'Web' layer</th><th width="300px">Files</th>
  </tr>
  <tr>
    <td>Spring MVC - XML integration</td>
    <td><a href="/src/main/resources/spring/mvc-view-config.xml">mvc-view-config.xml</a></td>
  </tr>
  <tr>
    <td>Spring MVC - ContentNegotiatingViewResolver</td>
    <td><a href="/src/main/resources/spring/mvc-view-config.xml">mvc-view-config.xml</a></td>
  </tr>
  <tr>
    <td>JSP custom tags</td>
    <td>
      <a href="/src/main/webapp/WEB-INF/tags">WEB-INF/tags</a>
    </td>
  </tr>
  <tr>
    <td>Bower</td>
    <td>
      Bower-install maven profile declaration inside <a href="/pom.xml">pom.xml</a> <br />
      JavaScript libraries are defined by the manifest file <a href="/bower.json">bower.json</a> <br />
      <a href="/.bowerrc">Bower configuration</a> using JSON <br />
      <a href="/src/main/resources/spring/mvc-core-config.xml#L30">Resource mapping</a> in Spring configuration <br />
	</td>
  </tr>
  <tr>
    <td>Dandelion-datatables</td>
    <td>
      <a href="/src/main/webapp/WEB-INF/jsp/instruments/wellcome.jsp">wellcome.jsp</a>, 
      <a href="/src/main/webapp/WEB-INF/jsp/instruments/instrumentsList.jsp">instrumentsList.jsp</a>, 
      <a href="/src/main/webapp/WEB-INF/jsp/products/instrumentView.jsp">instrumentView.jsp</a>, 
      <a href="/src/main/webapp/WEB-INF/jsp/products/instrumentEdit.jsp">instrumentEdit.jsp</a>, 
      <a href="/src/main/webapp/WEB-INF/web.xml">web.xml</a> and 
      <a href="/src/main/resources/dandelion/datatables/datatables.properties">datatables.properties</a> 
   </td>
  </tr>
</table>
<br />
<table border="1">
  <tr>
    <th width="300px">'Service' and 'Repository' layers</th><th width="510px">Files</th>
  </tr>
  <tr>
    <td>Transactions</td>
    <td>
      <a href="/src/main/resources/spring/business-config.xml">business-config.xml</a>, 
      <a href="/src/main/java/com/aas/samples/music/service/InstrumentServiceImpl.java">InstrumentServiceImpl.java</a> and  
      <a href="/src/main/java/com/aas/samples/music/service/HomeServiceImpl.java">HomeServiceImpl.java</a>
    </td>
  </tr>
  <tr>
    <td>Cache</td>
      <td>
       <a href="/src/main/resources/spring/tools-config.xml">tools-config.xml</a> and
       <a href="/src/main/java/com/aas/samples/music/service/InstrumentServiceImpl.java">InstrumentServiceImpl.java</a>
    </td>
  </tr>
  <tr>
    <td>Bean Profiles</td>
    <td>
      <a href="/src/main/resources/spring/business-config.xml">business-config.xml</a>, 
      <a href="/src/test/java/com/aas/samples/music/service/InstrumentServiceJdbcTests.java">InstrumentServiceJdbcTests.java</a> and 
      <a href="/src/main/webapp/WEB-INF/web.xml">web.xml</a>
    </td>
  </tr>
  <tr>
    <td>JdbcTemplate</td>
    <td>
      <a href="/src/main/resources/spring/business-config.xml">business-config.xml</a> and 
      <a href="/src/main/java/com/aas/samples/music/repository/jdbc">jdbc folder</a>
    </td>
  </tr>
  <tr>
    <td>JPA</td>
    <td>
      <a href="/src/main/resources/spring/business-config.xml">business-config.xml</a> and 
      <a href="/src/main/java/com/aas/samples/music/repository/jpa">jpa folder</a>
    </td>
  </tr>
  <tr>
    <td>Spring Data JPA</td>
    <td>
      <a href="/src/main/resources/spring/business-config.xml">business-config.xml</a> and 
      <a href="/src/main/java/com/aas/samples/music/repository/springdatajpa">springdatajpa folder</a>
    </td>
  </tr>
</table>


## Others
The graphics were created using [draw.io](https://www.draw.io/). They can be 
modified by uploading the XML files located under the [docs](docs/) directory 
from within [draw.io](https://www.draw.io/").

### TODO
- Need to complete the 'add new instrument view', 'edit instrument view' and 
	passing data to the controllers.
- No implementation to use JDBC, file or spring JPA data have been provided. 
	There are some scripts for MYSQL to create the database and tables and 
	initialise them.
- Complete unit-tests.
- If more types of elements would be used then it may be more appropriate to 
	use an schemaless DB like Riak (NOSQL DB).
