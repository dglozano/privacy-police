package production.system.criteria;

import production.system.Regla;

import java.util.ArrayList;
import java.util.List;

public class Novelty extends Criteria {

    @Override
    public List<Regla> apply(List<Regla> list) {
        //Calculo el mayor valor de novedad entre las reglas
        int mayorNovedad = getMayorNovedad(list);
        List<Regla> ret = new ArrayList<>();
        //Agrego a la lista de retorno aquellas reglas que tengan la mayor especifidad
        for(Regla regla : list){
            if(regla.getNovedad()==mayorNovedad){
                ret.add(regla);
            }
        }
        return ret; //Se retornan las reglas con mayor novedad
    }

    private int getMayorNovedad(List<Regla> list) {
        int novelty, mayor = 0;
        for(Regla r : list){
            novelty = r.getNovedad();
            if(novelty > mayor){
                mayor = novelty;
            }
        }
        return mayor;
    }

    @Override
    public String toString() {
        return "Novelty";
    }

}