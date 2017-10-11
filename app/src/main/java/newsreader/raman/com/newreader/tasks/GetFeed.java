package newsreader.raman.com.newreader.tasks;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import newsreader.raman.com.newreader.feedparser.FeedParserUtil;
import newsreader.raman.com.newreader.model.FeedItem;

/**
 * Created by Raman on 10-10-2017.
 */

public class GetFeed extends AsyncTask<String, Void, List<FeedItem>> {

    private static final String TAG = "GetFeed";

    @Override
    protected List<FeedItem> doInBackground(String... urls) {
        try {
            URL url = new URL(urls[0]);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            InputStream inputStream = urlConnection.getInputStream();

            List<FeedItem> feedItems = FeedParserUtil.parseFeeds(inputStream);

            return  feedItems;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
