//InClass03
//Members: Venkatesh Bonageri (800964302)
//         Vikas Deshpande    (800964852)


package com.example.venkatesh.inclass03;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static String sname,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle(getString(R.string.mainactivity));


        final EditText etname = (EditText) findViewById(R.id.etname);
        final EditText etemail = (EditText) findViewById(R.id.etemail);

        final RadioGroup rg =(RadioGroup) findViewById(R.id.rg);
        final RadioButton rbsis =(RadioButton) findViewById(R.id.rbsis);
        final RadioButton rbcs = (RadioButton) findViewById(R.id.rbcs);
        final RadioButton rbbio = (RadioButton) findViewById(R.id.rbbio);
        final RadioButton rbothers = (RadioButton) findViewById(R.id.rbothers);
        final SeekBar seekbarmood =(SeekBar) findViewById(R.id.seekbarmood);

        Button btnsubmit = (Button) findViewById(R.id.btnsubmit);


        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    String name,email,department=null;
                    int mood;

                    name = etname.getText().toString();

                    if(name==null || name.length()==0)
                    {
                        etname.setError("Enter a valid name");
                    }
                    else
                    {

                        email = etemail.getText().toString();

                        String emailPattern = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" + "\\@" + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" + "\\." + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+";

                        if (!(email.matches(emailPattern) && email.length() > 0))
                        {
                            etemail.setError("Enter a valid email");
                        }
                        else {

                            if (rg.getCheckedRadioButtonId() == rbsis.getId()) {
                                department = "SIS";
                            } else if (rg.getCheckedRadioButtonId() == rbcs.getId()) {
                                department = "CS";
                            } else if (rg.getCheckedRadioButtonId() == rbbio.getId()) {
                                department = "BIO";
                            } else if (rg.getCheckedRadioButtonId() == rbothers.getId()) {
                                department = "Others";
                            }

                            mood = seekbarmood.getProgress();

                            Student s = new Student(name, email, department, mood);

                            Intent intent = new Intent(MainActivity.this, DisplayActivity.class);

                            intent.putExtra("StudentDetails", s);

                            //intent.putExtra("StudentDetails",s);

                            startActivity(intent);
                        }
                    }

                }
                catch (Exception e){
                    Toast.makeText(getApplicationContext(),"Something went wrong",Toast.LENGTH_LONG).show();
                }

            }
        });





    }
}
