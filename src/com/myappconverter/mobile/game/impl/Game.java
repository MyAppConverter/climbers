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

package com.myappconverter.mobile.game.impl;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import android.view.MotionEvent;

import com.cocos2dx.andoird.wrappers.CCTouchDispatcher;
import com.cocos2dx.wrappers.CCDirector;
import com.cocos2dx.wrappers.CCEaseInOut;
import com.cocos2dx.wrappers.CCFadeIn;
import com.cocos2dx.wrappers.CCFadeOut;
import com.cocos2dx.wrappers.CCLabelBMFont;
import com.cocos2dx.wrappers.CCLabelTTF;
import com.cocos2dx.wrappers.CCMoveTo;
import com.cocos2dx.wrappers.CCParticleExplosion;
import com.cocos2dx.wrappers.CCParticleFireworks;
import com.cocos2dx.wrappers.CCParticleSystem;
import com.cocos2dx.wrappers.CCScaleTo;
import com.cocos2dx.wrappers.CCScene;
import com.cocos2dx.wrappers.CCSequence;
import com.cocos2dx.wrappers.CCSpawn;
import com.cocos2dx.wrappers.CCSprite;
import com.cocos2dx.wrappers.CCSpriteBatchNode;
import com.cocos2dx.wrappers.CCSpriteFrameCache;
import com.cocos2dx.wrappers.CCTextureCache;
import com.cocos2dx.wrappers.ccTexParams;
import com.cocos2dx.wrappers.cocos2dxMapping;
import com.cocos2dx.wrappers.audio.SimpleAudioEngine;
import com.cocos2dx.wrappers.protocols.CCTouchDelegateProtocol;
import com.myappconverter.java.applicationservices.CGGeometry;
import com.myappconverter.java.applicationservices.CGPoint;
import com.myappconverter.java.applicationservices.CGRect;
import com.myappconverter.java.applicationservices.CGSize;
import com.myappconverter.java.foundations.NSArray;
import com.myappconverter.java.foundations.NSBundle;
import com.myappconverter.java.foundations.NSMutableArray;
import com.myappconverter.java.foundations.NSObjCRuntime;
import com.myappconverter.java.foundations.NSScanner;
import com.myappconverter.java.foundations.NSString;
import com.myappconverter.java.uikit.UIAlertView;
import com.myappconverter.java.uikit.UIEvent;
import com.myappconverter.java.uikit.UITouch;
import com.myappconverter.mobile.verletrope.impl.VRope;

public class Game extends com.myappconverter.mobile.game.Game implements CCTouchDelegateProtocol {

	private static CCTouchDispatcher mDispatcher;
	// Enum constant kTagLabel
	protected static final int kTagLabel = 1;
	// Enum constant kTagLabel2
	protected static final int kTagLabel2 = 2;
	// Enum constant kTagLabel3
	protected static final int kTagLabel3 = 3;
	// Enum constant kTagBottom
	protected static final int kTagBottom = 4;
	// Enum constant kTagTop
	protected static final int kTagTop = 5;
	// Enum constant kTagWall
	protected static final int kTagWall = 6;
	// Enum constant kTagFlower
	protected static final int kTagFlower = 7;
	// Enum constant kTagFlowerPS
	protected static final int kTagFlowerPS = 8;

	CCLabelTTF label2;

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @return CCScene.
	 * @generated
	 */
	public static CCScene scene() {
		CCScene scene = CCScene.node();
		Game game = new Game();
		game.init();
		scene.addChild(game);
		return scene;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @return Game.
	 * @generated
	 */
	public boolean init() {
		super.init();
		dragInProgress = false;
		dragHero = null;
		dragOtherHero = null;
		registerWithTouchDispatcher();

		CGSize screenSize = CCDirector.sharedDirector().getWinSize();
		sw = screenSize.getWidth();
		sh = screenSize.getHeight();
		ropeLength = sh * 250 / 1024;
		heroStarDist = sh * 48.0f / 1024;
		heroRockDist = sh * 64.0f / 1024;
		snapDist = sh * 64.0f / 1024;

		CCSpriteFrameCache.sharedSpriteFrameCache().addSpriteFramesWithFile(new NSString("sprites.plist"));
		batch1 = new CCSpriteBatchNode();
		batch1.initWithFile(new NSString("sprites.png"), 50);
		this.addChild(batch1);
		batch2 = new CCSpriteBatchNode();
		batch2.initWithFile(new NSString("sprites.png"), 50);
		this.addChild(batch2, 10);
		this.hero1 = new Hero();
		this.hero1.initWithPosition(CGGeometry.CGPointMake(sw / 2 - ropeLength / 2.0f, sh / 16));
		batch2.addChild(hero1);
		hero2 = new Hero();
		hero2.initWithPosition(CGGeometry.CGPointMake(sw / 2 + ropeLength / 2.0f, sh / 16));

		hero1.setPosition(CGGeometry.CGPointMake(sw / 3 + ropeLength / 3.0f, sh / 8));
		batch2.addChild(hero2);

		ropeBatch = CCSpriteBatchNode.batchNodeWithFile(new NSString("rope.png"));

		rope = new VRope();
		rope.initWithPoints(hero1.getPosition(), hero2.getPosition(), ropeBatch);
		this.addChild(ropeBatch, 1);
		snapFeedback = new CCSprite();
		snapFeedback.initWithSpriteFrameName(new NSString("snapFeedback.png"));
		batch1.addChild(snapFeedback);
		snapFeedback.setOpacity(0);
		rock = new Rock();
		rock.initWithPosition(CGGeometry.CGPointZero);
		rock.setOpacity(0);
		batch1.addChild(rock, 10);
		rockAlert = new CCSprite();
		rockAlert.initWithSpriteFrameName(new NSString("rockAlert.png"));
		batch1.addChild(rockAlert, 13);
		rockAlert.setOpacity(0);
		CCSprite sprite;
		sprite = CCSprite.spriteWithSpriteFrameName(new NSString("starIcon.png"));
		sprite.setPosition(CGGeometry.CGPointMake(32, sh - 32));
		batch1.addChild(sprite, 15);
		starIcon = sprite;// .retain();
		sprite = CCSprite.spriteWithSpriteFrameName(new NSString("menuButton.png"));
		sprite.setPosition(CGGeometry.CGPointMake(sw - 32, sh - 32));
		batch1.addChild(sprite, 15);
		menuButton = sprite;// .retain();
		float fontSize = 24;
		fontSize = 12;
		CCLabelBMFont label = CCLabelBMFont.labelWithString(new NSString("0/0"), new NSString("digits.fnt"));
		label.setOpacity(128);
		label.setPosition(CGGeometry.CGPointMake(60, sh - 32));
		label.setAnchorPoint(CGGeometry.CGPointMake(0, 0.5f));
		this.addChild(label, 15);
		starsCollectedLabel = label;

		grabs = new NSMutableArray<Grab>();
		grabs.init();
		stars = new NSMutableArray<Star>();
		stars.init();
		rockTimer = null;
		currentLevel = 0;
		nextLevel = 0;
		if (nextLevel == 0) {
			nextLevel = 1;
		}
		this.resetLevel();

		SimpleAudioEngine.sharedEngine().preloadEffect(new NSString("levelCompleted.ogg"));
		SimpleAudioEngine.sharedEngine().preloadEffect(new NSString("levelFailed.ogg"));
		SimpleAudioEngine.sharedEngine().preloadEffect(new NSString("click.ogg"));
		SimpleAudioEngine.sharedEngine().preloadEffect(new NSString("grab.ogg"));
		SimpleAudioEngine.sharedEngine().preloadEffect(new NSString("collectStar.mp3"));
		SimpleAudioEngine.sharedEngine().preloadEffect(new NSString("dropRock.mp3"));
		SimpleAudioEngine.sharedEngine().stopBackgroundMusic();
		SimpleAudioEngine.sharedEngine().playBackgroundMusic(new NSString("game.mp3"), true);

		this.mSchedule(this);
		return true;
	}

	void mSchedule(final Game obj) {

		ScheduledExecutorService execService = Executors.newScheduledThreadPool(3);

		execService.scheduleAtFixedRate(new Runnable() {
			public void run() {
				obj.update(200);
			}
		}, 0L, 200L, TimeUnit.MILLISECONDS);

	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public void loadLevel() {
		this.removeChild(ps, false);
		NSString basename = NSString.stringWithFormat(new NSString("%02d"), currentLevel);

		NSString path = NSBundle.mainBundle().pathForResourceOfType(basename, new NSString("svg"));
		NSString content = NSString.stringWithContentsOfFileEncodingError(path, null, null);
		NSScanner scanner = NSScanner.scannerWithString(content);
		scanner.scanUpToStringIntoString(new NSString("wall"), null);
		scanner.scanUpToStringIntoString(new NSString("height"), null);
		scanner.scanStringIntoString(new NSString("height="), null);

		float[] tmp = { levelHeight };
		scanner.scanFloat(tmp);
		levelHeight = tmp[0];
		levelHeight = levelHeight * sh / 1024;
		this.removeChildByTag(Game.kTagWall, true);
		CCSprite wall = CCSprite.spriteWithFile(new NSString("wall.png"), CGGeometry.CGRectMake(0, 0, sw, levelHeight));
		wall.setPosition(CGGeometry.CGPointMake(sw / 2, levelHeight / 2.0f));
		this.addChild(wall, -2, Game.kTagWall);
		NSArray<Integer> nsarray = NSArray.arrayWithObjects(9729, 9729, 10497, 10497);
		ccTexParams tp = new ccTexParams(nsarray);

		wall.getTexture().setTexParameters(tp);
		startPosition1 = CGGeometry.CGPointMake(sw / 2 - ropeLength / 3, sh / 16);
		startPosition2 = CGGeometry.CGPointMake(sw / 2 + ropeLength / 3, sh / 16);
		if (grabs.count() != 0) {
			for (Grab g : grabs) {
				batch1.removeChild(g, true);
			}
			grabs.removeAllObjects();
		}
		if (stars.count() != 0) {
			for (Star s : stars) {
				batch1.removeChild(s, true);
			}
			stars.removeAllObjects();
		}
		starsTotal = 0;
		Grab grab;
		Star star;
		float x = 0;
		float y = 0;
		boolean isGrab;
		CGPoint p;
		while (true) {
			isGrab = false;
			if (!scanner.scanUpToStringIntoString(new NSString("circle"), null)) {
				break;
			}
			if (scanner.isAtEnd()) {
				break;
			}
			scanner.scanUpToStringIntoString(new NSString("id"), null);
			if (scanner.scanStringIntoString(new NSString("id=\"grab"), null)) {
				isGrab = true;
			} else {
				if (scanner.scanStringIntoString(new NSString("id=\"star"), null)) {
					isGrab = false;
				}
			}
			scanner.scanUpToStringIntoString(new NSString("cx"), null);
			scanner.scanStringIntoString(new NSString("cx=\""), null);

			float[] tmp1 = { x };
			scanner.scanFloat(tmp1);
			x = tmp1[0];
			scanner.scanUpToStringIntoString(new NSString("cy"), null);
			scanner.scanStringIntoString(new NSString("cy=\""), null);
			float[] tmp2 = { y };
			scanner.scanFloat(tmp2);
			y = tmp2[0];
			p = CGGeometry.CGPointMake(x * sw / 768, sh - y * sh / 1024);
			if (isGrab) {
				grab = new Grab();
				grab.initWithPosition(p);
				grabs.addObject(grab);
				batch1.addChild(grab, 5);

			} else {
				star = new Star();
				star.initWithPosition(p);
				stars.addObject(star);
				batch1.addChild(star, 6);
				starsTotal++;
			}
		}
		batch1.removeChildByTag(Game.kTagBottom, true);
		CCSprite bottom = CCSprite.spriteWithSpriteFrameName(new NSString("bottom.png"));
		bottom.setPosition(CGGeometry.CGPointMake(sw / 2, sh / 32));
		bottom.setTag(Game.kTagBottom);
		batch1.addChild(bottom, 11);
		batch1.removeChildByTag(Game.kTagTop, true);
		CCSprite top = CCSprite.spriteWithSpriteFrameName(new NSString("top.png"));
		top.setPosition(CGGeometry.CGPointMake(sw / 2, levelHeight));
		top.setTag(Game.kTagTop);
		batch1.addChild(top, 11);
		batch1.removeChildByTag(Game.kTagFlower, true);
		CCSprite flower = CCSprite.spriteWithSpriteFrameName(new NSString("flower.png"));
		flower.setPosition(CGGeometry.CGPointMake(sw / 2, levelHeight));
		flower.setTag(Game.kTagFlower);
		batch1.addChild(flower, 12);

	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public void resetLevel() {
		this.removeChild(ps, false);
		if (nextLevel != currentLevel) {
			currentLevel = nextLevel;
			if (currentLevel > 15) {
				return;
			}
			this.loadLevel();
		}
		this.removeChildByTag(Game.kTagLabel, true);
		this.removeChildByTag(Game.kTagLabel2, true);
		this.removeChildByTag(Game.kTagLabel3, true);
		this.removeChildByTag(Game.kTagFlowerPS, true);
		hero1.setState(Hero.kHeroStateIdle);
		hero2.setState(Hero.kHeroStateIdle);
		hero1.setPosition(startPosition1);
		hero2.setPosition(startPosition2);
		hero1.setTopGroundY(levelHeight + sh * 36 / 1024);
		hero2.setTopGroundY(levelHeight + sh * 36 / 1024);

		rope.resetWithPoints(hero1.getPosition(), hero2.getPosition());

		for (Star s : stars) {
			if (s.getCollected()) {
				s.setCollected(false);
				CCScaleTo a1 = CCScaleTo.actionWithDuration(0.5f, 1.0f);
				CCFadeIn a2 = CCFadeIn.actionWithDuration(0.5f);
				s.runAction(CCSpawn.actions(a1, a2));
			}
		}
		starsCollected = 0;
		this.updateStarsCollectedLabel();
		if (snapFeedback.getOpacity() > 0) {
			snapFeedback.runAction(CCFadeOut.actionWithDuration(0.25f));
		}
		cameraOffset = CGGeometry.CGPointZero;
		this.stopAllActions();
		this.setPosition(CGGeometry.CGPointZero);
		this.updateUIPosition();

		if (rock.getFalling()) {
			rock.setFalling(false);
			rock.setOpacity(0);
		}
		dragInProgress = false;
		gameInProgress = true;
		this.scheduleRockAlert();

	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public void levelFailed() {
		NSObjCRuntime.NSLog(new NSString("levelFailed"));
		SimpleAudioEngine.sharedEngine().playEffect(new NSString("levelFailed.caf"));
		gameInProgress = false;
		if (com.myappconverter.java.foundations.ExpressNullable.assertCondition(rockTimer)) {//
			rockTimer.invalidate();
			rockTimer = null;
		}
		if (rock.getOpacity() > 0) {
			rock.runAction(CCFadeOut.actionWithDuration(0.5f));
		}
		float fontSize = 64.0f;
		fontSize = 32.0f;
		CCLabelTTF label = CCLabelTTF.labelWithString(new NSString("NO-O-O-O!"), new NSString("ChalkboardSE-Bold"),
				fontSize);
		label.setColor(cocos2dxMapping.ccc3(240, 0, 0));
		label.setPosition(CGGeometry.CGPointMake(sw / 2, sh * 7 / 8 - this.getPosition().getY()));
		label.setTag(Game.kTagLabel);
		this.addChild(label, 12);
		nextLevel = currentLevel;
	}

	CCParticleSystem ps;

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public void levelCompleted() {
		gameInProgress = false;
		if (rockTimer != null) {
			rockTimer.invalidate();
			rockTimer = null;
		}
		float fontSize = 32.0f;

		CCLabelTTF label;

		if (currentLevel == 15) {

			label = CCLabelTTF.labelWithString(new NSString("Yeah! You did it!"), new NSString("Arial"), fontSize);

			label.setColor(cocos2dxMapping.ccc3(255, 255, 255));
			label.setPosition(CGGeometry.CGPointMake(sw / 2, levelHeight + sh * 3 / 8));
			label.setTag(Game.kTagLabel);
			this.addChild(label, 12);
			label = CCLabelTTF.labelWithString(new NSString(
					"Finally, you overcame all difficulties on your way. Great job!"), new NSString("Arial"),
					fontSize * 3 / 8);
			label.setColor(cocos2dxMapping.ccc3(255, 255, 255));
			label.setPosition(CGGeometry.CGPointMake(sw / 2, levelHeight + sh * 5 / 16));
			label.setTag(Game.kTagLabel2);
			this.addChild(label, 12);
		} else {
			label = CCLabelTTF.labelWithString(
					NSString.stringWithFormat(new NSString("Level %d completed"), currentLevel), new NSString("Arial"),
					fontSize * 3 / 8);

			label.setColor(cocos2dxMapping.ccc3(255, 255, 255));
			label.setPosition(CGGeometry.CGPointMake(sw / 2, levelHeight + sh * 5 / 16));
			label.setTag(Game.kTagLabel2);
			this.addChild(label, 12);
		}

		ps = CCParticleFireworks.node();
		ps.setTexture(CCTextureCache.sharedTextureCache().addImage(new NSString("stars.png")));
		ps.setStartSize(4);
		ps.setSpeed(100);
		ps.setPosition(CGGeometry.CGPointMake(sw / 2 - sw * 9 / 768, levelHeight + sh * 65 / 1024));
		this.addChild(ps, 10);

		nextLevel = currentLevel + 1;

		if (nextLevel > 15) {
			// NSUserDefaults.standardUserDefaults().setIntegerForKey(1, new NSString("currentLevel"));
		} else {
			// NSUserDefaults.standardUserDefaults().setIntegerForKey(nextLevel, new NSString("currentLevel"));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public void update(float dt) {
		if (gameInProgress && this.numberOfRunningActions() != 0) {
			if (dragInProgress) {
				this.updateDragHeroPositionWithTouchLocation(lastTouchLocation);
			}
		}
		hero1.update(dt);
		hero2.update(dt);

		if (gameInProgress && !dragInProgress) {
			if (hero1.getPosition().getY() >= hero1.getTopGroundY()
					&& hero2.getPosition().getY() >= hero2.getTopGroundY()) {
				cameraOffset = CGGeometry.CGPointMake(0, levelHeight - sh / 2);
				this.updateCamera();
				this.levelCompleted();
			}

			if (hero1.getState() != hero2.getState()
					&& (hero1.getState() == Hero.kHeroStateFall || hero2.getState() == Hero.kHeroStateFall)) {
				float dist = cocos2dxMapping.ccpDistance(hero1.getPosition(), hero2.getPosition());
				if (dist > ropeLength) {
					hero1.setState(Hero.kHeroStateFall);
					hero2.setState(Hero.kHeroStateFall);

					float vy = Math.min(hero1.getVelocity().getX(), hero1.getVelocity().getY());
					hero1.setVelocity(CGGeometry.CGPointMake(0, vy));
					hero2.setVelocity(CGGeometry.CGPointMake(0, vy));
					if (hero1.getPosition().getY() > sh / 2 || hero2.getPosition().getY() > sh / 2) {
						this.levelFailed();
					}
				}
			}
		}
		rope.updateWithPoints(hero1.getPosition(), hero2.getPosition(), dt);
		rope.updateSprites();
		if (this.numberOfRunningActions() != 0) {
			this.updateUIPosition();
			if (rockAlert.numberOfRunningActions() != 0 && -this.getPosition().getY() <= levelHeight - sh) {
				rockAlert.setPosition(CGGeometry.CGPointMake(rockAlert.getPosition().getX(), sh * 31 / 32
						- this.getPosition().getY()));
			}
		}
		if (gameInProgress && rock.getFalling()) {
			rock.update(dt);
			if (!rock.getFalling() && rock.getOpacity() > 0) {
				rock.runAction(CCFadeOut.actionWithDuration(0.5f));
			}
			if (gameInProgress && rock.getOpacity() > 0
					&& (hero1.getState() != Hero.kHeroStateIdle || hero2.getState() != Hero.kHeroStateIdle)) {
				if (cocos2dxMapping.ccpDistance(hero1.getPosition(), rock.getPosition()) < heroRockDist
						|| cocos2dxMapping.ccpDistance(hero2.getPosition(), rock.getPosition()) < heroRockDist) {
					hero1.setState(Hero.kHeroStateFall);
					hero1.setVelocity(rock.getVelocity());
					hero2.setState(Hero.kHeroStateFall);
					hero2.setVelocity(rock.getVelocity());
					this.levelFailed();
				}
			}
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public void registerWithTouchDispatcher() {
		CCTouchDispatcher.sharedDispatcher().addTargetedDelegate(this, 0, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */

	public void tapDownAt(CGPoint location) {
		CGRect rect = CGGeometry.CGRectMake(menuButton.getPosition().getX() - 32, menuButton.getPosition().getY() - 32
				+ this.getPosition().getY(), 64, 64);
		NSObjCRuntime.NSLog(new NSString("tapDownAt (%f.1,%.1f)"), location.getX(), location.getY());
		NSObjCRuntime.NSLog(new NSString("menuButton rect (%.1f,%.1f,%.1f,%.1f)"), rect.getOrigin().getX(), rect
				.getOrigin().getY(), rect.getSize().getWidth(), rect.getSize().getHeight());
		if (!gameInProgress) {
			this.resetLevel();
			SimpleAudioEngine.sharedEngine().playEffect(new NSString("grab.ogg"));
			return;
		}

		lastTouchLocation = location;
		location = cocos2dxMapping.ccpSub(location, this.getPosition());
		float tapRadius = 64.0f;

		float dist1 = cocos2dxMapping.ccpDistance(hero1.getPosition(), location);
		float dist2 = cocos2dxMapping.ccpDistance(hero2.getPosition(), location);

		if (cocos2dxMapping.ccpDistance(hero1.getPosition(), location) < tapRadius) {
			dragHero = hero1;
			dragOtherHero = hero2;
			if (dragOtherHero.getState() != Hero.kHeroStateFall) {
				dragInProgress = true;
				dragOffset = cocos2dxMapping.ccpSub(dragHero.getPosition(), location);
				dragHero.setState(Hero.kHeroStateFall);
				SimpleAudioEngine.sharedEngine().playEffect(new NSString("click.ogg"));
			}
			return;
		}
		if (cocos2dxMapping.ccpDistance(hero2.getPosition(), location) < tapRadius) {
			dragHero = hero2;
			dragOtherHero = hero1;
			if (dragOtherHero.getState() != Hero.kHeroStateFall) {
				dragInProgress = true;
				dragOffset = cocos2dxMapping.ccpSub(dragHero.getPosition(), location);
				dragHero.setState(Hero.kHeroStateFall);
				SimpleAudioEngine.sharedEngine().playEffect(new NSString("click.ogg"));
			}
			return;
		}

	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public void tapMoveAt(CGPoint location) {
		if (!gameInProgress) {
			return;
		}
		lastTouchLocation = location;
		if (dragInProgress) {
			this.updateDragHeroPositionWithTouchLocation(lastTouchLocation);
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public void tapUpAt(CGPoint location) {

		if (!gameInProgress) {
			return;
		}
		if (dragInProgress) {
			dragInProgress = false;
			dragHero.setState(Hero.kHeroStateFall);

			SimpleAudioEngine.sharedEngine().playEffect(new NSString("grab.ogg"));
			if (true) {
				snapFeedback.runAction(CCFadeOut.actionWithDuration(0.25f));
			}

			if ((dragHero.getPosition().getY() > dragHero.getTopGroundY() - snapDist)
					&& (dragHero.getPosition().getY() <= dragHero.getTopGroundY())) {
				dragHero.setPosition(CGGeometry.CGPointMake(dragHero.getPosition().getX(), dragHero.getTopGroundY()));
				dragHero.setState(Hero.kHeroStateIdle);

				return;
			}

			for (Grab g : grabs) {
				if (this.dragHeroNearGrab(g)) {
					dragHero.setPosition(g.getPosition());
					dragHero.setState(Hero.kHeroStateGrab);
					cameraOffset = CGGeometry.CGPointMake(0, dragHero.getPosition().getY() - sh / 2);
					if (cameraOffset.getY() < 0) {
						cameraOffset = CGGeometry.CGPointZero;
					}
					this.updateCamera();
					return;
				}
			}

			if (dragHero.getPosition().getY() < 200) {
				cameraOffset = CGGeometry.CGPointZero;
				this.updateCamera();
			}

		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @return boolean.
	 * @generated
	 */

	public boolean ccTouchesBegan(MotionEvent event) {
		int x = (int) event.getX();
		int y = (int) event.getY();

		CGPoint location = new CGPoint(x, y);
		location = CCDirector.sharedDirector().convertToGL(location);
		CGPoint locationtmp = CGGeometry.CGPointMake(location.getX(), location.getY());
		this.tapDownAt(location);
		return true;
	}

	public boolean ccTouchBegan(UITouch touch, UIEvent event) {
		CGPoint location = touch.locationInView(touch.view());
		location = CCDirector.sharedDirector().convertToGL(location);
		this.tapDownAt(location);
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */

	public boolean ccTouchesMoved(MotionEvent event) {
		int x = (int) event.getX();
		int y = (int) event.getY();
		CGPoint location = new CGPoint(x, y);
		location = CCDirector.sharedDirector().convertToGL(location);
		this.tapMoveAt(location);
		return true;
	}

	public void ccTouchMoved(UITouch touch, UIEvent event) {
		CGPoint location = touch.locationInView(touch.view());
		location = CCDirector.sharedDirector().convertToGL(location);
		this.tapMoveAt(location);
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */

	public boolean ccTouchesEnded(MotionEvent event) {
		int x = (int) event.getX();
		int y = (int) event.getY();
		CGPoint location = new CGPoint(x, y);
		location = CCDirector.sharedDirector().convertToGL(location);

		this.tapUpAt(location);
		return true;
	}

	public void ccTouchEnded(UITouch touch, UIEvent event) {
		CGPoint location = touch.locationInView(touch.view());
		location = CCDirector.sharedDirector().convertToGL(location);
		this.tapUpAt(location);
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */

	public boolean ccTouchesCancelled(MotionEvent event) {
		int x = (int) event.getX();
		int y = (int) event.getY();
		CGPoint location = new CGPoint(x, y);
		location = CCDirector.sharedDirector().convertToGL(location);

		this.tapUpAt(location);
		return true;
	}

	public void ccTouchCancelled(UITouch touch, UIEvent event) {
		CGPoint location = touch.locationInView(touch.view());
		location = CCDirector.sharedDirector().convertToGL(location);
		this.tapUpAt(location);
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public void sparkleAt(CGPoint p) {
		CCParticleSystem ps = CCParticleExplosion.node();
		this.addChild(ps, 12);
		ps.setTexture(CCTextureCache.sharedTextureCache().addImage(new NSString("stars.png")));
		ps.setPosition(p);
		ps.setLife(1.0f);
		ps.setLifeVar(1.0f);
		ps.setTotalParticles((long) 60.0f);
		ps.setAutoRemoveOnFinish(true);
		SimpleAudioEngine.sharedEngine().playEffect(new NSString("collectStar.mp3"));
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public void updateCamera() {
		CCMoveTo move = CCMoveTo.actionWithDuration(2, cocos2dxMapping.ccpNeg(cameraOffset));
		CCEaseInOut ease = CCEaseInOut.actionWithAction(move, 2);
		this.runAction(ease);
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @return boolean.
	 * @generated
	 */
	public boolean dragHeroNearGrab(Grab g) {
		float dist = cocos2dxMapping.ccpDistance(dragHero.getPosition(), g.getPosition());
		if (dist < snapDist) {
			return true;
		}
		return false;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public void scheduleRockAlert() {

	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public void showRockAlert() {
		rockTimer = null;
		if (!gameInProgress) {
			return;
		}

		if (-this.getPosition().getY() >= levelHeight - sh) {
			return;
		}
		float padding = sw * 128 / 768;
		float x = (float) ((Math.random() % (sw - padding * 2)) + padding);
		rockAlert.setPosition(CGGeometry.CGPointMake(x, sh * 31 / 32 - this.getPosition().getY()));
		CCFadeIn a1 = CCFadeIn.actionWithDuration(0.5f);
		CCFadeOut a2 = CCFadeOut.actionWithDuration(0.5f);
		rockAlert.runAction(CCSequence.actions(a1, a2, a1, a2, a1, a2, a2));
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public void dropRock() {
		rock.setPosition(CGGeometry.CGPointMake(rockAlert.getPosition().getX(), levelHeight));
		rock.setFalling(true);
		rock.runAction(CCFadeIn.actionWithDuration(0.5f));
		SimpleAudioEngine.sharedEngine().playEffect(new NSString("dropRock.mp3"));
		this.scheduleRockAlert();
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public void updateDragHeroPositionWithTouchLocation(CGPoint touchLocation) {
		CGPoint levelLocation = cocos2dxMapping.ccpSub(touchLocation, this.getPosition());
		float dist;
		dragHero.setPosition(cocos2dxMapping.ccpAdd(levelLocation, dragOffset));
		CGPoint heroDelta = cocos2dxMapping.ccpSub(dragHero.getPosition(), dragOtherHero.getPosition());
		dist = cocos2dxMapping.ccpLength(heroDelta);
		if (dist > ropeLength) {
			CGPoint dir = cocos2dxMapping.ccpNormalize(heroDelta);
			dragHero.setPosition(cocos2dxMapping.ccpAdd(dragOtherHero.getPosition(),
					cocos2dxMapping.ccpMult(dir, ropeLength)));
		}
		for (Star s : stars) {
			if (s.getCollected()) {
				continue;
			}
			dist = cocos2dxMapping.ccpDistance(dragHero.getPosition(), s.getPosition());
			if (dist < heroStarDist) {
				s.setCollected(true);
				starsCollected++;
				this.updateStarsCollectedLabel();
				CCScaleTo a1 = CCScaleTo.actionWithDuration(0.5f, 3.0f);
				CCFadeOut a2 = CCFadeOut.actionWithDuration(0.5f);
				s.runAction(CCSpawn.actions(a1, a2));
				this.sparkleAt(s.getPosition());
				break;
			}
		}

		boolean snapped = false;
		for (Grab g : grabs) {
			dist = cocos2dxMapping.ccpDistance(dragHero.getPosition(), g.getPosition());
			if (dist < snapDist) {
				float t = (snapDist - dist) / snapDist;
				snapFeedback.setScale(t * 0.75f + 0.25f);
				snapFeedback.setPosition(g.getPosition());
				snapped = true;
				break;
			}
		}
		if (!snapped && snapFeedback.getOpacity() > 0) {
			// snapFeedback.setOpacity(0);
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public void updateStarsCollectedLabel() {
		starsCollectedLabel.setString(NSString.stringWithFormat(new NSString("%d/%d"), starsCollected, starsTotal));
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public void updateUIPosition() {
		float ny = sh - 32 - this.getPosition().getY();
		starIcon.setPosition(CGGeometry.CGPointMake(starIcon.getPosition().getX(), ny));
		starsCollectedLabel.setPosition(CGGeometry.CGPointMake(starsCollectedLabel.getPosition().getX(), ny));
		menuButton.setPosition(CGGeometry.CGPointMake(menuButton.getPosition().getX(), ny));
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public void showPopupMenu() {
		gameInProgress = false;
		// UIAlertView alert = (new UIAlertView())
		// .initWithTitleMessageDelegateCancelButtonTitleOtherButtonTitles(
		// new NSString("Game Paused"), null, this, new NSString(
		// "Continue"), new NSString("Main Menu"));
		// alert.show();
		// alert.release();
		SimpleAudioEngine.sharedEngine().pauseBackgroundMusic();
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @return void.
	 * @generated
	 */
	public void alertView(UIAlertView alertView, int buttonIndex) {
		SimpleAudioEngine.sharedEngine().resumeBackgroundMusic();
		if (buttonIndex == 1) {
			// CCDirector.sharedDirector().pushScene(new Intro().scene());
		} else {
			if (this.getChildByTag(Game.kTagLabel) != null) {
			} else {
				gameInProgress = true;
			}
		}
	}

}
