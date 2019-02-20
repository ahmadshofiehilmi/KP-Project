package adam.notebook.example.com.kpproject6;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import adam.notebook.example.com.kpproject6.module.favorit.FavoritActivity;
import adam.notebook.example.com.kpproject6.module.historyorder.HistoryOrder;
import adam.notebook.example.com.kpproject6.module.list.DetailActivity;
import adam.notebook.example.com.kpproject6.module.profile.ProfileActivity;


public class BottomSheetdialog_tablayout extends BottomSheetDialogFragment {
    public BottomSheetdialog_tablayout(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottomsheet, container, false);
        LinearLayout L1= (LinearLayout)view.findViewById(R.id.keeplayout);
        L1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ProfileActivity.class));
            }
        });
        LinearLayout L2 = (LinearLayout)view.findViewById(R.id.emaillayout);
        L2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), HistoryOrder.class));
            }
        });
        LinearLayout L3= (LinearLayout)view.findViewById(R.id.hangoutslayout);
        L3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), DetailActivity.class));
            }
        });

        LinearLayout L4 = (LinearLayout)view.findViewById(R.id.googlepluslayout);
        L4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), FavoritActivity.class));
            }
        });
        return  view;
    }
}
