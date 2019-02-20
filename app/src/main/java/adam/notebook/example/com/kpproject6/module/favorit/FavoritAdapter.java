package adam.notebook.example.com.kpproject6.module.favorit;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import adam.notebook.example.com.kpproject6.R;

public class FavoritAdapter extends ArrayAdapter<FavoritModel> {
    private List<FavoritModel> list;
    private Context context;

    TextView currentFavoritName,
            currentCost,
            removeFavorit;
    ImageView img;
    Button paynow;

    public FavoritAdapter(Context context, List<FavoritModel> myOrders) {
        super(context, 0, myOrders);
        this.list = myOrders;
        this.context = context;
    }


    public View getView(final int position, View convertView, ViewGroup parent){
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.favorititem,parent,false
            );
        }

        final FavoritModel currentFood = getItem(position);

        currentFavoritName = (TextView)listItemView.findViewById(R.id.favoritnama);
        img = (ImageView)listItemView.findViewById(R.id.favoritimage);
        currentCost = (TextView)listItemView.findViewById(R.id.favoritharga);
        removeFavorit = (TextView)listItemView.findViewById(R.id.favoritdelete);
        paynow = (Button)listItemView.findViewById(R.id.btn_paynow);

        //Set the text of the meal, amount and quantity
        img.setImageResource(currentFood.getmImage());
        currentFavoritName.setText(currentFood.getmName());
        currentCost.setText("Rp."+" "+ (currentFood.getmAmount()));

        //OnClick listeners for all the buttons on the ListView Item
        removeFavorit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.remove(position);
                notifyDataSetChanged();
            }
        });

//        paynow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                context.startActivity(new Intent(context, CartActivity.class));
//            }
//        });

        return listItemView;
    }

}