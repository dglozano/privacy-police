package production.system;

import production.system.criteria.Criteria;
import ui.logger.ArchiveLogger;
import ui.logger.LogEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SistemaDeProduccion {

    private List<Criteria> criterias;
    private List<Regla> reglas;
    private ArchiveLogger logger;
    //Contiene la lista de reglas utilizadas. Sirve para el criterio de no duplicidad
    private static List<Regla> reglasUsadas = new ArrayList<>();

    public SistemaDeProduccion(List<Regla> reglas, List<Criteria> criterias, ArchiveLogger logger) {

        this.reglas = reglas;
        this.criterias = criterias;
        this.logger = logger;

        LogEntry reglasLog = new LogEntry();
        reglasLog.writeTitleLine("Reglas");
        for(Regla regla : reglas)
            reglasLog.writeLine(regla.toString());
        this.logger.addLog(reglasLog);

        LogEntry criteriasLog = new LogEntry();
        criteriasLog.writeTitleLine("Criterias");
        for(Criteria criteria : criterias)
            criteriasLog.writeLine(criteria.toString());
        this.logger.addLog(criteriasLog);
    }

    public String getAccionAgente(String fraseDelCarToy) {

        LogEntry logPalabrasClaves = new LogEntry();
        logPalabrasClaves.writeTitleLine("Preprocesado");
        logPalabrasClaves.writeLine("Frase original: " + fraseDelCarToy);
        logPalabrasClaves.markAsFirstLog();

        //Preproceso la frase y obtengo las palabras claves (Memoria de Trabajo)
        ProcesadorPalabrasClaves ppc = new ProcesadorPalabrasClaves();
        Set<String> palabrasClaves = ppc.getPalabrasClaves(fraseDelCarToy);


        //Si no hay palabras claves en el mensaje muestro este mensaje
        if(palabrasClaves.isEmpty()) {
            logPalabrasClaves.writeLine("No se encontraron palabras claves en la frase.");
            logger.addLog(logPalabrasClaves);
            return "Lo siento, no he entendido lo que dijo el juguete.";
        } else {
            logPalabrasClaves.writeLine("Palabras claves: " + palabrasClaves);
            logger.addLog(logPalabrasClaves);
        }

        LogEntry logReglasActivas = new LogEntry();
        logReglasActivas.writeTitleLine("Reglas activas");
        //Obtengo la lista de reglas activas (fase de cotejo)
        List<Regla> reglasActivas = matchear(palabrasClaves, reglas);

        //Si no hay reglas que matcheen muestro este mensaje
        if(reglasActivas.isEmpty()) {
            logReglasActivas.writeLine("No se matcheo ninguna regla.");
            logger.addLog(logReglasActivas);
            return "Lo siento, no he entendido lo que dijo el juguete.";
        } else {
            for(Regla regla : reglasActivas)
                logReglasActivas.writeLine(regla.toString());
        }
        logger.addLog(logReglasActivas);

        //Aplico los criterios para obtener una regla si hay mas de una regla activa.
        Regla reglaElegida = resolverConflictos(reglasActivas);

        //Escribo el log de la accion elegida
        LogEntry logAccion = new LogEntry();
        logAccion.writeTitleLine("Accion elegida");
        logAccion.writeLine(reglaElegida.getAccion());
        logger.addLog(logAccion);

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

        LogEntry logConflictos = new LogEntry();
        logConflictos.writeLine("Resolucion conflictos");

        //Si hay una sola regla activa.
        if(reglasActivas.size() == 1) {
            logConflictos.writeLine("No hubo conflictos a resolver porque habia una sola regla activa.\n");
            logConflictos.writeLine(reglasActivas.get(0).toString());
            logger.addLog(logConflictos);
            return reglasActivas.get(0);
        }

        logConflictos.writeLine("Reglas en Conflicto: \n");
        for(Regla regla: reglasActivas){
            logConflictos.writeLine(regla.toString());
        }

        Regla reglaSeleccionada = null;
        //Aplica todas las criterias hasta quedarse con una unica regla candidata
        //Se asegura que quede solo una porque la ultima criteria en aplicarse es la Random
        //El orden de aplicacion de criterias es: NoDuplicacion, Prioridad, Novedad, Especifididad, Random
        for(Criteria actualCriteria: criterias) {
            logConflictos.writeLine("Aplica criteria <" + actualCriteria + ">");
            reglasActivas = actualCriteria.apply(reglasActivas);
            if(reglasActivas.isEmpty()) {
                //Esto lo pongo por las dudas pero no se deberia dar porque lo valido antes.
                logConflictos.writeLine("No hay reglas para aplicar");
                break;
            } else {
                logConflictos.writeLine("Reglas en Conflicto: \n");
                for(Regla regla: reglasActivas){
                    logConflictos.writeLine(regla.toString());
                }
                if(reglasActivas.size() == 1) break;
            }
        }

        reglaSeleccionada = (reglasActivas.isEmpty()) ? null : reglasActivas.get(0);

        reglasUsadas.add(reglaSeleccionada);

        logConflictos.writeLine("Regla elegida: " + reglaSeleccionada.toString());
        logger.addLog(logConflictos);

        return reglaSeleccionada;
    }

    public static boolean fueReglaUsada(Regla regla) {
        return reglasUsadas.contains(regla);
    }

    public static void marcarReglasComoNoUsadas() {
        reglasUsadas.clear();
    }
}