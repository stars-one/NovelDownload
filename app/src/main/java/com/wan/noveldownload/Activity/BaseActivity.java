package com.wan.noveldownload;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            initParam(savedInstanceState);
        } else if (getIntent() != null && getIntent().getExtras() != null) {
            initParam(getIntent().getExtras());
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        if (savedInstanceState != null) {
            initParam(savedInstanceState);
        } else if (getIntent() != null && getIntent().getExtras() != null) {
            initParam(getIntent().getExtras());
        }
    }

    /**
     * 找到控件的实�??
     *
     * @param id  控件的id
     * @param <T> 相关的控件类�??
     * @return 返回已转型的控件View对象实例
     */
    protected <T extends View> T findView(int id) {
        return (T) findViewById(id);
    }

    /**
     * 处理activity中的传�?�内�??
     *
     * @param bundle
     */
    protected void initParam(Bundle bundle) {

    }

    /**
     * 设置contentView
     *
     * @param layoutResID 布局资源id
     */
    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        initData();
        initView();

    }

    /**
     * 设置contentView
     *
     * @param view �??个View对象
     */
    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        initData();
        initView();

    }

    /**
     * 初始化数�??
     */
    public abstract void initData();

    /**
     * 初始化View
     */
    public abstract void initView();

    /**
     * 显示出Toast
     *
     * @param s 内容
     */
    public void showToast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    public void startActivity(Class<?> clz) {
        startActivity(clz, null);
    }

    /**
     * 携带数据的页面跳�??
     *
     * @param clz    class
     * @param bundle Bundle
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 含有Bundle通过Class打开编辑界面
     *
     * @param cls         class文件
     * @param bundle      Bundle
     * @param requestCode requestCode
     */
    public void startActivityForResult(Class<?> cls, Bundle bundle,
                                       int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }
}
