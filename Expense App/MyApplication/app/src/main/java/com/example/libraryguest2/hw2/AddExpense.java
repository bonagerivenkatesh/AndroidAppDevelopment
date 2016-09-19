package com.example.libraryguest2.hw2;

import android.app.ActionBar;
import android.app.Activity;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;


public class AddExpense extends Activity {
    static EditText etdate = null;
    public String expname;
    public double amount;
    public int SELECT_IMAGE = 100;
    public ImageView ivreceipt = null;
    String uristring = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);


        final EditText etname = (EditText) findViewById(R.id.etname);
        final EditText etamount = (EditText) findViewById(R.id.etamount);
        etdate = (EditText) findViewById(R.id.etdate);
        ImageView ivdate = (ImageView) findViewById(R.id.ivdate);
        ivreceipt = (ImageView) findViewById(R.id.ivreceipt);
        final Button addbtnaddexpense = (Button) findViewById(R.id.addbtnaddexpense);
        final Spinner addspinnercategory = (Spinner) findViewById(R.id.addspinnercategory);





        ivdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getFragmentManager(), "datePicker");

            }
        });


        addbtnaddexpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    String cat = addspinnercategory.getSelectedItem().toString();
                    String expdate = etdate.getText().toString();

                    if (etname.getText().length() <= 0) {
                        etname.setError("Enter expense name");
                    } else {

                        expname = etname.getText().toString();

                        if (etamount.getText().length() <= 0) {
                            etamount.setError("Enter amount");
                        }
                        else
                        {
                            if(etdate.getText().toString().length()<=0)
                            {
                                etdate.setError("Select a proper date");
                            }
                            else {
                                if (cat.equalsIgnoreCase("Select Category")) {

                                    Toast.makeText(getApplicationContext(),"Select a proper category",Toast.LENGTH_LONG).show();

                                } else {
                                    amount = Double.parseDouble(etamount.getText().toString());

                                    Expense tempadd = new Expense(expname, amount, cat, expdate, uristring);

                                    Intent i = new Intent();
                                    i.putExtra("myexpensesadd", tempadd);
                                    setResult(RESULT_OK, i);

                                    Toast.makeText(getApplicationContext(), "Expense added successfully", Toast.LENGTH_LONG).show();

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


        ivreceipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_PICK);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_IMAGE);
            }
        });




    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            if (resultCode == RESULT_OK) {
                if (requestCode == SELECT_IMAGE) {
                    // Get the url from data
                    Uri selectedImageUri = data.getData();
                    if (null != selectedImageUri) {

                        // Set the image in ImageView
                        ivreceipt.setImageURI(selectedImageUri);

                        uristring=selectedImageUri.toString();


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

                etdate.setText(month + "/" + day + "/" + year);
                //Toast.makeText(getActivity(),month+"/"+day+"/"+year,Toast.LENGTH_LONG).show();

            }
        }


}