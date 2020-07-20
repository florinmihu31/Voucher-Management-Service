import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.time.LocalDateTime;
import java.util.StringTokenizer;

public class EditCampaignFrame extends JFrame implements ActionListener {
    private static final int WIDTH = 1200;
    private static final int HEIGHT = 800;
    private static final int COLUMN_WIDTH = 15;

    private JLabel id;
    private JLabel name;
    private JLabel description;
    private JLabel startDate;
    private JLabel endDate;
    private JLabel budget;
    private JTextField campaignId;
    private JTextField campaignName;
    private JTextField campaignDescription;
    private JTextField campaignStartDate;
    private JTextField campaignEndDate;
    private JTextField campaignBudget;
    private JButton updateButton;
    private JButton exitButton;
    private VMS vms;

    public EditCampaignFrame() {
        super("Editare Campanie");

        setVisible(true);
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        vms = VMS.getInstance();

        id = new JLabel("ID");
        name = new JLabel("Nume");
        description = new JLabel("Descriere");
        startDate = new JLabel("Data start");
        endDate = new JLabel("Data final");
        budget = new JLabel("Buget");

        campaignId = new JTextField(COLUMN_WIDTH);
        campaignName = new JTextField(COLUMN_WIDTH);
        campaignDescription = new JTextField(COLUMN_WIDTH);
        campaignStartDate = new JTextField(COLUMN_WIDTH);
        campaignEndDate = new JTextField(COLUMN_WIDTH);
        campaignBudget = new JTextField(COLUMN_WIDTH);

        updateButton = new JButton("Update");
        exitButton = new JButton("Iesire");

        updateButton.addActionListener(this);
        exitButton.addActionListener(this);

        updateButton.setMnemonic(KeyEvent.VK_U);
        exitButton.setMnemonic(KeyEvent.VK_I);

        layoutComponents();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();

        if (clicked == updateButton) {
            LocalDateTime currentDate = LocalDateTime.now();

            Campaign campaign = new Campaign();

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

            campaign.setCampaignId(id);

            campaign.setCampaignName(campaignName.getText());
            campaign.setCampaignDescription(campaignDescription
                    .getText());

            StringTokenizer startDateString =
                    new StringTokenizer(campaignStartDate.getText(), "- :");

            LocalDateTime startDate = new DateBuilder()
                    .setYear(Integer.parseInt(startDateString.nextToken()))
                    .setMonth(Integer.parseInt(startDateString.nextToken()))
                    .setDay(Integer.parseInt(startDateString.nextToken()))
                    .setHour(Integer.parseInt(startDateString.nextToken()))
                    .setMinutes(Integer.parseInt(startDateString.nextToken()))
                    .getDate();

            campaign.setStartDate(startDate);

            StringTokenizer endDateString =
                    new StringTokenizer(campaignEndDate.getText(), "- :");

            LocalDateTime endDate = new DateBuilder()
                    .setYear(Integer.parseInt(endDateString.nextToken()))
                    .setMonth(Integer.parseInt(endDateString.nextToken()))
                    .setDay(Integer.parseInt(endDateString.nextToken()))
                    .setHour(Integer.parseInt(endDateString.nextToken()))
                    .setMinutes(Integer.parseInt(endDateString.nextToken()))
                    .getDate();

            campaign.setEndDate(endDate);

            if (Utils.validateDate(currentDate, startDate)) {
                campaign.setCampaignStatus(CampaignStatusType.NEW);
            } else if (Utils.validateDate(currentDate, endDate)) {
                campaign.setCampaignStatus(CampaignStatusType.STARTED);
            } else {
                campaign.setCampaignStatus(CampaignStatusType.EXPIRED);
            }

            campaign.setTotalNoOfVouchers(Integer.parseInt(campaignBudget
                    .getText()));

            vms.updateCampaign(id, campaign);

            JOptionPane.showMessageDialog(null, "Campania a fost editata cu "
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
        add(campaignName, gridBagConstraints);


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
        add(campaignDescription, gridBagConstraints);


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
        add(campaignStartDate, gridBagConstraints);


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
        add(campaignEndDate, gridBagConstraints);


        // Al saselea rand
        gridBagConstraints.gridy++;

        // Prima coloana
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(0, 0, 0, 5);
        add(budget, gridBagConstraints);

        // A doua coloana
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new Insets(0, 5, 0, 0);
        add(campaignBudget, gridBagConstraints);


        // Al saptelea rand
        gridBagConstraints.gridy++;

        // Prima coloana
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        add(updateButton, gridBagConstraints);

        // A doua coloana
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        add(exitButton, gridBagConstraints);
    }
}
