package com.luooh.wxtab;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

public class ParentViewPager extends ViewPager 
{
	public ParentViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	@Override
	protected boolean canScroll(View v, boolean checkV, int dx, int x, int y) {
		if(v != this && v instanceof ViewPager) {
			ViewPager childViewPager = ((ViewPager)v);
			if(childViewPager.getAdapter() == null) return true;
			int currentItem = childViewPager.getCurrentItem();
			int countItem = childViewPager.getAdapter().getCount();
			if((currentItem == (countItem - 1) && dx < 0) || (currentItem == 0 && dx > 0)) {
				return false;
			}
			return true;
		}
		return super.canScroll(v, checkV, dx, x, y);
	}
}
