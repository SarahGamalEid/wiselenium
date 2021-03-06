/**
 * Copyright (c) 2013 Andre Ricardo Schaffer
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.github.wiselenium.core.element.frame.impl;

import static com.github.wiselenium.core.element.frame.impl.WiseFrameInnerElementUtil.exportFields;
import static com.github.wiselenium.core.element.frame.impl.WiseFrameInnerElementUtil.getWrappedElement;
import static com.github.wiselenium.core.element.frame.impl.WiseFrameInnerElementUtil.isGetWrappedElement;
import static com.github.wiselenium.core.util.FrameUtil.getCurrentFramePath;

import java.lang.reflect.Method;
import java.util.List;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import org.openqa.selenium.WebElement;

import com.github.wiselenium.core.WiseContext;
import com.github.wiselenium.core.pagefactory.WisePageFactory;
import com.github.wiselenium.core.util.FrameUtil;

/**
 * The wiselenium proxy for frame inner frames.
 * 
 * @author Andre Ricardo Schaffer
 * @since 0.1.0
 */
final class WiseFrameInnerFrameProxy<E> implements MethodInterceptor {
	
	private final E wrappedElement;
	private final List<String> framePath;
	private boolean elementsInitialized;
	
	
	private WiseFrameInnerFrameProxy(E element) {
		this.wrappedElement = element;
		this.framePath = FrameUtil.getCurrentFramePath();
	}
	
	static <E> E getInstance(E element) {
		return WiseFrameInnerElementUtil.createProxy(element, new WiseFrameInnerFrameProxy<E>(
			element));
	}
	
	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy)
		throws Throwable { // NOSONAR because it's an overridden method
	
		if (isGetWrappedElement(method)) return getWrappedElement(this.wrappedElement);
		
		List<String> currentPath = getCurrentFramePath();
		try {
			this.switchToFrame();
			this.initElements(obj);
			return proxy.invokeSuper(obj, args);
		} finally {
			FrameUtil.switchToFrame(currentPath);
		}
	}
	
	private synchronized void initElements(Object obj) {
		if (!this.elementsInitialized) {
			WisePageFactory.initElements(WiseContext.getDriver(), obj);
			exportFields(obj);
			this.elementsInitialized = true;
		}
	}
	
	private void switchToFrame() {
		FrameUtil.switchToFrame(this.framePath);
		WiseContext.getDriver().switchTo()
			.frame((WebElement) WiseFrameInnerElementUtil.getWrappedElement(this.wrappedElement));
	}
	
}
