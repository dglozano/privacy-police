package production.system.criteria;

import production.system.Regla;

import java.util.ArrayList;
import java.util.List;

public class Specifity extends Criteria {

    @Override
    public List<Regla> apply(List<Regla> list) {
        //Calculo el mayor valor de especifidad entre las reglas
        int mayorEspecifidad = getMayorEspecifidad(list);
        List<Regla> ret = new ArrayList<>();
        //Agrego a la lista de retorno aquellas reglas que tengan la mayor especifidad
        for(Regla regla : list){
            if(regla.getEspecificidad() == mayorEspecifidad){
                ret.add(regla);
            }
        }
        return ret;
    }

    private int getMayorEspecifidad(List<Regla> list) {
        int specifity, mayor=0;
        for(Regla r : list){
            specifity = r.getEspecificidad();
            if(specifity > mayor){
                mayor = specifity;
            }
        }
        return mayor;
    }

    @Override
    public String toString() {
        return "Specificity";
    }
}