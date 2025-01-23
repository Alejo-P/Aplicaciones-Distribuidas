import pandas as pd

# Cargar el archivo CSV
data = pd.read_csv('LanguageTable.csv')

# Mostrar las primeras filas del dataframe
print(data.head())

# Crear un archivo en formato JSON adecuado para Elasticsearch
with open('LanguageTable_bulk.json', 'w') as f:
    for index, row in data.iterrows():
        # Crear la acción de índice
        action = {
            "index": {
                "_index": "language",  # Nombre del índice
                "_id": index  # Puedes usar el índice o una columna como id
            }
        }
        
        action = str(action).replace("\'", "\"")  # Corregir las comillas simples por dobles
        row = str(row.to_dict()).replace("\'", "\"") # Corregir las comillas simples por dobles
        
        # Escribir la acción de índice y los datos en el archivo, asegurándote de que usas comillas dobles
        f.write(f"{action}\n")  # Corregir las comillas simples por dobles
        f.write(f"{row}\n")  # Corregir las comillas simples por dobles

# Verificar el archivo generado
print("El archivo 'LanguageTable_bulk.json' ha sido generado con éxito.")
