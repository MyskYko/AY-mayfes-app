package com.example.denjo.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class jenderChoice extends AppCompatActivity {
    int[] resultId;
    int category = 0;
    private static final String[] GENRE=new String[]{"男性","女性"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jender_choice);
        Intent i = getIntent();
        resultId = i.getIntArrayExtra("resultId");
        ListView listView = (ListView) findViewById(R.id.listView);
        //Adapter生成
        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, GENRE);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0) category = 1;
                else category = 2;
                moveResult(view);
            }
        });
    }
    public void moveResult(View view) {
        Intent intent = new Intent(this, result.class);
        intent.putExtra("resultId", resultId);
        intent.putExtra("category",category);
        startActivity(intent);
        finish();
    }
}
