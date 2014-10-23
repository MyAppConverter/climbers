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

import com.cocos2dx.wrappers.CCLayer;
import com.cocos2dx.wrappers.CCScene;
import com.cocos2dx.wrappers.CCSprite;

public abstract class Intro extends CCLayer {

	/**
	 * The cached value of the '<em>playButton</em>' property.
	 * 
	 * @see #getPlayButton()
	 * @generated
	 * @ordered
	 */

	protected CCSprite playButton;

	/**
	 * The cached value of the '<em>moreGamesButton</em>' property.
	 * 
	 * @see #getMoreGamesButton()
	 * @generated
	 * @ordered
	 */

	protected CCSprite moreGamesButton;

	/**
	 * The cached value of the '<em>gameSourcesButton</em>' property.
	 * 
	 * @see #getGameSourcesButton()
	 * @generated
	 * @ordered
	 */

	protected CCSprite gameSourcesButton;

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @return CCScene.
	 * @generated
	 */
	public abstract CCScene scene();
}
