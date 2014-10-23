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

public abstract class Rock extends CCSprite {
	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @return Rock.
	 * @generated
	 */
	public abstract Rock initWithPosition(CGPoint pos);

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public abstract void update(float dt);

	/**
	 * The cached value of the '<em>velocity</em>' property.
	 * 
	 * @see #getvelocity()
	 * @generated
	 * @ordered
	 */
	protected CGPoint velocity;

	/**
	 * Returns the value of the '<em><b>velocity</b></em>' property.
	 * 
	 * @return CGPoint.
	 * @see #setVelocity(CGPoint)
	 * @generated
	 */
	public CGPoint getVelocity() {
		return this.velocity;
	}

	/**
	 * Sets the value of the '{@link <em>velocity</em>}' property.
	 * 
	 * @param CGPoint the new value of the '<em>velocity</em>' property.
	 * @see #getVelocity()
	 * @generated
	 */
	protected void setVelocity(CGPoint velocity) {
		this.velocity = velocity;
	}

	/**
	 * The cached value of the '<em>falling</em>' property.
	 * 
	 * @see #getfalling()
	 * @generated
	 * @ordered
	 */
	protected boolean falling;

	/**
	 * Returns the value of the '<em><b>falling</b></em>' property.
	 * 
	 * @return boolean.
	 * @see #setFalling(boolean)
	 * @generated
	 */
	public boolean getFalling() {
		return this.falling;
	}

	/**
	 * Sets the value of the '{@link <em>falling</em>}' property.
	 * 
	 * @param boolean the new value of the '<em>falling</em>' property.
	 * @see #getFalling()
	 * @generated
	 */
	public void setFalling(boolean falling) {
		this.falling = falling;
	}

}
