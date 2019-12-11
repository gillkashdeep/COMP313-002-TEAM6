package com.example.isitraining;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class AdminAccountActivity extends AppCompatActivity {
    JSONObject jso;
    JSONArray jsa;
    TextView Feedback;
    private static final String Feedback_Request_URL = "https://isitraining.000webhostapp.com/Retrive_Feedback.php";
    InputStream is;
    String line;
    String result;
    String[] data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_account);

        // set toolbar title
        Toolbar tbAdminGetFeedback = findViewById(R.id.tbAdminGetFeedback);
        setSupportActionBar(tbAdminGetFeedback);
        getSupportActionBar().setTitle("All Feedback");

        Feedback = findViewById(R.id.txtViewFeedback);

        StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder().permitNetwork().build()));

        getData();

    }

    // add menu to toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu_toobar_admin_use, menu);

        return true;
    }

    // menu item onclick to different activity
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.goToHomePageWithoutLoginAdmin:
                Intent intentHome = new Intent(this,HomeActivity.class);
                startActivity(intentHome);
                break;
            case R.id.goToWarning:
                Intent intentWarning = new Intent(this,WarningActivity.class);
                startActivity(intentWarning);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    private void getData()
    {
        try
        {
            URL url = new URL(Feedback_Request_URL);
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();

            con.setRequestMethod("GET");

            is = new BufferedInputStream(con.getInputStream());

            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();

            while((line = br.readLine()) != null )
            {
                sb.append(line + "\n");
            }

            result = sb.toString();
            Toast.makeText(this, "data retrieve: " + result, Toast.LENGTH_SHORT).show();
            Feedback.setText(result);

//            jsa = new JSONArray(result);
//
//            data = new String[jsa.length()];
//
//            Toast.makeText(this, "jsa length: ", Toast.LENGTH_SHORT).show();
//
//            for(int i = 0; i < jsa.length(); i++)
//            {
//                jso = jsa.getJSONObject(i);
//                data[i] = jso.getString("User_name" + "\n" + "Feedback_Desc");
//            }
//
//            for(int i = 0; i < jsa.length(); i++)
//            {
//                Feedback.setText(jsa.length());
//
//            }

            is.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        try
        {

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
