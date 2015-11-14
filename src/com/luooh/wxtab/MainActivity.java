package com.luooh.wxtab;

import java.util.ArrayList;
import java.util.List;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.annotation.SuppressLint;
import android.graphics.Color;

public class MainActivity extends FragmentActivity implements View.OnClickListener, ViewPager.OnPageChangeListener
{
	private ImageView iv_contact_press, iv_contact_normal;
	private ImageView iv_message_normal, iv_message_press;
	private ImageView iv_discovery_normal, iv_discovery_press;
	private ImageView iv_mine_normal, iv_mine_press;
	private TextView tv_contact, tv_message;
	private TextView tv_discovery, tv_mine;
	private List<ImageView> iv_normals;
	private List<ImageView> iv_presss;
	private List<TextView> list_tvs;
	private ViewPager viewpager;
	private Handler handler;
	
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initView();
		initFragment();
		setListener();
	}
	
	private void initView() 
	{
		handler = new Handler();
		iv_normals = new ArrayList<ImageView>();
		iv_presss = new ArrayList<ImageView>();
		list_tvs = new ArrayList<TextView>();
		
		viewpager = (ViewPager) findViewById(R.id.viewpager);
		iv_message_normal = (ImageView) findViewById(R.id.iv_message_normal);
		iv_contact_normal = (ImageView) findViewById(R.id.iv_contact_normal);
		iv_discovery_normal = (ImageView) findViewById(R.id.iv_discovery_normal);
		iv_mine_normal = (ImageView) findViewById(R.id.iv_mine_normal);
		
		iv_message_press = (ImageView) findViewById(R.id.iv_message_press);
		iv_contact_press = (ImageView) findViewById(R.id.iv_contact_press);
		iv_discovery_press = (ImageView) findViewById(R.id.iv_discovery_press);
		iv_mine_press = (ImageView) findViewById(R.id.iv_mine_press);
		
		tv_message = (TextView) findViewById(R.id.tv_message);
		tv_contact = (TextView) findViewById(R.id.tv_contact);
		tv_discovery = (TextView) findViewById(R.id.tv_discovery);
		tv_mine = (TextView) findViewById(R.id.tv_mine);
		//
		list_tvs.add(tv_message);
		list_tvs.add(tv_contact);
		list_tvs.add(tv_discovery);
		list_tvs.add(tv_mine);
		//
		iv_normals.add(iv_message_normal);
		iv_normals.add(iv_contact_normal);
		iv_normals.add(iv_discovery_normal);
		iv_normals.add(iv_mine_normal);
		//
		iv_presss.add(iv_message_press);
		iv_presss.add(iv_contact_press);
		iv_presss.add(iv_discovery_press);
		iv_presss.add(iv_mine_press);
		
	}
	
	private void initFragment() {
		viewpager.setOnPageChangeListener(this);
		MyAdapter adapter = new MyAdapter(getSupportFragmentManager());
		adapter.notifyDataSetChanged();
		viewpager.setAdapter(adapter);
		viewpager.setCurrentItem(0);
		viewpager.setOffscreenPageLimit(4);
	}
	
	private void setListener() {
		findViewById(R.id.tab0).setOnClickListener(this);
		findViewById(R.id.tab1).setOnClickListener(this);
		findViewById(R.id.tab2).setOnClickListener(this);
		findViewById(R.id.tab3).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) 
	{
		switch(v.getId())
		{ 
			case R.id.tab0:
				viewpager.setCurrentItem(0, false);
				break;
			case R.id.tab1:
				viewpager.setCurrentItem(1, false);
				break;
			case R.id.tab2:
				viewpager.setCurrentItem(2, false);
				break;
			case R.id.tab3:
				viewpager.setCurrentItem(3, false);
				break;
		}
	}
	
	private class MyAdapter extends FragmentPagerAdapter 
	{
		public MyAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			if(position == 2)
				return new ChildrenFragment();
			return new TestFragment(position);
		}

		@Override
		public int getCount() {
			return 4;
		}
	}

	@Override
	public void onPageScrollStateChanged(int position) {
	}

	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
			changeTabColor(position, positionOffset);
		}
	}

	@Override
	public void onPageSelected(int position) {
		setPageChange(position);
	}
	
	@SuppressLint("NewApi")
	public void setPageChange(final int position) 
	{
		handler.post(new Runnable()
		{
			public void run() {
				int text_color = Color.parseColor("#999999");
				for(int i = 0; i < 4; i++) {
					iv_normals.get(i).setAlpha(1.0f);
					iv_presss.get(i).setAlpha(0f);
					list_tvs.get(i).setTextColor(text_color);
				}
				list_tvs.get(position).setTextColor(Color.parseColor("#05bf8d"));
				iv_presss.get(position).setAlpha(1.0f);
				iv_normals.get(position).setAlpha(0f);
			}
		});
	}
	
	@SuppressLint("NewApi")
	private void changeTabColor(int position, float offset)
	{
		if(position == 3) return;
		int c0 = (Integer) evaluate(offset, Color.parseColor("#05bf8d"), Color.parseColor("#999999"));
        int c1 = (Integer) evaluate(offset, Color.parseColor("#999999"), Color.parseColor("#05bf8d"));
		
		list_tvs.get(position).setTextColor(c0);
		iv_normals.get(position).setAlpha(offset);
		iv_presss.get(position).setAlpha(1 - offset);
		
		list_tvs.get(position+1).setTextColor(c1);
		iv_normals.get(position+1).setAlpha(1 - offset);
		iv_presss.get(position+1).setAlpha(offset);
	}
	
	public Object evaluate(float fraction, Object startValue, Object endValue) 
	{
		int startInt = (Integer) startValue;
        int startA = (startInt >> 24) & 0xff;
        int startR = (startInt >> 16) & 0xff;
        int startG = (startInt >> 8) & 0xff;
        int startB = startInt & 0xff;

        int endInt = (Integer) endValue;
        int endA = (endInt >> 24) & 0xff;
        int endR = (endInt >> 16) & 0xff;
        int endG = (endInt >> 8) & 0xff;
        int endB = endInt & 0xff;

        return (int) ((startA + (int) (fraction * (endA - startA))) << 24) |
               (int) ((startR + (int) (fraction * (endR - startR))) << 16) |
               (int) ((startG + (int) (fraction * (endG - startG))) << 8)  |
               (int) ((startB + (int) (fraction * (endB - startB))));
	}
}
