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
 * @date : Aug, 25 2014 - 18:56:03
 */

package com.myappconverter.mobile.game;

import com.cocos2dx.wrappers.CCLabelBMFont;
import com.cocos2dx.wrappers.CCLayer;
import com.cocos2dx.wrappers.CCSprite;
import com.cocos2dx.wrappers.CCSpriteBatchNode;
import com.myappconverter.java.applicationservices.CGPoint;
import com.myappconverter.java.foundations.NSMutableArray;
import com.myappconverter.java.foundations.NSTimer;
import com.myappconverter.mobile.game.impl.Grab;
import com.myappconverter.mobile.game.impl.Star;
import com.myappconverter.mobile.verletrope.impl.VRope;

public abstract class Game extends CCLayer {

	/**
	 * The cached value of the '<em>batch1</em>' property.
	 * 
	 * @see #getBatch1()
	 * @generated
	 * @ordered
	 */

	protected CCSpriteBatchNode batch1;

	/**
	 * The cached value of the '<em>batch2</em>' property.
	 * 
	 * @see #getBatch2()
	 * @generated
	 * @ordered
	 */

	protected CCSpriteBatchNode batch2;

	/**
	 * The cached value of the '<em>ropeBatch</em>' property.
	 * 
	 * @see #getRopeBatch()
	 * @generated
	 * @ordered
	 */

	protected CCSpriteBatchNode ropeBatch;

	/**
	 * The cached value of the '<em>hero1</em>' property.
	 * 
	 * @see #getHero1()
	 * @generated
	 * @ordered
	 */

	public Hero hero1;

	/**
	 * The cached value of the '<em>hero2</em>' property.
	 * 
	 * @see #getHero2()
	 * @generated
	 * @ordered
	 */

	protected Hero hero2;

	/**
	 * The cached value of the '<em>rope</em>' property.
	 * 
	 * @see #getRope()
	 * @generated
	 * @ordered
	 */

	protected VRope rope;

	/**
	 * The cached value of the '<em>grabs</em>' property.
	 * 
	 * @see #getGrabs()
	 * @generated
	 * @ordered
	 */

	protected NSMutableArray<Grab> grabs;

	/**
	 * The cached value of the '<em>stars</em>' property.
	 * 
	 * @see #getStars()
	 * @generated
	 * @ordered
	 */

	protected NSMutableArray<Star> stars;

	/**
	 * The cached value of the '<em>dragInProgress</em>' property.
	 * 
	 * @see #getDragInProgress()
	 * @generated
	 * @ordered
	 */

	protected boolean dragInProgress;

	/**
	 * The cached value of the '<em>dragHero</em>' property.
	 * 
	 * @see #getDragHero()
	 * @generated
	 * @ordered
	 */

	protected Hero dragHero;

	/**
	 * The cached value of the '<em>dragOtherHero</em>' property.
	 * 
	 * @see #getDragOtherHero()
	 * @generated
	 * @ordered
	 */

	protected Hero dragOtherHero;

	/**
	 * The cached value of the '<em>dragOffset</em>' property.
	 * 
	 * @see #getDragOffset()
	 * @generated
	 * @ordered
	 */

	protected CGPoint dragOffset;

	/**
	 * The cached value of the '<em>cameraOffset</em>' property.
	 * 
	 * @see #getCameraOffset()
	 * @generated
	 * @ordered
	 */

	protected CGPoint cameraOffset;

	/**
	 * The cached value of the '<em>levelHeight</em>' property.
	 * 
	 * @see #getLevelHeight()
	 * @generated
	 * @ordered
	 */

	protected float levelHeight;

	/**
	 * The cached value of the '<em>gameInProgress</em>' property.
	 * 
	 * @see #getGameInProgress()
	 * @generated
	 * @ordered
	 */

	protected boolean gameInProgress;

	/**
	 * The cached value of the '<em>currentLevel</em>' property.
	 * 
	 * @see #getCurrentLevel()
	 * @generated
	 * @ordered
	 */

	protected int currentLevel;

	/**
	 * The cached value of the '<em>nextLevel</em>' property.
	 * 
	 * @see #getNextLevel()
	 * @generated
	 * @ordered
	 */

	protected int nextLevel;

	/**
	 * The cached value of the '<em>startPosition1</em>' property.
	 * 
	 * @see #getStartPosition1()
	 * @generated
	 * @ordered
	 */

	protected CGPoint startPosition1;

	/**
	 * The cached value of the '<em>startPosition2</em>' property.
	 * 
	 * @see #getStartPosition2()
	 * @generated
	 * @ordered
	 */

	protected CGPoint startPosition2;

	/**
	 * The cached value of the '<em>snapFeedback</em>' property.
	 * 
	 * @see #getSnapFeedback()
	 * @generated
	 * @ordered
	 */

	protected CCSprite snapFeedback;

	/**
	 * The cached value of the '<em>rock</em>' property.
	 * 
	 * @see #getRock()
	 * @generated
	 * @ordered
	 */

	protected Rock rock;

	/**
	 * The cached value of the '<em>rockAlert</em>' property.
	 * 
	 * @see #getRockAlert()
	 * @generated
	 * @ordered
	 */

	protected CCSprite rockAlert;

	/**
	 * The cached value of the '<em>lastTouchLocation</em>' property.
	 * 
	 * @see #getLastTouchLocation()
	 * @generated
	 * @ordered
	 */

	protected CGPoint lastTouchLocation;

	/**
	 * The cached value of the '<em>rockTimer</em>' property.
	 * 
	 * @see #getRockTimer()
	 * @generated
	 * @ordered
	 */

	protected NSTimer rockTimer;

	/**
	 * The cached value of the '<em>ropeLength</em>' property.
	 * 
	 * @see #getRopeLength()
	 * @generated
	 * @ordered
	 */

	protected float ropeLength;

	/**
	 * The cached value of the '<em>sw</em>' property.
	 * 
	 * @see #getSw()
	 * @generated
	 * @ordered
	 */

	protected float sw;

	/**
	 * The cached value of the '<em>sh</em>' property.
	 * 
	 * @see #getSh()
	 * @generated
	 * @ordered
	 */

	protected float sh;

	/**
	 * The cached value of the '<em>heroStarDist</em>' property.
	 * 
	 * @see #getHeroStarDist()
	 * @generated
	 * @ordered
	 */

	protected float heroStarDist;

	/**
	 * The cached value of the '<em>heroRockDist</em>' property.
	 * 
	 * @see #getHeroRockDist()
	 * @generated
	 * @ordered
	 */

	protected float heroRockDist;

	/**
	 * The cached value of the '<em>snapDist</em>' property.
	 * 
	 * @see #getSnapDist()
	 * @generated
	 * @ordered
	 */

	protected float snapDist;

	/**
	 * The cached value of the '<em>starsCollected</em>' property.
	 * 
	 * @see #getStarsCollected()
	 * @generated
	 * @ordered
	 */

	protected int starsCollected;

	/**
	 * The cached value of the '<em>starsTotal</em>' property.
	 * 
	 * @see #getStarsTotal()
	 * @generated
	 * @ordered
	 */

	protected int starsTotal;

	/**
	 * The cached value of the '<em>starIcon</em>' property.
	 * 
	 * @see #getStarIcon()
	 * @generated
	 * @ordered
	 */

	protected CCSprite starIcon;

	/**
	 * The cached value of the '<em>starsCollectedLabel</em>' property.
	 * 
	 * @see #getStarsCollectedLabel()
	 * @generated
	 * @ordered
	 */

	protected CCLabelBMFont starsCollectedLabel;

	/**
	 * The cached value of the '<em>menuButton</em>' property.
	 * 
	 * @see #getMenuButton()
	 * @generated
	 * @ordered
	 */

	protected CCSprite menuButton;

}
