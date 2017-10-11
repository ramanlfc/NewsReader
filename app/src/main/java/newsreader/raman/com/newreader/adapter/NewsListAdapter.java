package newsreader.raman.com.newreader.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import newsreader.raman.com.newreader.R;
import newsreader.raman.com.newreader.model.FeedItem;
import newsreader.raman.com.newreader.tasks.GetFeedImage;

/**
 * Created by Raman on 11-10-2017.
 */

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.NewsHolder> {

    private List<FeedItem> feedItems;
    private Context context;
    private LayoutInflater inflater;


    public NewsListAdapter(List<FeedItem> feedItems, Context context) {
        this.feedItems = feedItems;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public NewsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.news_item, parent, false);
        NewsHolder holder = new NewsHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(NewsHolder holder, int position) {
        FeedItem feedItem = feedItems.get(position);
        holder.bindViews(feedItem);
    }

    @Override
    public int getItemCount() {
        return feedItems.size();
    }

    class NewsHolder extends RecyclerView.ViewHolder {

        private View view;
        private ImageView newsImageView;
        private TextView newsTitleTextView;
        private TextView pubDateTextView;

        NewsHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            this.newsTitleTextView = (TextView) view.findViewById(R.id.new_item_title);
            this.newsImageView = (ImageView) view.findViewById(R.id.new_item_img);
            this.pubDateTextView = (TextView) view.findViewById(R.id.news_item_date);
        }

        void bindViews(FeedItem item) {

            try {
                GetFeedImage task = new GetFeedImage();
                Bitmap bitmap = task.execute(item.getImage()).get();

                newsImageView.setImageBitmap(bitmap);

                newsTitleTextView.setText(item.getTitle());

                SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
                String format = sdf.format(item.getPublishDate());
                pubDateTextView.setText(format);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        }
    }

}
