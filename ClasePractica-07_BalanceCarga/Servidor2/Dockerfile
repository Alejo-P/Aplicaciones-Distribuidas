# Crear una imagen de Docker con un servidor web en Python
FROM python:latest

# Copiar los archivos al contenedor
COPY servidor2.py .

# Ejecutar comandos (instalar dependencias, en este caso Flask)
RUN pip install Flask

# Exponer el puerto 5000
EXPOSE 5000

# Ejecutar el servidor
CMD ["python", "servidor2.py"]