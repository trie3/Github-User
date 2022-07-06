package com.suwarso.tri.githubuser.utils.offline;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "github_user")
public class GithubUser {

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    private Integer id;
    @ColumnInfo(name = "login")
    private String login;
    @ColumnInfo(name = "nodeId")
    private String nodeId;
    @ColumnInfo(name = "avatarUrl")
    private String avatarUrl;
    @ColumnInfo(name = "gravatarId")
    private String gravatarId;
    @ColumnInfo(name = "url")
    private String url;
    @ColumnInfo(name = "htmlUrl")
    private String htmlUrl;
    @ColumnInfo(name = "followersUrl")
    private String followersUrl;
    @ColumnInfo(name = "followingUrl")
    private String followingUrl;
    @ColumnInfo(name = "gistsUrl")
    private String gistsUrl;
    @ColumnInfo(name = "starredUrl")
    private String starredUrl;
    @ColumnInfo(name = "subscriptionsUrl")
    private String subscriptionsUrl;
    @ColumnInfo(name = "organizationsUrl")
    private String organizationsUrl;
    @ColumnInfo(name = "reposUrl")
    private String reposUrl;
    @ColumnInfo(name = "eventsUrl")
    private String eventsUrl;
    @ColumnInfo(name = "receivedEventsUrl")
    private String receivedEventsUrl;
    @ColumnInfo(name = "type")
    private String type;
    @ColumnInfo(name = "siteAdmin")
    private Boolean siteAdmin;
    @ColumnInfo(name = "note")
    private String note;
    @ColumnInfo(name = "seen")
    private boolean seen;

    @Ignore
    public GithubUser() {
    }

    public GithubUser(Integer id, String login, String nodeId, String avatarUrl, String gravatarId, String url, String htmlUrl, String followersUrl, String followingUrl, String gistsUrl, String starredUrl, String subscriptionsUrl, String organizationsUrl, String reposUrl, String eventsUrl, String receivedEventsUrl, String type, Boolean siteAdmin, String note, boolean seen) {
        this.id = id;
        this.login = login;
        this.nodeId = nodeId;
        this.avatarUrl = avatarUrl;
        this.gravatarId = gravatarId;
        this.url = url;
        this.htmlUrl = htmlUrl;
        this.followersUrl = followersUrl;
        this.followingUrl = followingUrl;
        this.gistsUrl = gistsUrl;
        this.starredUrl = starredUrl;
        this.subscriptionsUrl = subscriptionsUrl;
        this.organizationsUrl = organizationsUrl;
        this.reposUrl = reposUrl;
        this.eventsUrl = eventsUrl;
        this.receivedEventsUrl = receivedEventsUrl;
        this.type = type;
        this.siteAdmin = siteAdmin;
        this.note = note;
        this.seen = seen;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getGravatarId() {
        return gravatarId;
    }

    public void setGravatarId(String gravatarId) {
        this.gravatarId = gravatarId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public String getFollowersUrl() {
        return followersUrl;
    }

    public void setFollowersUrl(String followersUrl) {
        this.followersUrl = followersUrl;
    }

    public String getFollowingUrl() {
        return followingUrl;
    }

    public void setFollowingUrl(String followingUrl) {
        this.followingUrl = followingUrl;
    }

    public String getGistsUrl() {
        return gistsUrl;
    }

    public void setGistsUrl(String gistsUrl) {
        this.gistsUrl = gistsUrl;
    }

    public String getStarredUrl() {
        return starredUrl;
    }

    public void setStarredUrl(String starredUrl) {
        this.starredUrl = starredUrl;
    }

    public String getSubscriptionsUrl() {
        return subscriptionsUrl;
    }

    public void setSubscriptionsUrl(String subscriptionsUrl) {
        this.subscriptionsUrl = subscriptionsUrl;
    }

    public String getOrganizationsUrl() {
        return organizationsUrl;
    }

    public void setOrganizationsUrl(String organizationsUrl) {
        this.organizationsUrl = organizationsUrl;
    }

    public String getReposUrl() {
        return reposUrl;
    }

    public void setReposUrl(String reposUrl) {
        this.reposUrl = reposUrl;
    }

    public String getEventsUrl() {
        return eventsUrl;
    }

    public void setEventsUrl(String eventsUrl) {
        this.eventsUrl = eventsUrl;
    }

    public String getReceivedEventsUrl() {
        return receivedEventsUrl;
    }

    public void setReceivedEventsUrl(String receivedEventsUrl) {
        this.receivedEventsUrl = receivedEventsUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getSiteAdmin() {
        return siteAdmin;
    }

    public void setSiteAdmin(Boolean siteAdmin) {
        this.siteAdmin = siteAdmin;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }
}
