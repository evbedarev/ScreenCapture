package email;

public class MsgDeath implements EmailMessage {
    private static final String SUBJECT = "msg from bot: location changed";
    private static final String TEXT = "location changed";
    GoogleMail googleMail = new GoogleMail();

    public void sendMsg() {
        googleMail.sendMessage(SUBJECT, TEXT);
    }
}
