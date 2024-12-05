from flask import Flask

# Crear un servidor web
servidor = Flask(__name__)

# Crear una ruta
@servidor.route('/')
def hola():
    return "Hola desde servidor"

#Crear ruta que debuelva un saludo con un mensaje que reciba como parametro
@servidor.route('/saludo/<mensaje>')
def saludo(mensaje):
    return f"Hola desde servidor, {mensaje}"

if __name__ == '__main__':
    servidor.run(
        host='0.0.0.0', # Especificar la IP para que sea visible desde cualquier lugar
        port=5000 # Especificar el puerto
    )