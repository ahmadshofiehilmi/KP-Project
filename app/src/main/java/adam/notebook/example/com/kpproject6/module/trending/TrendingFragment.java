package adam.notebook.example.com.kpproject6.module.trending;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import adam.notebook.example.com.kpproject6.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TrendingFragment extends Fragment {

    private Context mContext ;
    private List<Trending> lstBook ;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_trending, container, false);

        RecyclerView myrv_trending = (RecyclerView)view.findViewById(R.id.recyclerview_id_trending);
//        myrv_trending.setLayoutManager(new GridLayoutManager(getActivity(),2,GridLayoutManager.VERTICAL,false));
        myrv_trending.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        myrv_trending.setItemAnimator(new DefaultItemAnimator());


        lstBook = new ArrayList<>();
        lstBook.add(new Trending("The Vegitarian","Categorie Book","Description book",R.drawable.produkbattery));
        lstBook.add(new Trending("The Wild Robot","Categorie Book","Description book",R.drawable.produkcable));
        lstBook.add(new Trending("Maria Semples","Categorie Book","Description book",R.drawable.produkcerberus_keyboard));
        lstBook.add(new Trending("The Martian","Categorie Book","Description book",R.drawable.produkcpu));
        lstBook.add(new Trending("He Died with...","Categorie Book","Description book",R.drawable.produkhdmi));
        lstBook.add(new Trending("The Vegitarian","Categorie Book","Description book",R.drawable.produkheadphone));
        lstBook.add(new Trending("The Wild Robot","Categorie Book","Description book",R.drawable.produkheadset));
        lstBook.add(new Trending("Maria Semples","Categorie Book","Description book",R.drawable.produkkeyboardasus));
        lstBook.add(new Trending("The Martian","Categorie Book","Description book",R.drawable.produkkomputermsi));
        lstBook.add(new Trending("He Died with...","Categorie Book","Description book",R.drawable.produkbattery));
        lstBook.add(new Trending("The Vegitarian","Categorie Book","Description book",R.drawable.produkmonitorasus));
        lstBook.add(new Trending("The Wild Robot","Categorie Book","Description book",R.drawable.produkprinter));
        lstBook.add(new Trending("Maria Semples","Categorie Book","Description book",R.drawable.produkmonitorasus));
        lstBook.add(new Trending("The Martian","Categorie Book","Description book",R.drawable.produkprocessorinter));
        lstBook.add(new Trending("He Died with...","Categorie Book","Description book",R.drawable.produkcable));

        TrendingAdapter myAdapter = new TrendingAdapter(getContext(),lstBook);
        myrv_trending.setAdapter(myAdapter);

//        SpacesItemDecoration decoration = new SpacesItemDecoration(8);
//        myrv_trending.addItemDecoration(decoration);
        return view;



    }

}
