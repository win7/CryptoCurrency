package com.festeam.win7.cryptocurrency;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.festeam.win7.cryptocurrency.Utils.AdapterItems;
import com.festeam.win7.cryptocurrency.Utils.CCryto;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class MainActivity extends AppCompatActivity {

    OkHttpClient client = new OkHttpClient();
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



                Get("https://api.coinmarketcap.com/v1/ticker/", new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        // Something went wrong
                        Toast.makeText(getApplicationContext(), "¡Verifique Conexión A Internet!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final ArrayList<CCryto> list_cryto = new ArrayList<CCryto>();
                        final RecyclerView[] rv_list_cryto = new RecyclerView[1];
                        final RecyclerView.LayoutManager[] rv_layout_manager = new RecyclerView.LayoutManager[1];
                        CCryto cc = null;
                        JSONObject object_json = null;

                        if (response.isSuccessful()) {
                            String response_json = response.body().string();
                            try {
                                final JSONArray array_json = new JSONArray(response_json);
                                for (int k = 0; k < array_json.length(); k++){
                                    object_json = (JSONObject) array_json.get(k);
                                    cc = new CCryto(object_json.getString("name") + "|" + object_json.getString("symbol"),
                                            "$" + object_json.getString("price_usd"),
                                            object_json.getString("percent_change_24h"),
                                            object_json.getString("percent_change_7d"),
                                            object_json.getString("symbol"));
                                    list_cryto.add(cc);
                                }
                                MainActivity.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        //Handle UI here
                                        rv_list_cryto[0] = (RecyclerView) findViewById(R.id.items_crypto);
                                        rv_layout_manager[0] = new LinearLayoutManager(getApplication().getApplicationContext());
                                        rv_list_cryto[0].setLayoutManager(rv_layout_manager[0]);

                                        AdapterItems adapter_items = new AdapterItems(getApplication().getApplicationContext(), list_cryto);
                                        rv_list_cryto[0].setAdapter(adapter_items);
                                    }
                                });
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        } else {
                            Toast.makeText(getApplicationContext(), "¡Verifique Conexión A Internet!", Toast.LENGTH_SHORT).show();
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
        // Handle action bar items clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    Call Get(String url, Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = client.newCall(request);
        call.enqueue(callback);
        return call;
    }
}
