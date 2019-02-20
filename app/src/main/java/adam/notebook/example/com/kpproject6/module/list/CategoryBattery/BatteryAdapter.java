package adam.notebook.example.com.kpproject6.module.list.CategoryBattery;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import adam.notebook.example.com.kpproject6.R;
import adam.notebook.example.com.kpproject6.model.product.Product;
import adam.notebook.example.com.kpproject6.module.list.DetailActivity;

public class BatteryAdapter  extends RecyclerView.Adapter<BatteryAdapter.CustomViewHolder> implements Filterable {
    private List<Product> items;
    private List<Product> filteredProductList;
    private Context context;


    public BatteryAdapter(List<Product> items, List<Product> filteredProductList, Context context) {
        this.items = items;
        this.filteredProductList = filteredProductList;
        this.context = context;
    }

    public BatteryAdapter(Context applicationContext, List<Product> items) {
        this.items = items;
        this.context = applicationContext;
    }

    @Override
    public BatteryAdapter.CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_product, viewGroup, false);
        return new CustomViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(BatteryAdapter.CustomViewHolder viewHolder, int i) {
        viewHolder.title.setText(items.get(i).getProduct_name());
        viewHolder.githublink.setText(items.get(i).getProduct_price());

        Picasso.with(viewHolder.itemView.getContext())
                .load(items.get(i).getImage().getProduct_image())
//                .resize(120,60)
                .placeholder(R.drawable.ic_launcher_background)
                .into(viewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    filteredProductList = items;

                } else {
                    List filteredList = new ArrayList<>();
                    for (Product row : items) {


                        //change this to filter according to your case
                        if (row.getProduct_name().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    filteredProductList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredProductList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredProductList = (ArrayList)results.values;
                notifyDataSetChanged();
            }
        };
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        private TextView title, githublink;
        private ImageView imageView;


        public CustomViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            githublink = (TextView) view.findViewById(R.id.githublink1);
            imageView = (ImageView) view.findViewById(R.id.cover);

            //on item click
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        Product clickedDataItem = items.get(pos);
                        Intent intent = new Intent(context, DetailActivity.class);
                        intent.putExtra("product_name", items.get(pos).getProduct_name());
                        intent.putExtra("product_desc", items.get(pos).getProduct_price());
                        intent.putExtra("product_image", items.get(pos).getImage().getProduct_image());
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                        Toast.makeText(v.getContext(), "You clicked " + clickedDataItem.getProduct_name(), Toast.LENGTH_SHORT).show();
                    }
                }

            });
        }
    }
}
