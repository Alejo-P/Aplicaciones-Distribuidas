from locust import HttpUser, task, between

class MyUser(HttpUser): 
    wait_time = between(1, 2)  # Tiempo de espera aleatorio entre tareas
    host = "http://python_app:5000"  # URL base de las solicitudes
    
    @task
    def cargar_pagina_principal(self):
        """
        Simula la carga de la página principal (GET /)
        """
        response = self.client.get("/")
        if response.status_code == 200:
            print(f"[INFO] Página principal cargada correctamente ({response.status_code})")
        else:
            print(f"[ERROR] Error al cargar la página principal: {response.status_code}")

    