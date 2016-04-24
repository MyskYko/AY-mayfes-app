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

        ArrayList<HashMap<String, String>> groupData
                = new ArrayList<HashMap<String, String>>();

        ArrayList<ArrayList<HashMap<String, String>>> childData
                = new ArrayList<ArrayList<HashMap<String, String>>>();
        //親リストの追加
        HashMap<String, String> groupA = new HashMap<String, String>();
        groupA.put("group", "俳優");
        HashMap<String, String> groupB = new HashMap<String, String>();
        groupB.put("group", "女優");
        HashMap<String, String> groupC = new HashMap<String, String>();
        groupC.put("group", "ジャニーズ");
        HashMap<String, String> groupD = new HashMap<String, String>();
        groupD.put("group", "アイドル");
        HashMap<String, String> groupE = new HashMap<String, String>();
        groupE.put("group", "アーティスト");

        groupData.add(groupA);
        groupData.add(groupB);
        groupData.add(groupC);
        groupData.add(groupD);
        groupData.add(groupE);

        ArrayList<HashMap<String, String>> childListA = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> childAA = new HashMap<String, String>();
        childAA.put("group", "俳優");
        childAA.put("name", "俳優A");

        HashMap<String, String> childAB = new HashMap<String, String>();
        childAB.put("group", "俳優");
        childAB.put("name", "俳優B");

        childListA.add(childAA);
        childListA.add(childAB);
        childData.add(childListA);

        ArrayList<HashMap<String, String>> childListB = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> childBA = new HashMap<String, String>();
        childBA.put("group", "女優");
        childBA.put("name", "女優B");

        HashMap<String, String> childBB = new HashMap<String, String>();
        childBB.put("group", "女優");
        childBB.put("name", "女優A");

        childListB.add(childBA);
        childListB.add(childBB);
        childData.add(childListB);

        //子リストの追加１
        ArrayList<HashMap<String, String>> childListC = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> childCA = new HashMap<String, String>();
        childCA.put("group", "ジャニーズ");
        childCA.put("name", "嵐");
        HashMap<String, String> childCB = new HashMap<String, String>();
        childCB.put("group", "ジャニーズ");
        childCB.put("name", "SMAP");
        HashMap<String, String> childCC = new HashMap<String, String>();
        childCC.put("group", "ジャニーズ");
        childCC.put("name", "Hey!Say!Jump!");

        childListC.add(childCA);
        childListC.add(childCB);
        childListC.add(childCC);

        childData.add(childListC);

        //子リストの追加２
        ArrayList<HashMap<String, String>> childListD = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> childDA = new HashMap<String, String>();
        childDA.put("group", "アイドル");
        childDA.put("name", "AKB48グループ（卒業生含む）");
        HashMap<String, String> childDB = new HashMap<String, String>();
        childDB.put("group", "アイドル");
        childDB.put("name", "モーニング娘。（卒業生含む）");
        HashMap<String, String> childDC = new HashMap<String, String>();
        childDC.put("group", "アイドル");
        childDC.put("name", "ももいろクローバーZ");

        childListD.add(childDA);
        childListD.add(childDB);
        childListD.add(childDC);

        childData.add(childListD);

        //子リストの追加３
        ArrayList<HashMap<String, String>> childListE = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> childEA = new HashMap<String, String>();
        childEA.put("group", "アーティスト");
        childEA.put("name", "ソロアーティスト");
        HashMap<String, String> childEB = new HashMap<String, String>();
        childEB.put("group", "アーティスト");
        childEB.put("name", "EXILE");
        HashMap<String, String> childEC = new HashMap<String, String>();
        childEC.put("group", "アーティスト");
        childEC.put("name", "SEKAI NO OWARI");

        childListE.add(childEA);
        childListE.add(childEB);
        childListE.add(childEC);

        childData.add(childListE);

        //親リスト、子リストを含んだAdapterを生成
        SimpleExpandableListAdapter arrayAdapter = new SimpleExpandableListAdapter(
                getApplicationContext(), groupData,
                android.R.layout.simple_expandable_list_item_1,
                new String[]{"group"}, new int[]{android.R.id.text1},
                childData, android.R.layout.simple_expandable_list_item_2,
                new String[]{"name", "group"}, new int[]
                {android.R.id.text1, android.R.id.text2});

        //ExpandableListViewにAdapterをセット
        ExpandableListView listView1 = (ExpandableListView) findViewById(R.id.ExpandableListView);
        listView1.setAdapter(arrayAdapter);


    }
}