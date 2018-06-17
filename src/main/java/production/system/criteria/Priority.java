package production.system.criteria;

import production.system.Regla;

import java.util.ArrayList;
import java.util.List;

public class Priority extends Criteria {

    @Override
    public List<Regla> apply(List<Regla> list) {
        //Calculo el mayor valor de prioridad entre las reglas
        int mayorPrioridad = getMayorPrioridad(list);
        List<Regla> ret = new ArrayList<>();
        //Agrego a la lista de retorno aquellas reglas que tengan la mayor especifidad
        for(Regla regla : list){
            if(regla.getPrioridad() == mayorPrioridad){
                ret.add(regla);
            }
        }
        return ret;
    }

    private int getMayorPrioridad(List<Regla> list) {
        int priority, mayor = 0;
        for(Regla r : list){
            priority = r.getPrioridad();
            if(priority > mayor){
                mayor = priority;
            }
        }
        return mayor;
    }

    @Override
    public String toString() {
        return "Priority";
    }

}