package adam.notebook.example.com.kpproject6.module.listproduct;

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

public class ListProductAdapter extends BaseAdapter {

    Context mContex;
    String [] nama_listproduct = {"ASUS ROG","INTEL PROCESSOR","MOTHERBOARD ASUS","MONITOR GAMING","MOUSEPAD GAMING","HEADSET GAMING","GAMING CHAIR"};
    String [] price_listproduct = {"10000","20000","300000","400000","500000","600000","7000000"};
    int [] image_listproduct = {
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background};

    public ListProductAdapter(Context ctx) {
        this.mContex = ctx;
    }

    @Override
    public int getCount() {
        return nama_listproduct.length;
    }

    @Override
    public Object getItem(int position) {
        return nama_listproduct[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        if (view==null){
            LayoutInflater inflater = (LayoutInflater)mContex.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(R.layout.listproductitem,null);
        }

        //get view
        TextView txt_nameProduct = (TextView)view.findViewById(R.id.listnama);
        TextView txt_priceProduct = (TextView)view.findViewById(R.id.listharga);
        ImageView img_Product = (ImageView)view.findViewById(R.id.listimage);
        Button btn_buy = (Button)view.findViewById(R.id.btn_buy);

        //setData

        txt_nameProduct.setText(nama_listproduct[position]);
        txt_priceProduct.setText(price_listproduct[position]);
        img_Product.setImageResource(image_listproduct[position]);
//
//        btn_buy.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mContex.startActivity(new Intent(mContex, CartActivity.class));
//            }
//        });

        return view;
    }
}
