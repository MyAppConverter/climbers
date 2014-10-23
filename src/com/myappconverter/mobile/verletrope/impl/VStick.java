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

import com.cocos2dx.wrappers.cocos2dxMapping;
import com.myappconverter.java.applicationservices.CGGeometry;

public class VStick extends com.myappconverter.mobile.verletrope.VStick {
	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @return VStick.
	 * @generated
	 */
	public VStick initWith(VPoint argA, VPoint argB) {
		super.init();
		pointA = argA;
		pointB = argB;
		hypotenuse = cocos2dxMapping.ccpDistance(CGGeometry.CGPointMake(pointA.getX(), pointA.getY()),
				CGGeometry.CGPointMake(pointB.getX(), pointB.getY()));
		return this;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public void contract() {

		float dx = pointB.getX() - pointA.getX();
		float dy = pointB.getY() - pointA.getY();
		float h = cocos2dxMapping.ccpDistance(CGGeometry.CGPointMake(pointA.getX(), pointA.getY()),
				CGGeometry.CGPointMake(pointB.getX(), pointB.getY()));
		float diff = hypotenuse - h;
		float offx = (diff * dx / h) * 0.5f;
		float offy = (diff * dy / h) * 0.5f;

		pointA.setX(pointA.getX() - offx);
		pointA.setY(pointA.getY() - offy);
		pointB.setX(pointB.getX() + offx);
		pointB.setY(pointB.getY() + offy);

	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @return VPoint.
	 * @generated
	 */
	public VPoint getPointA() {
		return pointA;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @return VPoint.
	 * @generated
	 */
	public VPoint getPointB() {
		return pointB;
	}

}
