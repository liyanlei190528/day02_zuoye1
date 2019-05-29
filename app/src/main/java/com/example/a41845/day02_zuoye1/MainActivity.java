package com.example.a41845.day02_zuoye1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.a41845.day02_zuoye1.adapter.MyAdapter;
import com.example.a41845.day02_zuoye1.bean.RootBean;
import com.example.a41845.day02_zuoye1.model.MyModelImpl;
import com.example.a41845.day02_zuoye1.presenter.MyPersenterImpl;
import com.example.a41845.day02_zuoye1.view.MyView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

//李艳雷  1810A
public class MainActivity extends AppCompatActivity implements MyView {

    private RecyclerView mRv;
    private SmartRefreshLayout mSrl;
    private ArrayList<RootBean.ResultsBean> list;
    private MyAdapter myAdapter;
    private int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        MyPersenterImpl myPersenter = new MyPersenterImpl(new MyModelImpl(), this);
        myPersenter.getdata(page);
    }

    private void initView() {
        mRv = (RecyclerView) findViewById(R.id.rv);
        mSrl = (SmartRefreshLayout) findViewById(R.id.srl);

        list = new ArrayList<>();
        myAdapter = new MyAdapter(this, list);
        mRv.setAdapter(myAdapter);
        mRv.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onScuuess(RootBean rootBean) {
        List<RootBean.ResultsBean> results = rootBean.getResults();
        list.addAll(results);
        myAdapter.notifyDataSetChanged();
    }

    @Override
    public void onField(String msg) {
        Toast.makeText(this, ""+msg, Toast.LENGTH_SHORT).show();
    }
}
