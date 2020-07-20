import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class StrategyB implements Strategy {

    @Override
    public Voucher execute(Campaign campaign) {
        if (campaign.isExpired() || campaign.isCancelled() ||
            !(campaign.hasMoreVouchers())) {
            return null;
        }

        String eMailAddress = "";
        String type = "LoyaltyVoucher";

        int maximumNo = Integer.MIN_VALUE;
        float value = 50f;

        Set<Map.Entry<String, Collection<Voucher>>> entries =
                campaign.getVouchers().entrySet();

        for (Map.Entry<String, Collection<Voucher>> entry : entries) {
            int voucherCounter = 0;

            for (Voucher voucher : entry.getValue()) {
                if (!(voucher.isUnused())) {
                    voucherCounter++;
                }
            }

            if (voucherCounter > maximumNo) {
                maximumNo = voucherCounter;
                eMailAddress = entry.getKey();
            }
        }

        if (maximumNo > 0) {
            return campaign.generateVoucher(eMailAddress, type, value);
        }

        return null;
    }
}
