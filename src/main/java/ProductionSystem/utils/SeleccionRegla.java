package ProductionSystem.utils;

import ProductionSystem.Regla;

import java.util.ArrayList;
import java.util.List;

public class SeleccionRegla {


    public static Regla seleccionarRegla(List<Regla> reglasActivas, String[] criterios) {

        Regla reglaSeleccionada = null;

        if (reglasActivas.size() == 1){
            reglaSeleccionada = reglasActivas.get(0);
        }else if(reglasActivas.size() > 1){
            int random = (int) (Math.random() * 5);
            String criterio = criterios[random];                                                //Elijo uno de los 5 criterios al azar
            List<Regla> auxiliar = new ArrayList<Regla>();

            switch (criterio) {
                case "noDuplicacion":
                    for(Regla regla : reglasActivas){                                           //Recorro todas las reglas candidatas; si no fue ejecutada nunca, la agrego a la lista auxiliar
                        if (!regla.isFueEjecutada())
                            auxiliar.add(regla);
                    }
                    if (auxiliar.size()==1)                                                     //Si queda una sola regla en la lista auxiliar, esa es la elegida (la única que nunca fue ejecutada)
                        reglaSeleccionada =  auxiliar.get(0);
                    else if (auxiliar.size() > 1)                                               //Si hay más de una que nunca fue ejecutada, selecciono al azar una de ellas
                        reglaSeleccionada =  seleccionAlAzar (auxiliar);
                    else if (auxiliar.size() == 0)                                              //Si todas las reglas candidatas ya fueron ejecutadas, selecciono cualquiera al azar
                        reglaSeleccionada =  seleccionAlAzar (reglasActivas);
                    break;

                case "novedad":
                    reglaSeleccionada = reglasActivas.get(0);
                    for(Regla regla : reglasActivas){
                        if (regla.getNovedad() > reglaSeleccionada.getNovedad())                //Recorro todas las reglas candidatas; y me quedo con la que tiene mayor valor en el atributo Novedad
                            reglaSeleccionada = regla;
                    }
                    break;

                case "especificidad":
                    int mayorEspecificidad = reglasActivas.get(0).getEspecificidad();
                    for(Regla regla : reglasActivas){
                        if (regla.getEspecificidad() > mayorEspecificidad)                      //Recorro todas las reglas candidatas para encontrar el mayor valor del atributo Especificidad
                            mayorEspecificidad = regla.getEspecificidad();
                    }
                    for(Regla regla : reglasActivas){                                           //Vuelvo a recorrer todas las reglas candidatas, y me quedo con las que tienen ese maximo valor de especificidad
                        if (regla.getEspecificidad() == mayorEspecificidad)
                            auxiliar.add(regla);
                    }
                    if (auxiliar.size()==1)                                                     //Si queda una sola regla en la lista auxiliar, esa es la elegida (la única con valor máximo de especificidad)
                        reglaSeleccionada =  auxiliar.get(0);
                    else if (auxiliar.size() > 1)                                               //Si hay más de una con el máximo valor de especificidad, selecciono al azar una de ellas
                        reglaSeleccionada =  seleccionAlAzar (auxiliar);

                    break;

                case "prioridad":
                    reglaSeleccionada = reglasActivas.get(0);
                    for(Regla regla : reglasActivas){
                        if (regla.getPrioridad() > reglaSeleccionada.getPrioridad())            //Recorro todas las reglas candidatas; y me quedo con la que tiene mayor valor en el atributo Prioridad
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