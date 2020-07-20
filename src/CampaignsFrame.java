import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class CampaignsFrame extends JFrame implements ActionListener {
    private static final int SUCCESS_STATUS = 0;
    private static final int WIDTH = 1200;
    private static final int HEIGHT = 800;

    private JButton showExistingCampaigns;
    private JButton addCampaign;
    private JButton editCampaign;
    private JButton cancelCampaign;
    private JButton showNotifications;
    private JButton exitButton;
    private User user;

    public CampaignsFrame(User user) {
        super("Voucher Management Service");

        setVisible(true);
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.user = user;
        showExistingCampaigns = new JButton("Afiseaza toate campaniile");
        exitButton = new JButton("Iesire");

        if (user.isAdmin()) {
            addCampaign = new JButton("Adauga o campanie");
            editCampaign = new JButton("Editeaza o campanie");
            cancelCampaign = new JButton("Anuleaza o campanie");

            addCampaign.addActionListener(this);
            editCampaign.addActionListener(this);
            cancelCampaign.addActionListener(this);

            addCampaign.setMnemonic(KeyEvent.VK_D);
            editCampaign.setMnemonic(KeyEvent.VK_E);
            cancelCampaign.setMnemonic(KeyEvent.VK_N);
        } else {
            showNotifications = new JButton("Afiseaza notificarile");
            showNotifications.addActionListener(this);
            showNotifications.setMnemonic(KeyEvent.VK_N);
        }

        showExistingCampaigns.addActionListener(this);
        exitButton.addActionListener(this);

        showExistingCampaigns.setMnemonic(KeyEvent.VK_A);
        exitButton.setMnemonic(KeyEvent.VK_I);

        layoutComponents(user);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();

        if (clicked == showExistingCampaigns) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new ExistingCampaignsFrame(user);
                }
            });
        }

        if (clicked == addCampaign) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new NewCampaignFrame();
                }
            });
        }

        if (clicked == editCampaign) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new EditCampaignFrame();
                }
            });
        }

        if (clicked == cancelCampaign) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new CancelCampaignFrame();
                }
            });
        }

        if (clicked == showNotifications) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new NotificationsFrame(user);
                }
            });
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

    private void layoutComponents(User user) {
        setLayout(new GridBagLayout());

        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.fill = GridBagConstraints.NONE;

        if (!(user.isAdmin())) {

            // Primul rand
            gridBagConstraints.gridy = 0;

            // Prima coloana
            gridBagConstraints.gridx = 0;
            gridBagConstraints.anchor = GridBagConstraints.CENTER;
            gridBagConstraints.insets = new Insets(0, 0, 0, 0);
            add(showExistingCampaigns, gridBagConstraints);

            // A doua coloana
            gridBagConstraints.gridx = 1;
            gridBagConstraints.anchor = GridBagConstraints.CENTER;
            gridBagConstraints.insets = new Insets(0, 0, 0, 0);
            add(showNotifications, gridBagConstraints);


            // Al doilea rand
            gridBagConstraints.gridy = 1;

            // A doua coloana
            gridBagConstraints.gridx = 1;
            gridBagConstraints.anchor = GridBagConstraints.CENTER;
            gridBagConstraints.insets = new Insets(0, 0, 0, 0);
            add(exitButton, gridBagConstraints);

        } else {
            // Primul rand
            gridBagConstraints.gridy = 0;

            // Prima coloana
            gridBagConstraints.gridx = 0;
            gridBagConstraints.anchor = GridBagConstraints.CENTER;
            gridBagConstraints.insets = new Insets(0, 0, 0, 0);
            add(showExistingCampaigns, gridBagConstraints);

            // A doua coloana
            gridBagConstraints.gridx = 1;
            gridBagConstraints.anchor = GridBagConstraints.CENTER;
            gridBagConstraints.insets = new Insets(0, 0, 0, 0);
            add(addCampaign, gridBagConstraints);


            // Al doilea rand
            gridBagConstraints.gridy = 1;

            // Prima coloana
            gridBagConstraints.gridx = 0;
            gridBagConstraints.anchor = GridBagConstraints.CENTER;
            gridBagConstraints.insets = new Insets(0, 0, 0, 0);
            add(editCampaign, gridBagConstraints);

            // A doua coloana
            gridBagConstraints.gridx = 1;
            gridBagConstraints.anchor = GridBagConstraints.CENTER;
            gridBagConstraints.insets = new Insets(0, 0, 0, 0);
            add(cancelCampaign, gridBagConstraints);


            // Al treilea rand
            gridBagConstraints.gridy = 2;

            // A doua coloana
            gridBagConstraints.gridx = 1;
            gridBagConstraints.anchor = GridBagConstraints.CENTER;
            gridBagConstraints.insets = new Insets(0, 0, 0, 0);
            add(exitButton, gridBagConstraints);
        }
    }
}
