package adam.notebook.example.com.kpproject6.module.favorit;

import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import adam.notebook.example.com.kpproject6.R;

public class FavoritActivity extends AppCompatActivity {

    TextView mealTotalText;
    ArrayList<FavoritModel> orders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorit);

        ListView storedOrders = (ListView)findViewById(R.id.favoritlv);

        orders = getListItemData();
        FavoritAdapter adapter = new FavoritAdapter(this, orders);

        storedOrders.setAdapter(adapter);
        adapter.registerDataSetObserver(observer);

    }


    DataSetObserver observer = new DataSetObserver() {
        @Override
        public void onChanged() {
            super.onChanged();
        }
    };

    private ArrayList<FavoritModel> getListItemData(){
        ArrayList<FavoritModel> listViewItems = new ArrayList<FavoritModel>();
        listViewItems.add(new FavoritModel("pc",30, R.drawable.ic_launcher_background));
        listViewItems.add(new FavoritModel("vga",40, R.drawable.ic_launcher_background));
        listViewItems.add(new FavoritModel("laptop",60, R.drawable.ic_launcher_background));
        listViewItems.add(new FavoritModel("ssd",80, R.drawable.ic_launcher_background));
        listViewItems.add(new FavoritModel("ram",100, R.drawable.ic_launcher_background));
        listViewItems.add(new FavoritModel("pc",100, R.drawable.ic_launcher_background));

        return listViewItems;
    }

}