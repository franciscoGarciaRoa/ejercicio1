FROM skeet15/auth-vivelibre
VOLUME /tmp
COPY target/*.jar ejercicio1-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/ejercicio1-0.0.1-SNAPSHOT.jar"]