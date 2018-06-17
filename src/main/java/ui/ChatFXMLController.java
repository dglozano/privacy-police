package ui;

import ProductionSystem.Regla;
import ProductionSystem.utils.SeleccionRegla;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import ui.chatutils.ChatMessageCell;
import ui.chatutils.MessagePOJO;
import ProductionSystem.utils.ProcesaEntrada;
import ProductionSystem.utils.ObtencionReglas;
import ProductionSystem.utils.SeleccionRegla;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

public class ChatFXMLController implements Initializable {

    @FXML
    private Button botonAceptar;
    @FXML
    private TextField msgInput;
    @FXML
    private ListView chatListView;
    @FXML
    private Label logsView;

    List<Regla> reglasTotales = ObtencionReglas.obtenerTodas();                                           //Obtengo todas las reglas existentes
    String[] criterios = {"noDuplicacion", "novedad", "especificidad", "prioridad", "aleatorio"};

    ObservableList<MessagePOJO> mensajesChatList = FXCollections.observableArrayList();

    @FXML
    private void send(ActionEvent event){
        String input = msgInput.getText().toUpperCase();
        msgInput.clear();
        if(input.isEmpty())
            return;
        mensajesChatList.add(new MessagePOJO(input, MessagePOJO.TipoMensaje.CARTOY));

        //TOMAR ENTRADA Y GENERAR PALABRAS CLAVES:
        ArrayList palabrasClaves = ProcesaEntrada.encuentraPalabrasClaves(input);
        //FIXME: BORRAR LUEGO
        System.out.println("*Palabras claves: ");
        Iterator it = palabrasClaves.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }


        //TOMAR PALABRAS CLAVES Y OBTENER REGLAS CANDIDATAS (ACTIVAS):
        List<Regla> reglasActivas = null;
        if(!palabrasClaves.isEmpty()) {
            reglasActivas = ObtencionReglas.obtenerActivas(palabrasClaves, reglasTotales);                   //Fase de cotejo

            //FIXME: BORRAR LUEGO
            System.out.println("*Acciones de las reglas candidatas: ");
            Iterator it2 = reglasActivas.iterator();
            while (it2.hasNext()) {
                Regla regla = (Regla) it2.next();
                System.out.println(regla.getAccion());
            }
        } else{
            System.out.println("La frase ingresada no pertenece al Smart Toy “Hello Barbie” ");
        }

        //TOMAR LAS REGLAS CANDIDATAS Y SELECCIONAR UNA MEDIANTE UNO DE LOS CRITERIOS
        Regla reglaSeleccionada = null;
        if (reglasActivas != null) {
            if (!reglasActivas.isEmpty()) {
                reglaSeleccionada = SeleccionRegla.seleccionarRegla(reglasActivas, criterios);

                //FIXME: BORRAR LUEGO
                System.out.println("*Acción de las regla seleccionada: ");
                System.out.println(reglaSeleccionada.getAccion());
            }else {
                System.out.println("No se encontraron reglas para esta frase");
            }
        }

        // escribir en el log
        // observableList.add(new MessagePOJO(respuestaAgente, MessagePOJO.TipoMensaje.RESPUESTA_AGENTE));
        //FIXME: BORRAR LUEGO
        logsView.setText(logsView.getText() + "\nEscribir log de lo que hizo el agente");
        logsView.setText(logsView.getText() + "\nHizo esto");
        logsView.setText(logsView.getText() + "\ny esto");
        logsView.setText(logsView.getText() + "\ny responde esto");

        mensajesChatList.add(new MessagePOJO("Esta es una respuesta mock", MessagePOJO.TipoMensaje.RESPUESTA_AGENTE));

        //Scrollea hasta abajo cuando se agrega un mensaje
        Platform.runLater( () -> chatListView.scrollTo(mensajesChatList.size()-1));
    }

    public void setListView()
    {
        mensajesChatList.add(new MessagePOJO("Hola como te llamas? sadfasdfasdafsadfasdf sadf sdafsad daf sadfasdf sdaf adfsa f asdfsda safd ",
                MessagePOJO.TipoMensaje.CARTOY));
        mensajesChatList.add(new MessagePOJO("no estas obligado a decir  sdafasd fsaf sdaf saf sad fsadf sad fsad sadfsadf sad sf df af asd af saeso",
                MessagePOJO.TipoMensaje.RESPUESTA_AGENTE));

        chatListView.setItems(mensajesChatList);
        chatListView.setCellFactory((listView -> new ChatMessageCell()));
        logsView.setText("Inicializando simluador ... \n");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setListView();

        //Que tenga foco el input
        Platform.runLater( () -> msgInput.requestFocus());
    }



}
