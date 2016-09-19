package com.example.venkatesh.twoinclass;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText etlength1 = (EditText) findViewById(R.id.etlength1);
      final  EditText etlength2 = (EditText) findViewById(R.id.etlength2);

        final TextView tvresultdis = (TextView) findViewById(R.id.tvresultdis);

        Button btntriangle = (Button) findViewById(R.id.btntriangle);
        Button btnsquare = (Button) findViewById(R.id.btnsquare);
        Button btncircle = (Button) findViewById(R.id.btncircle);
        Button btnrectangle = (Button) findViewById(R.id.btnrectangle);
        Button btnclear = (Button) findViewById(R.id.btnclear);



        btntriangle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    double length1, length2, result;

                    length1 = Double.parseDouble(etlength1.getText().toString());
                    length2 = Double.parseDouble(etlength2.getText().toString());

                    if (length1 >= 0 && length2 >= 0) {
                        result = (0.5) * length1 * length2;

                        tvresultdis.setText(result + "");
                    } else {
                        Toast.makeText(getApplicationContext(), "Enter a valid number", Toast.LENGTH_SHORT).show();

                    }
                }
                catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(), "Enter a valid number", Toast.LENGTH_SHORT).show();
                }

            }
        });




        /////////////////
        btnsquare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    double length1, result;

                    length1 = Double.parseDouble(etlength1.getText().toString());

                    etlength2.setText("");

                    if (length1 >= 0) {
                        result = length1 * length1;

                        tvresultdis.setText(result + "");
                    } else {
                        Toast.makeText(getApplicationContext(), "Enter a valid number", Toast.LENGTH_SHORT).show();

                    }
                }
                catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(), "Enter a valid number", Toast.LENGTH_SHORT).show();
                }

            }
        });


        btnrectangle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    double length1, length2, result;

                    length1 = Double.parseDouble(etlength1.getText().toString());
                    length2 = Double.parseDouble(etlength2.getText().toString());

                    if (length1 >= 0 && length2 >= 0) {
                        result =  length1 * length2;

                        tvresultdis.setText(result + "");
                    } else {
                        Toast.makeText(getApplicationContext(), "Enter a valid number", Toast.LENGTH_SHORT).show();

                    }
                }
                catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(), "Enter a valid number", Toast.LENGTH_SHORT).show();
                }

            }
        });



        btncircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    double length1, result;

                    length1 = Double.parseDouble(etlength1.getText().toString());

                    etlength2.setText("");


                    if (length1 >= 0) {
                        result = (3.142) * length1 * length1;

                        tvresultdis.setText(result + "");
                    } else {
                        Toast.makeText(getApplicationContext(), "Enter a valid number", Toast.LENGTH_SHORT).show();

                    }
                }
                catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(), "Enter a valid number", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                etlength1.setText("");
                etlength2.setText("");
                tvresultdis.setText("");
            }
        });

    }
}
