package hcmue.congvu.drlstudent.Controller.LogInController;

import android.content.Context;

import hcmue.congvu.drlstudent.Model.LogInModel.ModelProcessLogIn;
import hcmue.congvu.drlstudent.View.LogInView.ViewProcessLogIn;

/**
 * Created by CongVu on 22/08/2018.
 */
public class ControllerLogicProcessLogIn implements ControllerImpProcessLogIn {
    Context context;
    ViewProcessLogIn viewProcessLogIn;

    public ControllerLogicProcessLogIn(Context context, ViewProcessLogIn viewProcessLogIn){
        this.context = context;
        this.viewProcessLogIn = viewProcessLogIn;
    }

    @Override
    public void checkLogIn(String username, String password) {
        ModelProcessLogIn modelProcessLogIn = new ModelProcessLogIn(context, username, password);
        String check = modelProcessLogIn.CheckLogInDB();

        if(check.equals("")){
            viewProcessLogIn.logInFail();
        }
        else{
            viewProcessLogIn.logInSuccessfull(check);
        }
        /*
        if(check){
            viewProcessLogIn.LogInSuccessfull();
        }
        else{
            viewProcessLogIn.LogInFail();
        }*/
    }
}
