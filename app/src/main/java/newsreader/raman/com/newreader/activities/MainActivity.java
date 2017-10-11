package newsreader.raman.com.newreader.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;
import java.util.concurrent.ExecutionException;

import newsreader.raman.com.newreader.R;
import newsreader.raman.com.newreader.adapter.NewsListAdapter;
import newsreader.raman.com.newreader.model.FeedItem;
import newsreader.raman.com.newreader.tasks.GetFeed;

public class MainActivity extends AppCompatActivity {

    private RecyclerView newsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newsList = (RecyclerView) findViewById(R.id.news_list);
        newsList.setLayoutManager(new LinearLayoutManager(this));

        try {
            String url = "http://feeds.feedburner.com/ndtvnews-india-news";
            GetFeed feed = new GetFeed();
            List<FeedItem> feedItems = feed.execute(url).get();
            NewsListAdapter adapter = new NewsListAdapter(feedItems, this);
            newsList.setAdapter(adapter);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
