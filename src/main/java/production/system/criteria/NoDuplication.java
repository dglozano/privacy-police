package production.system.criteria;

import production.system.Regla;
import production.system.SistemaDeProduccion;

import java.util.ArrayList;
import java.util.List;

public class NoDuplication extends Criteria {

    @Override
    public List<Regla> apply(List<Regla> list) {

        List<Regla> ret = new ArrayList<Regla>();
        for(Regla regla: list){
            //Como es No duplicidad, se tiene que analizar si la regla no fue utilizada con anterioridad
            if(!SistemaDeProduccion.fueReglaUsada(regla)){
                ret.add(regla);
            }
        }
        //Si todas fueron usadas se devuelven todas las reglas como activas y se debe borrar
        //la lista de usadas para poder usar nuevamente este criterio.
        if(ret.isEmpty()){
            SistemaDeProduccion.marcarReglasComoNoUsadas();
            return list;
        }
        return ret;
    }

    @Override
    public String toString() {
        return "No duplication";
    }
}