import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

public class InputFileFrame extends JFrame implements ActionListener {
    private static final int SUCCESS_STATUS = 0;
    private static final int WIDTH = 1200;
    private static final int HEIGHT = 800;

    private JLabel label;
    private JTextField textField;
    private JButton continueButton;
    private JButton exitButton;

    public InputFileFrame() {
        super("Incarcare Fisier");

        setVisible(true);
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        label = new JLabel("Introdu numarul fisierului:");
        textField = new JTextField(15);
        continueButton = new JButton("Continua");
        exitButton = new JButton("Iesire");

        continueButton.addActionListener(this);
        exitButton.addActionListener(this);

        continueButton.setMnemonic(KeyEvent.VK_C);
        exitButton.setMnemonic(KeyEvent.VK_I);

        layoutComponents();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();

        if (clicked == continueButton) {
            try {
                int testNo = Integer.parseInt(textField.getText());

                if (testNo < 0 || testNo > 9) {
                    JOptionPane.showMessageDialog(null,
                            "Nu ai introdus un numar valid", "Eroare",
                            JOptionPane.ERROR_MESSAGE);
                }

                VMS vms = VMS.getInstance();

                Utils.loadCampaigns(vms, Integer.toString(testNo));
                Utils.loadUsers(vms, Integer.toString(testNo));
                Utils.loadEvents(vms, Integer.toString(testNo));

                dispose();
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        new LoginFrame();
                    }
                });

            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(null, "Nu ai introdus un " +
                        "numar", "Eroare", JOptionPane.ERROR_MESSAGE);
            } catch (FileNotFoundException exception) {
                JOptionPane.showMessageDialog(null, "Fisierul nu exista",
                        "Eroare", JOptionPane.ERROR_MESSAGE);
            } catch (IOException ignored) {
            }
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
        add(label, gridBagConstraints);

        // A doua coloana
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new Insets(0, 5, 0, 0);
        add(textField, gridBagConstraints);


        // Al doilea rand
        gridBagConstraints.gridy = 1;

        // Prima coloana
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        add(continueButton, gridBagConstraints);

        // A doua coloana
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        add(exitButton, gridBagConstraints);
    }
}
