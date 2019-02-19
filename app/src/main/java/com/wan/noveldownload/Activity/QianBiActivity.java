package com.wan.noveldownload.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.wan.noveldownload.BaseActivity;
import com.wan.noveldownload.R;

import java.io.File;
import java.io.IOException;

import static com.wan.noveldownload.Util.DownloadTool.getBookName;
import static com.wan.noveldownload.Util.DownloadTool.getContent;
import static com.wan.noveldownload.Util.DownloadTool.getStartChapter;
import static com.wan.noveldownload.Util.DownloadTool.writeText;


public class QianBiActivity extends BaseActivity implements View.OnClickListener {

    private FloatingActionButton mBtn1;
    private TextView mJidu;
    private ConstraintLayout mDownloadLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qian_bi);
        initView();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {

        mBtn1 = (FloatingActionButton) findViewById(R.id.btn1);
        mBtn1.setOnClickListener(this);
        mJidu = (TextView) findViewById(R.id.jidu);
        mDownloadLayout = (ConstraintLayout) findViewById(R.id.downloadLayout);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                final TextInputEditText editText = new TextInputEditText(this);
                editText.setHint("输入小说网址");
                builder.setTitle("输入网址").setView(editText).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(QianBiActivity.this, "开始下载", Toast.LENGTH_SHORT).show();
                        final String url = editText.getText().toString();
                        mDownloadLayout.setVisibility(View.VISIBLE);
                        if (!TextUtils.isEmpty(url)) {
                            final String path = Environment.getExternalStorageDirectory() + "/" + "新一小说下载器";
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        downloadNovell(path, url);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    } catch (NullPointerException e) {
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Log.d("----更改---", "run: "+"运行,更改进度条");
                                                mDownloadLayout.setVisibility(View.GONE);
                                                Toast.makeText(QianBiActivity.this, "下载成功，文件保存在"+path, Toast.LENGTH_SHORT).show();
                                            }
                                        });

                                    }
                                }
                            }).start();
                            dialog.dismiss();

                        }


                    }
                }).show();
                break;
            default:
                break;
        }
    }

    /**
     * 下载小说
     *
     * @param path 下载的位置
     * @param url  小说网址
     */
    public  void downloadNovell(String path, String url) throws IOException {
        File file = new File(path, "/" + getBookName(url) + ".txt");
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        String firstUrl = getStartChapter(url);
        String[] s = getContent(firstUrl);
        String title = s[0];
        String content = s[1];
        String nextPage = s[2];

        final String title1 = s[0];
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.d("----更改---", "run: "+"运行更改text开头"+title1);
                mJidu.setText(title1);
            }
        });

        writeText(title, content, file);
        while (true) {

            if (nextPage == null) {
                break;
            } else {

                String[] temp = getContent(nextPage);
                title = temp[0];
                content = temp[1];
                nextPage = temp[2];
                //更改textview的内容
                final String title2 = temp[0];
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("----更改---", "run: "+"运行更改text第二个"+title2);
                        mJidu.setText(title2);
                    }
                });
                writeText(title, content, file);
            }

        }

    }
}