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
package com.github.wiselenium.core.pagefactory;

import static org.mockito.Mockito.mock;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.github.wiselenium.core.element.container.Select;
import com.github.wiselenium.core.element.container.impl.SelectImpl;
import com.github.wiselenium.core.pagefactory.ClassWithoutNoArgConstructorException;
import com.github.wiselenium.core.pagefactory.WiseContainerProxy;
import com.github.wiselenium.core.pagefactory.dummy.DummyFieldWithoutNoArgConstructor;
import com.github.wiselenium.core.pagefactory.dummy.DummyPage;
import com.github.wiselenium.core.test.WiseTestNG;

@SuppressWarnings("javadoc")
public class WiseContainerProxyTest extends WiseTestNG<WiseContainerProxyTest> {
	
	@Test(expectedExceptions = NoSuchElementException.class)
	public void shouldPropagateOriginalExceptionFromProxy() {
		Select proxy;
		try {
			DummyPage page = this.initElements(DummyPage.class);
			WebElement webElement = page.getText();
			proxy = WiseContainerProxy.getInstance(SelectImpl.class, webElement);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		proxy.getId();
	}
	
	@Test(expectedExceptions = ClassWithoutNoArgConstructorException.class)
	public void shouldThrowExceptionWhenProxyingClassWithoutNoArgConstructor() {
		WiseContainerProxy.getInstance(DummyFieldWithoutNoArgConstructor.class,
			mock(WebElement.class));
	}
	
}
