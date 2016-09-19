package com.example.libraryguest2.hw2;

import android.content.Intent;
import java.util.Comparator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    int GBL_EDIT=101;
    int GBL_ADD=102;
    int GBL_SHOW=103;
    int GBL_DELETE=104;


    ArrayList<Expense> myexpenses;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

          myexpenses = new ArrayList<Expense>();

        Button btneditexpense=(Button) findViewById(R.id.btneditexpense);
        Button btndeleteexpense=(Button) findViewById(R.id.btndeleteexpense);
        Button btnshowexpenses=(Button) findViewById(R.id.btnshowexpenses);
        Button btnfinish=(Button) findViewById(R.id.btnfinish);
        Button btnaddexpense=(Button)findViewById(R.id.btnaddexpense);

        btnaddexpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addexp=new Intent(getApplicationContext(),AddExpense.class);
                startActivityForResult(addexp,GBL_ADD);

            }
        });

        btneditexpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(myexpenses.size()==0)
                {
                    Toast.makeText(getApplicationContext(),"No expenses are added yet to edit",Toast.LENGTH_LONG).show();
                }
                else {
                    Intent editexp = new Intent(getApplicationContext(), EditExpense.class);
                    editexp.putExtra("myexpenses",myexpenses);
                    startActivityForResult(editexp,GBL_EDIT);

                }
            }
        });



        btnshowexpenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myexpenses.size()==0)
                {
                    Toast.makeText(getApplicationContext(), "There are no expenses to show", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent showexp=new Intent(getApplicationContext(),ShowExpense.class);
                    showexp.putExtra("myexpenses",myexpenses);
                    startActivityForResult(showexp,GBL_ADD);

                }
            }
        });

        btnfinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btndeleteexpense.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(myexpenses.size()==0)
                        {
                            Toast.makeText(getApplicationContext(), "You haven't entered any expenses to delete", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Intent deleteexp=new Intent(getApplicationContext(),DeleteExpense.class);
                            deleteexp.putExtra("myexpenses",myexpenses);
                            startActivityForResult(deleteexp,GBL_DELETE);

                        }
                    }
                });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == GBL_EDIT)
            {
                int tempindexedit = data.getExtras().getInt("tempindexedit");

                Expense tempexpedit = new Expense("test",6,"test","test","test");

                tempexpedit = (Expense) data.getExtras().getSerializable("tempexpedit");

                myexpenses.get(tempindexedit).name=tempexpedit.name;
                myexpenses.get(tempindexedit).uri=tempexpedit.uri;
                myexpenses.get(tempindexedit).amount=tempexpedit.amount;
                myexpenses.get(tempindexedit).category=tempexpedit.category;
                myexpenses.get(tempindexedit).date=tempexpedit.date;

                Collections.sort(myexpenses, new Comparator<Expense>() {

                    public int compare(Expense stu1, Expense stu2) {

                        if(stu1.name.compareToIgnoreCase(stu2.name)>1)
                            return 1;
                        else if(stu1.name.compareToIgnoreCase(stu2.name)<1)
                            return -1;
                        else
                            return 0;
                    }

                });


            }
            else if(requestCode == GBL_ADD){

                myexpenses.add((Expense)data.getExtras().getSerializable("myexpensesadd"));


                Collections.sort(myexpenses, new Comparator<Expense>() {

                    public int compare(Expense stu1, Expense stu2) {

                        if(stu1.name.compareToIgnoreCase(stu2.name)>1)
                            return 1;
                        else if(stu1.name.compareToIgnoreCase(stu2.name)<1)
                            return -1;
                        else
                            return 0;
                    }

                });
            }
            else if(requestCode==GBL_DELETE)
            {
                //myexpenses=(ArrayList<Expense>)data.getExtras().getSerializable("myexpensesdelete");
                int deletevalue=data.getExtras().getInt("myexpensesdelete");

                myexpenses.remove(deletevalue);
            }
        }
    }
}
