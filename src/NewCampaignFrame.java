import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.time.LocalDateTime;
import java.util.StringTokenizer;

public class NewCampaignFrame extends JFrame implements ActionListener {
    private static final int WIDTH = 1200;
    private static final int HEIGHT = 800;
    private static final int COLUMN_WIDTH = 15;

    private JLabel id;
    private JLabel name;
    private JLabel description;
    private JLabel startDate;
    private JLabel endDate;
    private JLabel totalNoOfVouchers;
    private JLabel strategy;
    private JTextField newCampaignId;
    private JTextField newCampaignName;
    private JTextField newCampaignDescription;
    private JTextField newCampaignStartDate;
    private JTextField newCampaignEndDate;
    private JTextField newCampaignVouchersNo;
    private JTextField newCampaignStrategy;
    private JButton addButton;
    private JButton exitButton;
    private VMS vms;

    public NewCampaignFrame() {
        super("Adaugare Campanie");

        setVisible(true);
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        vms = VMS.getInstance();

        id = new JLabel("ID");
        name = new JLabel("Nume");
        description = new JLabel("Descriere");
        startDate = new JLabel("Data start");
        endDate = new JLabel("Data final");
        totalNoOfVouchers = new JLabel("Vouchere totale");
        strategy = new JLabel("Strategie");

        newCampaignId = new JTextField(COLUMN_WIDTH);
        newCampaignName = new JTextField(COLUMN_WIDTH);
        newCampaignDescription = new JTextField(COLUMN_WIDTH);
        newCampaignStartDate = new JTextField(COLUMN_WIDTH);
        newCampaignEndDate = new JTextField(COLUMN_WIDTH);
        newCampaignVouchersNo = new JTextField(COLUMN_WIDTH);
        newCampaignStrategy = new JTextField(COLUMN_WIDTH);

        addButton = new JButton("Adauga");
        exitButton = new JButton("Iesire");

        addButton.addActionListener(this);
        exitButton.addActionListener(this);

        addButton.setMnemonic(KeyEvent.VK_A);
        exitButton.setMnemonic(KeyEvent.VK_I);

        layoutComponents();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();

        if (clicked == addButton) {
            LocalDateTime currentDate = LocalDateTime.now();

            Campaign newCampaign = new Campaign();

            int id = Integer.parseInt(newCampaignId.getText());

            if (id < vms.getCampaigns().size() + 1) {
                JOptionPane.showMessageDialog(null, "Campania deja exista, " +
                        "urmatoarea campanie are numarul " + (vms.getCampaigns()
                        .size() + 1), "Eroare", JOptionPane.ERROR_MESSAGE);
                return;
            } else if (id > vms.getCampaigns().size() + 1) {
                JOptionPane.showMessageDialog(null, "Urmatoarea campanie are "
                        + "numarul " + (vms.getCampaigns().size() + 1),
                        "Eroare", JOptionPane.ERROR_MESSAGE);
                return;
            } else {
                newCampaign.setCampaignId(id);
            }

            newCampaign.setCampaignName(newCampaignName.getText());
            newCampaign.setCampaignDescription(newCampaignDescription
                    .getText());

            StringTokenizer startDateString =
                    new StringTokenizer(newCampaignStartDate.getText(), "- :");

            LocalDateTime startDate = new DateBuilder()
                    .setYear(Integer.parseInt(startDateString.nextToken()))
                    .setMonth(Integer.parseInt(startDateString.nextToken()))
                    .setDay(Integer.parseInt(startDateString.nextToken()))
                    .setHour(Integer.parseInt(startDateString.nextToken()))
                    .setMinutes(Integer.parseInt(startDateString.nextToken()))
                    .getDate();

            newCampaign.setStartDate(startDate);

            StringTokenizer endDateString =
                    new StringTokenizer(newCampaignEndDate.getText(), "- :");

            LocalDateTime endDate = new DateBuilder()
                    .setYear(Integer.parseInt(endDateString.nextToken()))
                    .setMonth(Integer.parseInt(endDateString.nextToken()))
                    .setDay(Integer.parseInt(endDateString.nextToken()))
                    .setHour(Integer.parseInt(endDateString.nextToken()))
                    .setMinutes(Integer.parseInt(endDateString.nextToken()))
                    .getDate();

            newCampaign.setEndDate(endDate);

            if (Utils.validateDate(currentDate, startDate)) {
                newCampaign.setCampaignStatus(CampaignStatusType.NEW);
            } else if (Utils.validateDate(currentDate, endDate)) {
                newCampaign.setCampaignStatus(CampaignStatusType.STARTED);
            } else {
                newCampaign.setCampaignStatus(CampaignStatusType.EXPIRED);
            }

            newCampaign.setTotalNoOfVouchers(Integer
                    .parseInt(newCampaignVouchersNo.getText()));
            newCampaign.setStrategyType(newCampaignStrategy.getText());

            vms.addCampaign(newCampaign);

            JOptionPane.showMessageDialog(null, "Campania a fost adaugata cu "
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
        add(newCampaignId, gridBagConstraints);


        // Al doilea rand
        gridBagConstraints.gridy++;

        // Prima coloana
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(0, 0, 0, 5);
        add(name, gridBagConstraints);

        // A doua coloana
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new Insets(0, 5, 0, 0);
        add(newCampaignName, gridBagConstraints);


        // Al treilea rand
        gridBagConstraints.gridy++;

        // Prima coloana
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(0, 0, 0, 5);
        add(description, gridBagConstraints);

        // A doua coloana
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new Insets(0, 5, 0, 0);
        add(newCampaignDescription, gridBagConstraints);


        // Al patrulea rand
        gridBagConstraints.gridy++;

        // Prima coloana
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(0, 0, 0, 5);
        add(startDate, gridBagConstraints);

        // A doua coloana
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new Insets(0, 5, 0, 0);
        add(newCampaignStartDate, gridBagConstraints);


        // Al cincilea rand
        gridBagConstraints.gridy++;

        // Prima coloana
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(0, 0, 0, 5);
        add(endDate, gridBagConstraints);

        // A doua coloana
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new Insets(0, 5, 0, 0);
        add(newCampaignEndDate, gridBagConstraints);


        // Al saselea rand
        gridBagConstraints.gridy++;

        // Prima coloana
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(0, 0, 0, 5);
        add(totalNoOfVouchers, gridBagConstraints);

        // A doua coloana
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new Insets(0, 5, 0, 0);
        add(newCampaignVouchersNo, gridBagConstraints);


        // Al saptelea rand
        gridBagConstraints.gridy++;

        // Prima coloana
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(0, 0, 0, 5);
        add(strategy, gridBagConstraints);

        // A doua coloana
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new Insets(0, 5, 0, 0);
        add(newCampaignStrategy, gridBagConstraints);


        // Al optulea rand
        gridBagConstraints.gridy++;

        // Prima coloana
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        add(addButton, gridBagConstraints);

        // A doua coloana
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        add(exitButton, gridBagConstraints);
    }
}
