# Define la imagen base de Java
FROM openjdk:11

WORKDIR /app

# Copia los archivos compilados al contenedor
COPY . /app

#Para ejecutar comandos
RUN javac *.java

EXPOSE 1099

CMD ["java", "ServidorRMI"]
