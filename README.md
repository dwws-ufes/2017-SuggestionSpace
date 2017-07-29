# 2017-SuggestionSpace

### How to deploy

Instructions on how to deploy from scratch are listed below. If you need detailed instructions on how to set up Eclipse, WildFly and MySQL, please refer to this [tutorial at JButler's wiki](https://github.com/dwws-ufes/jbutler/wiki/Tutorial%3A-a-Java-EE-Web-Profile-application-with-JButler%2C-part-1).

1. Install [Eclipse Neon (version 4.6.x)](http://www.eclipse.org/);

2. Install [WildFly 10.x](http://wildfly.org) and create a Server configuration within Eclipse;

3. Install [MySQL](http://www.mysql.com/products/community/) and create a schema called `SuggestionSpace` and a user called `SuggestionSpace` with password `SuggestionSpace` and full access to the homonymous database;

4. Configure [the MySQL JDBC driver](http://dev.mysql.com/downloads/connector/j/) in WildFly;

5. Configure the datasource in WildFly's `standalone.xml` file:

```XML
 <datasource jta="true" jndi-name="java:/jboss/datasources/SuggestionSpace" pool-name="SuggestionSpacePool" enabled="true" use-java-context="true" use-ccm="true">
	<connection-url>jdbc:mysql://localhost:3306/SuggestionSpace</connection-url>
	<driver>mysql</driver>
	<security>
	    <user-name>SuggestionSpace</user-name>
	    <password>SuggestionSpace</password>
	</security>
	<statement>
	    <prepared-statement-cache-size>100</prepared-statement-cache-size>
	    <share-prepared-statements>true</share-prepared-statements>
	</statement>
 </datasource>
```

6. Configure the security domain in WildFly's `standalone.xml` file:
```XML
 <security-domain name="suggestionspace">
    <authentication>
        <login-module code="Database" flag="required">
            <module-option name="dsJndiName" value="java:jboss/datasources/SuggestionSpace"/>
            <module-option name="principalsQuery" value="select password from User u where u.email=?"/>
            <module-option name="rolesQuery" value="select role, 'Roles' from User u where u.email=?"/>
            <module-option name="hashAlgorithm" value="MD5"/>
            <module-option name="hashEncoding" value="base64"/>
            <module-option name="hashUserPassword" value="true"/>
        </login-module>
    </authentication>
</security-domain>
```
7. Configure the mail server in WildFly's `standalone.xml` file. Inside the
`<subsystem xmlns="urn:jboss:domain:mail:2.0">` tag, add:
```XML
<mail-session name="SuggestionSpace" debug="true" jndi-name="java:/jboss/mail/SuggestionSpace" from="your@email.com">
	<smtp-server outbound-socket-binding-ref="mail-smtp" ssl="true" username="your@email.com" password="yourpassword"/>
</mail-session>
```
Also, inside the `socket-binding-group` tag, **replace** the default `outbound-socket-binding` by:
```XML
<outbound-socket-binding name="mail-smtp" source-port="0" fixed-source-port="false">
	<remote-destination host="smtp.gmail.com" port="465"/>
</outbound-socket-binding>
```

8. Set `suggestionspace` to be the default security domain in WildFly's `standalone.xml` file:
```XML
 <default-security-domain value="suggestionspace"/>
```

## Built With
* [Java EE](http://www.oracle.com/technetwork/java/javaee/overview/index.html) - Standard Java EE stack of frameworks (JSF, CDI, JPA, EJB) 
* [JButler](https://github.com/dwws-ufes/jbutler) - Web framework useful for projects that use the standard Java EE stack
* [Maven](https://maven.apache.org/) - Dependency Management


## Authors

* **Cleisson Santos Guterres**
* **Jordão Rosário**
