from flask import Flask

# Crear un servidor web
servidor = Flask(__name__)

# Crear una ruta
@servidor.route('/')
def hola():
    return "Hola desde servidor"

if __name__ == '__main__':
    servidor.run(
        host='0.0.0.0', # Especificar la IP para que sea visible desde cualquier lugar
        port=5001 # Especificar el puerto
    )