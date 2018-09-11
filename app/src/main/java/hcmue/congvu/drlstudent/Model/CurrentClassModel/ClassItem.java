package hcmue.congvu.drlstudent.Model.CurrentClassModel;

/**
 * Created by CongVu on 11/09/2018.
 */
public class ClassItem {
    private String mClassName;
    private int mIdClass;
    private String mClassImg;

    public ClassItem(String mClassName, int mIdClass, String mClassImg) {
        this.mClassName = mClassName;
        this.mIdClass = mIdClass;
        this.mClassImg = mClassImg;
    }

    public ClassItem(){

    }

    public String getmClassName() {
        return mClassName;
    }

    public int getmIdClass() {
        return mIdClass;
    }

    public String getmClassImg() {
        return mClassImg;
    }

    public void setmClassName(String mClassName) {
        this.mClassName = mClassName;
    }

    public void setmIdClass(int mIdClass) {
        this.mIdClass = mIdClass;
    }

    public void setmClassImg(String mClassImg) {
        this.mClassImg = mClassImg;
    }
}
