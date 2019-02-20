package adam.notebook.example.com.kpproject6.module.favorit;

import java.io.Serializable;

public class FavoritModel implements Serializable {

    private  String mName;
    private int mImage;
    private int mAmount;

    public FavoritModel(String mName, int mAmount, int mImage) {
        this.mName = mName;
        this.mImage = mImage;
        this.mAmount = mAmount;
    }

    public String getmName() {
        return mName;
    }

    public int getmImage() {return mImage; }

    public int getmAmount() {
        return mAmount;
    }


}