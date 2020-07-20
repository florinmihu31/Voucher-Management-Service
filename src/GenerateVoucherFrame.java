import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class GenerateVoucherFrame extends JFrame implements ActionListener {
    private static final int WIDTH = 1200;
    private static final int HEIGHT = 800;
    private static final int COLUMN_WIDTH = 15;

    private JLabel id;
    private JLabel campaignId;
    private JLabel eMail;
    private JLabel voucherType;
    private JLabel value;
    private JTextField eMailField;
    private JTextField voucherTypeField;
    private JTextField valueField;
    private JButton generateButton;
    private JButton exitButton;
    private Campaign campaign;
    private VMS vms;

    public GenerateVoucherFrame(Campaign campaign) {
        super("Generare Voucher");

        setVisible(true);
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        vms = VMS.getInstance();
        this.campaign = campaign;

        id = new JLabel("ID Campanie");
        campaignId = new JLabel(Integer.toString(campaign.getCampaignId()));
        eMail = new JLabel("E-Mail");
        voucherType = new JLabel("Tip Voucher (GiftVoucher/LoyaltyVoucher)");
        value = new JLabel("Valoare");

        eMailField = new JTextField(COLUMN_WIDTH);
        voucherTypeField = new JTextField(COLUMN_WIDTH);
        valueField = new JTextField(COLUMN_WIDTH);

        generateButton = new JButton("Genereaza");
        exitButton = new JButton("Iesire");

        generateButton.addActionListener(this);
        exitButton.addActionListener(this);

        generateButton.setMnemonic(KeyEvent.VK_G);
        exitButton.setMnemonic(KeyEvent.VK_I);

        layoutComponents();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();

        if (clicked == generateButton) {
            if (campaign.hasMoreVouchers()) {
                String eMail = eMailField.getText();
                String voucherType = voucherTypeField.getText();

                if (!(voucherType.equals("GiftVoucher")) && !(voucherType.equals(
                        "LoyaltyVoucher"))) {
                    JOptionPane.showMessageDialog(null, "Nu ai introdus un " +
                            "tip corect!", "Eroare", JOptionPane.OK_OPTION);
                    return;
                }

                float value = Float.parseFloat(valueField.getText());

                Voucher voucher = vms.getCampaign(campaign.getCampaignId())
                                .generateVoucher(eMail, voucherType, value);

                vms.getUser(eMail).getReceivedVouchers().addVoucher(voucher);
                vms.getCampaign(campaign.getCampaignId()).getVouchers()
                        .addVoucher(voucher);
                vms.getCampaign(campaign.getCampaignId())
                        .addObserver(vms.getUser(eMail));

                JOptionPane.showMessageDialog(null, "Voucherul a fost adaugat" +
                        " cu succes!");
            }
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
        add(campaignId, gridBagConstraints);


        // A doua linie
        gridBagConstraints.gridy++;

        // Prima coloana
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(0, 0, 0, 5);
        add(eMail, gridBagConstraints);

        // A doua coloana
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new Insets(0, 5, 0, 0);
        add(eMailField, gridBagConstraints);


        // A treia linie
        gridBagConstraints.gridy++;

        // Prima coloana
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(0, 0, 0, 5);
        add(voucherType, gridBagConstraints);

        // A doua coloana
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new Insets(0, 5, 0, 0);
        add(voucherTypeField, gridBagConstraints);


        // A patra linie
        gridBagConstraints.gridy++;

        // Prima coloana
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(0, 0, 0, 5);
        add(value, gridBagConstraints);

        // A doua coloana
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new Insets(0, 5, 0, 0);
        add(valueField, gridBagConstraints);


        // A cincea linie
        gridBagConstraints.gridy++;

        // Prima coloana
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        add(generateButton, gridBagConstraints);

        // A doua coloana
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        add(exitButton, gridBagConstraints);
    }
}
