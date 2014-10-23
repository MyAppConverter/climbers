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

package com.myappconverter.mobile.verletrope;

import com.myappconverter.java.foundations.NSObject;

public abstract class VPoint extends NSObject {

	/**
	 * The cached value of the '<em>oldx</em>' property.
	 * 
	 * @see #getOldx()
	 * @generated
	 * @ordered
	 */

	protected float oldx;

	/**
	 * The cached value of the '<em>oldy</em>' property.
	 * 
	 * @see #getOldy()
	 * @generated
	 * @ordered
	 */

	protected float oldy;

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public abstract void setPos(float argX, float argY);

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public abstract void update();

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public abstract void applyGravity(float dt);

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public abstract void applyMinY(float minY);

	/**
	 * The cached value of the '<em>x</em>' property.
	 * 
	 * @see #getx()
	 * @generated
	 * @ordered
	 */
	protected float x;

	/**
	 * Returns the value of the '<em><b>x</b></em>' property.
	 * 
	 * @return float.
	 * @see #setX(float)
	 * @generated
	 */
	protected float getX() {
		return this.x;
	}

	/**
	 * Sets the value of the '{@link <em>x</em>}' property.
	 * 
	 * @param float the new value of the '<em>x</em>' property.
	 * @see #getX()
	 * @generated
	 */
	protected void setX(float x) {
		this.x = x;
	}

	/**
	 * The cached value of the '<em>y</em>' property.
	 * 
	 * @see #gety()
	 * @generated
	 * @ordered
	 */
	protected float y;

	/**
	 * Returns the value of the '<em><b>y</b></em>' property.
	 * 
	 * @return float.
	 * @see #setY(float)
	 * @generated
	 */
	protected float getY() {
		return this.y;
	}

	/**
	 * Sets the value of the '{@link <em>y</em>}' property.
	 * 
	 * @param float the new value of the '<em>y</em>' property.
	 * @see #getY()
	 * @generated
	 */
	protected void setY(float y) {
		this.y = y;
	}

}
