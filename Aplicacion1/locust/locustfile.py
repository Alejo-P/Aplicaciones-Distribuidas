from locust import HttpUser, task, between

class MyUser(HttpUser): #Esta clase hereda de HttpUser, que se utiliza para pruebas de rendimiento HTTP
    wait_time= between(1,2) #Esto establece un tiempo de espera aleatorio entre 1 y 2 segundos entre tareas.
    host = "http://aplicacion_python:5000" #Establece la URL base para todas las solicitudes HTTP.
    client_index = 0 #Variable para almacenar el índice de la solicitud.
    
    @task
    def index(self): #El método index envía una solicitud GET a la URL especificada.
        response = self.client.get("/")
        
    @task
    def submit_form(self): #El método submit_form envía una solicitud POST a la URL especificada.
        response = self.client.post("/", {
            'name': 'Cliente {}'.format(self.client_index),
            'email': 'cliente1200@gmail.com',
            'message': 'Hola, soy un cliente de prueba'
        })
        self.client_index += 1
         