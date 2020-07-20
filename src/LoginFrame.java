import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class LoginFrame extends JFrame implements ActionListener {
    private static final int SUCCESS_STATUS = 0;
    private static final int WIDTH = 1200;
    private static final int HEIGHT = 800;

    private JLabel userNameLabel;
    private JLabel passwordLabel;
    private JTextField userNameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton exitButton;
    private VMS vms;

    public LoginFrame() {
        super("Login");

        setVisible(true);
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        vms = VMS.getInstance();
        userNameLabel = new JLabel("Nume utilizator");
        passwordLabel = new JLabel("Parola");
        userNameField = new JTextField(15);
        passwordField = new JPasswordField(15);
        loginButton = new JButton("Autentificare");
        exitButton = new JButton("Iesire");

        loginButton.addActionListener(this);
        exitButton.addActionListener(this);

        loginButton.setMnemonic(KeyEvent.VK_A);
        exitButton.setMnemonic(KeyEvent.VK_I);

        layoutComponents();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();

        if (clicked == loginButton) {
            String userName = userNameField.getText();
            char[] userPassword = passwordField.getPassword();

            for (User user : vms.getUsers()) {
                if (user.getName().equals(userName)) {
                    String password = String.valueOf(userPassword);
                    if (password.equals(user.getUserPassword())) {
                        dispose();

                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                new CampaignsFrame(user);
                            }
                        });

                        return;
                    }

                    JOptionPane.showMessageDialog(null,
                            "Nu ai introdus nume sau parola valida",
                            "Eroare", JOptionPane.ERROR_MESSAGE);

                    return;
                }
            }

            JOptionPane.showMessageDialog(null,
                    "Nu ai introdus nume sau parola valida", "Eroare",
                    JOptionPane.ERROR_MESSAGE);
        }

        if (clicked == exitButton) {
            int action = JOptionPane.showConfirmDialog(null,
                    "Vrei sa parasesti aplicatia?", "Confirma iesirea",
                    JOptionPane.OK_CANCEL_OPTION);

            if (action == JOptionPane.OK_OPTION) {
                System.exit(SUCCESS_STATUS);
            }
        }
    }

    private void layoutComponents() {
        setLayout(new GridBagLayout());

        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.fill = GridBagConstraints.NONE;

        // Primul rand
        gridBagConstraints.gridy = 0;

        // Prima coloana
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(0, 0, 0, 5);
        add(userNameLabel, gridBagConstraints);

        // A doua coloana
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new Insets(0, 5, 0, 0);
        add(userNameField, gridBagConstraints);


        // Al doilea rand
        gridBagConstraints.gridy = 1;

        // Prima coloana
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(0, 0, 0, 5);
        add(passwordLabel, gridBagConstraints);

        // A doua coloana
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new Insets(0, 5, 0, 0);
        add(passwordField, gridBagConstraints);


        // Al treilea rand
        gridBagConstraints.gridy = 2;

        // Prima coloana
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        add(loginButton, gridBagConstraints);

        // A doua coloana
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        add(exitButton, gridBagConstraints);
    }
}
