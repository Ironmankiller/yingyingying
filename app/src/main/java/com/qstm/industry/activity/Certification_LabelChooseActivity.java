package com.qstm.industry.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.qstm.industry.Model.Item;
import com.qstm.industry.Model.Bean.TestBean;
import com.qstm.industry.R;
import com.qstm.industry.View.LabelsView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import static com.qstm.industry.R.id.labels;


public class Certification_LabelChooseActivity extends AppCompatActivity implements View.OnClickListener{
    private String[] software = {"Android","IOS","前端","后台","微信开发","游戏开发","Java","JavaScript","C++","PHP","Python","Swift"};
    //private String[] chemistry = new String[]{""}

    private String[] key = {"CHOOSE1","CHOOSE2","CHOOSE3"};
    private Button msure ;    //确定
    private LabelsView labelsView;
    private Map<String,Boolean> choosed=new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certification_labelchoose);
        //获取实例
        msure=(Button)findViewById(R.id.bt_sure);
        //设置监听器
        msure.setOnClickListener(this);
        labelsView = (LabelsView) findViewById(labels);

        Item item = (Item) getIntent().getSerializableExtra("CHOOSE");
        //Toast.makeText(Certification_LabelChooseActivity.this,"你点击的是:第"+item.getName()+"项item",Toast.LENGTH_SHORT).show();
        ArrayList<TestBean> testList = new ArrayList<>();
        switch (item.getName()){
            case "软件":
                for (int i=0;i<software.length;i++){
                    testList.add(new TestBean(software[i],i+1));
                    choosed.put(software[i],false);
                }
                break;
            case "语文":
                break;
            case "数学":
            case "英语":
            case "物理":
            case "化学":
            case "生物":
            case "政治":
            case "地理":
        }

        labelsView.setLabels(testList, new LabelsView.LabelTextProvider<TestBean>() {
            @Override
            public CharSequence getLabelText(TextView label, int position, TestBean data) {
                return data.getName();
            }
        });
        findViewById(R.id.btn_click).setOnClickListener(this);

        labelsView.setSelectType(LabelsView.SelectType.NONE);
        labelsView.setOnLabelClickListener(new LabelsView.OnLabelClickListener() {
            @Override
            public void onLabelClick(TextView label, Object data, int position) {
                TestBean testBean = (TestBean)data;
                if(choosed.get(testBean.getName())){
                    choosed.put(testBean.getName(),false);
                } else {
                    choosed.put(testBean.getName(),true);
                }
            }
        });

        labelsView.setSelectType(LabelsView.SelectType.MULTI);
        labelsView.setMaxSelect(3);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_click:
                break;
                case R.id.bt_sure:
                Intent intent = new Intent();
                int i=0;
                for(Map.Entry<String,Boolean> tag : choosed.entrySet()){
                    if(tag.getValue()){
                        intent.putExtra(key[i++],tag.getKey());
                    }
                }
                setResult(RESULT_OK,intent);
                finish();
                break;
        }
    }
}
