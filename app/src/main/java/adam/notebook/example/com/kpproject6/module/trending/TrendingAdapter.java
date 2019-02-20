package adam.notebook.example.com.kpproject6.module.trending;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


import adam.notebook.example.com.kpproject6.Categories_Activity;
import adam.notebook.example.com.kpproject6.R;

public class TrendingAdapter extends RecyclerView.Adapter<TrendingAdapter.MyViewHolder> {

    private Context mContext ;
    private List<Trending> mData_trending ;


    public TrendingAdapter(Context mContext, List<Trending> mData) {
        this.mContext = mContext;
        this.mData_trending = mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.categories_item_book,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.tv_book_title.setText(mData_trending.get(position).getTitle());
        holder.img_book_thumbnail.setImageResource(mData_trending.get(position).getThumbnail());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, Categories_Activity.class);

                // passing data to the book activity
                intent.putExtra("Title",mData_trending.get(position).getTitle());
                intent.putExtra("Description",mData_trending.get(position).getDescription());
                intent.putExtra("Thumbnail",mData_trending.get(position).getThumbnail());
                // start the activity
                mContext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mData_trending.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_book_title;
        ImageView img_book_thumbnail;
        CardView cardView ;

        public MyViewHolder(View itemView) {
            super(itemView);

            tv_book_title = (TextView) itemView.findViewById(R.id.book_title_id) ;
            img_book_thumbnail = (ImageView) itemView.findViewById(R.id.book_img_id);
            cardView = (CardView) itemView.findViewById(R.id.cardview_id);
        }
    }

}
