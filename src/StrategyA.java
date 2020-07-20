import java.util.Random;

public class StrategyA implements Strategy {

    @Override
    public Voucher execute(Campaign campaign) {
        if (campaign.isExpired() || campaign.isCancelled() ||
            !(campaign.hasMoreVouchers())) {
            return null;
        }

        String type = "GiftVoucher";
        Random random = new Random();

        int winnerNo = random.nextInt(campaign.getObservers().size());
        float value = 100f;

        for (User user : campaign.getObservers()) {
            if (winnerNo == 0) {
                return campaign.generateVoucher(user.geteMailAddress(), type,
                        value);
            }

            winnerNo--;
        }

        return null;
    }
}
