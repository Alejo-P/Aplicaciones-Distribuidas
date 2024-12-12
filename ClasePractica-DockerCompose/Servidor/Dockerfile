# Crear una imagen de Docker con un servidor web en Python
FROM python:latest

# Copiar los archivos al contenedor
COPY servidor.py . 
# Copiar el archivo servidor.py al contenedor

# Ejecutar comandos (instalar dependencias, en este caso Flask)
RUN pip install Flask

# Exponer el puerto 5000 (Puerto del contenedor)
EXPOSE 5000

# Ejecutar el servidor (comando para ejecutar el servidor)
CMD ["python", "servidor.py"]