package hcmue.congvu.drlstudent.Model.CurrentClassModel;

/**
 * Created by CongVu on 11/09/2018.
 */
public class ClassItem {
    private String mClassName;
    private int mIdClass;
    private int mNumberStudent;
    private String mClassImg;
    private boolean mIsAdmin;
    private int mTypeUserClass;

    public ClassItem() {
    }

    public ClassItem(String mClassName, int mIdClass, int mNumberStudent, String mClassImg, boolean mIsAdmin, int mTypeUserClass) {
        this.mClassName = mClassName;
        this.mIdClass = mIdClass;
        this.mNumberStudent = mNumberStudent;
        this.mClassImg = mClassImg;
        this.mIsAdmin = mIsAdmin;
        this.mTypeUserClass = mTypeUserClass;
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

    public boolean ismIsAdmin() {
        return mIsAdmin;
    }

    public void setmIsAdmin(boolean mIsAdmin) {
        this.mIsAdmin = mIsAdmin;
    }

    public int getmTypeUserClass() {
        return mTypeUserClass;
    }

    public void setmTypeUserClass(int mTypeUserClass) {
        this.mTypeUserClass = mTypeUserClass;
    }
}
