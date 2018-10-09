package hcmue.congvu.drlstudent.Controller.ActivityClassDetailController;

import android.content.Context;
import android.view.View;

import hcmue.congvu.drlstudent.AppConstants.AppUrl;
import hcmue.congvu.drlstudent.View.ActivityClassDetailView.ViewProcessActivityClassDetail;

/**
 * Created by CongVu on 09/10/2018.
 */
public class ControllerLogicProcessActivityClassDetail extends AppUrl implements ControllerImpProcessActivityClassDetail {
    Context context;
    ViewProcessActivityClassDetail viewProcessActivityClassDetail;

    public ControllerLogicProcessActivityClassDetail(Context context, ViewProcessActivityClassDetail viewProcessActivityClassDetail) {
        this.context = context;
        this.viewProcessActivityClassDetail = viewProcessActivityClassDetail;
    }

    @Override
    public void getDataListClassDetail(int idClass, int idClassDetail) {

    }
}
