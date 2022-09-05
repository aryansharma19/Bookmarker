package Entities;

import Constants.KidFriendlyStatus;

public abstract class Bookmark {
    private long id;
    private String title;
    private String profileUrl;
    private String kidFriendlyStatus = KidFriendlyStatus.UNKNOWN;
    private User kidFriendlyStatusMarkedBy;

    private User sharedBy;

    public User getSharedBy() {
        return sharedBy;
    }

    public void setSharedBy(User sharedBy) {
        this.sharedBy = sharedBy;
    }

    public User getKidFriendlyStatusMarkedBy() {
        return kidFriendlyStatusMarkedBy;
    }

    public void setKidFriendlyStatusMarkedBy(User kidFriendlyStatusMarkedBy) {
        this.kidFriendlyStatusMarkedBy = kidFriendlyStatusMarkedBy;
    }

    public String getKidFriendlyStatus() {
        return kidFriendlyStatus;
    }

    public void setKidFriendlyStatus(String kidFriendlyStatus) {
        this.kidFriendlyStatus = kidFriendlyStatus;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public abstract boolean isKidFriendlyEligible();
}
