package com.example.libraryguest2.hw2;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ShowExpense extends Activity {
    int currentivalue=0;
    TextView showtvnamedisplay;
    TextView showtvcategorydisplay;
    TextView showtvdatedisplay;
    TextView showtvamountdisplay;
    ImageView showivplayback;
    ImageView showivplay;
    ImageView showivreceipt;
    ArrayList<Expense> myexpenses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_expense);

        myexpenses = new ArrayList<Expense>();
        myexpenses=(ArrayList<Expense>) getIntent().getExtras().getSerializable("myexpenses");

        // getActionBar().setTitle("Show Expense");
        showtvnamedisplay=(TextView) findViewById(R.id.showtvnamedisplay);
        showtvamountdisplay=(TextView) findViewById(R.id.showtvamountdisplay);
        showtvdatedisplay=(TextView) findViewById(R.id.showtvdatedisplay);
        showtvcategorydisplay=(TextView) findViewById(R.id.showtvcategorydisplay);

        showivreceipt=(ImageView) findViewById(R.id.showivreceipt);


        ImageView showivlongback=(ImageView) findViewById(R.id.showivlongback);
        showivplayback=(ImageView) findViewById(R.id.showivplayback);
        showivplay=(ImageView) findViewById(R.id.showivplay);
        ImageView showivlongfwd=(ImageView) findViewById(R.id.showivlongfwd);
        Button btnfin=(Button)(findViewById(R.id.showbtnfinish));

        final int length=myexpenses.size();

        this.changedisplay();

        showivlongback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentivalue=0;
                changedisplay();
                /*showtvnamedisplay.setText(MainActivity.myexpenses.get(0).name);
                showtvamountdisplay.setText(MainActivity.myexpenses.get(0).amount+"");
                showtvdatedisplay.setText(MainActivity.myexpenses.get(0).date);
                showtvcategorydisplay.setText(MainActivity.myexpenses.get(0).category);*/
            }
        });


        showivlongfwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentivalue=myexpenses.size()-1;
                changedisplay();
            }
        });


        btnfin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        showivplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentivalue=currentivalue+1;
                changedisplay();
            }
        });

        showivplayback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentivalue=currentivalue-1;
                changedisplay();
            }
        });


    }



    public void changedisplay()
    {
        if(myexpenses.size()==0)
        {
            Toast.makeText(getApplicationContext(),"You haven't added any expenses",Toast.LENGTH_LONG).show();
            finish();
        }
        else
        {
            showtvnamedisplay.setText(myexpenses.get(currentivalue).name);
            showtvamountdisplay.setText(myexpenses.get(currentivalue).amount+"");
            showtvdatedisplay.setText(myexpenses.get(currentivalue).date);
            showtvcategorydisplay.setText(myexpenses.get(currentivalue).category);

            if(myexpenses.get(currentivalue).uri!=null)
            {
                showivreceipt.setImageURI(Uri.parse(myexpenses.get(currentivalue).uri));
            }
            else
            {
                showivreceipt.setImageResource(R.mipmap.receipt);
            }


            if(currentivalue==0)
            {
                showivplayback.setEnabled(false);
            }
            else
            {
                showivplayback.setEnabled(true);
            }


            if(currentivalue==myexpenses.size()-1)
            {
                showivplay.setEnabled(false);
            }
            else
            {
                showivplay.setEnabled(true);
            }


        }
    }
}
