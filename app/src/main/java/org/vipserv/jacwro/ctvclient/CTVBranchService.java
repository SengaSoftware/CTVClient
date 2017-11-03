package org.vipserv.jacwro.ctvclient;

import android.app.AlertDialog;
import android.content.DialogInterface;

import com.Wsdl2Code.WebServices.CTVBackendService.CTVBackendService;
import com.Wsdl2Code.WebServices.CTVBackendService.IWsdl2CodeEvents;
import com.Wsdl2Code.WebServices.CTVBackendService.VectortvOffer;
import com.Wsdl2Code.WebServices.CTVBackendService.tvOffer;

import java.util.ArrayList;

/**
 * Created by ejacwro on 2017-11-02.
 */

public class CTVBranchService implements IWsdl2CodeEvents {

    public ArrayList<tvOffer> list;
    public MainActivity activity;
    @Override
    public void Wsdl2CodeStartedRequest() {
        System.out.println("Koniec Wsdl2CodeStartedRequest");
        activity.showBussy();
    }

    @Override
    public void Wsdl2CodeFinished(String methodName, Object Data) {
        System.out.println("Koniec Wsdl2CodeFinished");
        VectortvOffer result = (VectortvOffer)Data;
        if(list != null) {
            list.clear();
            for (int i = 0; i < result.size(); i++) {
                list.add(result.get(i));
            }
        }
        if(activity != null)
        {
            activity.refreshView();
        }
        System.out.println(result.size());
    }

    @Override
    public void Wsdl2CodeFinishedWithException(Exception ex) {
        System.out.println("Koniec requestu z bledem"+ex.getMessage());
        if(list != null) {
            list.clear();
        }
        if(activity != null)
        {
            activity.refreshView();
        }
        this.showError(ex.getMessage());
    }

    @Override
    public void Wsdl2CodeEndedRequest() {
    System.out.println("Koniec requestu");
    activity.dismissBussy();
    }

    public void showError(String errorTxt)
    {
        AlertDialog alertDialog = new AlertDialog.Builder(activity).create();
        alertDialog.setTitle("Błąd połączenia z serwerem");
        alertDialog.setMessage(errorTxt);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
}
