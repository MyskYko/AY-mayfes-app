package com.example.denjo.test;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ExpandedMenuView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.SimpleExpandableListAdapter;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class genre_choice extends AppCompatActivity {
    int[] resultId;
    int category = 0;
    private static final String[] GENRE=new String[]{"俳優","男性アーティスト","男性芸人","女優","女性アーティスト","女性芸人"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genre_choice);
        Intent i = getIntent();
        resultId = i.getIntArrayExtra("resultId");

        ListView listView = (ListView) findViewById(R.id.ListView);
        //Adapter生成
        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, GENRE);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                category = position+2;
                if(category >= 5) category += 1;
                moveResult(view);
            }
        });
    }

    public void moveResult(View view) {
        Intent intent = new Intent(this, result.class);
        intent.putExtra("resultId", resultId);
        intent.putExtra("category",category);
        startActivity(intent);
    }
}