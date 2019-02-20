package adam.notebook.example.com.kpproject6.module.historyorder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import adam.notebook.example.com.kpproject6.R;

public class HistoryOrder extends AppCompatActivity {

    ListView listView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_order);


        ListView listView1 = (ListView)findViewById(R.id.historylv);
        HistoryOrderAdapter adapter = new HistoryOrderAdapter(this);
        listView1.setAdapter(adapter);

    }
}