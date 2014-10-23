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

package com.myappconverter.mobile.verletrope.impl;

public class VPoint extends com.myappconverter.mobile.verletrope.VPoint {
	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public void setPos(float argX, float argY) {
		x = oldx = argX;
		y = oldy = argY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public void update() {
		float tempx = x;
		float tempy = y;
		x = x + (x - oldx);
		y = y + (y - oldy);
		oldx = tempx;
		oldy = tempy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public void applyGravity(float dt) {
		y = y - (10.0f * dt);
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public void applyMinY(float minY) {
		if (y < minY) {
			y = minY;
			oldx = x;
			oldy = y;
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public void setX(float argX) {
		x = argX;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public void setY(float argY) {
		y = argY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @return float.
	 * @generated
	 */
	public float getX() {
		return x;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @return float.
	 * @generated
	 */
	public float getY() {
		return y;
	}

}
