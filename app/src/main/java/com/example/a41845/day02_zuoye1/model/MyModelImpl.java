package com.example.a41845.day02_zuoye1.model;

import com.example.a41845.day02_zuoye1.api.MyServer;
import com.example.a41845.day02_zuoye1.bean.RootBean;
import com.example.a41845.day02_zuoye1.callback.MyCallBack;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyModelImpl implements MyModel {
    @Override
    public void getdata(int page, final MyCallBack callBack) {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(MyServer.url)
                .build();
        MyServer myServer = retrofit.create(MyServer.class);
        Observable<RootBean> getdata = myServer.getdata(page);
        getdata.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RootBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RootBean rootBean) {
                        if (callBack !=null){
                             callBack.onScuuess(rootBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (callBack !=null){
                            callBack.onField(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
