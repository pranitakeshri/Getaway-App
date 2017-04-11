package com.example.sibin.getaway;



        import android.content.Intent;
        import android.os.AsyncTask;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.ListView;
        import android.widget.ProgressBar;

        import org.apache.commons.lang.StringUtils;
        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.net.HttpURLConnection;
        import java.net.URL;



public class list extends AppCompatActivity {
    String ap = null,title=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ap = getIntent().getStringExtra("type");

        setContentView(R.layout.activity_text);
        title = StringUtils.capitalize(ap);
        setTitle(title);
        new tour().execute("");

    }



    private class tour extends AsyncTask<String, Void, String> {
        String page;
        ProgressBar spin = (ProgressBar)findViewById(R.id.pb);
        @Override
        protected void onPreExecute(){
            super.onPreExecute();

            spin.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... params) {


            try {
                String url = "https://raw.githubusercontent.com/SibinJ/getaway/master/json/";
                url = url.concat(ap);
                url = url.concat(".json");
                URL obj = new URL(url);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                con.setRequestMethod("GET");
                if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String inputline;
                    StringBuffer response = new StringBuffer();
                    while ((inputline = in.readLine()) != null) {
                        response.append(inputline);

                    }
                    in.close();
                    page = response.toString();

                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return page;

        }

        @Override
        protected void onPostExecute(String result) {

            spin.setVisibility(View.GONE);
            final String x = result;
            if (result != null) {
                try {
                    JSONObject jsonObj = new JSONObject(result);

                    // Getting JSON Array node
                    final JSONArray his = jsonObj.getJSONArray(ap);

                    String[] l = new String[his.length()];
                    for (int i = 0; i < his.length(); i++) {
                        JSONObject c = his.getJSONObject(i);
                        l[i] = c.getString("name");
                    }


                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(list.this,
                            R.layout.activity_listview, l);

                    ListView listView = (ListView) findViewById(R.id.lv);
                    listView.setAdapter(adapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                            // your code is here on item click
                            JSONObject jsonObj1 = null;
                            try {
                                jsonObj1 = new JSONObject(x);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            JSONArray rest = null;
                            try {
                                rest = jsonObj1.getJSONArray(ap);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            String dis = null, name = null, des = null;
                            JSONObject a = null;
                            try {
                                a = rest.getJSONObject(position);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                            try {
                                des = a.getString("description");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            try {
                                name = a.getString("name");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            Intent intent = new Intent(getBaseContext(), desc.class);
                            intent.putExtra("des", des);
                            intent.putExtra("name", name);
                            startActivity(intent);

                        }
                    });

                } catch (final JSONException e) {
                    e.printStackTrace();
                }


            }
        }
    }
}


