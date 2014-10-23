/*
 * Copyright (c) 2014 MyAppConverter
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the MyAppConverter License v1.0
 * which accompanies this distribution, and is available at
 * http://www.myappconverter.com/legal/epl-v1.html
 *
 * Contributors:
 *    MyAppConverter Core Team - initial API and implementation
 * @date : Aug, 25 2014 - 18:56:04
 */

package com.myappconverter.mobile;

import org.cocos2dx.lib.Cocos2dxActivity;
import org.cocos2dx.lib.Cocos2dxGLSurfaceView;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.RelativeLayout;

import com.cocos2dx.wrappers.CCDirector;
import com.cocos2dx.wrappers.CCEGLView;
import com.cocos2dx.wrappers.CCTexture2D;
import com.cocos2dx.wrappers.CCTexture2DPixelFormat;
import com.cocos2dx.wrappers.ccDeviceOrientation;
import com.cocos2dx.wrappers.ccDirectorType;
import com.myappconverter.java.uikit.UIWindow;
import com.myappconverter.mapping.utils.GenericMainContext;
import com.myappconverter.mobile.game.impl.Game;
import com.myappconverter.mobile.system.impl.RootViewController;

public class GameActivity extends Cocos2dxActivity {
	/**
	 * The cached value of the '<em>mGLView</em>' property.
	 * 
	 * @generated
	 * @ordered
	 */
	private Cocos2dxGLSurfaceView mGLView;

	/**
	 * The cached value of the '<em>MAIN_ACTIVITY</em>' property.
	 * 
	 * @generated
	 * @ordered
	 */
	public static Activity MAIN_ACTIVITY;

	/**
	 * The cached value of the '<em>window</em>' property.
	 * 
	 * @see #getWindow()
	 * @generated
	 * @ordered
	 */
	protected UIWindow window;

	/**
	 * Sets the value of the '{@link <em>window</em>}' property.
	 * 
	 * @param UIWindow the new value of the '<em>window</em>' property.
	 * @see #getWindow()
	 * @generated
	 */
	protected void setWindow(UIWindow window) {
		this.window = window;
	}

	/**
	 * The cached value of the '<em>viewController</em>' property.
	 * 
	 * @see #getViewController()
	 * @generated
	 * @ordered
	 */
	protected RootViewController viewController;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String packageName = getApplication().getPackageName();
		super.setPackageName(packageName);
		GenericMainContext.MAIN_CONTEXT_ACTIVITY = this;
		mGLView = new Cocos2dxGLSurfaceView(this);
		RelativeLayout r = new RelativeLayout(this);
		r.setLayoutParams(new LayoutParams(640, 960));
		r.addView(mGLView);
		setContentView(r);
		mGLView.setCocos2dxStarter(this);

	}

	@Override
	public void cocos2dxInit(int w, int h) {
		if (!CCDirector.setDirectorType(ccDirectorType.kCCDirectorTypeDisplayLink)) {
			CCDirector.setDirectorType(ccDirectorType.kCCDirectorTypeDefault);
		}
		CCDirector director = CCDirector.sharedDirector();
		viewController = new RootViewController();
		viewController.setWrappedActivity(this);
		viewController.initWithNibNameBundle(null, null);
		viewController.wantsFullScreenLayout();

		CCEGLView glView = CCEGLView.sharedOpenGLView();
		glView.setFrameWidthAndHeight(w, h);
		glView.create(w, h);
		director.setOpenGLView(glView);
		if (!director.enableRetinaDisplay(true)) {

		}
		director.setDeviceOrientation(ccDeviceOrientation.kCCDeviceOrientationPortrait);
		director.setAnimationInterval(1.0f / 60);
		director.setDisplayFPS(false);
		director.setDepthTest(false);
		CCTexture2D.setDefaultAlphaPixelFormat(CCTexture2DPixelFormat.kCCTexture2DPixelFormat_RGBA8888);
		Game game = new Game();
		CCDirector.sharedDirector().runWithScene(game.scene());

	}

	@Override
	protected void onPause() {
		super.onPause();
		mGLView.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		mGLView.onResume();
	}

	public void finish() {
		CCDirector.sharedDirector().end();
		mGLView.postDelayed(new Runnable() {
			public void run() {
				GameActivity.super.finish();
			}
		}, 100);

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			this.finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	static {
		System.loadLibrary("cocosdenshion");
		System.loadLibrary("game");

	}

}
