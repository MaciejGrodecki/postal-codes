package com.example.macie_000.miejscowosci;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {
    private TextView txtInfo;
    private EditText etxtPostalCode;
    private ListView mainList;

    private String address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtInfo = (TextView)findViewById(R.id.txtInfo);
        etxtPostalCode = (EditText)findViewById(R.id.etxtPostalCode);
        mainList = (ListView)findViewById(R.id.mainList);

        etxtPostalCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etxtPostalCode.length() > 1) {
                    address = "http://api.geonames.org/postalCodeLookupJSON?postalcode=" +
                            s.toString() + "&country=PL&username=demo";
                    LoadInfo();
                }
            }
        });

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

    public void LoadInfo(){
        class MyThread extends AsyncTask<String, Void, ArrayList<Geonames> >{
            @Override
            protected ArrayList<Geonames> doInBackground(String... urls){
                try
                {
                    return Data.Get(urls[0]);
                }
                catch(Exception e){
                    e.printStackTrace();
                }
                return null;
            }
            @Override
            protected void onPostExecute(ArrayList<Geonames> geonamesArrayList){
            ArrayList<String> list = new ArrayList<String>();
                for(int i=0;i<geonamesArrayList.size();i++) {
                    list.add(geonamesArrayList.get(i).getPostalCode() + "     " +
                            geonamesArrayList.get(i).getCity() + "    " + geonamesArrayList.get(i).getRegion());
                }

                ArrayAdapter<String> mainArrayAdapter = new ArrayAdapter<String>(
                        MainActivity.this,android.R.layout.simple_list_item_1,list);
                mainList.setAdapter(mainArrayAdapter);

                mainList.setAdapter(mainArrayAdapter);
            }


        }
        new MyThread().execute(address);
    }


}

