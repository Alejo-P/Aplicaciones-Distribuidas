package Ejemplo5;

public class principal {
    public static void main(String[] args) {
        String[] archivos = { 
            "Marcelo.png",
            "Aula.html",
            "Presentacion.pptx",
            "hola.txt",
            "r.exe"
        };
        
        for (String archivo : archivos) {
            Hilodescarga h = new Hilodescarga(archivo);
            h.start();
        }
    }
}
