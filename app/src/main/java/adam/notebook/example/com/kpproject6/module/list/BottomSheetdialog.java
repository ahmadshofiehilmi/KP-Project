package adam.notebook.example.com.kpproject6.module.list;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;
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
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class BottomSheetdialog extends BottomSheetDialogFragment {
    @BindView(R.id.battery)LinearLayout L1;
    @BindView(R.id.cpu)LinearLayout L2;
    @BindView(R.id.hdmi)LinearLayout L3;
    @BindView(R.id.headphone)LinearLayout L4;
    @BindView(R.id.keyboard)LinearLayout L5;
    @BindView(R.id.laptop)LinearLayout L6;
    @BindView(R.id.mobo)LinearLayout L7;
    @BindView(R.id.monitor)LinearLayout L8;
    @BindView(R.id.mouse)LinearLayout L9;
    @BindView(R.id.powercable)LinearLayout L10;
    @BindView(R.id.printer)LinearLayout L11;
    @BindView(R.id.processor)LinearLayout L12;


    public BottomSheetdialog() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottomsheet_list, container, false);
        ButterKnife.bind(this,view);
        return  view;
    }
    @OnClick({R.id.battery,R.id.cpu,R.id.hdmi,R.id.headphone,R.id.keyboard,R.id.laptop,R.id.mobo,R.id.monitor,
            R.id.mouse,R.id.powercable,R.id.processor})
    public void onClick(View view){
        switch(view.getId()){
            case R.id.batre:
                startActivity(new Intent(getActivity(), BatteryCategory.class));
                Toast.makeText(getActivity(),"Battery Section",Toast.LENGTH_SHORT).show();
                dismiss();
                break;
            case R.id.cpu:
                startActivity(new Intent(getActivity(), CpuCategory.class));
                Toast.makeText(getActivity(),"Cpu Section",Toast.LENGTH_SHORT).show();
                dismiss();
                break;
            case R.id.hdmi:
                startActivity(new Intent(getActivity(), HdmiCategory.class));
                Toast.makeText(getActivity(),"Headphone Section",Toast.LENGTH_SHORT).show();
                dismiss();
                break;
            case R.id.headphone:
                startActivity(new Intent(getActivity(), HeadphoneCategory.class));
                Toast.makeText(getActivity(),"Headphone Section",Toast.LENGTH_SHORT).show();
                dismiss();
                break;
            case R.id.keyboard:
                startActivity(new Intent(getActivity(), KeyboardCategory.class));
                Toast.makeText(getActivity(),"Keyboard Section",Toast.LENGTH_SHORT).show();
                dismiss();
                break;
            case R.id.laptop:
                startActivity(new Intent(getActivity(), LaptopCategory.class));
                Toast.makeText(getActivity(),"Laptop Section",Toast.LENGTH_SHORT).show();
                dismiss();
                break;
            case R.id.mobo:
                startActivity(new Intent(getActivity(), MoboCategory.class));
                Toast.makeText(getActivity(),"Motherboad Section",Toast.LENGTH_SHORT).show();
                dismiss();
                break;
            case R.id.monitor:
                startActivity(new Intent(getActivity(), MonitorCategory.class));
                Toast.makeText(getActivity(),"Monitor Section",Toast.LENGTH_SHORT).show();
                dismiss();
                break;
            case R.id.mouse:
                startActivity(new Intent(getActivity(), MouseCategory.class));
                Toast.makeText(getActivity(),"Mouse Section",Toast.LENGTH_SHORT).show();
                dismiss();
                break;
            case R.id.powercable:
                startActivity(new Intent(getActivity(), PowerCableCategory.class));
                Toast.makeText(getActivity(),"Powercable Section",Toast.LENGTH_SHORT).show();
                dismiss();
                break;
            case R.id.printer:
                startActivity(new Intent(getActivity(), PrinterCategory.class));
                Toast.makeText(getActivity(),"Printer Section",Toast.LENGTH_SHORT).show();
                dismiss();
                break;
            case R.id.processor:
                startActivity(new Intent(getActivity(), ProcessorCategory.class));
                Toast.makeText(getActivity(),"Processor Section",Toast.LENGTH_SHORT).show();
                dismiss();
                break;
        }
    }
}

