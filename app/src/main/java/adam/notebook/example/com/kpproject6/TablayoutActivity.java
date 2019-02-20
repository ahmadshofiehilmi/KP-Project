package adam.notebook.example.com.kpproject6;

import android.content.DialogInterface;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import adam.notebook.example.com.kpproject6.module.category.CategoriesFragment;
import adam.notebook.example.com.kpproject6.module.trending.TrendingFragment;

public class TablayoutActivity extends AppCompatActivity{
    private LinearLayout bottomsheetlayout;
    private BottomSheetBehavior mbottomsheetbehavior;
    private FloatingActionButton floatingActionButton;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TextView tvJudul;
    private Typeface typeface_tablayout;
    private int [] IconsTab = {
            R.drawable.ic_action_cart,
            R.drawable.ic_add_shopping_cart,
//            R.drawable.ic_action_user
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablayout);
        TextView tvJudul = (TextView)findViewById(R.id.judultablayout);
        typeface_tablayout = Typeface.createFromAsset(this.getAssets(),"fonts/aliens and cows_trial.ttf");
        tvJudul.setTypeface(typeface_tablayout);
        bottomsheetlayout = (LinearLayout) findViewById(R.id.include);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab_menu);
        mbottomsheetbehavior = BottomSheetBehavior.from(bottomsheetlayout);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mbottomsheetbehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                new BottomSheetdialog_tablayout().show(getSupportFragmentManager(), "Dialog");

            }
        });

        // set callback for changes
        mbottomsheetbehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                    mbottomsheetbehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                }
                else if (newState == BottomSheetBehavior.STATE_DRAGGING){
                    mbottomsheetbehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                }
            }
            @Override
            public void onSlide(@NonNull View view, float v) {

            }
        });
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        setupIconTab();
    }

    private void setupIconTab() {
        tabLayout.getTabAt(0).setIcon(IconsTab[0]);
        tabLayout.getTabAt(1).setIcon(IconsTab[1]);
//        tabLayout.getTabAt(2).setIcon(IconsTab[2]);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new CategoriesFragment(), "Categories");
        adapter.addFragment(new TrendingFragment(), "Trending");
//        adapter.addFragment(new UpcomingFragment(), "Upcoming");
        viewPager.setAdapter(adapter);
    }

//    @Override
//    public void onClick(View v) {
//        bottomsheetlayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                switch (v.getRight()){
//                    case R.id.keeplayout:
//                        Intent a = new Intent(TablayoutActivity.this, ProfileActivity.class);
//                        startActivity(a);
//                }
//            }
//        });
//    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
//            return mFragmentTitleList.get(position);
            return null;
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    @Override
    public void onBackPressed() {
        ExitApp();
        //super.onBackPressed();
    }

    private void ExitApp() {
        new AlertDialog.Builder(TablayoutActivity.this)
                .setTitle(getString(R.string.app_name))
                .setMessage(getString(R.string.exit_msg))
//                .setIcon(R.mipmap.ic_launcherbr)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .show();
    }
}

