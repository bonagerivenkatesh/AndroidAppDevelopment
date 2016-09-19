package com.example.libraryguest2.hw2;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;

public class EditExpense extends Activity {
    static EditText editetdate=null;
    public String editexpname;
    public double editamount;
    String edituristring = null;
    public int SELECT_IMAGE_EDIT = 100;
    ImageView editivreceipt;
    AlertDialog.Builder disexpenses;
    int editindex;
    ArrayList<Expense> myexpenses;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_expense);


       // getActionBar().setTitle("Edit Expense");

        Button editbtnselectexpense=(Button) findViewById(R.id.editbtnselectexpense);
        Button editbtncancel=(Button) findViewById(R.id.editbtncancel);
        Button editbtnsave=(Button) findViewById(R.id.editbtnsave);
        final EditText editetexpensename=(EditText) findViewById(R.id.editetexpensename);
        final EditText editetamount=(EditText) findViewById(R.id.editetamount);
        editetdate=(EditText) findViewById(R.id.editetdate);
        ImageView editivcalendar=(ImageView)findViewById(R.id.editivcalendar);
        editivreceipt=(ImageView)findViewById(R.id.editivreceipt);

        final Spinner editspinnercategory = (Spinner) findViewById(R.id.editspinnercategory);

        disexpenses = new AlertDialog.Builder(this);

        myexpenses = new ArrayList<Expense>();
        myexpenses=(ArrayList<Expense>) getIntent().getExtras().getSerializable("myexpenses");

        editivcalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getFragmentManager(), "datePicker");

            }
        });

        editbtnselectexpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{
                final CharSequence[] cs = new CharSequence[myexpenses.size()];

                for (int j = 0; j < myexpenses.size(); j++) {
                    cs[j] = myexpenses.get(j).name;
                }

                disexpenses.setTitle("Pick an Expense")
                        .setItems(cs, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                editindex=which;
                                editetexpensename.setText(myexpenses.get(which).name);
                                editetamount.setText(myexpenses.get(which).amount + "");
                                editetdate.setText(myexpenses.get(which).date);

                                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.Categories, android.R.layout.simple_spinner_item);
                                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                editspinnercategory.setAdapter(adapter);
                                if (!myexpenses.get(which).category.equals(null)) {
                                    int spinnerPosition = adapter.getPosition(myexpenses.get(which).category);
                                    editspinnercategory.setSelection(spinnerPosition);
                                }

                                edituristring=myexpenses.get(which).uri;

                                if (myexpenses.get(which).uri != null) {


                                    editivreceipt.setImageURI(Uri.parse(myexpenses.get(which).uri));
                                        Log.d("testuri123",myexpenses.get(which).uri);



                                } else {
                                    editivreceipt.setImageResource(R.mipmap.receipt);

                                }

                                Log.d("test", myexpenses.size() + "");
                            }
                        });

                final AlertDialog alert = disexpenses.create();

                alert.show();
                alert.setCancelable(false);


            }
                catch (Exception e)
                {
                    Log.d("test", e.getMessage());
                }

        }

        });



        editbtnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    String cat = editspinnercategory.getSelectedItem().toString();
                    String expdate = editetdate.getText().toString();

                    if (editetexpensename.getText().length() <= 0) {
                        editetexpensename.setError("Enter expense name");
                    } else {

                        editexpname = editetexpensename.getText().toString();

                        if (editetamount.getText().length() <= 0) {
                            editetamount.setError("Enter amount");
                        }
                        else
                        {
                            if(editetdate.getText().toString().length()<=0)
                            {
                                editetdate.setError("Select a proper date");
                            }
                            else {

                                if (cat.equalsIgnoreCase("Select Category")) {

                                    Toast.makeText(getApplicationContext(),"Select a proper category",Toast.LENGTH_LONG).show();

                                } else {
                                    editamount = Double.parseDouble(editetamount.getText().toString());

                                    Expense tempexpedit = new Expense(editexpname,editamount,cat,expdate,edituristring);

                                    Intent i = new Intent();

                                    i.putExtra("tempindexedit",editindex);
                                    i.putExtra("tempexpedit",tempexpedit);
                                    setResult(RESULT_OK, i);

                                    Toast.makeText(getApplicationContext(), "Expense edited successfully", Toast.LENGTH_LONG).show();

                                    finish();
                                }
                            }

                        }

                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                }

            }
        });

        editbtncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Edit cancelled", Toast.LENGTH_SHORT).show();
                finish();
            }
        });



        editivreceipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_PICK);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_IMAGE_EDIT);
            }
        });


    }







    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            if (resultCode == RESULT_OK) {
                if (requestCode == SELECT_IMAGE_EDIT) {
                    // Get the url from data
                    Uri selectedImageUri = data.getData();
                    if (null != selectedImageUri) {

                        // Set the image in ImageView
                        editivreceipt.setImageURI(selectedImageUri);

                        edituristring=selectedImageUri.toString();

                    }

                }
            }
        }
        catch (Exception e)
        {
            Log.d("test",e.getMessage());
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }



    public static class DatePickerFragment extends DialogFragment implements
            DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceSateate) {

            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen
            editetdate.setText(month+"/"+day+"/"+year);
        }
    }
}
