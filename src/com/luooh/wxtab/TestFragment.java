package com.luooh.wxtab;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

@SuppressLint("ValidFragment")
public class TestFragment extends Fragment 
{
	private int position;
	private TextView tv_test;
	
	@Override
	public void setArguments(Bundle bundle) {
		super.setArguments(bundle);
	}
	
	public TestFragment(int position) {
		this.position = position;
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_test, container, false);
		tv_test = (TextView) view.findViewById(R.id.tv_test);
		tv_test.setText("这是第" + (position + 1) + "个页面");
		return view;
	}
}
