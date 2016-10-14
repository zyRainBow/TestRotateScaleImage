package com.example.testrotateimage;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class MainActivity extends Activity implements OnSeekBarChangeListener{

	private ImageView imageView;
	private TextView textView;
	private TextView textView2;
	private SeekBar seekBar;
	private SeekBar seekBar2;

	// 最小的缩放宽度
	private int minWidth = 80;

	// 矩阵类,用于对图像进行旋转
	private Matrix matrix = new Matrix();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		imageView = (ImageView) findViewById(R.id.imageView1);
		textView = (TextView) findViewById(R.id.textView1);
		seekBar = (SeekBar) findViewById(R.id.seekBar1);
		textView2 = (TextView) findViewById(R.id.textView2);
		seekBar2 = (SeekBar) findViewById(R.id.seekBar2);
		
		seekBar.setOnSeekBarChangeListener(this);
		seekBar2.setOnSeekBarChangeListener(this);

		// 创建一个空的展示矩阵，用于存储当前屏幕信息，如大小。
		DisplayMetrics displayMetrics = new DisplayMetrics();
		// 把当前管理的屏幕尺寸传递给displayMetrics
		getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		
		Log.i("displayMetrics", displayMetrics+"");
		
	}


	@SuppressLint("NewApi")
	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		// TODO Auto-generated method stub
		if (seekBar.getId() == R.id.seekBar1) {
			// imageView自身的setRotation方法是绕图的中心顺时针防线旋转
			// 同时该旋转不会改变imageView的输出大小，所超出imageView的部分会照常显示在所跨越视图的下方
			imageView.setRotation(progress);

			// 如果想创建一个不超出imageView输出框架的旋转图像，那么需要把原图像取出，把原图像进行旋转，重新放入到imageView中
			// 缺陷：占用系统较多的内存，会比较卡
			Log.i("progress", "" + progress);

			// Bitmap bitmap = ((BitmapDrawable) (getResources()
			// .getDrawable(R.drawable.ic_launcher))).getBitmap();
			// matrix.setRotate(progress);
			// bitmap = Bitmap.createBitmap(bitmap, 0, 0,
			// bitmap.getWidth(), bitmap.getHeight(), matrix, true);
			// imageView.setImageBitmap(bitmap);

			textView.setText("图片旋转" + progress + "度");
		}else if(seekBar.getId() == R.id.seekBar2) {
			int mwidth = progress + minWidth;
			int mheight = (int) (2 * mwidth / 3);
			// 设置imageView的输出大小参数
			// 因为图片是fitcenter参数，所以图片会随着image框架的大小变化而变化
			// 从而改变image框架的大小会引起图片大小的变化，实现图片的放大缩小功能
			imageView.setLayoutParams(new LinearLayout.LayoutParams(
					mwidth, mheight));
			textView2.setText("图片宽度：" + mwidth + "\t图片高度：" + mheight);
		}
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
}
