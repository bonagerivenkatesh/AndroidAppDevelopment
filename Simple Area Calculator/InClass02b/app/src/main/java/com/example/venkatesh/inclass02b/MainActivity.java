package com.example.venkatesh.inclass02b;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
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

        Button btncalculate = (Button) findViewById(R.id.btncalculate);

       final RadioGroup rg = (RadioGroup) findViewById(R.id.rg);

        btncalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try
                {

                    int checkedid;
                    double length1, length2, result;
                    checkedid = rg.getCheckedRadioButtonId();

                    if(checkedid == R.id.rbtriangle)
                    {
                        length1 = Double.parseDouble(etlength1.getText().toString());
                        length2 = Double.parseDouble(etlength2.getText().toString());

                        if (length1 >= 0 && length2 >= 0) {
                            result = (0.5) * length1 * length2;

                            tvresultdis.setText(result + "");
                        } else {
                            Toast.makeText(getApplicationContext(), "Enter a valid number", Toast.LENGTH_SHORT).show();

                        }

                    }
                    else if (checkedid == R.id.rbsquare)
                    {

                        length1 = Double.parseDouble(etlength1.getText().toString());

                        etlength2.setText("");

                        if (length1 >= 0) {
                            result = length1 * length1;

                            tvresultdis.setText(result + "");
                        } else {
                            Toast.makeText(getApplicationContext(), "Enter a valid number", Toast.LENGTH_SHORT).show();

                        }

                    }
                    else if (checkedid == R.id.rbcircle)
                    {

                        length1 = Double.parseDouble(etlength1.getText().toString());

                        etlength2.setText("");


                        if (length1 >= 0) {
                            result = (3.142) * length1 * length1;

                            tvresultdis.setText(result + "");
                        } else {
                            Toast.makeText(getApplicationContext(), "Enter a valid number", Toast.LENGTH_SHORT).show();

                        }

                    }
                    else if (checkedid == R.id.rbrectangle)
                    {

                        length1 = Double.parseDouble(etlength1.getText().toString());
                        length2 = Double.parseDouble(etlength2.getText().toString());

                        if (length1 >= 0 && length2 >= 0) {
                            result =  length1 * length2;

                            tvresultdis.setText(result + "");
                        } else {
                            Toast.makeText(getApplicationContext(), "Enter a valid number", Toast.LENGTH_SHORT).show();

                        }

                    }
                    else if (checkedid == R.id.rbclear)
                    {
                        etlength1.setText("");
                        etlength2.setText("");
                        tvresultdis.setText("");
                    }

                }
                catch (Exception e){

                    Toast.makeText(getApplicationContext(),"Enter a valid number",Toast.LENGTH_SHORT).show();
                }

            }
        });




    }
}
