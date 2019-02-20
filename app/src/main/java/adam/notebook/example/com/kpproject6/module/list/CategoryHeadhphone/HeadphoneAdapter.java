package adam.notebook.example.com.kpproject6.module.list.CategoryHeadhphone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import adam.notebook.example.com.kpproject6.R;

public class HeadphoneAdapter extends BaseAdapter {
    Context mContex;
    String [] name_product = {"ASUS ROG headphone","INTEL PROCESSOR","MOTHERBOARD ASUS","MONITOR GAMING","MOUSEPAD GAMING","HEADSET GAMING","GAMING CHAIR"};
    String [] price_product = {"10000","20000","300000","400000","500000","600000","7000000"};
    int [] image_product = {
            R.drawable.produkbattery,
            R.drawable.produkprocessorinter,
            R.drawable.produkmotherboard,
            R.drawable.produkmonitorasus,
            R.drawable.produkmouserazer,
            R.drawable.produkheadphone,
            R.drawable.produkcerberus_keyboard};

    public HeadphoneAdapter (Context ctx) {
        this.mContex = ctx;
    }

    @Override
    public int getCount() {
        return name_product.length;
    }

    @Override
    public Object getItem(int position) {
        return name_product[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        if (view==null){
            LayoutInflater inflater = (LayoutInflater)mContex.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(R.layout.itemlist,null);
        }

        //get view
        TextView txt_nameProduct = (TextView)view.findViewById(R.id.nama);
        TextView txt_priceProduct = (TextView)view.findViewById(R.id.desc_product);
        ImageView img_Product = (ImageView)view.findViewById(R.id.image_produk);

        //setData

        txt_nameProduct.setText(name_product[position]);
        txt_priceProduct.setText(price_product[position]);
        img_Product.setImageResource(image_product[position]);
        return view;
    }
}