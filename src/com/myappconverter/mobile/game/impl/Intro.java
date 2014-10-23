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

import android.view.MotionEvent;

import com.cocos2dx.andoird.wrappers.CCTouchDispatcher;
import com.cocos2dx.wrappers.CCDelayTime;
import com.cocos2dx.wrappers.CCDirector;
import com.cocos2dx.wrappers.CCFadeIn;
import com.cocos2dx.wrappers.CCLabelTTF;
import com.cocos2dx.wrappers.CCLayer;
import com.cocos2dx.wrappers.CCScene;
import com.cocos2dx.wrappers.CCSequence;
import com.cocos2dx.wrappers.CCSprite;
import com.cocos2dx.wrappers.CCSpriteBatchNode;
import com.cocos2dx.wrappers.CCSpriteFrameCache;
import com.cocos2dx.wrappers.audio.SimpleAudioEngine;
import com.myappconverter.java.applicationservices.CGGeometry;
import com.myappconverter.java.applicationservices.CGPoint;
import com.myappconverter.java.applicationservices.CGRect;
import com.myappconverter.java.applicationservices.CGSize;
import com.myappconverter.java.foundations.NSObjCRuntime;
import com.myappconverter.java.foundations.NSString;
import com.myappconverter.java.foundations.NSURL;
import com.myappconverter.java.uikit.UIApplication;
import com.myappconverter.java.uikit.UIEvent;
import com.myappconverter.java.uikit.UITouch;

public class Intro extends CCLayer {

	private static CCTouchDispatcher mDispatcher;
	public CCSprite playButton;

	/**
	 * The cached value of the '<em>moreGamesButton</em>' property.
	 * 
	 * @see #getMoreGamesButton()
	 * @generated
	 * @ordered
	 */

	public CCSprite moreGamesButton;

	/**
	 * The cached value of the '<em>gameSourcesButton</em>' property.
	 * 
	 * @see #getGameSourcesButton()
	 * @generated
	 * @ordered
	 */

	public CCSprite gameSourcesButton;

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @return CCScene.
	 * @generated
	 */

	public static CCScene scene() {
		CCScene scene = CCScene.node();

		Intro layer = new Intro();
		layer.init();
		// mDispatcher = CCTouchDispatcher.sharedDispatcher();
		// CCTargetedTouchHandler ccTargetedTouchHandler = new CCTargetedTouchHandler(layer, 0, true);
		// mDispatcher.getTargetedHandlers().add(0, ccTargetedTouchHandler);
		scene.addChild(layer);
		// onEnter();
		return scene;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @return Intro.
	 * @generated
	 */
	public boolean init() {
		super.init();
		this.setIsTouchEnabled(true);
		// this.registerWithTouchDispatcher();
		CGSize ss = CCDirector.sharedDirector().getWinSize();
		float sw = ss.getWidth();
		float sh = ss.getHeight();
		CCSprite wall = CCSprite.spriteWithFile(new NSString("wall.png"), CGGeometry.CGRectMake(0, 0, sw, sh / 2));
		wall.setPosition(CGGeometry.CGPointMake(sw / 2, sh / 4));
		this.addChild(wall);

		CCSpriteFrameCache.sharedSpriteFrameCache().addSpriteFramesWithFile(new NSString("sprites.plist"));
		CCSpriteBatchNode batch = (new CCSpriteBatchNode());
		batch.initWithFile(new NSString("sprites.png"), 50);
		this.addChild(batch);
		CCSprite sprite;
		sprite = CCSprite.spriteWithSpriteFrameName(new NSString("bottom.png"));
		sprite.setPosition(CGGeometry.CGPointMake(sw / 2, sh / 32));
		batch.addChild(sprite);
		sprite = CCSprite.spriteWithSpriteFrameName(new NSString("top.png"));
		sprite.setPosition(CGGeometry.CGPointMake(sw / 2, sh / 2));
		batch.addChild(sprite);
		sprite = CCSprite.spriteWithSpriteFrameName(new NSString("gameTitle.png"));
		sprite.setPosition(CGGeometry.CGPointMake(sw / 2, sh * 7 / 8));
		batch.addChild(sprite);
		sprite = CCSprite.spriteWithSpriteFrameName(new NSString("moreGamesButton.png"));
		sprite.setPosition(CGGeometry.CGPointMake(sw / 4, sh / 2));

		CCDelayTime a1 = CCDelayTime.actionWithDuration(0.2f);
		CCFadeIn a2 = CCFadeIn.actionWithDuration(0.4f);
		sprite.runAction(CCSequence.actions(a1, a2));
		batch.addChild(sprite);
		moreGamesButton = sprite;
		sprite = CCSprite.spriteWithSpriteFrameName(new NSString("playButton.png"));
		sprite.setPosition(CGGeometry.CGPointMake(sw / 2, sh / 2));

		sprite.runAction(CCFadeIn.actionWithDuration(0.2f));
		batch.addChild(sprite);
		playButton = sprite;
		sprite = CCSprite.spriteWithSpriteFrameName(new NSString("gameSourcesButton.png"));
		sprite.setPosition(CGGeometry.CGPointMake(sw * 3 / 4, sh / 2));

		a1 = CCDelayTime.actionWithDuration(0.2f);
		a2 = CCFadeIn.actionWithDuration(0.4f);
		sprite.runAction(CCSequence.actions(a1, a2));
		batch.addChild(sprite);
		gameSourcesButton = sprite;
		sprite = CCSprite.spriteWithSpriteFrameName(new NSString("gpcLogo.png"));
		sprite.setPosition(CGGeometry.CGPointMake(sw * 5 / 48, sh * 11.7f / 256));
		sprite.setOpacity(0);
		a1 = CCDelayTime.actionWithDuration(0.6f);
		a2 = CCFadeIn.actionWithDuration(0.4f);
		sprite.runAction(CCSequence.actions(a1, a2));
		batch.addChild(sprite);
		sprite = CCSprite.spriteWithSpriteFrameName(new NSString("cocos2dLogo.png"));
		sprite.setPosition(CGGeometry.CGPointMake(sw * 43 / 48, sh * 14.2f / 256));
		sprite.setOpacity(0);
		a1 = CCDelayTime.actionWithDuration(0.6f);
		a2 = CCFadeIn.actionWithDuration(0.4f);
		sprite.runAction(CCSequence.actions(a1, a2));
		batch.addChild(sprite);
		CCLabelTTF label;
		float fontSize = 12.0f;
		fontSize = 6.0f;
		label = CCLabelTTF.labelWithString(
				new NSString("Created by @haqu for Game Prototype Challenge using Cocos2D."), new NSString(
						"Verdana-Bold"), fontSize);
		label.setPosition(CGGeometry.CGPointMake(sw / 2, sh * 5 / 256));
		label.setOpacity(0);
		a1 = CCDelayTime.actionWithDuration(0.6f);
		a2 = CCFadeIn.actionWithDuration(0.4f);
		label.runAction(CCSequence.actions(a1, a2));
		this.addChild(label);
		SimpleAudioEngine.sharedEngine().preloadEffect(new NSString("click.caf"));
		SimpleAudioEngine.sharedEngine().preloadEffect(new NSString("grab.caf"));
		SimpleAudioEngine.sharedEngine().stopBackgroundMusic();
		SimpleAudioEngine.sharedEngine().playBackgroundMusic(new NSString("intro.mp3"));

		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */

	public void dealloc() {
		playButton.release();
		moreGamesButton.release();
		gameSourcesButton.release();
		super.dealloc();
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */

	public void registerWithTouchDispatcher() {
		CCTouchDispatcher dispatcher = CCTouchDispatcher.sharedDispatcher();
		dispatcher.addDelegate(this, 0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public void tapDownAt(CGPoint location) {
		NSObjCRuntime.NSLog(new NSString("tapDown"));
		CGSize screenSize = CCDirector.sharedDirector().getWinSize();
		float sw = screenSize.getWidth();
		float sh = screenSize.getHeight();
		CGRect rect;
		rect = CGGeometry.CGRectMake(sw / 2 - 64, sh / 2 - 64, 128, 128);

		CCDirector.sharedDirector().popScene();

		CCDirector.sharedDirector().pushScene(Game.scene());

		if (CGGeometry.CGRectContainsPoint(rect, location)) {
			playButton.setScale(0.95f);
			SimpleAudioEngine.sharedEngine().playEffect(new NSString("click.caf"));
		}
		rect = CGGeometry.CGRectMake(sw / 4 - 32, sh / 2 - 32, 64, 64);
		if (CGGeometry.CGRectContainsPoint(rect, location)) {
			moreGamesButton.setScale(0.95f);
			SimpleAudioEngine.sharedEngine().playEffect(new NSString("click.caf"));
		}
		rect = CGGeometry.CGRectMake(sw * 3 / 4 - 32, sh / 2 - 32, 64, 64);
		if (CGGeometry.CGRectContainsPoint(rect, location)) {
			gameSourcesButton.setScale(0.95f);
			SimpleAudioEngine.sharedEngine().playEffect(new NSString("click.caf"));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public void tapUpAt(CGPoint location) {
		NSObjCRuntime.NSLog(new NSString("tapUp"));
		CGSize screenSize = CCDirector.sharedDirector().getWinSize();
		float sw = screenSize.getWidth();
		float sh = screenSize.getHeight();
		CGRect rect;
		playButton.setScale(1.0f);
		moreGamesButton.setScale(1.0f);
		gameSourcesButton.setScale(1.0f);
		rect = CGGeometry.CGRectMake(sw / 2 - 64, sh / 2 - 64, 128, 128);
		if (CGGeometry.CGRectContainsPoint(rect, location)) {
			CCDirector.sharedDirector().pushScene(Game.scene());
		}
		rect = CGGeometry.CGRectMake(sw / 4 - 32, sh / 2 - 32, 64, 64);
		if (CGGeometry.CGRectContainsPoint(rect, location)) {
			NSString urlString = new NSString("itms-apps://itunes.com/apps/iplayfulinc");
			UIApplication.sharedApplication().openURL(NSURL.URLWithString(urlString));
		}
		rect = CGGeometry.CGRectMake(sw * 3 / 4 - 32, sh / 2 - 32, 64, 64);
		if (CGGeometry.CGRectContainsPoint(rect, location)) {
			NSString urlString = new NSString("https://github.com/haqu/climbers");
			UIApplication.sharedApplication().openURL(NSURL.URLWithString(urlString));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @return boolean.
	 * @generated
	 */

	public boolean ccTouchesBegan(MotionEvent event) {

		CCDirector.sharedDirector().pushScene(Game.scene());
		return true;
	}

	public static boolean ccTouchBegan(UITouch touch, UIEvent event) {

		CGPoint location = touch.locationInView(touch.view());
		location = CCDirector.sharedDirector().convertToGL(location);
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public void ccTouchEnded(UITouch touch, UIEvent event) {
		CGPoint location = touch.locationInView(touch.view());
		location = CCDirector.sharedDirector().convertToGL(location);
	}

}
