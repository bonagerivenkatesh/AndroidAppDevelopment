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
import android.widget.TableLayout;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {

    public int flag = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        getSupportActionBar().setTitle(getString(R.string.editactivity));



        final EditText editetname = (EditText) findViewById(R.id.editetname);
        final EditText editetemail = (EditText) findViewById(R.id.editetemail);

        final RadioGroup editrg =(RadioGroup) findViewById(R.id.editrg);
        final RadioButton editrbsis =(RadioButton) findViewById(R.id.editrbsis);
        final RadioButton editrbcs = (RadioButton) findViewById(R.id.editrbcs);
        final RadioButton editrbbio = (RadioButton) findViewById(R.id.editrbbio);
        final RadioButton editrbothers = (RadioButton) findViewById(R.id.editrbothers);
        final SeekBar editseekbarmood =(SeekBar) findViewById(R.id.editseekbarmood);

        TableLayout edittldepartment = (TableLayout) findViewById(R.id.tldepartment);
        TableLayout edittlmood = (TableLayout) findViewById(R.id.tlmood);

        Button editbtnsave = (Button) findViewById(R.id.editbtnsave);


        try {

            String idvalue = getIntent().getExtras().getString(DisplayActivity.ROWID);
            String value = getIntent().getExtras().getString(DisplayActivity.ROWVALUE);



            if (idvalue.equals("editetname")) {
                editetname.setVisibility(View.VISIBLE);
                editetname.setText(value);
                editetname.setSelection(editetname.getText().length());
                flag = 1;
            }
            else if(idvalue.equals("editetemail")) {
                editetemail.setVisibility(View.VISIBLE);
                editetemail.setText(value);
                editetemail.setSelection(editetemail.getText().length());
                flag = 2;
            }
            else if(idvalue.equals("edittldepartment")) {
                edittldepartment.setVisibility(View.VISIBLE);

                if (value.equals("SIS"))
                    editrbsis.setChecked(true);
                else if(value.equals("CS"))
                    editrbcs.setChecked(true);
                else if (value.equals("BIO"))
                    editrbbio.setChecked(true);
                else if (value.equals("Others"))
                    editrbothers.setChecked(true);
                flag = 3;
            }

            else if(idvalue.equals("edittlmood")) {
                edittlmood.setVisibility(View.VISIBLE);
                editseekbarmood.setProgress(Integer.parseInt(value));
                flag = 4;
            }


            editbtnsave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(flag==1)
                    {
                        String namevalue = editetname.getText().toString();

                        if(namevalue==null || namevalue.length()==0)
                        {
                            editetname.setError("Enter a valid name");
                        }

                        else {

                            if (namevalue == null || namevalue.length() == 0) {
                                setResult(RESULT_CANCELED);
                            } else {
                                Intent intent = new Intent();
                                intent.putExtra("namevalue", namevalue);
                                setResult(RESULT_OK, intent);
                            }
                            finish();
                        }

                    }
                    else if(flag==2)
                    {
                        String emailvalue = editetemail.getText().toString();

                        String emailPattern = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" + "\\@" + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" + "\\." + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+";

                        if (!(emailvalue.matches(emailPattern) && emailvalue.length() > 0))
                        {
                            editetemail.setError("Enter a valid email");
                        }
                        else {

                            if (emailvalue == null || emailvalue.length() == 0) {
                                setResult(RESULT_CANCELED);
                            } else {
                                Intent intent = new Intent();
                                intent.putExtra("emailvalue", emailvalue);
                                setResult(RESULT_OK, intent);
                            }
                            finish();
                        }
                    }
                    else if(flag==3)
                    {
                        String deptvalue=null;

                        if(editrg.getCheckedRadioButtonId() == editrbsis.getId())
                            deptvalue = "SIS";
                        else if(editrg.getCheckedRadioButtonId() == editrbcs.getId())
                            deptvalue = "CS";
                        else if(editrg.getCheckedRadioButtonId() == editrbbio.getId())
                            deptvalue = "BIO";
                        else if(editrg.getCheckedRadioButtonId() == editrbothers.getId())
                            deptvalue = "Others";


                        if (deptvalue == null || deptvalue.length() == 0){
                            setResult(RESULT_CANCELED);
                        }
                        else{
                            Intent intent = new Intent();
                            intent.putExtra("deptvalue",deptvalue);
                            setResult(RESULT_OK,intent);
                        }
                        finish();
                    }

                    else if(flag==4)
                    {
                        //String emailvalue = editetemail.getText().toString();

                        String moodvalue=editseekbarmood.getProgress()+"";

                        if (moodvalue == null || moodvalue.length() == 0){
                            setResult(RESULT_CANCELED);
                        }
                        else{
                            Intent intent = new Intent();
                            intent.putExtra("moodvalue",moodvalue);
                            setResult(RESULT_OK,intent);
                        }
                        finish();
                    }
                }
            });

        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),"Something went wrong",Toast.LENGTH_LONG).show();
        }

    }
}
