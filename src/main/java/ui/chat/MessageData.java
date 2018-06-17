package ui.chat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class MessageData {

    @FXML
    private HBox chatItemHBox;
    @FXML
    private Label chatMsgLabel;
    @FXML
    private ImageView imageChatMsg;

    public MessageData() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ChatItemCell.fxml"));
        fxmlLoader.setController(this);
        try
        {
            chatItemHBox = fxmlLoader.load();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void setInfo(MessagePOJO msgPOJO) {
        if(msgPOJO.getTipoMensaje() == MessagePOJO.TipoMensaje.CARTOY){
            chatItemHBox.setAlignment(Pos.CENTER_LEFT);
            chatMsgLabel.setText(msgPOJO.getMensaje());
            imageChatMsg.setImage(new Image(getClass().getClassLoader().getResourceAsStream("img/car-jackson-storm.png")));
        } else {
            chatItemHBox.setAlignment(Pos.CENTER_RIGHT);
            chatItemHBox.getChildren().add(chatItemHBox.getChildren().remove(0));
            chatMsgLabel.setText(msgPOJO.getMensaje());
            chatMsgLabel.setStyle("    -fx-background-color: -fx-shadow-highlight-color, -fx-outer-border, -fx-inner-border, rgba(60,228,0,0.22);\n" +
                    "    -fx-border-color: #014708;");
            imageChatMsg.setImage(new Image(getClass().getClassLoader().getResourceAsStream("img/police.png")));
        }
    }

    public HBox getBox() {
        return chatItemHBox;
    }
}
