package adam.notebook.example.com.kpproject6.module.historyorder;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import adam.notebook.example.com.kpproject6.R;
import adam.notebook.example.com.kpproject6.TablayoutActivity;

public class HistoryOrderAdapter extends BaseAdapter {

    Context mContex;
    String [] nama_his = {"ASUS ROG","INTEL PROCESSOR","MOTHERBOARD ASUS","MONITOR GAMING","MOUSEPAD GAMING","HEADSET GAMING","GAMING CHAIR"};
    String [] price_history = {"10000","20000","300000","400000","500000","600000","7000000"};
    int [] image_history = {
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background};

    public HistoryOrderAdapter(Context ctx) {
        this.mContex = ctx;
    }

    @Override
    public int getCount() {
        return nama_his.length;
    }

    @Override
    public Object getItem(int position) {
        return nama_his[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        if (view==null){
            LayoutInflater inflater = (LayoutInflater)mContex.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(R.layout.historyitem,null);
        }

        //get view
        TextView txt_namahis = (TextView)view.findViewById(R.id.historynama);
        TextView txt_pricehis = (TextView)view.findViewById(R.id.historyharga);
        ImageView img_Producthis = (ImageView)view.findViewById(R.id.historyimage);
        Button btn_status = (Button)view.findViewById(R.id.btn_status);

        //setData
        txt_namahis.setText(nama_his[position]);
        txt_pricehis.setText(price_history[position]);
        img_Producthis.setImageResource(image_history[position]);

        btn_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContex.startActivity(new Intent(mContex, TablayoutActivity.class));
            }
        });

        return view;

    }
}
