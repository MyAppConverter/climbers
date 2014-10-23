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

public abstract class Hero extends CCSprite {
	// Enum constant kHeroStateIdle
	public static final int kHeroStateIdle = 0;
	// Enum constant kHeroStateDrag
	public static final int kHeroStateDrag = 1;
	// Enum constant kHeroStateGrab
	public static final int kHeroStateGrab = 2;
	// Enum constant kHeroStateFall
	public static final int kHeroStateFall = 3;

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @return Hero.
	 * @generated
	 */
	public abstract Hero initWithPosition(CGPoint pos);

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
	public void setVelocity(CGPoint velocity) {
		this.velocity = velocity;
	}

	/**
	 * The cached value of the '<em>state</em>' property.
	 * 
	 * @see #getstate()
	 * @generated
	 * @ordered
	 */
	protected int state;

	/**
	 * Returns the value of the '<em><b>state</b></em>' property.
	 * 
	 * @return HeroState.
	 * @see #setState(HeroState)
	 * @generated
	 */
	public int getState() {
		return this.state;
	}

	/**
	 * Sets the value of the '{@link <em>state</em>}' property.
	 * 
	 * @param HeroState the new value of the '<em>state</em>' property.
	 * @see #getState()
	 * @generated
	 */
	public void setState(int state) {
		this.state = state;
	}

	/**
	 * The cached value of the '<em>topGroundY</em>' property.
	 * 
	 * @see #gettopGroundY()
	 * @generated
	 * @ordered
	 */
	protected float topGroundY;

	/**
	 * Returns the value of the '<em><b>topGroundY</b></em>' property.
	 * 
	 * @return float.
	 * @see #setTopGroundY(float)
	 * @generated
	 */
	public float getTopGroundY() {
		return this.topGroundY;
	}

	/**
	 * Sets the value of the '{@link <em>topGroundY</em>}' property.
	 * 
	 * @param float the new value of the '<em>topGroundY</em>' property.
	 * @see #getTopGroundY()
	 * @generated
	 */
	public void setTopGroundY(float topGroundY) {
		this.topGroundY = topGroundY;
	}

	/**
	 * The cached value of the '<em>bottomGroundY</em>' property.
	 * 
	 * @see #getbottomGroundY()
	 * @generated
	 * @ordered
	 */
	protected float bottomGroundY;

	/**
	 * Returns the value of the '<em><b>bottomGroundY</b></em>' property.
	 * 
	 * @return float.
	 * @see #setBottomGroundY(float)
	 * @generated
	 */
	protected float getBottomGroundY() {
		return this.bottomGroundY;
	}

	/**
	 * Sets the value of the '{@link <em>bottomGroundY</em>}' property.
	 * 
	 * @param float the new value of the '<em>bottomGroundY</em>' property.
	 * @see #getBottomGroundY()
	 * @generated
	 */
	protected void setBottomGroundY(float bottomGroundY) {
		this.bottomGroundY = bottomGroundY;
	}

}
