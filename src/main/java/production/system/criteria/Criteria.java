package production.system.criteria;


import production.system.Regla;

import java.util.List;

public abstract class Criteria {

    public abstract List<Regla> apply(List<Regla> list);
    public abstract String toString();
}