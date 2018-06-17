package production.system;

import java.util.ArrayList;
import java.util.List;

public class SeleccionRegla {


    public static Regla seleccionarRegla(List<Regla> reglasActivas, String[] criterios) {

        Regla reglaSeleccionada = null;

        if (reglasActivas.size() == 1){
            reglaSeleccionada = reglasActivas.get(0);
        }else if(reglasActivas.size() > 1){
            int random = (int) (Math.random() * 5);
            //Elijo uno de los 5 criterios al azar
            String criterio = criterios[random];
            List<Regla> auxiliar = new ArrayList<Regla>();

            switch (criterio) {
                case "noDuplicacion":
                    //Recorro todas las reglas candidatas; si no fue ejecutada nunca, la agrego a la lista auxiliar
                    for(Regla regla : reglasActivas){
                        if (!regla.isFueEjecutada())
                            auxiliar.add(regla);
                    }
                    //Si queda una sola regla en la lista auxiliar, esa es la elegida (la única que nunca fue ejecutada)
                    if (auxiliar.size()==1)
                        reglaSeleccionada =  auxiliar.get(0);
                    //Si hay más de una que nunca fue ejecutada, selecciono al azar una de ellas
                    else if (auxiliar.size() > 1)
                        reglaSeleccionada =  seleccionAlAzar (auxiliar);
                    //Si todas las reglas candidatas ya fueron ejecutadas, selecciono cualquiera al azar
                    else if (auxiliar.size() == 0)
                        reglaSeleccionada =  seleccionAlAzar (reglasActivas);
                    break;

                case "novedad":
                    reglaSeleccionada = reglasActivas.get(0);
                    for(Regla regla : reglasActivas){
                        //Recorro todas las reglas candidatas; y me quedo con la que tiene mayor valor en el atributo Novedad
                        if (regla.getNovedad() > reglaSeleccionada.getNovedad())
                            reglaSeleccionada = regla;
                    }
                    break;

                case "especificidad":
                    int mayorEspecificidad = reglasActivas.get(0).getEspecificidad();
                    for(Regla regla : reglasActivas){
                        //Recorro todas las reglas candidatas para encontrar el mayor valor del atributo Especificidad
                        if (regla.getEspecificidad() > mayorEspecificidad)
                            mayorEspecificidad = regla.getEspecificidad();
                    }
                    //Vuelvo a recorrer todas las reglas candidatas, y me quedo con las que tienen ese maximo valor de especificidad
                    for(Regla regla : reglasActivas){
                        if (regla.getEspecificidad() == mayorEspecificidad)
                            auxiliar.add(regla);
                    }
                    //Si queda una sola regla en la lista auxiliar, esa es la elegida (la única con valor máximo de especificidad)
                    if (auxiliar.size()==1)
                        reglaSeleccionada =  auxiliar.get(0);
                    //Si hay más de una con el máximo valor de especificidad, selecciono al azar una de ellas
                    else if (auxiliar.size() > 1)
                        reglaSeleccionada =  seleccionAlAzar (auxiliar);

                    break;

                case "prioridad":
                    reglaSeleccionada = reglasActivas.get(0);
                    for(Regla regla : reglasActivas){
                        //Recorro todas las reglas candidatas; y me quedo con la que tiene mayor valor en el atributo Prioridad
                        if (regla.getPrioridad() > reglaSeleccionada.getPrioridad())
                            reglaSeleccionada = regla;
                    }
                    break;

                case "aleatorio":
                    reglaSeleccionada = seleccionAlAzar(reglasActivas);
                    break;
            }
        }

        if(reglaSeleccionada != null)
            reglaSeleccionada.setFueEjecutada(true);
        return reglaSeleccionada;
    }

    private static Regla seleccionAlAzar(List<Regla> listaReglas) {

        int random = (int) (Math.random() * listaReglas.size());

        return listaReglas.get(random);
    }
}