package com.node.fang_nodeprogressview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.node.fang_nodeprogressview.view.LogisticsData;
import com.node.fang_nodeprogressview.view.NodeProgressAdapter;
import com.node.fang_nodeprogressview.view.NodeProgressView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private NodeProgressView nodeProgressView;
    private List<LogisticsData> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = new ArrayList<>();
        list.add(new LogisticsData().setTime("2016-6-30 01:05:31").setContent("快件在【相城中转仓】装车,正发往【无锡分拨中心】已签收,签收人是【王漾】,签收网点是【忻州原平】"));
        list.add(new LogisticsData().setTime("2016-6-29 23:34:20").setContent("快件在【相城中转仓】装车,正发往【无锡分拨中心】"));
        list.add(new LogisticsData().setTime("2016-6-29 19:14:00").setContent("【北京鸿运良乡站】的【010058.269】正在派件"));
        list.add(new LogisticsData().setTime("2016-6-29 16:00:02").setContent("快件到达【潍坊市中转部】,上一站是【】"));
        list.add(new LogisticsData().setTime("2016-6-29 14:00:07").setContent("快件在【潍坊市中转部】装车,正发往【潍坊奎文代派】"));
        list.add(new LogisticsData().setTime("2016-6-29 10:55:55").setContent("快件到达【潍坊】,上一站是【潍坊市中转部】"));
        list.add(new LogisticsData().setTime("2016-6-29 07:45:33").setContent("快件在【武汉分拨中心】装车,正发往【晋江分拨中心】"));
        list.add(new LogisticsData().setTime("2016-6-29 02:25:00").setContent("【北京鸿运良乡站】的【010058.269】正在派件"));
        list.add(new LogisticsData().setTime("2016-6-28 23:00:45").setContent("【北京鸿运良乡站】的【010058.269】正在派件"));
        list.add(new LogisticsData().setTime("2016-6-28 18:10:22").setContent("【北京鸿运良乡站】的【010058.269】正在派件"));
        list.add(new LogisticsData().setTime("2016-6-28 15:13:02").setContent("【北京鸿运良乡站】的【010058.269】正在派件"));

        nodeProgressView=findViewById(R.id.nodeProgressView);
        nodeProgressView.setNodeProgressAdapter(new NodeProgressAdapter() {
            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public List<LogisticsData> getData() {
                return list;
            }
        });
    }
}
