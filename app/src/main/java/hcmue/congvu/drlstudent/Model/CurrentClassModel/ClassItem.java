package hcmue.congvu.drlstudent.Model.CurrentClassModel;

/**
 * Created by CongVu on 11/09/2018.
 */
public class ClassItem {
    private String mClassName;
    private int mIdClass;
    private int mNumberStudent;
    private String mClassImg;

    public ClassItem() {
    }

    public ClassItem(String mClassName, int mIdClass, int mNumberStudent, String mClassImg) {
        this.mClassName = mClassName;
        this.mIdClass = mIdClass;
        this.mNumberStudent = mNumberStudent;
        this.mClassImg = mClassImg;
    }

    public String getmClassName() {
        return mClassName;
    }

    public void setmClassName(String mClassName) {
        this.mClassName = mClassName;
    }

    public int getmIdClass() {
        return mIdClass;
    }

    public void setmIdClass(int mIdClass) {
        this.mIdClass = mIdClass;
    }

    public int getmNumberStudent() {
        return mNumberStudent;
    }

    public void setmNumberStudent(int mNumberStudent) {
        this.mNumberStudent = mNumberStudent;
    }

    public String getmClassImg() {
        return mClassImg;
    }

    public void setmClassImg(String mClassImg) {
        this.mClassImg = mClassImg;
    }
}
