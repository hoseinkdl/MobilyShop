package com.ynwa.kdl.hosein.shopping.mvp.profile;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageButton;
import android.widget.TextView;

import com.rd.PageIndicatorView;
import com.ynwa.kdl.hosein.shopping.MyUtils;
import com.ynwa.kdl.hosein.shopping.R;
import com.ynwa.kdl.hosein.shopping.activity_without_mvp.PhoneDetailActivity;
import com.ynwa.kdl.hosein.shopping.adapter.CustomViewPagerAdapter;
import com.ynwa.kdl.hosein.shopping.parcelable.PhoneParcel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfileActivity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener{


    private PhoneParcel phone;

    @BindView(R.id.appbar_profile)
    AppBarLayout appBar;

    @BindView(R.id.collapse_profile)
    CollapsingToolbarLayout collaps;

    @BindView(R.id.toolbar_profile)
    Toolbar toolbar;

    @BindView(R.id.view_pager_profile)
    ViewPager viewPager;

    @BindView(R.id.indicator_profile)
    PageIndicatorView indicator;


    @BindView(R.id.tv_info)
    TextView tvInfo;
    @BindView(R.id.tv_more_detail__profile_activity)
    TextView tvMoreDetail;

    @BindView(R.id.ic_favorite_toolbar_profile)
    ImageButton icFavorite;
    @BindView(R.id.ic_back_toolbar_profile)
    ImageButton icBack;
    @BindView(R.id.ic_buybasket_toolbar_profile)
    ImageButton icBuyBasket;

    @BindView(R.id.tv_name_profile)
    TextView tvName;

    @BindView(R.id.tv_brand_profile)
    TextView tvBrand;

    @BindView(R.id.tv_price_profile)
    TextView tvPrice;

    @BindView(R.id.tv_description_profile)
    TextView tvDescription;

    private boolean isShow;
    private int scrollRange = -1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        appBar.addOnOffsetChangedListener(this);

        phone = getIntent().getParcelableExtra(MyUtils.PHONE_ITEM_EXTRA);

        viewPager.setAdapter(new CustomViewPagerAdapter(this, phone.getOtherImg()));

        if (phone.getOtherImg().size() > 0){
            indicator.setCount( phone.getOtherImg().size() );
        }


        tvName.setText("گوشی مدل  "+ phone.getName());
        tvBrand.setText(phone.getBrand());
        tvPrice.setText(MyUtils.priceSeprator(phone.getPrice())+ " تومان");




        String strSimCard = "- "+ phone.getDetail().getSimNumber()+ "\n\n";
        String strScreen = "- صفحه نمایش "+ phone.getDetail().getScreenSize()+ "\n\n";
        String strGpu = "- گرافیک "+ phone.getDetail().getGpu()+ "\n\n";
        String strCpu = "- تراشه پردازنده "+ phone.getDetail().getCpuChip()+ "\n\n";
        String strRam = "- رم "+ phone.getDetail().getRamSize()+ "\n\n";
        String strCamera = "- دوربین اصلی "+ phone.getDetail().getCameraMain()+ "\n";

        tvInfo.setText(strSimCard+ strScreen+ strGpu+ strCpu+ strRam+ strCamera);

        if (phone.getDesc().isEmpty())
            tvDescription.setText("---");
        else
            tvDescription.setText(phone.getDesc());

    }

    @OnClick({R.id.ic_back_toolbar_profile})
    public void btnBack(){
        finish();
    }

    @OnClick(R.id.tv_more_detail__profile_activity)
    public void tvMoreDetail(){
        Intent intent = new Intent(this, PhoneDetailActivity.class);
        intent.putExtra(MyUtils.PHONE_ITEM_EXTRA, phone);
        startActivity(intent);
    }


    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

        if (scrollRange == -1) {
            scrollRange = appBarLayout.getTotalScrollRange();
            icBack.setColorFilter( getResources().getColor(R.color.gray_toolbar) );
            icBuyBasket.setColorFilter( getResources().getColor(R.color.gray_toolbar) );
        }
        if (scrollRange + verticalOffset == 0) {
            //collapse map
            //TODO: change share icon color - set white share icon
            isShow = true;
            //
            icBack.setColorFilter( getResources().getColor(R.color.white) );
            icBuyBasket.setColorFilter( getResources().getColor(R.color.white) );
        } else if (isShow) {
            //expanded map
            //TODO: change share icon color - set dark share icon
            isShow = false;
            icBack.setColorFilter( getResources().getColor(R.color.gray_toolbar) );
            icBuyBasket.setColorFilter( getResources().getColor(R.color.gray_toolbar) );
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
