package com.example.denjo.test;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ExpandedMenuView;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genre_choice);
        ListView listView = (ListView) findViewById(R.id.ListView);

        //Adapter生成
        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, GENRE);

        listView.setAdapter(adapter);
    }
    private static final String[] GENRE=new String[]{"俳優・女優","タレント","アーティスト","芸人","声優","ハリウッド俳優・ハリウッド女優","社長・科学者・政治家"};

}