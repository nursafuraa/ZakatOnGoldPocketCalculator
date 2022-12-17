package com.example.zakat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class AboutActivity extends AppCompatActivity {
    TextView tvUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        tvUrl=findViewById(R.id.tvUrl);
        tvUrl.setMovementMethod(LinkMovementMethod.getInstance());

    }
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.about:

                Toast.makeText(this,"This is About page",Toast.LENGTH_LONG).show();
                break;

            case R.id.calculator:
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);

                break;


        }
        return super.onOptionsItemSelected(item);
    }
}