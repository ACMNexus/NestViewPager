package com.luooh.wxtab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ChildrenFragment extends Fragment implements ViewPager.OnPageChangeListener
{
	private ParentViewPager vp_indicator;
	private ChilderAdapter adapter;
	private String titles[] = {"首页", "消息", "发现", "我的"};
	private SimpleViewPagerIndicator spi_indicator;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_children, container, false);
		initView(view);
		return view;
	}
	
	private void initView(View view) {
		spi_indicator = (SimpleViewPagerIndicator) view.findViewById(R.id.spi_indicator);
		vp_indicator = (ParentViewPager) view.findViewById(R.id.vp_indicator);
		adapter = new ChilderAdapter(getChildFragmentManager());
		vp_indicator.setOnPageChangeListener(this);
		spi_indicator.setTitles(titles);
		vp_indicator.setAdapter(adapter);
		spi_indicator.setViewpager(vp_indicator);
	}
	
	
	private class ChilderAdapter extends FragmentPagerAdapter
	{
		public ChilderAdapter(FragmentManager fm) {
			super(fm);
		}
		
		@Override
		public Fragment getItem(int position) {
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
		spi_indicator.scroll(position, positionOffset);
	}

	@Override
	public void onPageSelected(int position) {
	}
}
