package cs.finalproject;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;



import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class QuestionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        StrictMode.enableDefaults(); //STRICT MODE ENABLED
        getData();
    }

    public void getData(){

        String result = "";

        //InputStream isr = null;

        StringBuffer chaine = new StringBuffer("");
        try {
            URL url = new URL("http://192.168.56.1/Users/Ilana/AndroidStudioProjects/CSFinalProject/FinalProject/app/src/main/res/Myfilw.php");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("User-Agent", "");
            connection.setRequestMethod("GET");
            // connection.setDoInput(true);
            connection.connect();

            // HttpPost httppost = new HttpPost("http://ieeehiit.host22.com/myfile.php"); //YOUR PHP SCRIPT ADDRESS

// HttpPost httppost = new HttpPost("http://172.23.193.32/elift-test/myfile.php"); //YOUR PHP SCRIPT ADDRESS

            InputStream inputStream = connection.getInputStream();

            BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while ((line = rd.readLine()) != null) {
                chaine.append(line);

            }
        }
            catch (IOException e) {
                // Writing exception to log
                e.printStackTrace();
            }

        catch(Exception e){

//            Log.e("log_tag", "Error in http connection "+e.toString());
//
//            resultView.setText("Couldnt connect to database");

        }

//convert response to string

//        try{
//
//            //BufferedReader reader = new BufferedReader(new InputStreamReader(isr,"iso-8859-1"),8);
//
//            //StringBuilder sb = new StringBuilder();
//
//            //String line = null;
//
//           // while ((line = reader.readLine()) != null) {
//
//            //    sb.append(line + "\n");
//
//            //}
//
//           // isr.close();
//
//            //result=sb.toString();
//
//        }
//
//        catch(Exception e){
//
//            Log.e("log_tag", "Error  converting result "+e.toString());
//
//        }

//parse json data

        try {

            String s = "";

            JSONArray jArray = new JSONArray(result);

            for(int i=0; i<jArray.length();i++){

                JSONObject json = jArray.getJSONObject(i);

                s = s +"Name : "+json.getString("id")+" "+json.getString("username");  }

//            resultView.setText(s);

        } catch (Exception e) {

// TODO: handle exception

//            Log.e("log_tag", "Error Parsing Data "+e.toString());

        }

    }

}