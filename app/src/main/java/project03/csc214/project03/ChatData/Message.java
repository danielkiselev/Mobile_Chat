package project03.csc214.project03.ChatData;

/**
 * Created by Daniel on 6/28/2017.
 */

public class Message {

    String message;
    String UID;//uid of person ur chatting

    public Message(String message, String UID) {
        this.message = message;
        this.UID = UID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }
}
