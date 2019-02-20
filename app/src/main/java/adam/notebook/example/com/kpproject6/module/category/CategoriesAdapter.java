package adam.notebook.example.com.kpproject6.module.category;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import adam.notebook.example.com.kpproject6.module.list.CategoryBattery.BatteryCategory;
import adam.notebook.example.com.kpproject6.module.list.CategoryCpu.CpuCategory;
import adam.notebook.example.com.kpproject6.module.list.CategoryHdmi.HdmiCategory;
import adam.notebook.example.com.kpproject6.module.list.CategoryHeadhphone.HeadphoneCategory;
import adam.notebook.example.com.kpproject6.module.list.CategoryKeyboard.KeyboardCategory;
import adam.notebook.example.com.kpproject6.module.list.CategoryLaptop.LaptopCategory;
import adam.notebook.example.com.kpproject6.module.list.CategoryMobo.MoboCategory;
import adam.notebook.example.com.kpproject6.module.list.CategoryMonitor.MonitorCategory;
import adam.notebook.example.com.kpproject6.module.list.CategoryMouse.MouseCategory;
import adam.notebook.example.com.kpproject6.module.list.CategoryPowerCable.PowerCableCategory;
import adam.notebook.example.com.kpproject6.module.list.CategoryPrinter.PrinterCategory;
import adam.notebook.example.com.kpproject6.module.list.CategoryProcessor.ProcessorCategory;
import adam.notebook.example.com.kpproject6.R;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.MyViewHolder> {

    private Context mContext ;
    private List<Categories> mData ;


    public CategoriesAdapter(Context mContext, List<Categories> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.categories_item_book,parent,false);
        return new MyViewHolder(view);


    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.tv_book_title.setText(mData.get(position).getTitle());
        holder.img_book_thumbnail.setImageResource(mData.get(position).getThumbnail());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (position){
                    case 0:
                        Intent intent = new Intent(mContext, BatteryCategory.class);
                        mContext.startActivity(intent);
                        break;

                    case 1:
                        Intent intent1 = new Intent(mContext, CpuCategory.class);
                        mContext.startActivity(intent1);
                        break;

                    case 2:
                        Intent intent2 = new Intent (mContext, HdmiCategory.class);
                        mContext.startActivity(intent2);
                        break;

                    case 3:
                        Intent intent3 = new Intent(mContext, HeadphoneCategory.class);
                        mContext.startActivity(intent3);
                        break;

                    case 4:
                        Intent intent4 = new Intent(mContext, KeyboardCategory.class);
                        mContext.startActivity(intent4);
                        break;

                    case 5:
                        Intent intent5 = new Intent(mContext, LaptopCategory.class);
                        mContext.startActivity(intent5);
                        break;

                    case 6:
                        Intent intent6 = new Intent(mContext, MoboCategory.class);
                        mContext.startActivity(intent6);
                        break;

                    case 7:
                        Intent intent7 = new Intent(mContext, MonitorCategory.class);
                        mContext.startActivity(intent7);
                        break;

                    case 8:
                        Intent intent8 = new Intent(mContext, MouseCategory.class);
                        mContext.startActivity(intent8);
                        break;

                    case 9:
                        Intent intent9 = new Intent(mContext, PowerCableCategory.class);
                        mContext.startActivity(intent9);
                        break;

                    case 10:
                        Intent intent10 = new Intent(mContext, PrinterCategory.class);
                        mContext.startActivity(intent10);
                        break;

                    case 11:
                        Intent intent11 = new Intent(mContext, ProcessorCategory.class);
                        mContext.startActivity(intent11);
                        break;
                }

//                Intent intent = new Intent(mContext,Categories_Activity.class);
//
//                // passing data to the book activity
//                intent.putExtra("Title",mData.get(position).getTitle());
//                intent.putExtra("Description",mData.get(position).getDescription());
//                intent.putExtra("Thumbnail",mData.get(position).getThumbnail());
//                // start the activity
//                mContext.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_book_title;
        ImageView img_book_thumbnail;
        CardView cardView ;

        public MyViewHolder(View itemView) {
            super(itemView);

            tv_book_title = (TextView) itemView.findViewById(R.id.book_title_id) ;
            img_book_thumbnail = (ImageView) itemView.findViewById(R.id.book_img_id);
            cardView = (CardView) itemView.findViewById(R.id.cardview_id);


        }
    }


}

