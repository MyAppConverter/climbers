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

package com.myappconverter.mobile.game.impl;

import com.myappconverter.java.applicationservices.CGPoint;
import com.myappconverter.java.foundations.NSString;

public class Grab extends com.myappconverter.mobile.game.Grab {
	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @return Grab.
	 * @generated
	 */
	public Grab initWithPosition(CGPoint pos) {
		super.initWithSpriteFrameName(new NSString("grab.png"));
		this.setPosition(pos);

		return this;
	}

}
