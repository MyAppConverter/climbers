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

import com.cocos2dx.wrappers.cocos2dxMapping;
import com.myappconverter.java.applicationservices.CGGeometry;
import com.myappconverter.java.applicationservices.CGPoint;
import com.myappconverter.java.foundations.NSString;

public class Rock extends com.myappconverter.mobile.game.Rock {
	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @return Rock.
	 * @generated
	 */
	public Rock initWithPosition(CGPoint pos) {

		if (super.initWithSpriteFrameName(new NSString("rock.png"))) {
			this.setPosition(pos);
			velocity = CGGeometry.CGPointZero;
			falling = false;
		}
		return this;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public void update(float dt) {
		if (this.getPosition().getY() > 32.0f) {
			velocity = cocos2dxMapping.ccpAdd(velocity, CGGeometry.CGPointMake(0, -20.0f * dt));
			if (velocity.getY() < -10.0f) {
				velocity = CGGeometry.CGPointMake(0, -10);
			}
			this.setPosition(cocos2dxMapping.ccpAdd(this.getPosition(), velocity));
		} else {
			if (this.getPosition().getY() < 32.0f) {
				this.setPosition(CGGeometry.CGPointMake(this.getPosition().getX(), 32.0f));
				falling = false;
				velocity = CGGeometry.CGPointZero;
			}
		}
	}

}
