import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoyaltyVoucher extends Voucher {
    private float discount;

    @Override
    public int getVoucherId() {
        return super.voucherId;
    }

    @Override
    public int getCampaignId() {
        return super.campaignId;
    }

    @Override
    public String getUserEMailAdress() {
        return super.userEMailAdress;
    }

    @Override
    public VoucherStatusType getVoucherStatus() {
        return super.voucherStatus;
    }

    @Override
    public LocalDateTime getDateUsed() {
        return super.dateUsed;
    }

    public float getDiscount() {
        return discount;
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
    public void setDateUsed(LocalDateTime dateUsed) {
        super.dateUsed = dateUsed;
    }

    @Override
    public void setVoucherId(int voucherId) {
        super.voucherId = voucherId;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    @Override
    public boolean isUnused() {
        return super.voucherStatus == VoucherStatusType.UNUSED;
    }

    @Override
    public boolean isGiftVoucher() {
        return false;
    }

    @Override
    public String toString() {
        String dateFormat = (dateUsed != null) ?
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(dateUsed) :
                "null";

        return "[" + voucherId + ";" + voucherStatus + ";" + userEMailAdress
                + ";" + discount + ";" + campaignId + ";" + dateFormat + "]";
    }
}
