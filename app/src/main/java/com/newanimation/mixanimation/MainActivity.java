package com.newanimation.mixanimation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.newanimation.mixanimation.Adapter.TestAdapter;
import com.newanimation.mylibrary.AnimationAdapter;
import com.newanimation.mylibrary.NextAnimation;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {


    int itemColum = 2;
    int numberOfAds = 6;
    private GridLayoutManager gridlayoutManager;
    private RecyclerView rv;

    private List<String> feedItems = new ArrayList<>();
    TestAdapter testAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        BigAnimation.TopAnimation(this, findViewById(R.id.top_animation));//Native Code
//        SmallAnimation.BottomAnimation(this, findViewById(R.id.bottom_animation));//Banner Code
//        QurekaFixAnimation.QurekaRoundAnimation(this,findViewById(R.id.top_animation),1,1);

        initView();


    }

    public void ADS(View view) {
        NextAnimation.NextSliderAnimation(this, new Intent(this, TestingActivity.class), 0);//Next Interstitial
    }

    @Override
    public void onBackPressed() {
        NextAnimation.BackAnimation(this, null);//Back Interstitial
    }

    private void initView() {
        loadData();
        rv = (RecyclerView) findViewById(R.id.rv);

        gridlayoutManager = new GridLayoutManager(this, itemColum);
        gridlayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return (position + 1) % (numberOfAds + 1) == 0 ? itemColum : 1;
            }
        });
        rv.setLayoutManager(gridlayoutManager);
        testAdapter = new TestAdapter(this, feedItems);
        AnimationAdapter fbAdapter = AnimationAdapter.Builder.with(testAdapter).adItemInterval(numberOfAds)
                .build();
        rv.setAdapter(fbAdapter);
    }

    private void loadData() {
        feedItems.add("asd");
        feedItems.add("aseryeryd");
        feedItems.add("rth");
        feedItems.add("sert");
        feedItems.add("sert");
        feedItems.add("rtdfg");
        feedItems.add("erter");
        feedItems.add("dsfg4r");
        feedItems.add("sdfgdsfg");
        feedItems.add("4trrgg");
        feedItems.add("ytjytu");
        feedItems.add("erretr");
        feedItems.add("asd");
        feedItems.add("aseryeryd");
        feedItems.add("rth");
        feedItems.add("sert");
        feedItems.add("sert");
        feedItems.add("rtdfg");
        feedItems.add("erter");
        feedItems.add("dsfg4r");
        feedItems.add("sdfgdsfg");
        feedItems.add("4trrgg");
        feedItems.add("ytjytu");
        feedItems.add("erretr");
        feedItems.add("asd");
        feedItems.add("aseryeryd");
        feedItems.add("rth");
        feedItems.add("sert");
        feedItems.add("sert");
        feedItems.add("rtdfg");
        feedItems.add("erter");
        feedItems.add("dsfg4r");
        feedItems.add("sdfgdsfg");
        feedItems.add("4trrgg");
        feedItems.add("ytjytu");
        feedItems.add("erretr");
        feedItems.add("asd");
        feedItems.add("aseryeryd");
        feedItems.add("rth");
        feedItems.add("sert");
        feedItems.add("sert");
        feedItems.add("rtdfg");
        feedItems.add("erter");
        feedItems.add("dsfg4r");
        feedItems.add("sdfgdsfg");
        feedItems.add("4trrgg");
        feedItems.add("ytjytu");
        feedItems.add("erretr");
        feedItems.add("asd");
        feedItems.add("aseryeryd");
        feedItems.add("rth");
        feedItems.add("sert");
        feedItems.add("sert");
        feedItems.add("rtdfg");
        feedItems.add("erter");
        feedItems.add("dsfg4r");
        feedItems.add("sdfgdsfg");
        feedItems.add("4trrgg");
        feedItems.add("ytjytu");
        feedItems.add("erretr");
    }
}
