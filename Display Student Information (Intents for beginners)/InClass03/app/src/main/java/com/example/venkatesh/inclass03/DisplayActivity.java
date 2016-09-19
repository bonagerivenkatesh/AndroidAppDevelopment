//InClass03
//Members: Venkatesh Bonageri (800964302)
//         Vikas Deshpande    (800964852)

package com.example.venkatesh.inclass03;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayActivity extends AppCompatActivity {
    public Student s1;

    public static final int REQCODE_NAME = 101;
    public static final int REQCODE_EMAIl = 102;
    public static final int REQCODE_DEPT = 103;
    public static final int REQCODE_MOOD = 104;

    public static final String ROWID = "rowid";
    public static final String ROWVALUE = "value";

    TextView tvnamedis;
    TextView tvemaildis;
    TextView tvdeptdis;
    TextView tvmooddis;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == REQCODE_NAME)
        {
            if(resultCode == RESULT_OK)
            {
                Toast.makeText(getApplicationContext(),"The name has been updated",Toast.LENGTH_LONG).show();

                String namevalue = data.getExtras().getString("namevalue");

                tvnamedis.setText(namevalue);

                if(namevalue.length()>10)
                    tvnamedis.setTextSize(14);


            }
            else if(resultCode == RESULT_CANCELED)
            {
                Toast.makeText(getApplicationContext(),"The name couldnt get updated successfully",Toast.LENGTH_LONG).show();
            }
        }
        else if(requestCode == REQCODE_EMAIl)
        {
            if(resultCode == RESULT_OK)
            {
                Toast.makeText(getApplicationContext(),"The email has been updated",Toast.LENGTH_LONG).show();

                String emailvalue = data.getExtras().getString("emailvalue");

                tvemaildis.setText(emailvalue);

                if(emailvalue.length()>10)
                    tvnamedis.setTextSize(14);

            }
            else if(resultCode == RESULT_CANCELED)
            {
                Toast.makeText(getApplicationContext(),"The email couldnt get updated successfully",Toast.LENGTH_LONG).show();
            }
        }
        else if(requestCode == REQCODE_DEPT)
        {
            if(resultCode == RESULT_OK)
            {
                Toast.makeText(getApplicationContext(),"The department has been updated",Toast.LENGTH_LONG).show();

                String deptvalue = data.getExtras().getString("deptvalue");

                tvdeptdis.setText(deptvalue);

            }
            else if(resultCode == RESULT_CANCELED)
            {
                Toast.makeText(getApplicationContext(),"The department couldnt get updated successfully",Toast.LENGTH_LONG).show();
            }
        }

        else if(requestCode == REQCODE_MOOD)
        {
            if(resultCode == RESULT_OK)
            {
                Toast.makeText(getApplicationContext(),"Mood has been updated",Toast.LENGTH_LONG).show();

                String moodvalue = data.getExtras().getString("moodvalue");

                tvmooddis.setText(moodvalue + "% positive");
                s1.mood=Integer.parseInt(moodvalue);

            }
            else if(resultCode == RESULT_CANCELED)
            {
                Toast.makeText(getApplicationContext(),"The moodvalue couldnt get updated successfully",Toast.LENGTH_LONG).show();
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        getSupportActionBar().setTitle(getString(R.string.displayactivity));


        LinearLayout test1 = (LinearLayout) findViewById(R.id.displayactivityxml);
        test1.getBackground().setAlpha(100);


        tvnamedis = (TextView) findViewById(R.id.tvnamedis);
        tvemaildis = (TextView) findViewById(R.id.tvemaildis);
        tvdeptdis = (TextView) findViewById(R.id.tvdeptdis);
        tvmooddis = (TextView) findViewById(R.id.tvmooddis);

        ImageView ivname = (ImageView) findViewById(R.id.ivname);
        ImageView ivemail = (ImageView) findViewById(R.id.ivemail);
        ImageView ivdepartment = (ImageView) findViewById(R.id.ivdepartment);
        ImageView ivmood = (ImageView) findViewById(R.id.ivmood);


        try {
            if (getIntent().getExtras() != null) {
                 s1 = (Student) getIntent().getExtras().getSerializable("StudentDetails");

                tvnamedis.setText(s1.name);
                if(s1.name.length()>10)
                    tvnamedis.setTextSize(14);

                tvemaildis.setText(s1.email);

                if(s1.email.length()>10)
                    tvemaildis.setTextSize(14);

                tvdeptdis.setText(s1.department);
                tvmooddis.setText(s1.mood + "% positive");
            } else {
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_LONG).show();
            }


            ivname.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent ("com.example.venkatesh.inclass03.intent.action.EDIT");

                    intent.putExtra(ROWID,"editetname");
                    intent.putExtra(ROWVALUE,tvnamedis.getText().toString());

                    startActivityForResult(intent,REQCODE_NAME);

                }
            });

            ivemail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent ("com.example.venkatesh.inclass03.intent.action.EDIT");

                    intent.putExtra(ROWID,"editetemail");
                    intent.putExtra(ROWVALUE,tvemaildis.getText().toString());

                    startActivityForResult(intent,REQCODE_EMAIl);
                }
            });

            ivdepartment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent ("com.example.venkatesh.inclass03.intent.action.EDIT");

                    intent.putExtra(ROWID,"edittldepartment");
                    intent.putExtra(ROWVALUE,tvdeptdis.getText().toString());

                    startActivityForResult(intent,REQCODE_DEPT);
                }
            });


            ivmood.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent ("com.example.venkatesh.inclass03.intent.action.EDIT");

                    intent.putExtra(ROWID,"edittlmood");
                    intent.putExtra(ROWVALUE,s1.mood + "");

                    startActivityForResult(intent,REQCODE_MOOD);
                }
            });
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_LONG).show();
        }
    }
}
