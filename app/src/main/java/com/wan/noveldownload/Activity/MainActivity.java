package com.wan.noveldownload.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.didikee.donate.AlipayDonate;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.wan.noveldownload.BaseActivity;
import com.wan.noveldownload.R;


public class MainActivity extends BaseActivity implements View.OnClickListener {

    private CardView mQianbi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about:
                showAbout();
                break;
            case R.id.decription:
                break;
            case R.id.money:
                donateAlipay("fkx09316opdsamnwrivcud2");
                break;
            default:
                break;
        }
        return true;
    }

    private void showAbout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("关于").setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).setMessage("一款使用了爬虫技术下载小说的工具，由于是每一章每一章的爬取，所以下载速度不会很快。\n本软件在酷安网首发,后续会增加更多的网站支持哦，欢迎提出建议，喜欢的不妨给个赞赏呗~\n作者：柯兴新一").show();
    }
    /**
     * 支付宝支付
     * @param payCode 收款码后面的字符串；例如：收款二维码里面的字符串为 https://qr.alipay.com/stx00187oxldjvyo3ofaw60 ，则
     *                payCode = stx00187oxldjvyo3ofaw60
     *                注：不区分大小写
     */
    private void donateAlipay(String payCode) {
        boolean hasInstalledAlipayClient = AlipayDonate.hasInstalledAlipayClient(this);
        if (hasInstalledAlipayClient) {
            AlipayDonate.startAlipayClient(this, payCode);
        } else {
            Toast.makeText(this, "您的手机未安装支付宝哦", Toast.LENGTH_SHORT).show();
        } 
    }
    @Override
    public void initData() {

    }

    @Override
    public void initView() {

        mQianbi = (CardView) findViewById(R.id.qianbi);
        mQianbi.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.qianbi:
                startActivity(QianBiActivity.class);
                break;
            default:break;
        }
    }
}
