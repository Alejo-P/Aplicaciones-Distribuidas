from flask import Flask

# Crear un servidor web
servidor = Flask(__name__)

# Crear una ruta
@servidor.route('/')
def hola():
    return "Hola desde servidor 2"

@servidor.route('/mensaje')
def mensaje():
    return "Hola desde la ruta mensaje"

if __name__ == '__main__':
    servidor.run(
        host='0.0.0.0', # Especificar la IP para que sea visible desde cualquier lugar
        port=5000 # Especificar el puerto
    )