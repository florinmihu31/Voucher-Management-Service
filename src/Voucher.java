import java.time.LocalDateTime;

public abstract class Voucher {
    protected int voucherId;
    protected int campaignId;

    private String voucherCode;
    protected String userEMailAdress;

    protected VoucherStatusType voucherStatus;
    protected LocalDateTime dateUsed;

    public Voucher() {
        voucherStatus = VoucherStatusType.UNUSED;
    }

    public abstract int getVoucherId();

    public abstract int getCampaignId();

    public abstract VoucherStatusType getVoucherStatus();

    public abstract String getUserEMailAdress();

    public abstract LocalDateTime getDateUsed();

    public abstract void setUserEMailAdress(String userEMailAdress);

    public abstract void setVoucherStatus(VoucherStatusType voucherStatus);

    public abstract void setCampaignId(int campaignId);

    public abstract void setVoucherId(int voucherId);

    public abstract void setDateUsed(LocalDateTime dateUsed);

    public abstract boolean isUnused();

    public abstract boolean isGiftVoucher();
}
