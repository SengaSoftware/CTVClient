package org.vipserv.jacwro.ctvclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.Wsdl2Code.WebServices.CTVBackendService.tvOffer;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Intent i=this.getIntent();
        //RECEIVE DATA
        Integer itemId=i.getExtras().getInt("ITEM_ID");
        tvOffer offer = (tvOffer) i.getSerializableExtra("tvOffer");
        System.out.println("wybrano: "+itemId);
        System.out.println("name: "+offer.offerName);
        System.out.println("description: "+offer.offerDescription);
        System.out.println("currency: "+offer.offerCurrency);
        System.out.println("activation price: "+offer.offerActivationPrice);
        System.out.println("monthly cost: "+offer.offerMonthlyPrice);
        System.out.println("months: "+offer.offerMonthlyLength);

        TextView text1 = (TextView) findViewById(R.id.nameTextView);
        text1.setText(offer.offerName);
        TextView text2 = (TextView) findViewById(R.id.descriptionTextView);
        text2.setText(offer.offerDescription);
        TextView text3 = (TextView) findViewById(R.id.activationTextView);
        text3.setText(String.valueOf(offer.offerActivationPrice));
        TextView text4 = (TextView) findViewById(R.id.monhlyFeeTextView);
        text4.setText(String.valueOf(offer.offerMonthlyPrice));
        TextView text5 = (TextView) findViewById(R.id.monthsTextView);
        text5.setText(String.valueOf(offer.offerMonthlyLength));
    }
}
