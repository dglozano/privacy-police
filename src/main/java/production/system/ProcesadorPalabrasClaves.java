package production.system;

import java.text.Normalizer;
import java.util.HashSet;
import java.util.Set;

public class ProcesadorPalabrasClaves {

    //El set de palabras claves seria la memoria de trabajo
    public Set<String> getPalabrasClaves(String fraseOriginal){

        System.out.println("Frase original: " + fraseOriginal);
        String frasePreprocesada = preprocesar(fraseOriginal);
        System.out.println("Frase preprocesada: " + frasePreprocesada);

        //HashSet es mas eficiente que una lista y asegura que las palabras claves no esten duplicadas
        Set<String> palabrasClaves = new HashSet<String>();
        String[] palabras = frasePreprocesada.split(" ");
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
        StringBuilder sb = new StringBuilder();
        sb.append("Palabras claves: ");
        for(String pc: palabrasClaves){
            sb.append(pc + "  ");
        }
        System.out.println(sb.toString());
        return palabrasClaves;
    }


    private String preprocesar(String frase) {

        //Elimina tabulaciones y espacios dobles
        String frasePreprocesada = frase.trim();

        //Convierte caracteres especiales a estandares
        frasePreprocesada = Normalizer
                .normalize(frasePreprocesada, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "");

        //Le saca los caracteres especiales (puntuación, interrogación, etc)
        frasePreprocesada = frasePreprocesada.replaceAll("[^\\dA-Za-z ]", "");

        //La pone en mayuscula
        frasePreprocesada = frasePreprocesada.toUpperCase();

        return frasePreprocesada;
    }
}

