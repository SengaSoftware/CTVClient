package org.vipserv.jacwro.ctvclient;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.Wsdl2Code.WebServices.CTVBackendService.CTVBackendService;
import com.Wsdl2Code.WebServices.CTVBackendService.VectortvOffer;
import com.Wsdl2Code.WebServices.CTVBackendService.WS_Enums;
import com.Wsdl2Code.WebServices.CTVBackendService.tvOffer;

import java.util.ArrayList;
import java.util.Arrays;
import android.view.Menu;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    private ListView listView ;
    private ArrayAdapter<String> adapter ;
    private MainListAdapter adapter1;
    private CTVBranchService serviceEventHandler;
    private ArrayList<tvOffer> offerList;

    public ProgressDialog mDialog;
    static {
        System.loadLibrary("native-lib");
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list_view);
        serviceEventHandler = new CTVBranchService();
        offerList = new ArrayList<tvOffer>();
        serviceEventHandler.list = offerList;
        serviceEventHandler.activity = this;

        mDialog = new ProgressDialog(getApplicationContext());
        mDialog.setMessage("Please wait...");
        mDialog.setCancelable(false);

        listView = (ListView) findViewById(R.id.offersListView);

        adapter1 = new MainListAdapter(this, offerList);


        listView.setAdapter(adapter1);
        reloadData();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                intent.putExtra("ITEM_ID", position);
                intent.putExtra("tvOffer", offerList.get(position));
                startActivity(intent);
            }
        });




        //System.out.println("zwrocono rekordow"+result.size());

        // Example of a call to a native method
//        TextView tv = (TextView) findViewById(R.id.sample_text);
//        tv.setText(stringFromJNI());
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
//    public native String stringFromJNI();

    public void refreshView()
    {
        adapter1.notifyDataSetChanged();
    }

    private void reloadData()
    {
        CTVBackendService service = new CTVBackendService();
        service.eventHandler = serviceEventHandler;
        service.soapVersion = WS_Enums.SoapProtocolVersion.Soap12;
        service.setTimeOut(30);
        //VectortvOffer result = service.getOffers();
        try {
            service.getOffersAsync();
        }catch (Exception e)
        {
            serviceEventHandler.showError(e.getMessage());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.reloadMenuButton:
                // User chose the "Settings" item, show the app settings UI...
                reloadData();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    public void showBussy()
    {
        mDialog = new ProgressDialog(this);
        mDialog.setTitle("Loading");
        mDialog.setMessage("Wait while loading...");
        mDialog.setCancelable(false); // disable dismiss by tapping outside of the dialog
        mDialog.show();
    }

    public void dismissBussy()
    {
        mDialog.dismiss();
    }
}
