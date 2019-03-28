Spring Boot + Wildfly
======

Apenas um teste simples, para rodar as aplicações Spring Boot no Wildfly 8.0.0.

### Classe Principal
A classe principal deverá ser algo similar à:
```
package com.github.fenxlol.SpringBootWildfly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SpringBootWildflyApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWildflyApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(applicationClass);
	}

	private static Class<SpringBootWildflyApplication> applicationClass = SpringBootWildflyApplication.class;
}

```

Precisamos extender a classe **org.springframework.boot.web.servlet.support.SpringBootServletInitializer** para que a aplicação springboot vire um contâiner Java EE e também transforme a classe em um **WebApplicationInitializer** para que rode o **SpringApplication** via deploy tradicional do arquivo.war

### Pom.xml

Em seguida, algumas alterações também são necessárias no pom.xml, para excluir o inicializador padrão do servidor da Web incorporado do Tomcat e incluir a dependência do Servlet para poder compilar o projeto, as dependências do pom deverão ser algo como:
```
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
        <exclusions>
            <exclusion>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-tomcat</artifactId>
            </exclusion>
        </exclusions>
    </dependency>
    <dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>javax.servlet-api</artifactId>
        <version>3.1.0</version>
        <scope>provided</scope>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```

Lembre-se que o seu pom deverá ter o nome do artefato, versão e formato de empacotamento, tal como:
```
<artifactId>SpringBootWildfly</artifactId>
<version>0.0.1-SNAPSHOT</version>
<packaging>war</packaging>
```

Feito isso sua aplicação estará apta para efetuar o deploy do war, no wildfly.

### Links
![spring boot](https://img.shields.io/badge/Spring%20Boot-2.2.0.BUILD.SNAPSHOT-brightgreen.svg) - https://spring.io/projects/spring-boot
![wildfly](https://img.shields.io/badge/Wildfly-8.0.0.Final-brightgreen.svg) - https://wildfly.org/
