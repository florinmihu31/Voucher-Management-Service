import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class CampaignTableModel extends AbstractTableModel {
    private static final int COLUMNS = 6;

    private ArrayList<String> columnNames;
    private VMS vms;

    public CampaignTableModel() {
        vms = VMS.getInstance();
        columnNames = new ArrayList<>(COLUMNS);

        columnNames.add("ID");
        columnNames.add("Nume");
        columnNames.add("Descriere");
        columnNames.add("Data inceput");
        columnNames.add("Data final");
        columnNames.add("Status");
    }

    @Override
    public int getRowCount() {
        return vms.getCampaigns().size();
    }

    @Override
    public int getColumnCount() {
        return COLUMNS;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Campaign campaign = vms.getCampaign(rowIndex + 1);

        switch (columnIndex + 1) {
            case 1:
                return campaign.getCampaignId();
            case 2:
                return campaign.getCampaignName();
            case 3:
                return campaign.getCampaignDescription();
            case 4:
                return campaign.getStartDate();
            case 5:
                return campaign.getEndDate();
            case 6:
                return campaign.getCampaignStatus();
        }

        return null;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames.get(column);
    }
}
