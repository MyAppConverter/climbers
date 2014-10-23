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

import com.cocos2dx.wrappers.CCSprite;
import com.cocos2dx.wrappers.CCSpriteBatchNode;
import com.myappconverter.java.applicationservices.CGPoint;
import com.myappconverter.java.foundations.NSMutableArray;
import com.myappconverter.mobile.verletrope.impl.VPoint;

public abstract class VRope extends CCSprite {

	/**
	 * The cached value of the '<em>numPoints</em>' property.
	 * 
	 * @see #getNumPoints()
	 * @generated
	 * @ordered
	 */

	protected int numPoints;

	/**
	 * The cached value of the '<em>vPoints</em>' property.
	 * 
	 * @see #getVPoints()
	 * @generated
	 * @ordered
	 */

	protected NSMutableArray<VPoint> vPoints;

	/**
	 * The cached value of the '<em>vSticks</em>' property.
	 * 
	 * @see #getVSticks()
	 * @generated
	 * @ordered
	 */

	protected NSMutableArray<com.myappconverter.mobile.verletrope.impl.VStick> vSticks;

	/**
	 * The cached value of the '<em>ropeSprites</em>' property.
	 * 
	 * @see #getRopeSprites()
	 * @generated
	 * @ordered
	 */

	protected NSMutableArray<CCSprite> ropeSprites;

	/**
	 * The cached value of the '<em>spriteSheet</em>' property.
	 * 
	 * @see #getSpriteSheet()
	 * @generated
	 * @ordered
	 */

	protected CCSpriteBatchNode spriteSheet;

	/**
	 * The cached value of the '<em>antiSagHack</em>' property.
	 * 
	 * @see #getAntiSagHack()
	 * @generated
	 * @ordered
	 */

	protected float antiSagHack;

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @return VRope.
	 * @generated
	 */
	public abstract VRope initWithPoints(CGPoint pointA, CGPoint pointB, CCSpriteBatchNode spriteSheetArg);

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public abstract void createRope(CGPoint pointA, CGPoint pointB);

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public abstract void resetWithPoints(CGPoint pointA, CGPoint pointB);

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public abstract void updateWithPoints(CGPoint pointA, CGPoint pointB, float dt);

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public abstract void debugDraw();

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public abstract void updateSprites();

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public abstract void removeSprites();
}
