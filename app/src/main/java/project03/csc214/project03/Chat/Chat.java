package project03.csc214.project03.Chat;

/**
 * Created by Daniel on 6/27/2017.
 */

public class Chat {


    String fName;
    String lName;
    String UID;

    public Chat(String fName, String lName, String UID) {
        this.fName = fName;
        this.lName = lName;
        this.UID = UID;
    }


    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }
}
