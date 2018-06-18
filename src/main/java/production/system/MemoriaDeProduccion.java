package production.system;

import java.util.ArrayList;
import java.util.List;

public class MemoriaDeProduccion {

    public static List<Regla> getReglas(){
            List<Regla> reglas = new ArrayList<>();
            //NOMBRE:
            reglas.add(new Regla(1, "PREGUNTA-NOMBRE", "Sugerir no contestar", 1, 2, 28));
            reglas.add(new Regla(2, "NOMBRE-ALLEGADO", "Grabar la charla", 2, 2, 27));
            reglas.add(new Regla(3, "PREGUNTA-NOMBRE-ALLEGADO", "Llamar a los padres", 3, 3, 26));
            reglas.add(new Regla(4, "ALLEGADO-PREGUNTA-NOMBRE", "Sugerir contestar con información ficticia", 4, 3, 25));
            reglas.add(new Regla(5, "PREGUNTA-NOMBRE-ALLEGADO", "Sugerir no contestar", 5, 3, 24));
            //EDAD:
            reglas.add(new Regla(6, "PREGUNTA-EDAD", "Sugerir contestar 'No me acuerdo'", 6, 2, 23));
            reglas.add(new Regla(7, "PREGUNTA-EDAD", "Sugerir contestar 'No se'", 7, 2, 22));
            //NACIMIENTO:
            reglas.add(new Regla(8, "NACIMIENTO-VIVIENDA", "Sugerir apagar SmartToy", 8, 2, 21));
            //ESCUELA:
            reglas.add(new Regla(9, "PREGUNTA-ESCUELA", "Sugerir contestar 'No se'", 9, 2, 20));
            reglas.add(new Regla(10, "PREGUNTA-ESCUELA", "Sugerir no contestar", 10, 2, 19));
            //PROFESOR:
            reglas.add(new Regla(9, "PROFESOR-PREGUNTA", "Sugerir contestar con información ficticia", 11, 2, 18));
            reglas.add(new Regla(9, "PREGUNTA-ESCUELA-PROFESOR", "Sugerir contestar con información ficticia", 12, 3, 17));
            //BANCO:
            reglas.add(new Regla(9, "BANCO-PREGUNTA-ALLEGADO-TRABAJO", "Alertar a los padres y sugerir apagar SmarToy", 13, 3, 16));
            reglas.add(new Regla(9, "DINERO", "Alertar a los padres y sugerir apagar SmarToy", 14, 1, 15));
            //DINERO:
            reglas.add(new Regla(9, "ALLEGADO-DINERO", "Alertar a los padres y sugerir apagar SmarToy", 15, 2, 14));
            //JOYAS:
            reglas.add(new Regla(9, "JOYA-PREGUNTA-ALLEGADO", "Alertar a los padres y sugerir apagar SmarToy", 16, 3, 13));
            reglas.add(new Regla(9, "ALLEGADO-JOYA-ESCUELA", "Alertar a los padres y sugerir apagar SmarToy", 17, 3, 12));
            //COLLAR:
            reglas.add(new Regla(9, "JOYA-ALLEGADO-NOMBRE", "Alertar a los padres y sugerir apagar SmarToy", 18, 3, 11));
            //TRABAJO:
            reglas.add(new Regla(9, "PREGUNTA-TRABAJO", "Sugerir contestar con información ficticia", 19, 2, 10));
            //HOGAR:
            reglas.add(new Regla(9, "VIVIENDA-PREGUNTA", "Sugerir no contestar", 20, 2, 9));
            reglas.add(new Regla(9, "PREGUNTA-VIVIENDA", "Grabar la charla", 21, 2, 8));
            reglas.add(new Regla(9, "VIVIENDA-PREGUNTA", "Sugerir contestar con información ficticia", 22, 2, 7));
            //CASA:
            reglas.add(new Regla(9, "VIVIENDA", "Grabar la charla", 23, 1, 6));
            reglas.add(new Regla(9, "ALLEGADO-VIVIENDA", "Grabar la charla", 24, 2, 5));
            reglas.add(new Regla(9, "VIVIENDA-VACACIONES-EDAD-PREGUNTA", "Llamar a los padres", 25, 4, 4));
            //LICENCIA CONDUCIR:
            reglas.add(new Regla(9, "ALLEGADO-LICENCIA", "Sugerir ignorar SmartToy", 26, 2, 3));
            //ACTIVIDAD:
            reglas.add(new Regla(9, "ACTIVIDAD-ESCUELA-PREGUNTA", "Sugerir contestar con información ficticia", 27, 3, 2));
            //HOBBY:
            reglas.add(new Regla(9, "PASATIEMPO", "Sugerir no contestar", 28, 1, 1));

            return reglas;
    }
}
