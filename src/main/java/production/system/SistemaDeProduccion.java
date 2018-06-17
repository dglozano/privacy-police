package production.system;

import production.system.criteria.Criteria;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SistemaDeProduccion {

    private List<Criteria> criterias;
    private List<Regla> reglas;
    //Contiene la lista de reglas utilizadas. Sirve para el criterio de no duplicidad
    private static List<Regla> reglasUsadas = new ArrayList<>();

    public SistemaDeProduccion(List<Regla> reglas, List<Criteria> criterias) {
        this.reglas = reglas;
        this.criterias = criterias;
    }

    public String getAccionAgente(String fraseDelCarToy) {

        //Preproceso la frase y obtengo las palabras claves (Memoria de Trabajo)
        ProcesadorPalabrasClaves ppc = new ProcesadorPalabrasClaves();
        Set<String> palabrasClaves = ppc.getPalabrasClaves(fraseDelCarToy);

        //Si no hay palabras claves en el mensaje muestro este mensaje
        if(palabrasClaves.isEmpty()) {
            return "Lo siento, no he entendido lo que dijo el juguete.";
        }

        //Obtengo la lista de reglas activas (fase de cotejo)
        List<Regla> reglasActivas = matchear(palabrasClaves, reglas);

        //Si no hay reglas que matcheen muestro este mensaje
        if(reglasActivas.isEmpty()) {
            return "Lo siento, no he entendido lo que dijo el juguete.";
        }

        //Aplico los criterios para obtener una regla
        Regla reglaElegida = resolverConflictos(reglasActivas);

        //Devuelvo la accion del agente
        return reglaElegida.getAccion();
    }

    public List<Regla> matchear(Set<String> palabrasClavesPercibidas, List<Regla> memoriaDeProduccion) {
        List<Regla> reglasActivas = new ArrayList<>();
        //Recorro una por una todas las reglas
        for (Regla regla : memoriaDeProduccion) {
            //Por cada regla tengo que verificar que la secuencia de palabras sea la misma.
            //Si es la misma entonces hay que agregar la regla a la lista a devolver.
            Set<String> palabrasClavesRegla = regla.getPalabrasClaves();
            int cantPalabrasPercibidas = palabrasClavesPercibidas.size();
            if((palabrasClavesRegla.size() == cantPalabrasPercibidas)
                    && palabrasClavesRegla.containsAll(palabrasClavesPercibidas)) {
                reglasActivas.add(regla);
            }
        }
        return reglasActivas;
    }

    public Regla resolverConflictos(List<Regla> reglasActivas) {

        //FIXME borrar system outs cuando este listo el logger
        Regla reglaSeleccionada = null;
        System.out.println("Reglas Activas: " + reglasActivas);
        //Aplica todas las criterias hasta quedarse con una unica regla candidata
        //Se asegura que quede solo una porque la ultima criteria en aplicarse es la Random
        //El orden de aplicacion de criterias es: NoDuplicacion, Prioridad, Novedad, Especifididad, Random
        for(Criteria actualCriteria: criterias) {
            reglasActivas = actualCriteria.apply(reglasActivas);
            System.out.println("Criteria: " + actualCriteria);
            if(reglasActivas.isEmpty()) {
                //Esto lo pongo por las dudas pero no se deberia dar porque lo valido antes.
                System.out.println("No hay reglas para aplicar");
                break;
            } else {
                System.out.println("Reglas en Conflicto: \n");
                for(Regla regla: reglasActivas){
                    System.out.println(regla);
                }
                if(reglasActivas.size() == 1) break;
            }
        }

        reglaSeleccionada = (reglasActivas.isEmpty()) ? null : reglasActivas.get(0);

        return reglaSeleccionada;
    }

    public static boolean fueReglaUsada(Regla regla) {
        return reglasUsadas.contains(regla);
    }

    public static void marcarReglasComoNoUsadas() {
        reglasUsadas.clear();
    }
}