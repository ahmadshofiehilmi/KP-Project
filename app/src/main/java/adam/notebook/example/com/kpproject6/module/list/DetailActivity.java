package adam.notebook.example.com.kpproject6.module.list;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.flaviofaria.kenburnsview.KenBurnsView;

import adam.notebook.example.com.kpproject6.CheckedLogin;
import adam.notebook.example.com.kpproject6.GeneralUtility.Utils;
import adam.notebook.example.com.kpproject6.R;
import adam.notebook.example.com.kpproject6.model.product.Product;
import adam.notebook.example.com.kpproject6.module.login.LoginActivity;
import adam.notebook.example.com.kpproject6.service.detail.ProductDetailPresenter;
import adam.notebook.example.com.kpproject6.service.detail.ServiceCallback;
import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static adam.notebook.example.com.kpproject6.MyApplication.pref;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.layout_detail_container)
    CoordinatorLayout container;

    @BindView(R.id.title_product)
    TextView product_name;

    @BindView(R.id.desc_product)
    TextView product_desc;

    @BindView(R.id.img_produk)
    KenBurnsView image_product;

    @BindView(R.id.edt_jumlah)
    EditText editText;


    private Product product;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
//        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        if(getSupportActionBar() != null)
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String name = getIntent().getExtras().getString("product_name");
        String desc = getIntent().getExtras().getString("product_desc");
        String pict = getIntent().getExtras().getString("product_image");

        product_name.setText(name);
        product_desc.setText("Rp "+ Utils.toFormatCurrency(desc));

        Glide.with(this)
                .load(pict)
                .apply(new RequestOptions()
                .placeholder(R.drawable.ic_launcher_background))
                .into(image_product);


    }
    @OnClick(R.id.btn_increase)
    public void onIncrease(View view) {
        String jumlah = editText.getText().toString();
        jumlah = (jumlah.isEmpty()) ? "0" : jumlah;
        int jml = Integer.parseInt(jumlah) + 1;
        editText.setText(String.valueOf(jml));
    }

    @OnClick(R.id.btn_decrease)
    public void onDecrease(View view) {
        String jumlah = editText.getText().toString();
        jumlah = (jumlah.isEmpty()) ? "0" : jumlah;
        int jml = Integer.parseInt(jumlah);
        jml = (jml == 1) ? 1 : (jml - 1);
        editText.setText(String.valueOf(jml));
    }

}

