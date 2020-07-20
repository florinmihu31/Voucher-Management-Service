import javax.swing.*;
import java.awt.*;

public class NotificationsFrame extends JFrame {
    private static final int WIDTH = 1200;
    private static final int HEIGHT = 800;

    private JTextArea notificationsArea;

    public NotificationsFrame(User user) {
        super("Notificari");

        setVisible(true);
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        notificationsArea = new JTextArea();

        user.listNotifications(notificationsArea);

        notificationsArea.setEditable(false);

        add(notificationsArea);
    }
}
