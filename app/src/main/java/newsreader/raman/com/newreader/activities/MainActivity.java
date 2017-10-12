package newsreader.raman.com.newreader.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.ExecutionException;

import newsreader.raman.com.newreader.R;
import newsreader.raman.com.newreader.adapter.NewsListAdapter;
import newsreader.raman.com.newreader.model.FeedItem;
import newsreader.raman.com.newreader.tasks.GetFeed;

public class MainActivity extends AppCompatActivity {

    private RecyclerView newsList;
    private NewsListAdapter adapter;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newsList = (RecyclerView) findViewById(R.id.news_list);
        newsList.setLayoutManager(new LinearLayoutManager(this));

        url = "http://feeds.feedburner.com/ndtvnews-india-news";
        setupAdapter(url);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        switch (itemId) {
            case R.id.home:
                if (!url.equalsIgnoreCase("http://feeds.feedburner.com/ndtvnews-india-news")) {
                    url = "http://feeds.feedburner.com/ndtvnews-india-news";
                    setupAdapter(url);
                } else {
                    Toast.makeText(this, "Home news already displaying", Toast.LENGTH_SHORT).show();
                }
                return true;
            case R.id.sport:
                url = "http://feeds.feedburner.com/ndtvsports-latest";
                setupAdapter(url);
                return true;
            case R.id.business:
                url = "http://feeds.feedburner.com/ndtvprofit-latest";
                setupAdapter(url);
                return true;
            case R.id.world:
                url = "http://feeds.feedburner.com/ndtvnews-world-news";
                setupAdapter(url);
                return true;
        }// end switch

        return super.onOptionsItemSelected(item);
    }

    private void setupAdapter(String url) {
        try {
            GetFeed feed = new GetFeed();
            List<FeedItem> feedItems = feed.execute(url).get();
            adapter = new NewsListAdapter(feedItems, this);
            
            if (newsList.getAdapter() == null)
                newsList.setAdapter(adapter);
            else
                newsList.swapAdapter(adapter, false);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}
