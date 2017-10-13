package newsreader.raman.com.newreader.activities;

import android.content.Intent;
import android.content.SharedPreferences;
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

    public static final String NEWS_INDIA_LINK = "http://feeds.feedburner.com/ndtvnews-india-news";
    public static final String SPORTS_LINK = "http://feeds.feedburner.com/ndtvsports-latest";
    public static final String BUSINESS_LINK = "http://feeds.feedburner.com/ndtvprofit-latest";
    public static final String WORLD_NEWS_LINK = "http://feeds.feedburner.com/ndtvnews-world-news";
    private RecyclerView newsList;
    private NewsListAdapter adapter;
    private String url;
    private List<FeedItem> feedItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            this.url = savedInstanceState.getString("url");
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences preferences = getSharedPreferences(SettingActivity.PREFERENCES, MODE_PRIVATE);
        this.url = preferences.getString("url", null);

        if (url == null)
            url = NEWS_INDIA_LINK;

        newsList = (RecyclerView) findViewById(R.id.news_list);
        newsList.setLayoutManager(new LinearLayoutManager(this));

        setupAdapter(url);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("url", url);
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
            case R.id.india:
                if (!url.equalsIgnoreCase(NEWS_INDIA_LINK)) {
                    url = NEWS_INDIA_LINK;
                    setupAdapter(url);
                } else {
                    Toast.makeText(this, "India news already displaying", Toast.LENGTH_SHORT).show();
                }
                return true;
            case R.id.sport:
                if (!url.equalsIgnoreCase(SPORTS_LINK)) {
                    url = SPORTS_LINK;
                    setupAdapter(url);
                } else {
                    Toast.makeText(this, "Sports news already displaying", Toast.LENGTH_SHORT).show();
                }
                setupAdapter(url);
                return true;
            case R.id.business:
                if (!url.equalsIgnoreCase(BUSINESS_LINK)) {
                    url = BUSINESS_LINK;
                    setupAdapter(url);
                } else {
                    Toast.makeText(this, "Business news already displaying", Toast.LENGTH_SHORT).show();
                }
                setupAdapter(url);
                return true;
            case R.id.world:
                if (!url.equalsIgnoreCase(WORLD_NEWS_LINK)) {
                    url = WORLD_NEWS_LINK;
                    setupAdapter(url);
                } else {
                    Toast.makeText(this, "World news already displaying", Toast.LENGTH_SHORT).show();
                }
                setupAdapter(url);
                return true;
            case R.id.settings:
                Intent intent = new Intent(this, SettingActivity.class);
                startActivity(intent);
                return true;
        }// end switch

        return super.onOptionsItemSelected(item);
    }

    private void setupAdapter(String url) {
        try {
            GetFeed feed = new GetFeed();
            this.feedItems = feed.execute(url).get();
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
