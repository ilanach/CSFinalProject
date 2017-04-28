package cs.finalproject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.StrictMode;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // StrictMode.enableDefaults(); //STRICT MODE ENABLED

    }


    public void ChangeToRegistrationActivity(View v) {
        Intent intent = new Intent(this, RegActivity.class);
        startActivity(intent);
    }

    public void ChangeToStartActivity(View v) {
        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
    }

    class MyAsyncTask extends AsyncTask<String, Integer, String> {

        public MyAsyncTask() {
// TODO Auto-generated constructor stub
        }

        @Override
        protected String doInBackground(String... params) {
            //pb.setVisibility(View.GONE);
            StringBuffer chaine = new StringBuffer("");
            String result = "";

            InputStream isr = null;

            try {

                URL url = new URL("http://ronandilanaapp.freeiz.com/Myfilw.php");
                //URL url = new URL("http://192.168.56.1/Users/Ilana/AndroidStudioProjects/CSFinalProject/FinalProject/app/src/main/res/Myfilw.php");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setConnectTimeout(1000);
                connection.setRequestProperty("User-Agent", "");
                connection.setRequestMethod("GET");
                // connection.setDoInput(true);
                //             connection.connect();

                // HttpPost httppost = new HttpPost("http://ieeehiit.host22.com/myfile.php"); //YOUR PHP SCRIPT ADDRESS
// HttpPost httppost = new HttpPost("http://172.23.193.32/elift-test/myfile.php"); //YOUR PHP SCRIPT ADDRESS

                InputStream inputStream = connection.getInputStream();

                BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream));
                String line = "";
                while ((line = rd.readLine()) != null) {
                    chaine.append(line);

                }
                inputStream.close();
                String s = chaine.toString();
            } catch (IOException e) {
                // Writing exception to log
                String es = e.toString();
                e.printStackTrace();
            }

            // NOT HERE YET
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(isr, "iso-8859-1"), 8);
                StringBuilder sb = new StringBuilder();
               String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                isr.close();
               result = sb.toString();
            } catch (Exception e) {
                String s = e.getMessage();
                // Log.e("log_tag", "Error  converting result "+e.toString());
            }
            return result;
        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        protected void onPostExecute(Double result) {

//parse json data
            try {
                String s = "";
                JSONArray jArray = new JSONArray(result);
                for (int i = 0; i < jArray.length(); i++) {

                    JSONObject json = jArray.getJSONObject(i);

                    s = s + "Name : " + json.getString("id") + " " + json.getString("username");
                }

                // resultView.setText(s);

            } catch (Exception e) {

// TODO: handle exception
                String s = e.getMessage();
                // Log.e("log_tag", "Error Parsing Data "+e.toString());

            }

        }
    }

    public void getAll(View v) {
        try {
            new MyAsyncTask().execute("").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

}
