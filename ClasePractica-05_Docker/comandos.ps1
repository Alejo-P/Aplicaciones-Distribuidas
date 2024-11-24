docker pull nginx # Comando usado oara descargar la imagen de nginx
docker pull phpmyadmin # Comando usado para descargar la imagen de phpmyadmin
docker pull httpd # Comando usado para descargar la imagen de httpd
docker images # Comando usado para ver las imagenes descargadas

# Ejecutar contenedores en modo daemon
docker run -d -p 8080:80 --name my-apache-app httpd # Comando usado para ejecutar el contenedor de httpd
docker run -d -p 8081:80 --name my-nginx-app nginx # Comando usado para ejecutar el contenedor de nginx
docker run -d -p 8082:80 --name my-phpmyadmin-app phpmyadmin # Comando usado para ejecutar el contenedor de phpmyadmin

# Listar los contenedores en ejecución
docker ps # Comando usado para listar los contenedores en ejecución
docker ps -a # Comando usado para listar todos los contenedores

# Detener contenedores
docker stop my-apache-app # Comando usado para detener el contenedor de httpd
docker stop my-nginx-app # Comando usado para detener el contenedor de nginx
docker stop my-phpmyadmin-app # Comando usado para detener el contenedor de phpmyadmin

# ---------------------------- Construir imagen de Dockerfile ----------------------------
docker build -t python_server . # Comando usado para construir la imagen de python_server
docker run -d -p 8083:80 --name my-python-server python_server # Comando usado para ejecutar el contenedor de python_server