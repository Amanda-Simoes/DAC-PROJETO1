FROM openjdk:11

COPY Recurso2.java /recurso2/

WORKDIR /recurso2

RUN javac Recurso2.java
RUN jar cvf projeto.jar Recurso2.class
RUN touch DOCKER.MF
RUN echo "Main-Class: Recurso2" > ./DOCKER.MF
RUN jar cvmf DOCKER.MF projeto.jar Recurso2.class

ENTRYPOINT java Recurso2

CMD ["java","-jar","projeto.jar"]