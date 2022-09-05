package Entities;

import Partner.Shareable;

public class WebLink extends Bookmark implements Shareable {
    private String url;
    private String host;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    @Override
    public String toString() {
        return "WebLink{" +
                "url='" + url + '\'' +
                ", host='" + host + '\'' +
                '}';
    }

    @Override
    public boolean isKidFriendlyEligible() {
        if(url.contains("porn") || getTitle().contains("porn") || host.contains("adult")){
            return false;
        }
        return true;
    }

    @Override
    public String getItemData() {
        StringBuilder sb = new StringBuilder();

        sb.append("<item>");
        sb.append("<type>WebLink</type>");
        sb.append("<title>").append(getTitle()).append("</title>");
        sb.append("<url>").append(url).append("</url>");
        sb.append("<title>").append(host).append("</title>");
        sb.append("</item>");
        return sb.toString();
    }
}
