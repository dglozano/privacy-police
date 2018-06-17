package production.system;

import java.util.ArrayList;
import java.util.List;

public class MemoriaDeProduccion {

    public static List<Regla> getReglas(){
            List<Regla> reglas = new ArrayList<>();
            //NOMBRE:
            reglas.add(new Regla(1, "PREGUNTA-NOMBRE", "Sugerir no contestar", 1, 2, 10));
            reglas.add(new Regla(2, "NOMBRE-ALLEGADO", "Grabar la charla", 2, 2, 9));
            reglas.add(new Regla(3, "PREGUNTA-NOMBRE-ALLEGADO", "Llamar a la madre", 3, 3, 8));
            reglas.add(new Regla(4, "ALLEGADO-PREGUNTA-NOMBRE", "Llamar al padre", 4, 3, 7));
            reglas.add(new Regla(5, "PREGUNTA-NOMBRE-ALLEGADO", "Sugerir no contestar", 5, 3, 6));
            //EDAD:
            reglas.add(new Regla(6, "PREGUNTA-EDAD", "Sugerir contestar 'No se'", 6, 2, 5));
            reglas.add(new Regla(7, "PREGUNTA-EDAD", "Sugerir contestar 'No se'", 7, 2, 4));
            //NACIMIENTO:
            reglas.add(new Regla(8, "NACIMIENTO-VIVIENDA", "Sugerir apagar SmartToy", 8, 2, 3));
            //ESCUELA:
            reglas.add(new Regla(9, "PREGUNTA-ESCUELA", "Sugerir no contestar", 9, 2, 2));
            reglas.add(new Regla(10, "PREGUNTA-ESCUELA", "Sugerir no contestar", 10, 2, 1));

            return reglas;
    }
}
