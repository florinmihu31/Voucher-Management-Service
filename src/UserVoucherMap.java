import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

public class UserVoucherMap extends ArrayMap<Integer, Collection<Voucher>> {
    public boolean addVoucher(Voucher v) {
        int campaignId = v.getCampaignId();
        Set<Entry<Integer, Collection<Voucher>>> entriesSet = super.entrySet();

        for (Entry<Integer, Collection<Voucher>> entry : entriesSet) {
            if (entry.getKey().equals(campaignId)) {
                return entry.getValue().add(v);
            }
        }

        Collection<Voucher> newCollection = new ArrayList<>();
        newCollection.add(v);

        put(campaignId, newCollection);
        return true;
    }
}
