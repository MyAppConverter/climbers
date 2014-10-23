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

package com.myappconverter.mobile.system.impl;

import com.myappconverter.java.uikit.UIApplication.UIInterfaceOrientation;

public class RootViewController extends com.myappconverter.mobile.system.RootViewController {
	public RootViewController() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @return boolean.
	 * @generated
	 */
	public boolean shouldAutorotateToInterfaceOrientation(UIInterfaceOrientation interfaceOrientation) {
		return (interfaceOrientation == UIInterfaceOrientation.UIInterfaceOrientationPortrait);
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public void didReceiveMemoryWarning() {
		super.didReceiveMemoryWarning();
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public void viewDidUnload() {
		super.viewDidUnload();
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public void dealloc() {
		super.dealloc();
	}

}
