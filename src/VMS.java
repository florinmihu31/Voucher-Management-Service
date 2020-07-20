import java.util.ArrayList;
import java.util.Collection;

public class VMS {
    private ArrayList<Campaign> campaigns;
    private Collection<User> users;

    private final static VMS VMS = new VMS();

    private VMS() {
        campaigns = new ArrayList<>();
        users = new ArrayList<>();
    }

    public static VMS getInstance() {
        return VMS;
    }

    public Collection<Campaign> getCampaigns() {
        return campaigns;
    }

    public Campaign getCampaign(Integer id) {
        for (Campaign campaign : campaigns) {
            if (campaign.getCampaignId() == id) {
                return campaign;
            }
        }

        return null;
    }

    public boolean addCampaign(Campaign campaign) {
        return campaigns.add(campaign);
    }

    public void updateCampaign (Integer id, Campaign campaign) {
        int index = campaigns.indexOf(getCampaign(id));
        campaigns.remove(getCampaign(id));
        campaigns.add(index, campaign);
    }

    public void cancelCampaign (Integer id) {
        for (Campaign campaign : campaigns) {
            if (campaign.getCampaignId() == id) {
                campaign.setCampaignStatus(CampaignStatusType.CANCELLED);
            }
        }
    }

    public Collection<User> getUsers() {
        return users;
    }

    public User getUser(Integer id) {
        for (User user : users) {
            if (user.getUserId() == id) {
                return user;
            }
        }

        return null;
    }

    public User getUser(String eMailAddress) {
        for (User user : users) {
            if (user.geteMailAddress().equals(eMailAddress)) {
                return user;
            }
        }

        return null;
    }

    public boolean addUser(User user) {
        return users.add(user);
    }
}
