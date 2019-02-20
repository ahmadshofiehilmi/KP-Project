package adam.notebook.example.com.kpproject6.module.category;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


import adam.notebook.example.com.kpproject6.R;
import ss.com.bannerslider.Slider;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoriesFragment extends Fragment {
    private Context mContext ;
    private List<Categories> lstBook ;
    private Slider slider;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_categories, container, false);
       Slider.init(new PicassoImageLoadingService(getContext()));
       slider = view.findViewById(R.id.banner_slider1);
       slider.setAdapter(new MainSliderAdapter());


        RecyclerView myrv = (RecyclerView)view.findViewById(R.id.recyclerview_id);
        myrv.setLayoutManager(new GridLayoutManager(getActivity(),3,GridLayoutManager.VERTICAL,false));
        myrv.setItemAnimator(new DefaultItemAnimator());
        lstBook = new ArrayList<>();
        lstBook.add(new Categories("Battery","Categorie Book","Description book",R.drawable.battery));
        lstBook.add(new Categories("CPU","Categorie Book","Description book",R.drawable.cpu));
        lstBook.add(new Categories("HDMI","Categorie Book","Description book",R.drawable.hdmi));
        lstBook.add(new Categories("Headphone","Categorie Book","Description book",R.drawable.headphone));
        lstBook.add(new Categories("Keyboard","Categorie Book","Description book",R.drawable.keyboard));
        lstBook.add(new Categories("Laptop","Categorie Book","Description book",R.drawable.laptop));
        lstBook.add(new Categories("Motherboard","Categorie Book","Description book",R.drawable.motherboard));
        lstBook.add(new Categories("Monitor","Categorie Book","Description book",R.drawable.monitor));
        lstBook.add(new Categories("Mouse","Categorie Book","Description book",R.drawable.mouse));
        lstBook.add(new Categories("PowerCable","Categorie Book","Description book",R.drawable.powercable));
        lstBook.add(new Categories("Printer","Categorie Book","Description book",R.drawable.printer));
        lstBook.add(new Categories("Processor","Categorie Book","Description book",R.drawable.chip));

        CategoriesAdapter myAdapter = new CategoriesAdapter(getContext(),lstBook);
        myrv.setAdapter(myAdapter);
        return view;
    }
}
