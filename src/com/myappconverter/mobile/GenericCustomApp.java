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

import android.app.Application;

import com.cocos2dx.wrappers.CCDirector;
import com.myappconverter.java.uikit.UIWindow;
import com.myappconverter.mobile.system.impl.RootViewController;

public class GenericCustomApp extends Application {

	/**
	 * The cached value of the '<em>window</em>' property.
	 * 
	 * @see #getWindow()
	 * @generated
	 * @ordered
	 */
	protected UIWindow window;

	/**
	 * Returns the value of the '<em><b>window</b></em>' property.
	 * 
	 * @return UIWindow.
	 * @see #setWindow(UIWindow)
	 * @generated
	 */
	protected UIWindow getWindow() {
		return this.window;
	}

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

	@Override
	public void onTerminate() {
		super.onTerminate();
		CCDirector director = CCDirector.sharedDirector();
		director.getOpenGLView().delete();
		director.end();

	}

}
