package com.example.scorey3.uicctest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import android.telephony.TelephonyManager;
import android.content.Context;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String rApdu = tm.iccTransmitApduBasicChannel(0x00, 0xC0, 0x00, 0x00, 0x00, "");
        Log.d("UICCTest",rApdu);
        String filePath = "foo";
        int fileID = 0x6FAD;
        byte[] cipherInd = tm.iccExchangeSimIO(fileID,176,0,0,0,filePath);
        Log.d("UICCTest", String.format("Return length: 0x%20x", cipherInd.length));
        for (byte b: cipherInd){
            Log.d("UICCTest", String.format("0x%20x", b));
        }
    }

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
