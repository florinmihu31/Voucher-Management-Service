import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GiftVoucher extends Voucher {
    private float maxSum;

    @Override
    public int getVoucherId() {
        return super.voucherId;
    }

    @Override
    public int getCampaignId() {
        return super.campaignId;
    }

    @Override
    public VoucherStatusType getVoucherStatus() {
        return super.voucherStatus;
    }

    @Override
    public String getUserEMailAdress() {
        return super.userEMailAdress;
    }

    @Override
    public LocalDateTime getDateUsed() {
        return super.dateUsed;
    }

    public float getMaxSum() {
        return maxSum;
    }

    @Override
    public void setUserEMailAdress(String userEMailAdress) {
        super.userEMailAdress = userEMailAdress;
    }

    @Override
    public void setVoucherStatus(VoucherStatusType voucherStatus) {
        super.voucherStatus = voucherStatus;
    }

    @Override
    public void setCampaignId(int campaignId) {
        super.campaignId = campaignId;
    }

    @Override
    public void setVoucherId(int voucherId) {
        super.voucherId = voucherId;
    }

    @Override
    public void setDateUsed(LocalDateTime dateUsed) {
        super.dateUsed = dateUsed;
    }

    public void setMaxSum(float maxSum) {
        this.maxSum = maxSum;
    }

    @Override
    public boolean isUnused() {
        return super.voucherStatus == VoucherStatusType.UNUSED;
    }

    public boolean isGiftVoucher() {
        return true;
    }

    @Override
    public String toString() {
        String dateFormat = (dateUsed != null) ?
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(dateUsed) :
                "null";

        return "[" + voucherId + ";" + voucherStatus + ";" + userEMailAdress
                + ";" + maxSum + ";" + campaignId + ";" + dateFormat + "]";
    }
}
