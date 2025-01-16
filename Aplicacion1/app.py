from flask import Flask, render_template, request, redirect, url_for
from flask_mysqldb import MySQL
import config

app = Flask(__name__)

#clave secreta Flask para habilitar el uso de sesiones
app.secret_key = "your_secret_key"

# Configuración de la base de datos MySQL
app.config['MYSQL_HOST'] = config.Config.MYSQL_HOST
app.config['MYSQL_USER'] = config.Config.MYSQL_USER
app.config['MYSQL_PASSWORD'] = config.Config.MYSQL_PASSWORD
app.config['MYSQL_DB'] = config.Config.MYSQL_DB
app.config['MYSQL_PORT'] = config.Config.MYSQL_PORT

# Inicializar la base de datos
mysql = MySQL(app)

@app.route('/', methods=['GET', 'POST'])
def formulario():
    if request.method == 'POST':
        # Obtener datos del formulario
        nombre = request.form.get('name')
        correo = request.form.get('email')
        mensaje = request.form.get('message')
        
        # los datos, almacenarlos en una base de datos, etc.
        cur = mysql.connection.cursor()
        cur.execute("INSERT INTO datos (nombre,correo,mensaje) VALUES (%s, %s, %s)", (nombre,correo,mensaje))
        mysql.connection.commit()
        cur.close()
                
        # Redirigir al formulario
        return redirect(url_for('formulario'))

    return render_template('index.html')

if __name__ == "__main__":
    app.run(host='0.0.0.0')

