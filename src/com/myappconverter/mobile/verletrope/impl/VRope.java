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

import com.cocos2dx.wrappers.CCDirector;
import com.cocos2dx.wrappers.CCSprite;
import com.cocos2dx.wrappers.CCSpriteBatchNode;
import com.cocos2dx.wrappers.cocos2dxMapping;
import com.myappconverter.java.applicationservices.CGGeometry;
import com.myappconverter.java.applicationservices.CGPoint;
import com.myappconverter.java.applicationservices.CGSize;
import com.myappconverter.java.foundations.NSMutableArray;
import com.myappconverter.java.glkit.MACGLES20;

public class VRope extends com.myappconverter.mobile.verletrope.VRope {
	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @return VRope.
	 * @generated
	 */
	public VRope initWithPoints(CGPoint pointA, CGPoint pointB, CCSpriteBatchNode spriteSheetArg) {
		super.init();
		spriteSheet = spriteSheetArg;
		this.createRope(pointA, pointB);

		return this;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */

	public void createRope(CGPoint pointA, CGPoint pointB) {
		vPoints = new NSMutableArray<VPoint>();
		vPoints.init();
		vSticks = new NSMutableArray<VStick>();
		vSticks.init();
		ropeSprites = new NSMutableArray<CCSprite>();
		ropeSprites.init();
		float distance = cocos2dxMapping.ccpDistance(pointA, pointB);
		int segmentFactor = 8;
		numPoints = (int) distance / segmentFactor;
		CGPoint diffVector = cocos2dxMapping.ccpSub(pointB, pointA);
		float multiplier = distance / (numPoints - 1);
		antiSagHack = 0.1f;
		for (int i = 0; i < numPoints; i++) {
			CGPoint tmpVector = cocos2dxMapping.ccpAdd(
					pointA,
					cocos2dxMapping.ccpMult(cocos2dxMapping.ccpNormalize(diffVector), multiplier * i
							* (1 - antiSagHack)));
			VPoint tmpPoint = new VPoint();
			tmpPoint.setPos(tmpVector.getX(), tmpVector.getY());
			vPoints.addObject(tmpPoint);
		}
		for (int i = 0; i < numPoints - 1; i++) {
			VStick tmpStick = (new VStick()).initWith(vPoints.objectAtIndex(i), vPoints.objectAtIndex(i + 1));
			vSticks.addObject(tmpStick);
		}
		if (spriteSheet != null) {
			for (int i = 0; i < numPoints - 1; i++) {
				VPoint point1 = vSticks.objectAtIndex(i).getPointA();
				VPoint point2 = vSticks.objectAtIndex(i).getPointB();
				CGPoint stickVector = cocos2dxMapping.ccpSub(CGGeometry.CGPointMake(point1.getX(), point1.getY()),
						CGGeometry.CGPointMake(point2.getX(), point2.getY()));
				float stickAngle = cocos2dxMapping.ccpToAngle(stickVector);
				CCSprite tmpSprite = CCSprite.spriteWithBatchNode(
						spriteSheet,
						CGGeometry.CGRectMake(0, 0, multiplier, spriteSheet.getTextureAtlas().getTexture()
								.getPixelsHigh()));
				tmpSprite.setPosition(cocos2dxMapping.ccpMidpoint(CGGeometry.CGPointMake(point1.getX(), point1.getY()),
						CGGeometry.CGPointMake(point2.getX(), point2.getY())));
				tmpSprite.setRotation(-1 * ((stickAngle) * 57.29578f));
				spriteSheet.addChild(tmpSprite);
				ropeSprites.addObject(tmpSprite);
			}
		}

	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public void resetWithPoints(CGPoint pointA, CGPoint pointB) {
		float distance = cocos2dxMapping.ccpDistance(pointA, pointB);
		CGPoint diffVector = cocos2dxMapping.ccpSub(pointB, pointA);
		float multiplier = distance / (numPoints - 1);
		for (int i = 0; i < numPoints; i++) {
			CGPoint tmpVector = cocos2dxMapping.ccpAdd(
					pointA,
					cocos2dxMapping.ccpMult(cocos2dxMapping.ccpNormalize(diffVector), multiplier * i
							* (1 - antiSagHack)));
			VPoint tmpPoint = vPoints.objectAtIndex(i);
			tmpPoint.setPos(tmpVector.getX(), tmpVector.getY());
		}

	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public void removeSprites() {
		for (int i = 0; i < numPoints - 1; i++) {
			CCSprite tmpSprite = ropeSprites.objectAtIndex(i);
			spriteSheet.removeChild(tmpSprite, true);
		}
		ropeSprites.removeAllObjects();
		ropeSprites.release();

	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public void updateWithPoints(CGPoint pointA, CGPoint pointB, float dt) {
		dt = 0.0f;
		vPoints.objectAtIndex(0).setPos(pointA.getX(), pointA.getY());
		vPoints.objectAtIndex(numPoints - 1).setPos(pointB.getX(), pointB.getY());

		CGSize screenSize = CCDirector.sharedDirector().getWinSize();
		float sh = screenSize.getHeight();

		for (int i = 1; i < numPoints - 1; i++) {
			vPoints.objectAtIndex(i).applyGravity(dt);
			vPoints.objectAtIndex(i).update();
			vPoints.objectAtIndex(i).applyMinY(sh / 32);
		}
		int iterations = 12;

		for (int j = 0; j < iterations; j++) {
			for (int i = 0; i < numPoints - 1; i++) {
				vSticks.objectAtIndex(i).contract();
			}
		}

	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public void updateSprites() {
		if (spriteSheet != null) {
			for (int i = 0; i < numPoints - 1; i++) {
				VPoint point1 = vSticks.objectAtIndex(i).getPointA();
				VPoint point2 = vSticks.objectAtIndex(i).getPointB();
				CGPoint point1_ = CGGeometry.CGPointMake(point1.getX(), point1.getY());
				CGPoint point2_ = CGGeometry.CGPointMake(point2.getX(), point2.getY());
				float stickAngle = cocos2dxMapping.ccpToAngle(cocos2dxMapping.ccpSub(point1_, point2_));
				CCSprite tmpSprite = ropeSprites.objectAtIndex(i);
				tmpSprite.setPosition(cocos2dxMapping.ccpMidpoint(point1_, point2_));
				tmpSprite.setRotation(-(stickAngle * 57.29577951f));
			}
		}

	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public void debugDraw() {
		MACGLES20.glDisable(3553);
		MACGLES20.glDisableClientState(32888);
		MACGLES20.glColor4f(0.0f, 0.0f, 1.0f, 1.0f);
		MACGLES20.glLineWidth(5.0f);
		for (int i = 0; i < numPoints - 1; i++) {
			VPoint pointA = vSticks.objectAtIndex(i).getPointA();
			VPoint pointB = vSticks.objectAtIndex(i).getPointB();
			cocos2dxMapping.ccDrawLine(CGGeometry.CGPointMake(pointA.getX(), pointA.getY()),
					CGGeometry.CGPointMake(pointB.getX(), pointB.getY()));
		}
		MACGLES20.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		MACGLES20.glLineWidth(1);
		MACGLES20.glEnableClientState(32888);
		MACGLES20.glEnable(3553);
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public void dealloc() {
		for (int i = 0; i < numPoints; i++) {
			vPoints.objectAtIndex(i).release();
			if (i != numPoints - 1) {
				vSticks.objectAtIndex(i).release();
			}
		}
		vPoints.removeAllObjects();
		vSticks.removeAllObjects();

	}
}
