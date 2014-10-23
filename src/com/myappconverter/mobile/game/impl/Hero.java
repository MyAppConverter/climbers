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

import com.cocos2dx.wrappers.CCSpriteFrameCache;
import com.cocos2dx.wrappers.cocos2dxMapping;
import com.myappconverter.java.applicationservices.CGGeometry;
import com.myappconverter.java.applicationservices.CGPoint;
import com.myappconverter.java.foundations.NSString;

public class Hero extends com.myappconverter.mobile.game.Hero {
	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @return Hero.
	 * @generated
	 */
	public Hero initWithPosition(CGPoint pos) {
		super.initWithSpriteFrameName(new NSString("hero.png"));
		this.setPosition(pos);
		velocity = CGGeometry.CGPointZero;
		topGroundY = this.getPosition().getY();
		bottomGroundY = this.getPosition().getY();
		this.setState(Hero.kHeroStateIdle);

		return this;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public void setState(int s) {
		if (state == s) {
			return;
		}
		state = s;

		if (state == Hero.kHeroStateGrab) {
			this.setDisplayFrame(CCSpriteFrameCache.sharedSpriteFrameCache().spriteFrameByName(
					new NSString("heroGrab.png")));

		} else if (state == Hero.kHeroStateFall) {

			this.setDisplayFrame(CCSpriteFrameCache.sharedSpriteFrameCache().spriteFrameByName(
					new NSString("heroDrag.png")));

		} else {
			this.setDisplayFrame(CCSpriteFrameCache.sharedSpriteFrameCache()
					.spriteFrameByName(new NSString("hero.png")));
		}
		velocity = CGGeometry.CGPointZero;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public void update(float dt) {
		if (state == Hero.kHeroStateFall) {
			boolean overTop = false;
			if (this.getPosition().getY() > topGroundY) {
				overTop = true;
			}
			if (this.getPosition().getY() > bottomGroundY) {
				velocity = cocos2dxMapping.ccpAdd(velocity, CGGeometry.CGPointMake(0, -20.0f * dt));
				this.setPosition(cocos2dxMapping.ccpAdd(this.getPosition(), velocity));
			}
			if (overTop && this.getPosition().getY() <= topGroundY) {
				this.setPosition(CGGeometry.CGPointMake(this.getPosition().getX(), topGroundY));
				this.setState(Hero.kHeroStateIdle);
			} else {
				if (this.getPosition().getY() <= bottomGroundY) {
					this.setPosition(CGGeometry.CGPointMake(this.getPosition().getX(), bottomGroundY));
					this.setState(Hero.kHeroStateIdle);
				}
			}
		}
	}

}
