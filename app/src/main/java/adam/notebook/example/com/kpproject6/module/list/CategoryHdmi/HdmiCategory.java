package adam.notebook.example.com.kpproject6.module.list.CategoryHdmi;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.github.ilyagh.TypewriterRefreshLayout;

import adam.notebook.example.com.kpproject6.module.list.BottomSheetdialog;
import adam.notebook.example.com.kpproject6.module.list.DetailActivity;
import adam.notebook.example.com.kpproject6.R;

public class HdmiCategory extends AppCompatActivity {
    private LinearLayout bottomsheetlayout_list;
    private BottomSheetBehavior mbottomsheetbehavior;
    private FloatingActionButton fabmenu;
    ListView listView;
    TypewriterRefreshLayout pulltorefresh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hdmi_category); bottomsheetlayout_list = (LinearLayout) findViewById(R.id.bottomsheetlist);
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

                if (BottomSheetBehavior.STATE_DRAGGING == newState) {
                    fabmenu.animate().scaleX(0).scaleY(0).setDuration(300).start();
                } else if (BottomSheetBehavior.STATE_COLLAPSED == newState) {
                    fabmenu.animate().scaleX(1).scaleY(1).setDuration(300).start();
                } else if (BottomSheetBehavior.STATE_HIDDEN == newState) {
                    fabmenu.animate().scaleX(1).scaleY(1).setDuration(300).start();
                }
                if (newState == BottomSheetBehavior.STATE_DRAGGING) {
                    mbottomsheetbehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
            }
        });

        ListView listView = (ListView)findViewById(R.id.listProduk);
        final TypewriterRefreshLayout pulltorefresh=(TypewriterRefreshLayout)findViewById(R.id.pulltoRefresh);
        pulltorefresh.setOnRefreshListener(new TypewriterRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pulltorefresh.setRefreshing(isFinishing());

            }
        });
        HdmiAdapter adapter = new HdmiAdapter(this);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                intent.putExtra("Position",position);
                startActivity(intent);
            }
        });

    }
}
