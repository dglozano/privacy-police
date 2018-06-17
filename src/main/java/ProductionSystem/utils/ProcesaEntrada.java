package ProductionSystem.utils;

import java.util.ArrayList;

public class ProcesaEntrada {

    public static ArrayList<String> encuentraPalabrasClaves(String fraseOriginal){

        String fraseLimpia = limpiarFrase(fraseOriginal);
        ArrayList<String> palabrasClaves = new ArrayList<String>();

        String[] palabras = fraseLimpia.split(" ");
        for (String palabra : palabras) {

            switch (palabra){
                case "CUAL": case "COMO": case "CUANTO": case "QUE":
                    if(!palabrasClaves.contains("PREGUNTA"))
                        palabrasClaves.add("PREGUNTA");
                    break;

                case "NOMBRE": case "LLAMA": case "NOMBRAR":
                    if(!palabrasClaves.contains("NOMBRE"))
                        palabrasClaves.add("NOMBRE");
                    break;

                case "MADRE": case "PADRE": case "FAMILIA": case "AMIGO": case "AMIGOS":
                    if(!palabrasClaves.contains("ALLEGADO"))
                        palabrasClaves.add("ALLEGADO");
                    break;

                case "ANOS": case "ANO":
                    if(!palabrasClaves.contains("EDAD"))
                        palabrasClaves.add("EDAD");
                    break;

                case "NACI":
                    if(!palabrasClaves.contains("NACIMIENTO"))
                        palabrasClaves.add("NACIMIENTO");
                    break;

                case "VIVO":
                    if(!palabrasClaves.contains("VIVIENDA"))
                        palabrasClaves.add("VIVIENDA");
                    break;

                case "ESCUELA":
                    if(!palabrasClaves.contains("ESCUELA"))
                        palabrasClaves.add("ESCUELA");
                    break;
            }
        }

        return palabrasClaves;
    }


    private static String limpiarFrase(String frase) {
        // Caracteres de acentuación a reemplazar
        String original = "áàäéèëíìïóòöúùuñÁÀÄÉÈËÍÌÏÓÒÖÚÙÜÑçÇ";
        // Caracteres que reemplazarán a los originales
        String ascii = "aaaeeeiiiooouuunAAAEEEIIIOOOUUUNcC";
        String resultado = frase;
        for (int i=0; i<original.length(); i++) {
            resultado = resultado.replace(original.charAt(i), ascii.charAt(i));
        }
        //Antes de retornar la frase sin acentos, le saca los caracteres especiales (puntuación, interrogación, etc)
        return resultado.replaceAll("[^\\dA-Za-z ]", "");
    }

}

