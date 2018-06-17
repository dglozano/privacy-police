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

        //FIXME reemplazar por log
        StringBuilder sb = new StringBuilder();
        sb.append("Palabras: ");
        for(String pc: palabras){
            sb.append(pc + " ");
        }
        System.out.println(sb.toString());

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
        //FIXME reemplazar por log
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Palabras claves: ");
        for(String pc: palabrasClaves){
            sb2.append(pc + " ");
        }
        System.out.println(sb2.toString());

        return palabrasClaves;
    }


    private String preprocesar(String frase) {

        //Elimina espacios al principio y al final (trim) y si hay mas de uno en el medio deja solo uno.
        String frasePreprocesada = frase.trim().replaceAll(" +"," ");

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

