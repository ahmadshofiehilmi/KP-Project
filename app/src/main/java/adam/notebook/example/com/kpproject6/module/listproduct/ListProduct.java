package adam.notebook.example.com.kpproject6.module.listproduct;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import adam.notebook.example.com.kpproject6.R;
import adam.notebook.example.com.kpproject6.module.favorit.FavoritActivity;

public class ListProduct extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_product);

        ImageView btn_list = (ImageView) findViewById(R.id.listback);
        btn_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ListProduct.this, FavoritActivity.class);
                startActivity(i);
            }
        });

        ListView listView = (ListView)findViewById(R.id.listlv);
        ListProductAdapter adapter = new ListProductAdapter(this);
        listView.setAdapter(adapter);

    }
}