import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class Campaign {
    private int campaignId;
    private int totalNoOfVouchers;
    private int availableVouchers;
    private int voucherCounter;

    private String campaignName;
    private String campaignDescription;
    private String strategyType;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private CampaignStatusType campaignStatus;
    private CampaignVoucherMap assignedVouchers;
    private Collection<User> users;

    public Campaign() {
        users = new ArrayList<>();
        assignedVouchers = new CampaignVoucherMap();
        voucherCounter = 1;
    }

    public CampaignVoucherMap getVouchers() {
        return assignedVouchers;
    }

    public void getVoucher(String code) {}

    public Voucher getVoucher(Integer id) {
        Set<Map.Entry<String, Collection<Voucher>>> entries =
                assignedVouchers.entrySet();

        for (Map.Entry<String, Collection<Voucher>> entry : entries) {
            for (Voucher voucher : entry.getValue()) {
                if (voucher.getVoucherId() == id) {
                    return voucher;
                }
            }
        }

        return null;
    }

    public Voucher generateVoucher(String eMail, String voucherType,
                                   float value) {
        if (voucherType.equals("GiftVoucher")) {
            GiftVoucher voucher = new GiftVoucher();

            voucher.setVoucherId(voucherCounter);
            voucher.setCampaignId(campaignId);
            voucher.setUserEMailAdress(eMail);
            voucher.setMaxSum(value);

            voucherCounter++;
            availableVouchers--;

            return voucher;
        } else if (voucherType.equals("LoyaltyVoucher")) {
            LoyaltyVoucher voucher = new LoyaltyVoucher();

            voucher.setVoucherId(voucherCounter);
            voucher.setCampaignId(campaignId);
            voucher.setUserEMailAdress(eMail);
            voucher.setDiscount(value);

            voucherCounter++;
            availableVouchers--;

            return voucher;
        }

        return null;
    }

    public void redeemVoucher(String code, LocalDateTime date) {}

    public void redeemVoucher(Integer id, LocalDateTime date) {
        Voucher voucher = getVoucher(id);

        if (voucher.isUnused() && Utils.validateDate(date, endDate)) {
            voucher.setVoucherStatus(VoucherStatusType.USED);
            voucher.setDateUsed(date);
        }
    }

    public Collection<User> getObservers() {
        return users;
    }

    public boolean addObserver(User user) {
        if (!(users.contains(user))) {
            return users.add(user);
        }

        return false;
    }

    public boolean removeObserver(User user) {
        if (users.contains(user)) {
            return users.remove(user);
        }

        return false;
    }

    public void notifyAllObservers(Notification notification) {
        for (User user : users) {
            user.update(notification);
        }
    }

    public Voucher executeStrategy() {
        Strategy strategy;

        switch (strategyType) {
            case "A":
                strategy = new StrategyA();
                return strategy.execute(this);

            case "B":
                strategy = new StrategyB();
                return strategy.execute(this);

            case "C":
                strategy = new StrategyC();
                return strategy.execute(this);
        }

        return null;
    }

    public boolean isNew() {
        return getCampaignStatus() == CampaignStatusType.NEW;
    }

    public boolean isStarted() {
        return getCampaignStatus() == CampaignStatusType.STARTED;
    }

    public boolean isExpired() {
        return getCampaignStatus() == CampaignStatusType.EXPIRED;
    }

    public boolean isCancelled() {
        return getCampaignStatus() == CampaignStatusType.CANCELLED;
    }

    public boolean hasMoreVouchers() {
        return availableVouchers > 0;
    }

    public int getCampaignId() {
        return campaignId;
    }

    public String getCampaignName() {
        return campaignName;
    }

    public String getCampaignDescription() {
        return campaignDescription;
    }

    public CampaignStatusType getCampaignStatus() {
        return campaignStatus;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setCampaignId(int campaignId) {
        this.campaignId = campaignId;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }

    public void setCampaignDescription(String campaignDescription) {
        this.campaignDescription = campaignDescription;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public void setCampaignStatus(CampaignStatusType campaignStatus) {
        this.campaignStatus = campaignStatus;
    }

    public void setStrategyType(String strategyType) {
        this.strategyType = strategyType;
    }

    public void setTotalNoOfVouchers(int totalNoOfVouchers) {
        this.totalNoOfVouchers = totalNoOfVouchers;
        availableVouchers = totalNoOfVouchers;
    }

    @Override
    public String toString() {
        return campaignId + " " +  campaignName + " " + campaignDescription
                + " " + startDate + " " + endDate + " " + campaignStatus;
    }
}
