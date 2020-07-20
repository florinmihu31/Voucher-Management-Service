import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

public class CampaignVoucherMap extends ArrayMap<String, Collection<Voucher>> {
    public boolean addVoucher(Voucher v) {
        String userEMailAdress = v.getUserEMailAdress();
        Set<Entry<String, Collection<Voucher>>> entries = super.entrySet();

        for (Entry<String, Collection<Voucher>> entry : entries) {
            if (entry.getKey().equals(userEMailAdress)) {
                return entry.getValue().add(v);
            }
        }

        Collection<Voucher> newCollection = new ArrayList<>();
        newCollection.add(v);

        put(userEMailAdress, newCollection);
        return true;
    }
}
