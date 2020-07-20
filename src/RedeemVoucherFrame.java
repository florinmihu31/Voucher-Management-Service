import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.time.LocalDateTime;

public class RedeemVoucherFrame extends JFrame implements ActionListener {
    private static final int WIDTH = 1200;
    private static final int HEIGHT = 800;
    private static final int COLUMN_WIDTH = 15;

    private JLabel id;
    private JTextField idField;
    private JButton redeemButton;
    private JButton exitButton;
    private Campaign campaign;
    private VMS vms;

    public RedeemVoucherFrame(Campaign campaign) {
        super("Marcare Voucher");

        setVisible(true);
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        vms = VMS.getInstance();
        this.campaign = campaign;

        id = new JLabel("ID Voucher");
        idField = new JTextField(COLUMN_WIDTH);

        redeemButton = new JButton("Marcheaza");
        exitButton = new JButton("Iesire");

        redeemButton.addActionListener(this);
        exitButton.addActionListener(this);

        redeemButton.setMnemonic(KeyEvent.VK_M);
        exitButton.setMnemonic(KeyEvent.VK_I);

        layoutComponents();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();

        if (clicked == redeemButton) {
            if (campaign.isExpired() || campaign.isCancelled()) {
                JOptionPane.showMessageDialog(null, "Campania nu mai este " +
                        "disponibila!", "Eroare", JOptionPane.OK_OPTION);
                return;
            }

            int id = Integer.parseInt(idField.getText());

            if (campaign.getVoucher(id) == null) {
                JOptionPane.showMessageDialog(null, "Voucherul nu exista!",
                        "Eroare", JOptionPane.OK_OPTION);
                return;
            }

            if (!(Utils.validateDate(LocalDateTime.now(),
                    campaign.getEndDate()))) {
                JOptionPane.showMessageDialog(null, "Campania nu mai este " +
                        "disponibila!", "Eroare", JOptionPane.OK_OPTION);
                vms.getCampaign(campaign.getCampaignId())
                        .setCampaignStatus(CampaignStatusType.EXPIRED);
                return;
            }

            vms.getCampaign(campaign.getCampaignId()).redeemVoucher(id, LocalDateTime.now());

            JOptionPane.showMessageDialog(null, "Voucherul a fost marcat cu " +
                    "succes!");
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

        // Prima linie
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
        add(idField, gridBagConstraints);


        // A doua linie
        gridBagConstraints.gridy = 1;

        // Prima coloana
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        add(redeemButton, gridBagConstraints);

        // A doua coloana
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        add(exitButton, gridBagConstraints);
    }
}
