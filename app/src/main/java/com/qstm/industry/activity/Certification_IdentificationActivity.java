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

public class Certification_IdentificationActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mnext ;    //下一步
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certification_identification);
        //获取实例
        mnext=(Button)findViewById(R.id.bt_next);
        //设置监听器
        mnext.setOnClickListener(this);

        StepView setpview = (StepView) findViewById(R.id.step_view0);
        List<StepBean> stepsBeanList = new ArrayList<>();
        StepBean stepBean0 = new StepBean("身份认证",0);
        StepBean stepBean1 = new StepBean("擅长方向",-1);
        StepBean stepBean2 = new StepBean("个人成就",-1);
        StepBean stepBean3 = new StepBean("申请完成",-1);

        stepsBeanList.add(stepBean0);
        stepsBeanList.add(stepBean1);
        stepsBeanList.add(stepBean2);
        stepsBeanList.add(stepBean3);

        setpview.setStepViewTexts(stepsBeanList)
                .setTextSize(16)//set textSize
                .setStepsViewIndicatorCompletedLineColor(ContextCompat.getColor(Certification_IdentificationActivity.this, android.R.color.white))//设置StepsViewIndicator完成线的颜色
                .setStepsViewIndicatorUnCompletedLineColor(ContextCompat.getColor(Certification_IdentificationActivity.this, R.color.uncompleted_text_color))//设置StepsViewIndicator未完成线的颜色
                .setStepViewComplectedTextColor(ContextCompat.getColor(Certification_IdentificationActivity.this, android.R.color.white))//设置StepsView text完成线的颜色
                .setStepViewUnComplectedTextColor(ContextCompat.getColor(Certification_IdentificationActivity.this, R.color.uncompleted_text_color))//设置StepsView text未完成线的颜色
                .setStepsViewIndicatorCompleteIcon(ContextCompat.getDrawable(Certification_IdentificationActivity.this, R.drawable.complted))//设置StepsViewIndicator CompleteIcon
                .setStepsViewIndicatorDefaultIcon(ContextCompat.getDrawable(Certification_IdentificationActivity.this, R.drawable.default_icon))//设置StepsViewIndicator DefaultIcon
                .setStepsViewIndicatorAttentionIcon(ContextCompat.getDrawable(Certification_IdentificationActivity.this, R.drawable.attention));//设置StepsViewIndicator AttentionIcon

    }
    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        intent.setClass(Certification_IdentificationActivity.this, Certification_LabelActivity.class);
        startActivity(intent);
        finish();
    }
}
