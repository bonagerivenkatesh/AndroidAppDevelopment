package com.example.libraryguest2.hw2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class DeleteExpense extends Activity {
    AlertDialog.Builder deletedisexpenses;
    int deletecurrentvalue=0;
    ArrayList<Expense> myexpenses;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_expense);

        myexpenses = new ArrayList<Expense>();
        myexpenses=(ArrayList<Expense>) getIntent().getExtras().getSerializable("myexpenses");

        Button deletebtnselectexpense=(Button) findViewById(R.id.deletebtnselectexpense);
        Button deletebtncancel=(Button) findViewById(R.id.deletebtncancel);
        Button deletebtndelete=(Button) findViewById(R.id.deletebtndelete);
        final EditText deleteetexpensename=(EditText) findViewById(R.id.deleteetexpensename);
        final EditText deleteetdate=(EditText) findViewById(R.id.deleteetdate);
        final EditText deleteetamount=(EditText) findViewById(R.id.deleteetamount);
        ImageView deleteivcalendar=(ImageView)findViewById(R.id.deleteivcalendar);
        final ImageView deleteivreeceipt=(ImageView)findViewById(R.id.deleteivreeceipt);
        final Spinner deletespinnercategory = (Spinner) findViewById(R.id.deletespinnercategory);
        //getActionBar().setTitle("Delete Expense");
        deletedisexpenses = new AlertDialog.Builder(this);

        deleteetexpensename.setEnabled(false);
        deleteetdate.setEnabled(false);
        deleteetamount.setEnabled(false);
        deletespinnercategory.setEnabled(false);
        deletespinnercategory.setClickable(false);


        deletebtnselectexpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {
                    final CharSequence[] cs= new CharSequence[myexpenses.size()];
                    for(int i=0;i<myexpenses.size();i++)
                    {
                        cs[i]=myexpenses.get(i).name;
                    }

                    deletedisexpenses.setTitle("Pick an Expense")
                            .setItems(cs, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    deletecurrentvalue=which;
                                    deleteetexpensename.setText(myexpenses.get(which).name);
                                    deleteetamount.setText(myexpenses.get(which).amount + "");
                                    deleteetdate.setText(myexpenses.get(which).date);

                                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.Categories, android.R.layout.simple_spinner_item);
                                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    deletespinnercategory.setAdapter(adapter);
                                    if (!myexpenses.get(which).category.equals(null)) {
                                        int spinnerPosition = adapter.getPosition(myexpenses.get(which).category);
                                        deletespinnercategory.setSelection(spinnerPosition);
                                    }

                                    if (myexpenses.get(which).uri != null) {


                                        deleteivreeceipt.setImageURI(Uri.parse(myexpenses.get(which).uri));





                                    } else {
                                        deleteivreeceipt.setImageResource(R.mipmap.receipt);

                                    }


                                }
                            });

                    final AlertDialog alert = deletedisexpenses.create();

                    alert.show();
                    alert.setCancelable(false);



                }
                catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(), "Please select an expense first", Toast.LENGTH_SHORT).show();
                    Log.d("test",e.getMessage());
                }
            }
        });


        deletebtncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();;
            }
        });

        deletebtndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // myexpenses.remove(deletecurrentvalue);

                Intent i = new Intent();
                i.putExtra("myexpensesdelete",deletecurrentvalue);
                setResult(RESULT_OK,i);

                Toast.makeText(getApplicationContext(),"Expense deleted successfully",Toast.LENGTH_LONG).show();



                finish();
            }
        });
    }
}
