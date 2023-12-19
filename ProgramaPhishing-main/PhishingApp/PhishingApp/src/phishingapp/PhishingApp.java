package phishingapp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Jennifer
 */
public class PhishingApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Definir la lista de palabras, frases y nombres de compañías con sus valores de puntos asociados.
        Map<String, Integer> phishingKeywords = new HashMap<>();
        phishingKeywords.put("banco", 2);
        phishingKeywords.put("PayPal", 3);        
        phishingKeywords.put("Datos Personales", 3);
        phishingKeywords.put("Ofertas de Trabajo", 3);     
        phishingKeywords.put("Compra ahora mismo", 2);
        phishingKeywords.put("Has sido seleccionado", 2);
        phishingKeywords.put("Haz clic en este enlace", 2);
        
        try {
            // Ruta del archivo de texto a analizar.
            // Cambiar la ruta de donde se encuntre guardado el proyecto
            //Se recomienda colocarlo en el escritorio y actualizar el nombre del usuario
        String filePath = "C:\\Users\\DELL\\OneDrive\\Escritorio\\PhishingApp\\TextoPishig.txt";

        // Leer el archivo de texto y realizar el análisis.
        int totalPoints = 0;
        
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line;
            Map<String, Integer> keywordOccurrences;
            
                
                keywordOccurrences = new HashMap<>();
                while ((line = br.readLine()) != null) {
                    for (String keyword : phishingKeywords.keySet()) {
                        int keywordCount = countOccurrences(line.toLowerCase(), keyword.toLowerCase());
                        if (keywordCount > 0) {
                            keywordOccurrences.put(keyword, keywordCount);
                            totalPoints += keywordCount * phishingKeywords.get(keyword);
                        }
                    }
            }
            br.close();

            // Imprimir las palabras clave encontradas junto con el número de ocurrencias y los puntos asociados.
            for (String keyword : keywordOccurrences.keySet()) {
                int occurrences = keywordOccurrences.get(keyword);
                int points = occurrences * phishingKeywords.get(keyword);
                System.out.println(keyword + " - Ocurrencias: " + occurrences + " - Puntos: " + points);
            }

            // Imprimir el total de puntos para todo el mensaje.
            System.out.println("Total de puntos para el mensaje: " + totalPoints);

        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        
        
        
        
        
    }
           
    // Función para contar las ocurrencias de una palabra o frase en un texto.
    private static int countOccurrences(String text, String keyword) {
        int count = 0;
        int index = text.indexOf(keyword);
        while (index != -1) {
            count++;
            index = text.indexOf(keyword, index + 1);
        }
        return count;
    }
               
    
    
}