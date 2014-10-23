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

package com.myappconverter.mobile.game;

import com.cocos2dx.wrappers.CCSprite;
import com.myappconverter.java.applicationservices.CGPoint;

public abstract class Star extends CCSprite {
	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @return Star.
	 * @generated
	 */
	public abstract Star initWithPosition(CGPoint pos);

	/**
	 * The cached value of the '<em>collected</em>' property.
	 * 
	 * @see #getcollected()
	 * @generated
	 * @ordered
	 */
	protected boolean collected;

	/**
	 * Returns the value of the '<em><b>collected</b></em>' property.
	 * 
	 * @return boolean.
	 * @see #setCollected(boolean)
	 * @generated
	 */
	public boolean getCollected() {
		return this.collected;
	}

	/**
	 * Sets the value of the '{@link <em>collected</em>}' property.
	 * 
	 * @param boolean the new value of the '<em>collected</em>' property.
	 * @see #getCollected()
	 * @generated
	 */
	public void setCollected(boolean collected) {
		this.collected = collected;
	}

}
