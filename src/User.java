import javax.swing.*;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class User {
    private int userId;

    private String name;
    private String eMailAddress;
    private String userPassword;

    private UserType userType;
    private UserVoucherMap receivedVouchers;
    private Collection<Notification> notifications;

    public User() {
        notifications = new ArrayList<>();
        receivedVouchers = new UserVoucherMap();
    }

    public boolean update(Notification notification) {
        if (!(notifications.contains(notification))) {
            return notifications.add(notification);
        }

        return false;
    }

    public void listNotifications(PrintWriter printWriter) {
        if (notifications.isEmpty()) {
            printWriter.println("[]");
            return;
        }

        for (Notification notification : notifications) {
            printWriter.print(notification + " ");

            int campaignId = notification.getCampaignId();

            Set<Map.Entry<Integer, Collection<Voucher>>> entries =
                    receivedVouchers.entrySet();

            for (Map.Entry<Integer, Collection<Voucher>> entry : entries) {
                if (entry.getKey().equals(campaignId)) {
                    printWriter.print("[ ");

                    for (Voucher voucher : entry.getValue()) {
                        printWriter.print(voucher.getVoucherId() + " ");
                    }

                    printWriter.println("]");
                }
            }
        }
    }

    public void listNotifications(JTextArea textArea) {
        if (notifications.isEmpty()) {
            textArea.setText("[]");
            return;
        }

        for (Notification notification : notifications) {
            textArea.setText(notification + " ");

            int campaignId = notification.getCampaignId();

            Set<Map.Entry<Integer, Collection<Voucher>>> entries =
                    receivedVouchers.entrySet();

            for (Map.Entry<Integer, Collection<Voucher>> entry : entries) {
                if (entry.getKey().equals(campaignId)) {
                    textArea.append("[ ");

                    for (Voucher voucher : entry.getValue()) {
                        textArea.append(voucher.getVoucherId() + " ");
                    }

                    textArea.append("]\n");
                }
            }
        }
    }

    public String getName() {
        return name;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public int getUserId() {
        return userId;
    }

    public UserType getUserType() {
        return userType;
    }

    public String geteMailAddress() {
        return eMailAddress;
    }

    public UserVoucherMap getReceivedVouchers() {
        return receivedVouchers;
    }

    public Collection<Notification> getNotifications() {
        return notifications;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void seteMailAddress(String eMailAddress) {
        this.eMailAddress = eMailAddress;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public boolean isAdmin() {
        return getUserType() == UserType.ADMIN;
    }

    @Override
    public String toString() {
        return "[" + userId + ";" + name + ";" + eMailAddress + ";"
                + userType + "]";
    }
}
