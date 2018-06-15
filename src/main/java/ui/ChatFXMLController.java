package ui;

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

import java.net.URL;
import java.util.ArrayList;
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

    ObservableList<MessagePOJO> mensajesChatList = FXCollections.observableArrayList();

    @FXML
    private void send(ActionEvent event){
        String input = msgInput.getText();
        msgInput.clear();
        if(input.isEmpty())
            return;
        mensajesChatList.add(new MessagePOJO(input, MessagePOJO.TipoMensaje.CARTOY));
        // procesar input para sacar palabras claves
        // pasar al sistemas de produccciones las palabras claves y obtener respuestas
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
