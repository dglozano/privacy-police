package ui.chat;

import javafx.scene.control.ListCell;

public class ChatMessageCell extends ListCell<MessagePOJO> {

    @Override
    public void updateItem(MessagePOJO msgPOJO, boolean empty) {
        super.updateItem(msgPOJO, empty);

        if(empty) {
            setGraphic(null);
        } else {
            MessageData data = new MessageData();
            data.setInfo(msgPOJO);
            setGraphic(data.getBox());
        }
    }
}