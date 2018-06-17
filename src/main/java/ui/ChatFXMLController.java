package ui;

import production.system.MemoriaDeProduccion;
import production.system.Regla;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import production.system.SistemaDeProduccion;
import production.system.criteria.*;
import ui.chatutils.ChatMessageCell;
import ui.chatutils.MessagePOJO;


import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

public class ChatFXMLController implements Initializable {

    @FXML
    private TextField msgInput;
    @FXML
    private ListView chatListView;
    @FXML
    private Label logsView;

    private ObservableList<MessagePOJO> mensajesChatList;
    private SistemaDeProduccion sistemaDeProduccion;

    @FXML
    private void send(ActionEvent event) {
        // Obtengo la frase del cartoy desde el input
        String fraseCarToy = msgInput.getText();
        msgInput.clear();

        // Si es un mensaje en blanco no lo proceso
        if(fraseCarToy.trim().isEmpty())
            return;

        // Muestro en pantalla el mensaje del cartoy
        mensajesChatList.add(new MessagePOJO(fraseCarToy, MessagePOJO.TipoMensaje.CARTOY));

        // Obtengo del sistema de producciones la respuesta del agente
        String respuestaAgente = sistemaDeProduccion.getAccionAgente(fraseCarToy);

        // TODO: escribir en el log
        //FIXME: BORRAR LUEGO
        logsView.setText(logsView.getText() + "\nEscribir log de lo que hizo el agente");
        logsView.setText(logsView.getText() + "\nHizo esto");
        logsView.setText(logsView.getText() + "\ny esto");
        logsView.setText(logsView.getText() + "\ny responde esto");

        // Muestro la respueta del agente
        mensajesChatList.add(new MessagePOJO(respuestaAgente, MessagePOJO.TipoMensaje.RESPUESTA_AGENTE));

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
        //Seteo Listviews.
        mensajesChatList = FXCollections.observableArrayList();
        setListView();

        //Pongo foco en el input
        Platform.runLater( () -> msgInput.requestFocus());

        //Obtengo todas las reglas precargadas
        List<Regla> reglas = MemoriaDeProduccion.getReglas();

        //Creo la lista de criterias a usar. Para resolver los conflictos se usan todas.
        //Es importante el orden (NoDuplicacion es la primera en usarse y Random la ultima).
        List<Criteria> criterias = new ArrayList<>();
        criterias.add(new NoDuplication());
        criterias.add(new Priority());
        criterias.add(new Novelty());
        criterias.add(new Specifity());
        criterias.add(new Random());

        //Obtengo un SistemaDeProduccion con mis reglas y criterias
        sistemaDeProduccion = new SistemaDeProduccion(reglas, criterias);
    }
}
