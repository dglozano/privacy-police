package production.system;

import java.text.Normalizer;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class ProcesadorPalabrasClaves {

    //El set de palabras claves seria la memoria de trabajo
    public Set<String> getPalabrasClaves(String fraseOriginal){
        //Defino si en la fraseOriginal hay una pregunta o no
        boolean pregunta = false;
        if (fraseOriginal.indexOf("?") != -1){
            pregunta=true;
        }

        String frasePreprocesada = preprocesar(fraseOriginal);

        //HashSet es mas eficiente que una lista y asegura que las palabras claves no esten duplicadas
        Set<String> palabrasClaves = new HashSet<String>();
        String palabraClave;
        //Si había una pregunta, agrego "PREGUNTA" como palabra clave
        if (pregunta)
            palabrasClaves.add("PREGUNTA");

        String[] palabras = frasePreprocesada.split(" ");

        for (String palabra : palabras) {
            palabraClave = encuentraSinonimos(palabra);
            if (palabraClave != null)
                palabrasClaves.add(palabraClave);
        }
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

    private String encuentraSinonimos(String palabra) {

        Map<String, String> mapaSinonimos = new TreeMap<String, String>();
        mapaSinonimos.put("DECIME", "PREGUNTA");
        mapaSinonimos.put("DIME", "PREGUNTA");
        mapaSinonimos.put("CONTAME", "PREGUNTA");
        mapaSinonimos.put("CUENTAME", "PREGUNTA");
        mapaSinonimos.put("EXPLICAME", "PREGUNTA");
        mapaSinonimos.put("DESCRIBIME", "PREGUNTA");
        mapaSinonimos.put("DESCRIBEME", "PREGUNTA");
        mapaSinonimos.put("MOSTRAME", "PREGUNTA");
        mapaSinonimos.put("MUESTRAME", "PREGUNTA");
        mapaSinonimos.put("PREGUNTAR", "PREGUNTA");
        mapaSinonimos.put("PREGUNTO", "PREGUNTA");

        mapaSinonimos.put("NOMBRE", "NOMBRE");
        mapaSinonimos.put("NOMBRES", "NOMBRE");
        mapaSinonimos.put("APELLIDO", "NOMBRE");
        mapaSinonimos.put("APELLIDOS", "NOMBRE");
        mapaSinonimos.put("APODO", "NOMBRE");
        mapaSinonimos.put("APODOS", "NOMBRE");
        mapaSinonimos.put("ALIAS", "NOMBRE");
        mapaSinonimos.put("LLAMAS", "NOMBRE");
        mapaSinonimos.put("LLAMA", "NOMBRE");
        mapaSinonimos.put("NOMBRAR", "NOMBRE");

        mapaSinonimos.put("MADRE", "ALLEGADO");
        mapaSinonimos.put("PADRE", "ALLEGADO");
        mapaSinonimos.put("MAMA", "ALLEGADO");
        mapaSinonimos.put("PAPA", "ALLEGADO");
        mapaSinonimos.put("PROGENITOR", "ALLEGADO");
        mapaSinonimos.put("PROGENITORES", "ALLEGADO");
        mapaSinonimos.put("PADRES", "ALLEGADO");
        mapaSinonimos.put("ABUELO", "ALLEGADO");
        mapaSinonimos.put("ABUELA", "ALLEGADO");
        mapaSinonimos.put("ABUELOS", "ALLEGADO");
        mapaSinonimos.put("ABUELAS", "ALLEGADO");
        mapaSinonimos.put("HERMANO", "ALLEGADO");
        mapaSinonimos.put("HERMANA", "ALLEGADO");
        mapaSinonimos.put("HERMANOS", "ALLEGADO");
        mapaSinonimos.put("HERMANAS", "ALLEGADO");
        mapaSinonimos.put("FAMILIAR", "ALLEGADO");
        mapaSinonimos.put("FAMILIARES", "ALLEGADO");
        mapaSinonimos.put("FAMILIA", "ALLEGADO");
        mapaSinonimos.put("AMIGO", "ALLEGADO");
        mapaSinonimos.put("AMIGOS", "ALLEGADO");
        mapaSinonimos.put("COLEGA", "ALLEGADO");
        mapaSinonimos.put("COLEGAS", "ALLEGADO");
        mapaSinonimos.put("COMPAÑERO", "ALLEGADO");
        mapaSinonimos.put("COMPAÑEROS", "ALLEGADO");
        mapaSinonimos.put("ALLEGADO", "ALLEGADO");
        mapaSinonimos.put("ALLEGADOS", "ALLEGADO");
        mapaSinonimos.put("TUTOR", "ALLEGADO");
        mapaSinonimos.put("TUTORES", "ALLEGADO");

        mapaSinonimos.put("ANO", "EDAD");
        mapaSinonimos.put("ANOS", "EDAD");
        mapaSinonimos.put("EDAD", "EDAD");
        mapaSinonimos.put("EDADES", "EDAD");
        mapaSinonimos.put("CUMPLEAÑOS", "EDAD");

        mapaSinonimos.put("NACIMIENTO", "NACIMIENTO");
        mapaSinonimos.put("NACIMIENTOS", "NACIMIENTO");
        mapaSinonimos.put("NATALICIO", "NACIMIENTO");
        mapaSinonimos.put("NATALICIOS", "NACIMIENTO");
        mapaSinonimos.put("NACER", "NACIMIENTO");
        mapaSinonimos.put("NACISTE", "NACIMIENTO");
        mapaSinonimos.put("NACI", "NACIMIENTO");

        mapaSinonimos.put("VIVIENDA", "VIVIENDA");
        mapaSinonimos.put("VIVIENDAS", "VIVIENDA");
        mapaSinonimos.put("DOMICILIO", "VIVIENDA");
        mapaSinonimos.put("EDIFICIO", "VIVIENDA");
        mapaSinonimos.put("EDIFICIOS", "VIVIENDA");
        mapaSinonimos.put("RESIDENCIA", "VIVIENDA");
        mapaSinonimos.put("RESIDENCIAS", "VIVIENDA");
        mapaSinonimos.put("CASA", "VIVIENDA");
        mapaSinonimos.put("CASAS", "VIVIENDA");
        mapaSinonimos.put("HOGAR", "VIVIENDA");
        mapaSinonimos.put("HOGARES", "VIVIENDA");
        mapaSinonimos.put("DEPARTAMENTO", "VIVIENDA");
        mapaSinonimos.put("DEPARTAMENTOS", "VIVIENDA");
        mapaSinonimos.put("VIVIR", "VIVIENDA");
        mapaSinonimos.put("VIVIS", "VIVIENDA");
        mapaSinonimos.put("VIVES", "VIVIENDA");
        mapaSinonimos.put("VIVIAN", "VIVIENDA");
        mapaSinonimos.put("VIVO", "VIVIENDA");
        mapaSinonimos.put("VIVI", "VIVIENDA");

        mapaSinonimos.put("ESCUELA", "ESCUELA");
        mapaSinonimos.put("ESCUELAS", "ESCUELA");
        mapaSinonimos.put("COLEGIO", "ESCUELA");
        mapaSinonimos.put("COLEGIOS", "ESCUELA");
        mapaSinonimos.put("ACADEMIA", "ESCUELA");
        mapaSinonimos.put("ACADEMIAS", "ESCUELA");
        mapaSinonimos.put("INSTITUTO", "ESCUELA");
        mapaSinonimos.put("INSTITUTOS", "ESCUELA");
        mapaSinonimos.put("UNIVERSIDAD", "ESCUELA");
        mapaSinonimos.put("UNIVERSIDADES", "ESCUELA");
        mapaSinonimos.put("FACULTAD", "ESCUELA");
        mapaSinonimos.put("FACULTADES", "ESCUELA");
        mapaSinonimos.put("ESTUDIO", "ESCUELA");
        mapaSinonimos.put("ESTUDIOS", "ESCUELA");
        mapaSinonimos.put("ESTUDIAR", "ESCUELA");
        mapaSinonimos.put("ESTUDIAS", "ESCUELA");
        mapaSinonimos.put("ESTUDIASTE", "ESCUELA");
        mapaSinonimos.put("ESTUDIAN", "ESCUELA");
        mapaSinonimos.put("CLASE", "ESCUELA");
        mapaSinonimos.put("CLASES", "ESCUELA");

        mapaSinonimos.put("PROFESOR", "PROFESOR");
        mapaSinonimos.put("PROFESORES", "PROFESOR");
        mapaSinonimos.put("PRECEPTOR", "PROFESOR");
        mapaSinonimos.put("PRECEPTORES", "PROFESOR");
        mapaSinonimos.put("DOCENTE", "PROFESOR");
        mapaSinonimos.put("DOCENTES", "PROFESOR");
        mapaSinonimos.put("MAESTRO", "PROFESOR");
        mapaSinonimos.put("MAESTROS", "PROFESOR");
        mapaSinonimos.put("EDUCADOR", "PROFESOR");
        mapaSinonimos.put("EDUCADORES", "PROFESOR");

        mapaSinonimos.put("BANCO", "BANCO");
        mapaSinonimos.put("BANCOS", "BANCO");
        mapaSinonimos.put("FINANCIERA", "BANCO");
        mapaSinonimos.put("FINANCIERAS", "BANCO");
        mapaSinonimos.put("PRESTAMISTA", "BANCO");
        mapaSinonimos.put("PRESTAMISTAS", "BANCO");
        mapaSinonimos.put("CREDITO", "BANCO");
        mapaSinonimos.put("CREDITOS", "BANCO");

        mapaSinonimos.put("DINERO", "DINERO");
        mapaSinonimos.put("DINEROS", "DINERO");
        mapaSinonimos.put("CAPITAL", "DINERO");
        mapaSinonimos.put("CAPITALES", "DINERO");
        mapaSinonimos.put("FORTUNA", "DINERO");
        mapaSinonimos.put("FORTUNAS", "DINERO");
        mapaSinonimos.put("PATRIMONIO", "DINERO");
        mapaSinonimos.put("PATRIMONIOS", "DINERO");
        mapaSinonimos.put("RICO", "DINERO");
        mapaSinonimos.put("RICOS", "DINERO");
        mapaSinonimos.put("RIQUEZA", "DINERO");
        mapaSinonimos.put("RIQUEZAS", "DINERO");
        mapaSinonimos.put("TESORO", "DINERO");
        mapaSinonimos.put("TESOROS", "DINERO");
        mapaSinonimos.put("AHORRO", "DINERO");
        mapaSinonimos.put("AHORROS", "DINERO");
        mapaSinonimos.put("BIENES", "DINERO");
        mapaSinonimos.put("ECONOMIA", "DINERO");
        mapaSinonimos.put("ECONOMIAS", "DINERO");
        mapaSinonimos.put("EFECTIVO", "DINERO");
        mapaSinonimos.put("EFECTIVOS", "DINERO");
        mapaSinonimos.put("MONEDA", "DINERO");
        mapaSinonimos.put("MONEDAS", "DINERO");
        mapaSinonimos.put("BILLETE", "DINERO");
        mapaSinonimos.put("BILLETES", "DINERO");
        mapaSinonimos.put("ALCANCIA", "DINERO");
        mapaSinonimos.put("ALCANCIAS", "DINERO");

        mapaSinonimos.put("JOYA", "JOYA");
        mapaSinonimos.put("JOYAS", "JOYA");
        mapaSinonimos.put("ALHAJA", "JOYA");
        mapaSinonimos.put("ALHAJAS", "JOYA");
        mapaSinonimos.put("RELIQUIA", "JOYA");
        mapaSinonimos.put("RELIQUIAS", "JOYA");
        mapaSinonimos.put("GEMA", "JOYA");
        mapaSinonimos.put("GEMAS", "JOYA");
        mapaSinonimos.put("DIAMANTE", "JOYA");
        mapaSinonimos.put("DIAMANTES", "JOYA");
        mapaSinonimos.put("PERLA", "JOYA");
        mapaSinonimos.put("PERLAS", "JOYA");
        mapaSinonimos.put("ANILLO", "JOYA");
        mapaSinonimos.put("ANILLOS", "JOYA");
        mapaSinonimos.put("COLLAR", "JOYA");
        mapaSinonimos.put("COLLARES", "JOYA");
        mapaSinonimos.put("ARO", "JOYA");
        mapaSinonimos.put("AROS", "JOYA");
        mapaSinonimos.put("CORONA", "JOYA");
        mapaSinonimos.put("CORONAS", "JOYA");
        mapaSinonimos.put("SORTIJA", "JOYA");
        mapaSinonimos.put("SORTIJAS", "JOYA");
        mapaSinonimos.put("GARGANTILLA", "JOYA");
        mapaSinonimos.put("GARGANTILLAS", "JOYA");
        mapaSinonimos.put("JOYERIA", "JOYA");
        mapaSinonimos.put("JOYERIAS", "JOYA");

        mapaSinonimos.put("TRABAJO", "TRABAJO");
        mapaSinonimos.put("TRABAJOS", "TRABAJO");
        mapaSinonimos.put("LABOR", "TRABAJO");
        mapaSinonimos.put("LABORES", "TRABAJO");
        mapaSinonimos.put("EMPLEO", "TRABAJO");
        mapaSinonimos.put("EMPLEOS", "TRABAJO");
        mapaSinonimos.put("OFICIO", "TRABAJO");
        mapaSinonimos.put("OFICIOS", "TRABAJO");
        mapaSinonimos.put("OCUPACION", "TRABAJO");
        mapaSinonimos.put("OCUPACIONES", "TRABAJO");
        mapaSinonimos.put("PROFESION", "TRABAJO");
        mapaSinonimos.put("PROFESIONES", "TRABAJO");
        mapaSinonimos.put("SUELDO", "TRABAJO");
        mapaSinonimos.put("SUELDOS", "TRABAJO");
        mapaSinonimos.put("REMUNERACION", "TRABAJO");
        mapaSinonimos.put("REMUNERACIONES", "TRABAJO");
        mapaSinonimos.put("TRABAJAR", "TRABAJO");
        mapaSinonimos.put("TRABAJAN", "TRABAJO");
        mapaSinonimos.put("TRABAJA", "TRABAJO");
        mapaSinonimos.put("TRABAJAS", "TRABAJO");

        mapaSinonimos.put("VACACIONES", "VACACIONES");
        mapaSinonimos.put("VIAJE", "VACACIONES");
        mapaSinonimos.put("VIAJES", "VACACIONES");
        mapaSinonimos.put("VACACIONAR", "VACACIONES");
        mapaSinonimos.put("VACACIONAN", "VACACIONES");
        mapaSinonimos.put("VACACIONAS", "VACACIONES");

        mapaSinonimos.put("LICENCIA", "LICENCIA");
        mapaSinonimos.put("LICENCIAS", "LICENCIA");
        mapaSinonimos.put("PERMISO", "LICENCIA");
        mapaSinonimos.put("CARNET", "LICENCIA");

        mapaSinonimos.put("ACTIVIDAD", "ACTIVIDAD");
        mapaSinonimos.put("ACTIVIDADES", "ACTIVIDAD");
        mapaSinonimos.put("DEBERES", "ACTIVIDAD");
        mapaSinonimos.put("DEBERES", "ACTIVIDAD");
        mapaSinonimos.put("DILIGENCIA", "ACTIVIDAD");
        mapaSinonimos.put("DILIGENCIAS", "ACTIVIDAD");

        mapaSinonimos.put("PASATIEMPO", "PASATIEMPO");
        mapaSinonimos.put("PASATIEMPOS", "PASATIEMPO");
        mapaSinonimos.put("RECREO", "PASATIEMPO");
        mapaSinonimos.put("RECREOS", "PASATIEMPO");
        mapaSinonimos.put("ESPARCIMIENTO", "PASATIEMPO");
        mapaSinonimos.put("ESPARCIMIENTOS", "PASATIEMPO");
        mapaSinonimos.put("HOBBY", "PASATIEMPO");
        mapaSinonimos.put("DISTRACCION", "PASATIEMPO");
        mapaSinonimos.put("DISTRACCIONES", "PASATIEMPO");
        mapaSinonimos.put("ENTRETENIMIENTO", "PASATIEMPO");
        mapaSinonimos.put("ENTRETENIMIENTOS", "PASATIEMPO");

        String palabraClave = mapaSinonimos.get(palabra);

        return palabraClave;
    }
}

