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
package com.github.wiselenium.core.test;

import static com.github.wiselenium.core.test.util.TestResourceFinder.getAbsolutePath;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.github.wiselenium.core.element.container.Select;
import com.github.wiselenium.core.element.field.Hyperlink;
import com.github.wiselenium.core.pagefactory.Page;

@SuppressWarnings("javadoc")
public class DummyPage extends Page<DummyPage> {
	
	public static final By BY_SELECT1 = By.id("select1");
	public static final By BY_RADIOBUTTONS = By.name("sex");
	public static final String PARTIAL_URL = "dummy.html";
	public static final String URL = getAbsolutePath(PARTIAL_URL);
	
	@FindBy(name = "sex")
	private List<WebElement> radiobuttons;
	private WebElement text;
	private Select select1;
	private Hyperlink link;
	
	
	public Hyperlink getLink() {
		return this.link;
	}
	
	public List<WebElement> getRadiobuttons() {
		return this.radiobuttons;
	}
	
	public Select getSelect1() {
		return this.select1;
	}
	
	public WebElement getText() {
		return this.text;
	}
	
	@Override
	protected void isLoaded() {
		assertTrue(this.getWrappedDriver().getCurrentUrl().endsWith(PARTIAL_URL));
	}
	
	@Override
	protected void load() {
		this.get(URL);
	}
	
}
