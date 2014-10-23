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
import com.myappconverter.mobile.verletrope.impl.VPoint;

public abstract class VStick extends NSObject {

	/**
	 * The cached value of the '<em>pointA</em>' property.
	 * 
	 * @see #getPointA()
	 * @generated
	 * @ordered
	 */

	protected VPoint pointA;

	/**
	 * The cached value of the '<em>pointB</em>' property.
	 * 
	 * @see #getPointB()
	 * @generated
	 * @ordered
	 */

	protected VPoint pointB;

	/**
	 * The cached value of the '<em>hypotenuse</em>' property.
	 * 
	 * @see #getHypotenuse()
	 * @generated
	 * @ordered
	 */

	protected float hypotenuse;

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @return VStick.
	 * @generated
	 */
	public abstract VStick initWith(VPoint argA, VPoint argB);

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public abstract void contract();

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @return VPoint.
	 * @generated
	 */
	public abstract VPoint getPointA();

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @return VPoint.
	 * @generated
	 */
	public abstract VPoint getPointB();
}
