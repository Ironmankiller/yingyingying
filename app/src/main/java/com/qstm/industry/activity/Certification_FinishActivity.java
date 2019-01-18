package com.qstm.industry.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import com.qstm.industry.Model.Bean.StepBean;
import com.qstm.industry.R;
import com.qstm.industry.View.StepView;


import java.util.ArrayList;
import java.util.List;

public class Certification_FinishActivity extends AppCompatActivity implements View.OnClickListener{
    private Button msure ;    //确定
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certification_finish);
        //获取实例
        msure=(Button)findViewById(R.id.bt_sure);
        //设置监听器
        msure.setOnClickListener(this);

        StepView setpview = (StepView) findViewById(R.id.step_view0);
        List<StepBean> stepsBeanList = new ArrayList<>();
        StepBean stepBean0 = new StepBean("身份认证",1);
        StepBean stepBean1 = new StepBean("擅长方向",1);
        StepBean stepBean2 = new StepBean("个人成就",1);
        StepBean stepBean3 = new StepBean("申请完成",0);

        stepsBeanList.add(stepBean0);
        stepsBeanList.add(stepBean1);
        stepsBeanList.add(stepBean2);
        stepsBeanList.add(stepBean3);

        setpview.setStepViewTexts(stepsBeanList)
                .setTextSize(16)//set textSize
                .setStepsViewIndicatorCompletedLineColor(ContextCompat.getColor(Certification_FinishActivity.this, android.R.color.white))//设置StepsViewIndicator完成线的颜色
                .setStepsViewIndicatorUnCompletedLineColor(ContextCompat.getColor(Certification_FinishActivity.this, R.color.uncompleted_text_color))//设置StepsViewIndicator未完成线的颜色
                .setStepViewComplectedTextColor(ContextCompat.getColor(Certification_FinishActivity.this, android.R.color.white))//设置StepsView text完成线的颜色
                .setStepViewUnComplectedTextColor(ContextCompat.getColor(Certification_FinishActivity.this, R.color.uncompleted_text_color))//设置StepsView text未完成线的颜色
                .setStepsViewIndicatorCompleteIcon(ContextCompat.getDrawable(Certification_FinishActivity.this, R.drawable.complted))//设置StepsViewIndicator CompleteIcon
                .setStepsViewIndicatorDefaultIcon(ContextCompat.getDrawable(Certification_FinishActivity.this, R.drawable.default_icon))//设置StepsViewIndicator DefaultIcon
                .setStepsViewIndicatorAttentionIcon(ContextCompat.getDrawable(Certification_FinishActivity.this, R.drawable.attention));//设置StepsViewIndicator AttentionIcon
    }
    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        finish();
    }
}
