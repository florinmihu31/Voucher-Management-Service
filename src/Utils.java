import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class Utils {
    public static boolean validateDate(LocalDateTime currentDate,
                                       LocalDateTime endDate) {
        if (endDate.getYear() > currentDate.getYear()) {
            return true;
        } else if (endDate.getYear() < currentDate.getYear()) {
            return false;
        }

        if (endDate.getMonthValue() > currentDate.getMonthValue()) {
            return true;
        } else if (endDate.getMonthValue() < currentDate.getMonthValue()) {
            return false;
        }

        if (endDate.getDayOfMonth() > currentDate.getDayOfMonth()) {
            return true;
        } else if (endDate.getDayOfMonth() < currentDate.getDayOfMonth()) {
            return false;
        }

        if (endDate.getHour() > currentDate.getHour()) {
            return true;
        } else if (endDate.getHour() < currentDate.getHour()) {
            return false;
        }

        if (endDate.getMinute() > currentDate.getMinute()) {
            return true;
        } else if (endDate.getMinute() < currentDate.getMinute()) {
            return false;
        }

        return true;
    }

    public static void loadCampaigns(VMS vms, String testNo)
            throws FileNotFoundException {

        File campaignsFile = new File("VMStests/test0" + testNo + "/input" +
                "/campaigns.txt");
        Scanner scanner = new Scanner(campaignsFile);

        int noOfCampaigns = scanner.nextInt();

        scanner.nextLine(); // Trecem la urmatoarea linie

        StringTokenizer date = new StringTokenizer(scanner.nextLine(), "- :");

        LocalDateTime currentDate = new DateBuilder()
                .setYear(Integer.parseInt(date.nextToken()))
                .setMonth(Integer.parseInt(date.nextToken()))
                .setDay(Integer.parseInt(date.nextToken()))
                .setHour(Integer.parseInt(date.nextToken()))
                .setMinutes(Integer.parseInt(date.nextToken()))
                .getDate();

        for (int i = 0; i < noOfCampaigns; i++) {
            StringTokenizer currentCampaign;
            currentCampaign = new StringTokenizer(scanner.nextLine(),
                    "; :-");
            Campaign newCampaign = new Campaign();

            newCampaign.setCampaignId(Integer.parseInt(currentCampaign
                    .nextToken()));
            newCampaign.setCampaignName(currentCampaign.nextToken());
            newCampaign.setCampaignDescription(currentCampaign.nextToken());

            LocalDateTime startDate = new DateBuilder()
                    .setYear(Integer.parseInt(currentCampaign.nextToken()))
                    .setMonth(Integer.parseInt(currentCampaign.nextToken()))
                    .setDay(Integer.parseInt(currentCampaign.nextToken()))
                    .setHour(Integer.parseInt(currentCampaign.nextToken()))
                    .setMinutes(Integer.parseInt(currentCampaign.nextToken()))
                    .getDate();

            newCampaign.setStartDate(startDate);

            LocalDateTime endDate = new DateBuilder()
                    .setYear(Integer.parseInt(currentCampaign.nextToken()))
                    .setMonth(Integer.parseInt(currentCampaign.nextToken()))
                    .setDay(Integer.parseInt(currentCampaign.nextToken()))
                    .setHour(Integer.parseInt(currentCampaign.nextToken()))
                    .setMinutes(Integer.parseInt(currentCampaign.nextToken()))
                    .getDate();

            newCampaign.setEndDate(endDate);

            if (validateDate(currentDate, startDate)){
                newCampaign.setCampaignStatus(CampaignStatusType.NEW);
            } else if (validateDate(currentDate, endDate)) {
                newCampaign.setCampaignStatus(CampaignStatusType.STARTED);
            } else {
                newCampaign.setCampaignStatus(CampaignStatusType.EXPIRED);
            }

            int totalNumberOfVouchers = Integer
                    .parseInt(currentCampaign.nextToken());

            newCampaign.setTotalNoOfVouchers(totalNumberOfVouchers);
            newCampaign.setStrategyType(currentCampaign.nextToken());

            vms.addCampaign(newCampaign);
        }
    }

    public static void loadUsers(VMS vms, String testNo)
            throws FileNotFoundException {
        File usersFile = new File("VMStests/test0" + testNo + "/input/users" +
                ".txt");
        Scanner scanner = new Scanner(usersFile);

        int usersNumber = scanner.nextInt();

        scanner.nextLine();

        for (int i = 0; i < usersNumber; i++) {
            StringTokenizer currentUser;
            currentUser = new StringTokenizer(scanner.nextLine(), "; -:");
            User newUser = new User();

            newUser.setUserId(Integer.parseInt(currentUser.nextToken()));
            newUser.setName(currentUser.nextToken());
            newUser.setUserPassword(currentUser.nextToken());
            newUser.seteMailAddress(currentUser.nextToken());
            if (currentUser.nextToken().equals("ADMIN")) {
                newUser.setUserType(UserType.ADMIN);
            } else {
                newUser.setUserType(UserType.GUEST);
            }

            vms.addUser(newUser);
        }
    }

    public static void loadEvents(VMS vms, String testNo)
            throws IOException {
        FileWriter fileWriter = new FileWriter("VMStests/test0" + testNo +
                "/output.txt");
        PrintWriter printWriter = new PrintWriter(fileWriter);

        File eventsFile = new File("VMStests/test0" + testNo + "/input" +
                "/events.txt");
        Scanner scanner = new Scanner(eventsFile);

        StringTokenizer date = new StringTokenizer(scanner.nextLine(), "- :");

        LocalDateTime currentDate = new DateBuilder()
                .setYear(Integer.parseInt(date.nextToken()))
                .setMonth(Integer.parseInt(date.nextToken()))
                .setDay(Integer.parseInt(date.nextToken()))
                .setHour(Integer.parseInt(date.nextToken()))
                .setMinutes(Integer.parseInt(date.nextToken()))
                .getDate();

        int eventsNumber = scanner.nextInt();

        scanner.nextLine();

        for (int i = 0; i < eventsNumber; i++) {
            StringTokenizer currentEvent = new StringTokenizer(scanner.nextLine(),
                    "; :-");

            User user = vms.getUser(Integer.parseInt(currentEvent.nextToken()));

            String event = currentEvent.nextToken();

            switch (event) {
                case "addCampaign":
                    if (user.isAdmin()) {
                        Campaign newCampaign = new Campaign();

                        newCampaign.setCampaignId(Integer
                                .parseInt(currentEvent.nextToken()));
                        newCampaign.setCampaignName(currentEvent.nextToken());
                        newCampaign.setCampaignDescription(currentEvent.nextToken());

                        LocalDateTime startDate = new DateBuilder()
                                .setYear(Integer.parseInt(currentEvent.nextToken()))
                                .setMonth(Integer.parseInt(currentEvent.nextToken()))
                                .setDay(Integer.parseInt(currentEvent.nextToken()))
                                .setHour(Integer.parseInt(currentEvent.nextToken()))
                                .setMinutes(Integer.parseInt(currentEvent.nextToken()))
                                .getDate();

                        newCampaign.setStartDate(startDate);

                        LocalDateTime endDate = new DateBuilder()
                                .setYear(Integer.parseInt(currentEvent.nextToken()))
                                .setMonth(Integer.parseInt(currentEvent.nextToken()))
                                .setDay(Integer.parseInt(currentEvent.nextToken()))
                                .setHour(Integer.parseInt(currentEvent.nextToken()))
                                .setMinutes(Integer.parseInt(currentEvent.nextToken()))
                                .getDate();

                        newCampaign.setEndDate(endDate);

                        newCampaign.setCampaignStatus(CampaignStatusType.NEW);

                        int totalNumberOfVouchers = Integer
                                .parseInt(currentEvent.nextToken());

                        newCampaign.setTotalNoOfVouchers(totalNumberOfVouchers);
                        newCampaign.setStrategyType(currentEvent.nextToken());

                        vms.addCampaign(newCampaign);
                    }

                    break;

                case "editCampaign":
                    if (user.isAdmin()) {
                        int campaignId = Integer
                                .parseInt(currentEvent.nextToken());

                        if (vms.getCampaign(campaignId).isNew()) {
                            Campaign campaign = vms.getCampaign(campaignId);

                            campaign.setCampaignName(currentEvent.nextToken());
                            campaign.setCampaignDescription(currentEvent.nextToken());

                            LocalDateTime startDate = new DateBuilder()
                                    .setYear(Integer.parseInt(currentEvent.nextToken()))
                                    .setMonth(Integer.parseInt(currentEvent.nextToken()))
                                    .setDay(Integer.parseInt(currentEvent.nextToken()))
                                    .setHour(Integer.parseInt(currentEvent.nextToken()))
                                    .setMinutes(Integer.parseInt(currentEvent.nextToken()))
                                    .getDate();

                            campaign.setStartDate(startDate);

                            LocalDateTime endDate = new DateBuilder()
                                    .setYear(Integer.parseInt(currentEvent.nextToken()))
                                    .setMonth(Integer.parseInt(currentEvent.nextToken()))
                                    .setDay(Integer.parseInt(currentEvent.nextToken()))
                                    .setHour(Integer.parseInt(currentEvent.nextToken()))
                                    .setMinutes(Integer.parseInt(currentEvent.nextToken()))
                                    .getDate();

                            campaign.setEndDate(endDate);

                            campaign.setTotalNoOfVouchers(Integer
                                    .parseInt(currentEvent.nextToken()));

                            vms.updateCampaign(campaignId, campaign);

                            Notification notification = new Notification();
                            notification.setCampaignId(campaignId);
                            notification.setNotificationDate(currentDate);
                            notification.setNotificationType(NotificationType.EDIT);

                            vms.getCampaign(campaignId).notifyAllObservers(notification);
                        } else if (vms.getCampaign(campaignId).isStarted()) {
                            int eventsNo = 7;

                            Campaign campaign = vms.getCampaign(campaignId);

                            // Parcurgerea pana la evenimentul care ne intereseaza
                            for (int j = 0; j < eventsNo; j++) {
                                currentEvent.nextToken();
                            }

                            LocalDateTime endDate = new DateBuilder()
                                    .setYear(Integer.parseInt(currentEvent.nextToken()))
                                    .setMonth(Integer.parseInt(currentEvent.nextToken()))
                                    .setDay(Integer.parseInt(currentEvent.nextToken()))
                                    .setHour(Integer.parseInt(currentEvent.nextToken()))
                                    .setMinutes(Integer.parseInt(currentEvent.nextToken()))
                                    .getDate();

                            campaign.setEndDate(endDate);

                            campaign.setTotalNoOfVouchers(Integer
                                    .parseInt(currentEvent.nextToken()));

                            vms.updateCampaign(campaignId, campaign);

                            Notification notification = new Notification();
                            notification.setCampaignId(campaignId);
                            notification.setNotificationDate(currentDate);
                            notification.setNotificationType(NotificationType.EDIT);

                            vms.getCampaign(campaignId).notifyAllObservers(notification);
                        }
                    }

                    break;

                case "cancelCampaign":
                    if (user.isAdmin()) {
                        int campaignId = Integer
                                .parseInt(currentEvent.nextToken());

                        if (vms.getCampaign(campaignId).isNew()
                                || vms.getCampaign(campaignId).isStarted()) {
                            vms.cancelCampaign(campaignId);

                            Notification notification = new Notification();
                            notification.setCampaignId(campaignId);
                            notification.setNotificationDate(currentDate);
                            notification.setNotificationType(NotificationType.CANCEL);

                            vms.getCampaign(campaignId).notifyAllObservers(notification);
                        }
                    }

                    break;

                case "generateVoucher":
                    if (user.isAdmin()) {
                        int campaignId = Integer
                                .parseInt(currentEvent.nextToken());

                        if (vms.getCampaign(campaignId).hasMoreVouchers()) {

                            String eMail = currentEvent.nextToken();
                            String voucherType = currentEvent.nextToken();

                            float value = Float
                                    .parseFloat(currentEvent.nextToken());

                            Voucher voucher = vms.getCampaign(campaignId).
                                    generateVoucher(eMail, voucherType, value);

                            vms.getUser(eMail).getReceivedVouchers()
                                    .addVoucher(voucher);
                            vms.getCampaign(campaignId).getVouchers()
                                    .addVoucher(voucher);
                            vms.getCampaign(campaignId)
                                    .addObserver(vms.getUser(eMail));
                        }
                    }

                    break;

                case "redeemVoucher":
                    if (user.isAdmin()) {
                        int campaignId = Integer
                                .parseInt(currentEvent.nextToken());
                        if (vms.getCampaign(campaignId).isNew()
                                || vms.getCampaign(campaignId).isStarted()) {

                            int voucherId = Integer
                                    .parseInt(currentEvent.nextToken());

                            LocalDateTime dateUsed = new DateBuilder()
                                    .setYear(Integer.parseInt(currentEvent.nextToken()))
                                    .setMonth(Integer.parseInt(currentEvent.nextToken()))
                                    .setDay(Integer.parseInt(currentEvent.nextToken()))
                                    .setHour(Integer.parseInt(currentEvent.nextToken()))
                                    .setMinutes(Integer.parseInt(currentEvent.nextToken()))
                                    .getDate();

                            vms.getCampaign(campaignId)
                                    .redeemVoucher(voucherId, dateUsed);
                        }
                    }

                    break;

                case "getVouchers":
                    if (!(user.isAdmin())) {
                        Set<Map.Entry<Integer, Collection<Voucher>>> entries =
                                user.getReceivedVouchers().entrySet();

                        if (entries.isEmpty()) {
                            printWriter.println("[]");
                        } else {
                            for (Map.Entry<Integer, Collection<Voucher>> entry :
                                    entries) {
                                printWriter.print(entry.getValue() + ", ");
                            }
                            printWriter.println();
                        }
                    }

                    break;

                case "getObservers":
                    if (user.isAdmin()) {
                        int campaignId = Integer
                                .parseInt(currentEvent.nextToken());

                        Campaign campaign = vms.getCampaign(campaignId);

                        printWriter.println(campaign.getObservers());
                    }

                    break;

                case "getNotifications":
                    if (!(user.isAdmin())) {
                        user.listNotifications(printWriter);
                    }

                    break;

                case "getVoucher":

                    if (user.isAdmin()) {
                        int campaignId = Integer
                                .parseInt(currentEvent.nextToken());

                        Voucher voucher =
                                vms.getCampaign(campaignId).executeStrategy();

                        if (voucher != null) {
                            vms.getUser(voucher.getUserEMailAdress())
                                    .getReceivedVouchers().addVoucher(voucher);
                            vms.getCampaign(campaignId).getVouchers()
                                    .addVoucher(voucher);
                            printWriter.println(voucher);
                        }
                    }

                    break;
            }
        }
        printWriter.close();
        scanner.close();
    }
}
