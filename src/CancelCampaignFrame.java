import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class CancelCampaignFrame extends JFrame implements ActionListener {
    private static final int WIDTH = 1200;
    private static final int HEIGHT = 800;
    private static final int COLUMN_WIDTH = 15;

    private JLabel id;
    private JTextField campaignId;
    private JButton cancelButton;
    private JButton exitButton;
    private VMS vms;

    public CancelCampaignFrame() {
        super("Anulare Campanie");

        setVisible(true);
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        vms = VMS.getInstance();
        id = new JLabel("ID");
        campaignId = new JTextField(COLUMN_WIDTH);
        cancelButton = new JButton("Anuleaza");
        exitButton = new JButton("Iesire");

        cancelButton.addActionListener(this);
        exitButton.addActionListener(this);

        cancelButton.setMnemonic(KeyEvent.VK_A);
        exitButton.setMnemonic(KeyEvent.VK_I);

        layoutComponents();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();

        if (clicked == cancelButton) {
            int id = Integer.parseInt(campaignId.getText());

            if (id > vms.getCampaigns().size()) {
                JOptionPane.showMessageDialog(null, "Campania cu numarul " +
                        "introdus nu exista!", "Eroare",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (vms.getCampaign(id).isExpired() || vms.getCampaign(id)
                    .isCancelled()) {
                JOptionPane.showMessageDialog(null, "Campania cu numarul " +
                        "introdus nu este noua sau inceputa!", "Eroare",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            vms.cancelCampaign(id);

            JOptionPane.showMessageDialog(null, "Campania a fost anulata cu "
                    + "succes!");
        }

        if (clicked == exitButton) {
            dispose();
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
        add(id, gridBagConstraints);

        // A doua coloana
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new Insets(0, 5, 0, 0);
        add(campaignId, gridBagConstraints);


        // Al doilea rand
        gridBagConstraints.gridy = 1;

        // Prima coloana
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        add(cancelButton, gridBagConstraints);

        // A doua coloana
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        add(exitButton, gridBagConstraints);
    }
}
