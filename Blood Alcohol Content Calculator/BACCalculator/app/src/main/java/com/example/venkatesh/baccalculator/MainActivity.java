//Group number: 23 (HomeWork1)
//Names: Venkatesh Bonageri (800964302)
//        Vikas Deshpande (800964852)

package com.example.venkatesh.baccalculator;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public double weight,weightback,gencons,genconsback;
    public int flag=0;
    public String gender,genderback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar ab = getSupportActionBar();

        ab.setDisplayShowHomeEnabled(true);
        ab.setDisplayShowTitleEnabled(true);

        ab.setIcon(R.mipmap.ic_launcherone);
        ab.setTitle("BAC Calculator");


        final TextView tvweight= (TextView) findViewById(R.id.tvweight);
        TextView tvdrinksize= (TextView) findViewById(R.id.tvdrinksize);
        TextView tvalcohol= (TextView) findViewById(R.id.tvalcohol);
        final TextView tvbaclevel= (TextView) findViewById(R.id.tvbaclevel);
        final TextView tvpercdisplay= (TextView) findViewById(R.id.tvpercdisplay);
        final TextView tvresultbac= (TextView) findViewById(R.id.tvresultbac);
        final TextView tvresultprint= (TextView) findViewById(R.id.tvresultprint);
        TextView tvstatus= (TextView) findViewById(R.id.tvstatus);

        final EditText etweight= (EditText) findViewById(R.id.etweight);

        final Switch genderswitch= (Switch) findViewById(R.id.genderswitch);

        final Button savebutton= (Button) findViewById(R.id.savebutton);
        Button resetbutton= (Button) findViewById(R.id.resetbutton);
        final Button adddrinkbutton= (Button) findViewById(R.id.adddrinkbutton);

        final RadioGroup radiogroup1= (RadioGroup) findViewById(R.id.radiogroup1);
        final RadioButton oneozradiobutton= (RadioButton) findViewById(R.id.oneozradiobutton);
        final RadioButton fiveozradiobutton= (RadioButton) findViewById(R.id.fiveozradiobutton);
        final RadioButton twelveozradiobutton= (RadioButton) findViewById(R.id.twelveozradiobutton);

        final SeekBar alcoholseekbar=(SeekBar) findViewById(R.id.alcoholseekbar);
        alcoholseekbar.setProgress(0);

        tvpercdisplay.setText(((alcoholseekbar.getProgress()*5)+5)+"%");

        alcoholseekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tvpercdisplay.setText((5+(i*5))+"%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });




        final ProgressBar bacprogressbar= (ProgressBar) findViewById(R.id.bacprogressbar);




        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try
                {
                    weight=Double.parseDouble(etweight.getText().toString());

                    if(genderswitch.isChecked()) {
                        gender = "M";
                        gencons=0.68;
                    }
                    else {
                        gender = "F";
                        gencons=0.55;
                    }
                    flag=1;

                    Toast.makeText(getApplicationContext(),"Weight & Gender details saved successfully",Toast.LENGTH_LONG).show();

                    if(Double.parseDouble(tvresultbac.getText().toString())==0.0)
                    {

                        weightback=weight;
                        genderback=gender;
                        genconsback=gencons;
                    }
                    else
                    {

                        double tmpalcho,tmpbac,newbac;

                        tmpbac=Double.parseDouble(tvresultbac.getText().toString());
                        tmpalcho = tmpbac*weightback*genconsback/(6.24);

                        newbac=(tmpalcho*6.24)/(weight*gencons);


                        String tempres = String.format("%.04f",newbac);
                        tvresultbac.setText(tempres);

                        newbac = newbac*100;
                        int temppro = (int) newbac;

                        bacprogressbar.setProgress(temppro);

                        genconsback=gencons;
                        weightback=weight;
                        genderback=gender;

                        double bacforstatus;

                        bacforstatus=Double.parseDouble(tempres);

                        if(bacforstatus<0.08)
                        {
                            tvresultprint.setBackgroundColor(Color.parseColor("#006400"));
                            tvresultprint.setText("You're safe");

                        }
                        else if(bacforstatus<0.20)
                        {
                            tvresultprint.setBackgroundColor(Color.parseColor("#ffa500"));
                            tvresultprint.setText("Be careful...");

                        }
                        else
                        {
                            tvresultprint.setBackgroundColor(Color.parseColor("#ff0000"));
                            tvresultprint.setText("Over the limit!");
                        }


                        if(bacforstatus>=0.25)
                        {
                            Toast.makeText(getApplicationContext(),"No more drinks for you",Toast.LENGTH_LONG).show();

                            savebutton.setEnabled(false);
                            adddrinkbutton.setEnabled(false);
                        }

                    }


                }
                catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(),"Enter weight",Toast.LENGTH_LONG).show();
                }
            }
        });

      adddrinkbutton.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              try
              {
                  double ounce=1,a,baclevel;
                  if(flag==1)
                  {

                        if(radiogroup1.getCheckedRadioButtonId()==oneozradiobutton.getId())
                        {

                            ounce=1;
                        }
                      else if(radiogroup1.getCheckedRadioButtonId()==fiveozradiobutton.getId())
                        {
                            ounce=5;
                        }
                      else if(radiogroup1.getCheckedRadioButtonId()==twelveozradiobutton.getId())
                        {
                            ounce=12;
                        }


                      System.out.println(alcoholseekbar.getProgress());


                      a=(ounce)*((((alcoholseekbar.getProgress())*5.0)+5)/100);

                      baclevel=((a*6.24)/(weight*gencons))+(Double.parseDouble(tvresultbac.getText().toString()));

                      String tempres = String.format("%.04f",baclevel);
                      tvresultbac.setText(tempres);

                      double bacforstatus;

                      bacforstatus=Double.parseDouble(tempres);

                      baclevel = baclevel*100;
                      int temppro = (int) baclevel;

                      bacprogressbar.setProgress(temppro);

                      if(bacforstatus<0.08)
                      {
                          tvresultprint.setBackgroundColor(Color.parseColor("#006400"));
                          tvresultprint.setText("You're safe");
                      }
                      else if(bacforstatus<0.20)
                      {
                          tvresultprint.setBackgroundColor(Color.parseColor("#ffa500"));
                          tvresultprint.setText("Be careful...");
                      }
                      else
                      {
                          tvresultprint.setBackgroundColor(Color.parseColor("#ff0000"));
                          tvresultprint.setText("Over the limit!");
                      }


                      if(bacforstatus>=0.25)
                      {
                          Toast.makeText(getApplicationContext(),"No more drinks for you",Toast.LENGTH_LONG).show();

                          savebutton.setEnabled(false);
                          adddrinkbutton.setEnabled(false);
                      }
                  }
                  else
                  {
                      etweight.setError("Enter weight in lbs and click save");
                      etweight.setText("");
                  }
              }
              catch(Exception e)
              {
                    Toast.makeText(getApplicationContext(),"Something went wrong",Toast.LENGTH_LONG).show();
              }
          }
      });


        resetbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag=0;
                alcoholseekbar.setProgress(0);
                tvpercdisplay.setText(((alcoholseekbar.getProgress()*5)+5)+"%");
                bacprogressbar.setProgress(0);
                weight=weightback=0;
                gender=genderback=null;
                gencons=genconsback=0;
                tvresultbac.setText("0.00");
                tvresultprint.setText("You're safe");
                savebutton.setEnabled(true);
                adddrinkbutton.setEnabled(true);
                etweight.setText("");
                genderswitch.setChecked(false);
                radiogroup1.check(R.id.oneozradiobutton);
                tvresultprint.setBackgroundColor(Color.parseColor("#006400"));
            }
        });

    }
}
