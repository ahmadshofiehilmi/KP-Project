package adam.notebook.example.com.kpproject6.module.list.CategoryBattery;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.support.v7.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import java.util.List;

import adam.notebook.example.com.kpproject6.GeneralUtility.ConnectionUtility.Client;
import adam.notebook.example.com.kpproject6.GeneralUtility.ConnectionUtility.apiservice.OpenApiService;
import adam.notebook.example.com.kpproject6.model.product.Product;
import adam.notebook.example.com.kpproject6.model.product.ProductResponse;
import adam.notebook.example.com.kpproject6.module.list.BottomSheetdialog;
import adam.notebook.example.com.kpproject6.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BatteryCategory extends AppCompatActivity  {

public static final String TITLE = "List Product";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private RecyclerView recyclerView;
    TextView Disconnected;
    private Product item;
    ProgressDialog pd;
    private Context context;

    private BatteryAdapter adapter;
    private SwipeRefreshLayout swipeContainer;
    private LinearLayout bottomsheetlayout_list;
    private BottomSheetBehavior mbottomsheetbehavior;
    private FloatingActionButton fabmenu;
    private LinearLayout btnbattery;
    ListView listView;
    //    TypewriterRefreshLayout pulltorefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_battery);
        ButterKnife.bind(this);

        initProgress();
        initView();


        String title = getIntent().getStringExtra(TITLE);
        setSupportActionBar(toolbar);
        setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);

        swipeContainer.setColorSchemeResources(android.R.color.holo_orange_dark);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
            @Override
            public void onRefresh(){
                loadJSON();
                Toast.makeText(BatteryCategory.this, "Our Data Refreshed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search,menu);
        final MenuItem searchMenuItem = menu.findItem(R.id.search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchMenuItem);
        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // TODO Auto-generated method stub
                if (!hasFocus) {
                    MenuItemCompat.collapseActionView(searchMenuItem);
                    searchView.setQuery("", false);
            }
        }
    });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // TODO Auto-generated method stub
                adapter.getFilter().filter(query);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                // TODO Auto-generated method stub
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void loadJSON() {
        Disconnected = (TextView) findViewById(R.id.disconnected);
        try{
            Client Client = new Client();
            OpenApiService apiService =
                    Client.getClient().create(OpenApiService.class);
            Call<ProductResponse> call = apiService.getProductResponse();
            call.enqueue(new Callback<ProductResponse>() {
                @Override
                public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                    List<Product> items = response.body().getProduct();
                    recyclerView.setAdapter(new BatteryAdapter(getApplicationContext(), items));
                    recyclerView.smoothScrollToPosition(0);
                    swipeContainer.setRefreshing(false);
                    pd.hide();
                }

                @Override
                public void onFailure(Call<ProductResponse> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                    Toast.makeText(BatteryCategory.this, "Error Fetching Data!", Toast.LENGTH_SHORT).show();
                    Disconnected.setVisibility(View.VISIBLE);
                    pd.hide();
                }
            });

        }catch (Exception e){
            Log.d("Error", e.getMessage());
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    private void initProgress() {
        pd = new ProgressDialog(this);
        pd.setMessage("Fetching Our Product...");
        pd.setCancelable(false);
        pd.show();
        recyclerView=(RecyclerView) findViewById(R.id.listProduk_battery);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.smoothScrollToPosition(0);
        pd.dismiss();
        loadJSON();
    }

    private void initView() {

        bottomsheetlayout_list = (LinearLayout) findViewById(R.id.bottomsheetlist);
        fabmenu = (FloatingActionButton) findViewById(R.id.fab_menu);
        mbottomsheetbehavior = BottomSheetBehavior.from(bottomsheetlayout_list);

        fabmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mbottomsheetbehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                new BottomSheetdialog().show(getSupportFragmentManager(), "Dialog");
            }
        });

        // set callback for changes
        mbottomsheetbehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {

//                if (BottomSheetBehavior.STATE_DRAGGING == newState) {
//                    fabmenu.animate().scaleX(0).scaleY(0).setDuration(300).
//                            start();
//                } else if (BottomSheetBehavior.STATE_COLLAPSED == newState) {
//                    fabmenu.animate().scaleX(1).scaleY(1).setDuration(300).start();
//                } else if (BottomSheetBehavior.STATE_HIDDEN == newState) {
//                    fabmenu.animate().scaleX(1).scaleY(1).setDuration(300).start();
//                }
//                String strNewState = "";
//
//                switch(newState) {
//                    case BottomSheetBehavior.STATE_COLLAPSED:
//                        strNewState = "STATE_COLLAPSED";
//                        fabmenu.animate().scaleX(1).scaleY(1).setDuration(300).start();
//                        break;
//                    case BottomSheetBehavior.STATE_EXPANDED:
//                        strNewState = "STATE_EXPANDED";
//                        break;
//                    case BottomSheetBehavior.STATE_HIDDEN:
//                        strNewState = "STATE_HIDDEN";
//                        fabmenu.animate().scaleX(1).scaleY(1).setDuration(300).start();
//                        break;
//                    case BottomSheetBehavior.STATE_DRAGGING:
//                        strNewState = "STATE_DRAGGING";
//                        fabmenu.animate().scaleX(0).scaleY(0).setDuration(300).start();
//                        break;
//                    case BottomSheetBehavior.STATE_SETTLING:
//                        strNewState = "STATE_SETTLING";
//                        break;
//                }
//                Log.i("BottomSheets", "New state: " + strNewState);

                if (newState == BottomSheetBehavior.STATE_DRAGGING) {
                    mbottomsheetbehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }


            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                Log.i("BottomSheets", "Offset: " + slideOffset);
            }
        });

    }
}