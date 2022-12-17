package com.example.zakat;

import static com.example.zakat.R.*;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener { //implements View.OnClickListener 5

    //Get references from edit text 4
    EditText etWeight;
    EditText etGoldValue;
    Button btnKeep;        //button choosen to keep 2
    TextView tvTittleOutput;
    Button btnWear;        //button choosen to wear 2
    Button btnClear;
    ImageButton btnQues;

    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);

        btnQues = (ImageButton) findViewById(id.btnQues);
        btnQues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);


                alertDialog.setTitle("Step on how to use calculator:");
                alertDialog.setMessage("" +
                        "\nSTEP 1: Insert the gold weight in gram" +
                        "\nSTEP 2: Insert the current gold value" +
                        "\nSTEP 3: Choose Keep or Wear." +
                        "\n\nCalculation is being performed.");

                // add the buttons
                alertDialog.setPositiveButton("Continue with Calculator", null);

                // create and show the alert dialog
                AlertDialog dialog = alertDialog.create();
                dialog.show();
            }
        });

        etWeight = (EditText) findViewById(id.etWeight);      //variable untuk hasil convert 1
        etGoldValue = (EditText) findViewById(id.etGoldValue);      //variable untuk hasil convert 1
        btnKeep = (Button) findViewById(id.btnKeep);
        btnWear = (Button) findViewById(id.btnWear);
        btnClear = findViewById(id.btnClear);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etWeight.getText().clear();
                etGoldValue.getText().clear();
            }
        });

        btnKeep.setOnClickListener(this);
        btnWear.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //6
        double weight;
        weight = Double.parseDouble(etWeight.getText().toString()); //parse double sbb tak sama
        double gvalue;
        gvalue = Double.parseDouble(etGoldValue.getText().toString()); //parse double sbb tak sama
        float weight2 = Float.parseFloat(etWeight.getText().toString()); //parse float sbb tak sama
        float gvalue2 = Float.parseFloat(etGoldValue.getText().toString()); //parse float sbb tak sama

        sharedPref = this.getSharedPreferences("weightS", Context.MODE_PRIVATE);
        sharedPref = this.getSharedPreferences("gvalueS", Context.MODE_PRIVATE);
        weight2 = sharedPref.getFloat("weightS", 0);
        gvalue2 = sharedPref.getFloat("gvalueS", 0);

        double totalValue = weight * gvalue;
        double uruf;

        switch (v.getId()) {
            case R.id.btnKeep:
                try {
                    uruf = weight - 85;
                    double zakatPayableK = uruf <= 0 ? 0 : uruf * gvalue;
                    double totalZakatK = zakatPayableK * 0.025;

                    //alert dialog to show message

                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Calculated Value");
                    builder.setMessage("" +
                            "Total Value of Gold :RM " + totalValue +
                            "\nUruf :RM " + uruf +
                            "\nZakat Payable :RM " + zakatPayableK +
                            "\nTotal Zakat: RM " + (String.format("%.2f", totalZakatK)));

                    // add the buttons
                    builder.setPositiveButton("Continue", null);

                    // create and show the alert dialog
                    AlertDialog dialog = builder.create();
                    dialog.show();

                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("weight", String.valueOf(weight));
                    editor.putString("price", String.valueOf(gvalue));
                    editor.apply();

                } catch (java.lang.NumberFormatException nfe) {

                    Toast.makeText(this, "Please enter valid number", Toast.LENGTH_SHORT).show();

                } catch (Exception exp) {
                    Toast.makeText(this, "Unknown Exception" + exp.getMessage(), Toast.LENGTH_SHORT).show();

                    Log.d("Exception", exp.getMessage());
                }

                break;


            case R.id.btnWear:
                try {

                    uruf = weight - 200;
                    double zakatPayableW = uruf <= 0 ? 0 : uruf * gvalue;
                    double totalZakatW = zakatPayableW * 0.025;

                    //alert dialog to show message

                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Calculated Value");
                    builder.setMessage("" +
                            "Total Value of Gold :RM " + totalValue +
                            "\nUruf :RM " + uruf +
                            "\nZakat Payable :RM " + zakatPayableW +
                            "\nTotal Zakat: RM " + (String.format("%.2f", totalZakatW)));

                    // add the buttons
                    builder.setPositiveButton("Continue", null);

                    // create and show the alert dialog
                    AlertDialog dialog = builder.create();
                    dialog.show();

                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("weight", String.valueOf(weight));
                    editor.putString("price", String.valueOf(gvalue));
                    editor.apply();

                } catch (java.lang.NumberFormatException nfe) {

                    Toast.makeText(this, "Please enter valid number", Toast.LENGTH_SHORT).show();
                } catch (Exception exp) {
                    Toast.makeText(this, "Unknown Exception" + exp.getMessage(), Toast.LENGTH_SHORT).show();

                    Log.d("Exception", exp.getMessage());
                }
                break;

        }
        //Save data
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putFloat("weightS", weight2);
        editor.apply();

        SharedPreferences.Editor editor2 = sharedPref.edit();
        editor2.putFloat("gvalueS", gvalue2);
        editor2.apply();

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case id.calculator:

                Toast.makeText(this, "This is Zakat Calculator", Toast.LENGTH_LONG).show();
                break;

            case id.about:
                Intent intent = new Intent(this, AboutActivity.class);
                startActivity(intent);

                break;


        }
        return super.onOptionsItemSelected(item);
    }
}
