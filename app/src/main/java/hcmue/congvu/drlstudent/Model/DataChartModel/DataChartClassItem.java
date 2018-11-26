package hcmue.congvu.drlstudent.Model.DataChartModel;

/**
 * Created by CongVu on 26/11/2018.
 */
public class DataChartClassItem {
    int idClass;
    String className;
    String[] classTerm;
    int[] scoresClassTerm;

    public DataChartClassItem() {
    }

    public DataChartClassItem(int idClass, String className, String[] classTerm, int[] scoresClassTerm) {
        this.idClass = idClass;
        this.className = className;
        this.classTerm = classTerm;
        this.scoresClassTerm = scoresClassTerm;
    }

    public int getIdClass() {
        return idClass;
    }

    public void setIdClass(int idClass) {
        this.idClass = idClass;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String[] getClassTerm() {
        return classTerm;
    }

    public void setClassTerm(String[] classTerm) {
        this.classTerm = classTerm;
    }

    public int[] getScoresClassTerm() {
        return scoresClassTerm;
    }

    public void setScoresClassTerm(int[] scoresClassTerm) {
        this.scoresClassTerm = scoresClassTerm;
    }
}
