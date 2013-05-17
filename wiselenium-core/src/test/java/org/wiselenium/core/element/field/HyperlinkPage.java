package org.wiselenium.core.element.field;

import org.openqa.selenium.WebElement;
import org.wiselenium.core.pagefactory.Page;

@SuppressWarnings("javadoc")
public class HyperlinkPage extends Page<HyperlinkPage> {
	
	public static final String HYPERLINK_CLICKED_MESSAGE = "hyperlink clicked";
	public static final String HYPERLINK_VALUE = "hyperlink test";
	public static final String HIDDEN_HYPERLINK_VALUE = "hiddenHyperlink test";
	public static final String URL = "hyperlink.html";
	public static final String TITLE = "page for hyperlink tests";
	public static final String HYPERLINK_HREF = "#";
	public static final String HYPERLINK_TARGET = "_self";
	
	private Hyperlink hyperlink;
	private Hyperlink hiddenHyperlink;
	private WebElement message;
	
	
	public Hyperlink getHiddenHyperlink() {
		return this.hiddenHyperlink;
	}
	
	public Hyperlink getHyperlink() {
		return this.hyperlink;
	}
	
	public String getMessage() {
		return this.message.getText();
	}
	
}