package com.example.stage;

import static android.os.Environment.getExternalStorageDirectory;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.viewpager.widget.ViewPager;

import com.example.stage.imagescroll.PagerAda;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, MediaPlayer.OnCompletionListener {

    private static final String TAG = "fja";
    private ListView listView;
    private List<Map<String, Object>> musicList;
    private MediaPlayer mp;
    private ImageButton play;
    private ImageButton stop;
    private ImageButton prev;
    private ImageButton next;
    private ViewPager vpager;
    private PagerAda pagerAda;

    private int musicId = 0;
    private boolean isRelease = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "欢迎你," + getIntent().getExtras().get("username").toString(), Toast.LENGTH_SHORT).show();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        init();

        //获取音乐文件
        File file = Environment.getExternalStorageDirectory();
        getMusic(file);
        if (musicList.size() == 0) { // 无音乐时显示信息
            Toast.makeText(this, "未找到歌曲", Toast.LENGTH_SHORT).show();
        } else {
            SimpleAdapter simpleAdapter = new SimpleAdapter(this, musicList,
                    R.layout.list_item, new String[]{"musicName"},
                    new int[]{R.id.text});
            listView.setAdapter(simpleAdapter);

            //点击切换歌曲
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    musicId = position;
                    changeMusic();
                    if (!isRelease) {
                        play.setImageResource(R.drawable.pause);
                    }
                }
            });

            mp = new MediaPlayer();
            mp.setOnCompletionListener(this);
            initMp();
        }
    }

    protected void init() {
        musicList = new ArrayList<>();
        listView = findViewById(R.id.lv);
        play = findViewById(R.id.play);
        stop = findViewById(R.id.stop);
        prev = findViewById(R.id.prev);
        next = findViewById(R.id.next);
        vpager = findViewById(R.id.vpager);
        pagerAda = new PagerAda();
        pagerAda.notifyDataSetChanged();

        play.setOnClickListener(this);
        stop.setOnClickListener(this);
        prev.setOnClickListener(this);
        next.setOnClickListener(this);

        vpager.setAdapter(pagerAda);

    }

    protected void initMp() {
        try {
            mp.setDataSource(musicList.get(0).get("musicPath").toString());
            mp.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void changeMusic() {
        mp.reset();
        try {
            mp.setDataSource(musicList.get(musicId).get("musicPath").toString());
            mp.prepare();
            mp.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.play:
                if (isRelease) {
                    mp.start();
                    isRelease = false;
                    play.setImageResource(R.drawable.pause);
                } else {
                    isRelease = true;
                    mp.pause();
                    play.setImageResource(R.drawable.play);
                }
                break;
            case R.id.stop:
                if (mp.isPlaying()) {
                    mp.reset();
                    initMp();
                    isRelease = true;
                    play.setImageResource(R.drawable.play);
                }
                break;
            case R.id.next:
                musicId = (musicId + 1) % musicList.size();
                changeMusic();
                break;
            case R.id.prev:
                musicId--;
                if (musicId == -1) {
                    musicId = musicList.size() - 1;
                }
                changeMusic();
                break;
            default:
                break;
        }
    }

    //循环播放
    @Override
    public void onCompletion(MediaPlayer mp) {
        musicId = (musicId + 1) % musicList.size();
        changeMusic();
    }


    //获取音乐列表
    public void getMusic(File filePath) {
        Map<String, Object> item = null;
        for (int i = 0; i < filePath.listFiles().length; i++) {
            File itemFile = filePath.listFiles()[i];
            if (itemFile.isDirectory()&&itemFile.getName().toString()!="Android") {
                getMusic(itemFile);
            } else {
                if (itemFile.toString().endsWith(".mp3")) {
                    item = new HashMap<>();
                    item.put("musicPath", itemFile.getPath());
                    item.put("musicName", itemFile.getName());
                    musicList.add(item);
                }
            }
        }
    }


    //是的此activity常驻后台
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_UP) {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                moveTaskToBack(true);
                Toast.makeText(this, "应用已进入后台运行", Toast.LENGTH_SHORT).show();
                return true;
            }
        }

        return super.onKeyUp(keyCode, event);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
