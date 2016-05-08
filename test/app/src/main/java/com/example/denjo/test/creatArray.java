package com.example.denjo.test;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageSwitcher;
import android.widget.ImageView;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.regex.Pattern;

public class createArray extends AppCompatActivity{
    public String[] arrayName = new String[1000];
    public int[] arrayId = new int[1000];
    public int determinedId;
    public String determinedName;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_result);
    //id、名前(説明)が入ったtxtファイルを開き、配列を作成
    fileOpen("sorce.txt");
    for (int i = 0 ; arrayId[i] != 0 ; i++) {
      if(arrayId[i] == resultId) {
        determinedId = arrayId[i];
        determinedName = arrayName[i];
        break;
      }
    }
  }

  public void fileOpen(String filename){
    int counter = 0;
    //1行ずつ読み込み
      try{
          File file = new File(filename);

          if (checkBeforeReadfile(file)){
              BufferedReader br
                  = new BufferedReader(new FileReader(file));

              String str;
              while((str = br.readLine()) != null){
                  //トークン分解
                  Pattern p = Pattern.compile("[�@|\\s]+");
                  String[] result = p.split(str);
                  if(result.length != 2) {
                    System.out.println("different number of token");
                  }
                  //id、名前をそれぞれ配列に
                  int tmpid = Integer.parseInt(result[0]);
                  arrayId[counter] = tmpid;
                  arrayName[counter] = result[1];
                  counter++;
              }
              br.close();
          }else{
              System.out.println("�t�@�C����������Ȃ����J���܂���");
          }
      }catch(FileNotFoundException e){
          System.out.println(e);
      }catch(IOException e){
          System.out.println(e);
      }
      //表示テスト
    }

    private static boolean checkBeforeReadfile(File file){
        if (file.exists()){
            if (file.isFile() && file.canRead()){
                return true;
            }
        }
        return false;
    }
}
