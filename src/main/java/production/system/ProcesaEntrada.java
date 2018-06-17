package production.system;

import java.text.Normalizer;
import java.util.HashSet;
import java.util.Set;

public class ProcesaEntrada {

    public static Set<String> encuentraPalabrasClaves(String fraseOriginal){

        String fraseLimpia = limpiarFrase(fraseOriginal);
        //HashSet es mas eficiente que una lista y asegura que las palabras claves no esten duplicadas
        Set<String> palabrasClaves = new HashSet<String>();
        String[] palabras = fraseLimpia.split(" ");
        for (String palabra : palabras) {
            //TODO: BUSCAR SINONIMOS Y PASAR VERBOS AL INFINITIVO
            switch (palabra){
                case "CUAL": case "COMO": case "CUANTO": case "QUE":
                    palabrasClaves.add("PREGUNTA");
                    break;

                case "NOMBRE": case "LLAMA": case "NOMBRAR":
                    palabrasClaves.add("NOMBRE");
                    break;

                case "MADRE": case "PADRE": case "FAMILIA": case "AMIGO": case "AMIGOS":
                    palabrasClaves.add("ALLEGADO");
                    break;

                case "ANOS": case "ANO":
                    palabrasClaves.add("EDAD");
                    break;

                case "NACI":
                    palabrasClaves.add("NACIMIENTO");
                    break;

                case "VIVO":
                    palabrasClaves.add("VIVIENDA");
                    break;

                case "ESCUELA":
                    palabrasClaves.add("ESCUELA");
                    break;
            }
        }
        return palabrasClaves;
    }


    private static String limpiarFrase(String frase) {
        //Elimina tabulaciones y espacios dobles
        frase = frase.trim();
        //Convierte caracteres especiales a estandares
        String resultado = Normalizer
                .normalize(frase, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "");
        //Antes de retornar la frase sin acentos, le saca los caracteres especiales (puntuación, interrogación, etc)
        return resultado.replaceAll("[^\\dA-Za-z ]", "");
    }

}

