package hcmue.congvu.drlstudent.Controller.CurrentClassController;

import android.content.Context;

import hcmue.congvu.drlstudent.View.CurrentClassView.ViewProcessCurrentClass;

/**
 * Created by CongVu on 11/09/2018.
 */
public class ControllerLogicProcessCurrentClass implements ControllerImpProcessCurrentClass {
    Context context;
    ViewProcessCurrentClass viewProcessCurrentClass;

    public ControllerLogicProcessCurrentClass(Context context, ViewProcessCurrentClass viewProcessCurrentClass) {
        this.context = context;
        this.viewProcessCurrentClass = viewProcessCurrentClass;
    }

    @Override
    public void getCurrentClassList(final int idUser) {

    }
}
