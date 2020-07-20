import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Notification {
    private NotificationType notificationType;
    private LocalDateTime notificationDate;
    private int campaignId;
    private List<Integer> vouchersCodes;

    public Notification() {
        vouchersCodes = new ArrayList<>();
    }

    public int getCampaignId() {
        return campaignId;
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    public void setNotificationDate(LocalDateTime notificationDate) {
        this.notificationDate = notificationDate;
    }

    public void setCampaignId(int campaignId) {
        this.campaignId = campaignId;
    }

    @Override
    public String toString() {
        String dateFormat = DateTimeFormatter
                .ofPattern("yyyy-MM-dd HH:mm").format(notificationDate);

        return "[" + dateFormat + "] " + "Campania cu numarul " + campaignId +
                " a fost" + (notificationType == NotificationType.EDIT ?
                " editata." : " anulata.");
    }
}
