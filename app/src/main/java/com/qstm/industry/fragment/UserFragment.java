package com.qstm.industry.fragment;



import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.qstm.industry.R;
import com.qstm.industry.activity.Certification_IdentificationActivity;
import com.qstm.industry.activity.LoginActivity;
import com.qstm.industry.activity.PersonalHomeActivity;


/**
 * 我的
 */
public class UserFragment extends Fragment  implements View.OnClickListener{
    private ImageView mHeadView;
    private Button mCertificationButton;    //我要认证
    private Button mZhuYe;          //个人主页
    private ImageButton mYeWu;              //我的业务
    private ImageButton mWenZhang;          //我的文章
    private ImageButton mTiWen;            //我的提问
    private ImageButton mHuiDa;             //我的回答
    private ImageButton mShouCang;         //我的收藏
    private ImageButton mGuanZhu;           //我的关注
    private ImageButton mLiuLan;           // 我的浏览
    private ImageButton mChengJiu;          //我的成就
    private ImageButton mQianBao;            //我的钱包
    private ImageButton mHelp;               //帮助
    private ImageButton mYeJian;             //夜间模式
    private ImageButton mConfig;             //设置

    private SharedPreferences login_sp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_user, container, false);
        login_sp = getActivity().getSharedPreferences("userInfo", 0);
        mCertificationButton = (Button)view.findViewById(R.id.user_renzheng);
        mZhuYe = (Button)view.findViewById(R.id.user_zhuye);
        mConfig = (ImageButton) view.findViewById(R.id.icon_shezhi);
        //在这里添加实例

        //设置监听器
        mCertificationButton.setOnClickListener(this);
        mZhuYe.setOnClickListener(this);

        mConfig.setOnClickListener(this);


        return view;
    }
    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.user_renzheng:
                intent.setClass(getActivity(), Certification_IdentificationActivity.class);
                startActivity(intent);
                break;
            case R.id.user_zhuye:
                intent.setClass(getActivity(), PersonalHomeActivity.class);
                startActivity(intent);
                break;
            case R.id.icon_shezhi:
                Toast.makeText(getActivity(),"注销成功",Toast.LENGTH_SHORT).show();
                SharedPreferences.Editor editor = login_sp.edit();
                editor.putBoolean("LOG_STATE",false);
                editor.commit();
                intent.setClass(getActivity(), LoginActivity.class);
                getActivity().startActivity(intent);
                getActivity().finish();
                break;
            default: break;
        }
    }
}
