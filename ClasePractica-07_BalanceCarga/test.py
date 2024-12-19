import requests # Importar el módulo requests para hacer peticiones HTTP
from collections import Counter # Importar el módulo Counter para contar elementos
import tqdm # Importar el módulo tqdm para mostrar una barra de progreso
import time # Importar el módulo time para esperar un tiempo


respuestas = [] # Lista para guardar las respuestas
# Hacer 100 peticiones (mostrar una barra de progreso)
for _ in tqdm.tqdm(range(100)):
    # Hacer una petición GET a la URL
    response = requests.get("http://localhost:80/")
    # Guardar la respuesta en la lista
    respuestas.append(response.text.strip())
    # Esperar 0.1 segundos
    time.sleep(0.1)
    
# Contar las respuestas
conteo = Counter(respuestas)
suma = sum(conteo.values())
# Imprimir el conteo
for elemento in conteo:
    print(f"{elemento}: {conteo[elemento]}")
    
print(f"Total: {suma}")