import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class VouchersFrame extends JFrame implements ActionListener {
    private static final int WIDTH = 1200;
    private static final int HEIGHT = 800;

    private JButton showExistingVouchersButton;
    private JButton generateVoucherButton;
    private JButton redeemVoucherButton;
    private JButton exitButton;
    private Campaign campaign;
    private User user;

    public VouchersFrame(User user, Campaign campaign) {
        super("Pagina Vouchere");

        setVisible(true);
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        this.user = user;
        this.campaign = campaign;

        showExistingVouchersButton = new JButton("Afiseaza voucherele");
        showExistingVouchersButton.addActionListener(this);
        showExistingVouchersButton.setMnemonic(KeyEvent.VK_A);

        if (user.isAdmin()) {
            generateVoucherButton = new JButton("Genereaza voucher");
            redeemVoucherButton = new JButton("Marcheaza voucher");

            generateVoucherButton.addActionListener(this);
            redeemVoucherButton.addActionListener(this);

            generateVoucherButton.setMnemonic(KeyEvent.VK_G);
            redeemVoucherButton.setMnemonic(KeyEvent.VK_M);
        }

        exitButton = new JButton("Iesire");
        exitButton.addActionListener(this);
        exitButton.setMnemonic(KeyEvent.VK_I);

        layoutComponents(user);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();

        if (clicked == showExistingVouchersButton) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new ExistingVouchersFrame(user, campaign);
                }
            });
        }

        if (clicked == generateVoucherButton) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new GenerateVoucherFrame(campaign);
                }
            });
        }

        if (clicked == redeemVoucherButton) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new RedeemVoucherFrame(campaign);
                }
            });
        }

        if (clicked == exitButton) {
            dispose();
        }
    }

    private void layoutComponents(User user) {
        setLayout(new GridBagLayout());

        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.fill = GridBagConstraints.NONE;

        // Prima linie
        gridBagConstraints.gridy = 0;

        // Prima coloana
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.insets = new Insets(0, 0, 0, 5);
        add(showExistingVouchersButton, gridBagConstraints);

        if (user.isAdmin()) {
            // A doua coloana
            gridBagConstraints.gridx = 1;
            gridBagConstraints.anchor = GridBagConstraints.CENTER;
            gridBagConstraints.insets = new Insets(0, 5, 0, 0);
            add(generateVoucherButton, gridBagConstraints);


            // Al doilea rand
            gridBagConstraints.gridy = 1;

            // Prima coloana
            gridBagConstraints.gridx = 0;
            gridBagConstraints.anchor = GridBagConstraints.CENTER;
            gridBagConstraints.insets = new Insets(0, 0, 0, 5);
            add(redeemVoucherButton, gridBagConstraints);
        }

        // A doua coloana
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.insets = new Insets(0, 5, 0, 0);
        add(exitButton, gridBagConstraints);
    }
}
