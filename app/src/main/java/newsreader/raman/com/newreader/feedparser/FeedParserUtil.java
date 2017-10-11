package newsreader.raman.com.newreader.feedparser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import newsreader.raman.com.newreader.model.FeedItem;

/**
 * Created by Raman on 10-10-2017.
 */

public class FeedParserUtil {

    public static final String TITLE = "title";
    public static final String LINK = "link";
    public static final String IMAGE = "StoryImage";
    public static final String DESCRIPTION = "description";
    public static final String PUBDATE = "pubDate";

    public static List<FeedItem> parseFeeds(InputStream stream) {

        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(stream, null);

            int eventType = parser.getEventType();
            List<FeedItem> feedItems = null;
            FeedItem feedItem = null;
            String tagname = "";

            while (eventType != XmlPullParser.END_DOCUMENT) {

                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        feedItems = new ArrayList<>();
                        break;
                    case XmlPullParser.START_TAG:
                        tagname = parser.getName();
                        if (tagname.equalsIgnoreCase("item")) {
                            feedItem = new FeedItem();
                            feedItems.add(feedItem);
                        }
                        break;
                    case XmlPullParser.TEXT:
                        if (feedItem != null) {
                            switch (tagname) {
                                case TITLE:
                                    feedItem.setTitle(parser.getText());
                                    break;
                                case LINK:
                                    feedItem.setLink(parser.getText());
                                    break;
                                case PUBDATE:
                                    try {
                                        String text = parser.getText();
                                        SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");
                                        Date date = sdf.parse(text);
                                        feedItem.setPublishDate(date);
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                        return null;
                                    }
                                case IMAGE:
                                    feedItem.setImage(parser.getText());
                                    break;
                                case DESCRIPTION:
                                    feedItem.setDescription(parser.getText());
                                    break;
                            }
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        tagname = "";
                        break;
                }// end switch
                eventType = parser.next();
            }// end while
            return feedItems;
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}

