package com.colinrob.tipcalculator;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;


public class MainActivity extends ActionBarActivity {

   private static final String TOTAL_BILL = "TOTAL_BILL";
    private static final String CURRENT_TIP = "CURRENT_TIP";
    private static final String BILL_WITHOUT_TIP = "BILL_WITHOUT_TIP";

    private double billBeforeTip;
    private double tipAmount;
    private double finalBill;

    EditText billBeforeTipET;
    EditText tipAmountET;
    EditText finalBillET;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null) {
            billBeforeTip = 0.0;
            tipAmount = .15;
            finalBill = 0.0;
        } else {
            billBeforeTip = savedInstanceState.getDouble(TOTAL_BILL);
            tipAmount = savedInstanceState.getDouble(CURRENT_TIP);
            finalBill = savedInstanceState.getDouble(BILL_WITHOUT_TIP);
        }

        billBeforeTipET = (EditText)findViewById(R.id.editTextBill);
        tipAmountET = (EditText)findViewById(R.id.editTextTip);
        finalBillET = (EditText)findViewById(R.id.editTextFinal);

        billBeforeTipET.addTextChangedListener(billBeforeTipListener);
    }

    private TextWatcher billBeforeTipListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try
            {
                billBeforeTip = Double.parseDouble(s.toString());
            }
            catch (NumberFormatException e)
            {
                billBeforeTip = 0.0;
            }

            updateTipAndFinalBill();
        }

        private void updateTipAndFinalBill()
        {
            double tipAmount = Double.parseDouble(billBeforeTipET.getText().toString());

            double finalBillTemp = billBeforeTip + (billBeforeTip * tipAmount);

            finalBillET.setText(String.format("%.02", finalBillTemp));
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };¬

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
