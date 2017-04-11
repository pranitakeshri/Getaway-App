package com.example.sibin.getaway;



        import android.content.DialogInterface;
        import android.content.Intent;
        import android.net.ConnectivityManager;
        import android.net.NetworkInfo;
        import android.support.v7.app.AlertDialog;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.GridView;

public class MainActivity extends AppCompatActivity {
    String[] app = new String[]{"historical","lakes","museums","nature","pilgrimage","adventure","wildlife","waterfalls"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ConnectivityManager man= (ConnectivityManager)getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo ni= man.getActiveNetworkInfo();
        if (ni!=null && ni.isConnected())
        {

            GridView gridview = (GridView) findViewById(R.id.gv);
            gridview.setAdapter(new ImageAdapter(this));
            gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    Intent intent = new Intent(getBaseContext(), list.class);
                    intent.putExtra("type",app[position]);
                    startActivity(intent);


                }
            });
        }
        else
        {
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("No Internet")
                    .setMessage("There seems to be no internet connection")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // continue with delete
                            finish();
                        }
                    })
                    .show();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.info, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about:
                Intent abt = new Intent(getBaseContext(),About.class);
                startActivity(abt);
                return true;
            case R.id.refresh:
                Intent intent = getIntent();
                finish();
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

