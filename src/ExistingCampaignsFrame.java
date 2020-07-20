import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ExistingCampaignsFrame extends JFrame {
    private static final int WIDTH = 1200;
    private static final int HEIGHT = 800;

    private CampaignTableModel campaignTableModel;
    private JTable campaignsTable;
    private JPopupMenu showDetails;
    private JMenuItem showItem;
    private VMS vms;

    public ExistingCampaignsFrame(User user) {
        super("Campanii Existente");

        setVisible(true);
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        vms = VMS.getInstance();
        campaignTableModel = new CampaignTableModel();
        campaignsTable = new JTable(campaignTableModel);

        showDetails = new JPopupMenu();
        showItem = new JMenuItem("Arata Detalii");

        showDetails.add(showItem);

        campaignsTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int row = campaignsTable.rowAtPoint(e.getPoint());

                campaignsTable.getSelectionModel().setSelectionInterval(row,
                        row);

                if (e.getButton() == MouseEvent.BUTTON3) {
                    showDetails.show(campaignsTable, e.getX(), e.getY());
                }
            }
        });

        showItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = campaignsTable.getSelectedRow() + 1;

                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        new VouchersFrame(user, vms.getCampaign(id));
                    }
                });
            }
        });

        add(new JScrollPane(campaignsTable));
    }
}
