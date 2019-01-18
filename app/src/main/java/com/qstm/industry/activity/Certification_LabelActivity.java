package com.qstm.industry.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;


import com.qstm.industry.Model.Item;
import com.qstm.industry.Model.Bean.StepBean;
import com.qstm.industry.R;
import com.qstm.industry.View.StepView;
import com.qstm.industry.adapter.ClassifyAdapter;

import java.util.ArrayList;
import java.util.List;

public class Certification_LabelActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mnext ;    //下一步
    private List<Item> itemList=new ArrayList<>();
    private static final String TAG = "Certification_LabelActi";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode){
            case 1:
                if(resultCode==RESULT_OK){
                    //绑定activity_main布局文件中的布局项
                    LinearLayout linear=(LinearLayout) findViewById(R.id.linear1);
                    if(data.hasExtra("CHOOSE1")) {
                        String choose1 = data.getStringExtra("CHOOSE1");
                        Log.d(TAG, choose1);
                        // 将Button 1加入到LinearLayout 中
                        Button b1 = new Button(this);
                        b1.setText(choose1);
                        linear.addView(b1);
                    }
                    if(data.hasExtra("CHOOSE2")){
                        String choose2 = data.getStringExtra("CHOOSE2");
                        Log.d(TAG,choose2);
                        // 将Button 2 加入到LinearLayout 中
                        Button b2 = new Button(this);
                        b2.setText(choose2);
                        linear. addView ( b2 );
                    }

                    if(data.hasExtra("CHOOSE3")) {
                        String choose3 = data.getStringExtra("CHOOSE3");
                        Log.d(TAG, choose3);
                        // 将Button 3 加入到LinearLayout 中
                        Button b2 = new Button(this);
                        b2.setText(choose3);
                        linear. addView ( b2 );
                    }
                }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certification_label);
        initItems();
        ClassifyAdapter adapter=new ClassifyAdapter(Certification_LabelActivity.this,R.layout.list_item,itemList);
        ListView listView=(ListView) findViewById(R.id.list_label);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Item item=itemList.get(position);
                Intent intent = new Intent(Certification_LabelActivity.this, Certification_LabelChooseActivity.class);
                intent.putExtra("CHOOSE",item);
                startActivityForResult(intent,1);

            }
        });



        //获取实例
        mnext=(Button)findViewById(R.id.bt_next);
        //设置监听器
        mnext.setOnClickListener(this);

        StepView setpview = (StepView) findViewById(R.id.step_view0);
        List<StepBean> stepsBeanList = new ArrayList<>();
        StepBean stepBean0 = new StepBean("身份认证",1);
        StepBean stepBean1 = new StepBean("擅长方向",0);
        StepBean stepBean2 = new StepBean("个人成就",-1);
        StepBean stepBean3 = new StepBean("申请完成",-1);

        stepsBeanList.add(stepBean0);
        stepsBeanList.add(stepBean1);
        stepsBeanList.add(stepBean2);
        stepsBeanList.add(stepBean3);

        setpview.setStepViewTexts(stepsBeanList)
                .setTextSize(16)//set textSize
                .setStepsViewIndicatorCompletedLineColor(ContextCompat.getColor(Certification_LabelActivity.this, android.R.color.white))//设置StepsViewIndicator完成线的颜色
                .setStepsViewIndicatorUnCompletedLineColor(ContextCompat.getColor(Certification_LabelActivity.this, R.color.uncompleted_text_color))//设置StepsViewIndicator未完成线的颜色
                .setStepViewComplectedTextColor(ContextCompat.getColor(Certification_LabelActivity.this, android.R.color.white))//设置StepsView text完成线的颜色
                .setStepViewUnComplectedTextColor(ContextCompat.getColor(Certification_LabelActivity.this, R.color.uncompleted_text_color))//设置StepsView text未完成线的颜色
                .setStepsViewIndicatorCompleteIcon(ContextCompat.getDrawable(Certification_LabelActivity.this, R.drawable.complted))//设置StepsViewIndicator CompleteIcon
                .setStepsViewIndicatorDefaultIcon(ContextCompat.getDrawable(Certification_LabelActivity.this, R.drawable.default_icon))//设置StepsViewIndicator DefaultIcon
                .setStepsViewIndicatorAttentionIcon(ContextCompat.getDrawable(Certification_LabelActivity.this, R.drawable.attention));//设置StepsViewIndicator AttentionIcon
    }
    private void initItems(){
        for(int i=0;i<1;i++){
            Item apple1=new Item("软件");
            itemList.add(apple1);
            Item apple2=new Item("语文");
            itemList.add(apple2);
            Item apple3=new Item("数学");
            itemList.add(apple3);
            Item apple4=new Item("英语");
            itemList.add(apple4);
            Item apple5=new Item("物理");
            itemList.add(apple5);
            Item apple6=new Item("化学");
            itemList.add(apple6);
            Item apple7=new Item("生物");
            itemList.add(apple7);
            Item apple8=new Item("政治");
            itemList.add(apple8);
            Item apple9=new Item("历史");
            itemList.add(apple9);
            Item apple10=new Item("地理");
            itemList.add(apple10);
            Item apple11=new Item("语文");
            itemList.add(apple2);
            Item apple12=new Item("数学");
            itemList.add(apple3);
            Item apple13=new Item("英语");
            itemList.add(apple4);
            Item apple14=new Item("物理");
            itemList.add(apple5);
            Item apple15=new Item("化学");
            itemList.add(apple6);
            Item apple16=new Item("生物");
            itemList.add(apple7);
            Item apple17=new Item("政治");
            itemList.add(apple8);
            Item apple18=new Item("历史");
            itemList.add(apple9);
            Item apple19=new Item("地理");
            itemList.add(apple10);
        }
    }
    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        intent.setClass(Certification_LabelActivity.this, Certification_PatentActivity.class);
        startActivity(intent);
        finish();
    }
}
