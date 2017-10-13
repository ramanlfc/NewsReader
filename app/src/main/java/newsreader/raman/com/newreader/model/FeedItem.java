package newsreader.raman.com.newreader.model;

import java.io.Serializable;
import java.net.URL;
import java.util.Date;

/**
 * Created by Raman on 10-10-2017.
 */

public class FeedItem{

    String title;
    String link;
    String image;
    Date publishDate;
    String description;

    public FeedItem() {
    }

    public FeedItem(String title, String link, String image, Date publishDate, String description) {
        this.title = title;
        this.link = link;
        this.image = image;
        this.publishDate = publishDate;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "FeedItem{" +
                "title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", image='" + image + '\'' +
                ", publishDate=" + publishDate +
                ", description='" + description + '\'' +
                '}';
    }
}


