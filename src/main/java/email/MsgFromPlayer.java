package email;

public class MsgFromPlayer implements EmailMessage {
    private static final String SUBJECT = "msg from bot: Recieved new message from player";
    private static final String TEXT = "New message Ragnarok";
    GoogleMail googleMail = new GoogleMail();

    public void sendMsg() {
        googleMail.sendMessage(SUBJECT, TEXT);
    }
}
