package production.system;

import java.util.ArrayList;
import java.util.List;

public class ObtencionReglas {

    public static List<Regla> obtenerTodas(){
            List<Regla> reglas = new ArrayList<Regla>();
            //NOMBRE:
            reglas.add(new Regla(1, "PREGUNTA-NOMBRE", "Sugerir no contestar", 1, 2, 10, false));
            reglas.add(new Regla(2, "NOMBRE-ALLEGADO", "Grabar la charla", 2, 2, 9, false));
            reglas.add(new Regla(3, "PREGUNTA-NOMBRE-ALLEGADO", "Llamar a la madre", 3, 3, 8, false));
            reglas.add(new Regla(4, "ALLEGADO-PREGUNTA-NOMBRE", "Llamar al padre", 4, 3, 7, false));
            reglas.add(new Regla(5, "PREGUNTA-NOMBRE-ALLEGADO", "Sugerir no contestar", 5, 3, 6, false));
            //EDAD:
            reglas.add(new Regla(6, "PREGUNTA-EDAD", "Sugerir contestar 'No se'", 6, 2, 5, false));
            reglas.add(new Regla(7, "PREGUNTA-EDAD", "Sugerir contestar 'No se'", 7, 2, 4, false));
            //NACIMIENTO:
            reglas.add(new Regla(8, "NACIMIENTO-VIVIENDA", "Sugerir apagar SmartToy", 8, 2, 3, false));
            //ESCUELA:
            reglas.add(new Regla(9, "PREGUNTA-ESCUELA", "Sugerir no contestar", 9, 2, 2, false));
            reglas.add(new Regla(10, "PREGUNTA-ESCUELA", "Sugerir no contestar", 10, 2, 1, false));

            return reglas;
    }


    public static List<Regla> obtenerActivas(ArrayList<String> palabrasClaves, List<Regla> reglasTotales){
        //List<Regla> reglas = obtenerTodas();
        List<Regla> reglasActivas = new ArrayList<Regla>();

        for (Regla regla : reglasTotales){                                                                  //Recorro una por una todas las reglas
            int contador = regla.getEspecificidad(), cantPalabras = palabrasClaves.size();                  //El valor de la especificidad de la regla me sirve para saber cuantos "patrones del lado izquierdo" (o palabras claves) tiene esa regla
            if (contador == cantPalabras){                                                                  //Si la cantidad de palabras claves de la regla no es igual a la cantidad de palabras claves que me llegaron, no se activa la regla
                for (String palabra : palabrasClaves){                                                      //Recorro cada palabra clave que me llegó de la entrada, si la regla contiene esa palabra clave, decremento el contador
                    if(regla.getPalabrasClaves().contains(palabra)){
                        contador--;
                    }
                }
                //
                if (contador==0){                                                                           //Si el contador es exactamente == 0, entonces la regla tenía exactamente las mismas palabras claves que la entrada
                    reglasActivas.add(regla);
                }
            }
        }
        return reglasActivas;
    }

}
