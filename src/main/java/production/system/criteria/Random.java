package production.system.criteria;


import production.system.Regla;

import java.util.ArrayList;
import java.util.List;

public class Random extends Criteria {

    @Override
    public List<Regla> apply(List<Regla> list) {
        java.util.Random r = new java.util.Random(System.currentTimeMillis());
        int valor = r.nextInt(list.size());
        List<Regla> ret = new ArrayList<>();
        ret.add(list.get(valor));
        return ret;
    }

    @Override
    public String toString() {
        return "Random";
    }
}