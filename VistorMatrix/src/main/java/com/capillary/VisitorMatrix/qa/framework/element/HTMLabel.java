package com.capillary.VisitorMatrix.qa.framework.element;

import org.openqa.selenium.By;

import com.capillary.VisitorMatrix.qa.framework.element.internal.ILabel;

public class HTMLabel extends HTMWebElement implements ILabel{
    
	public HTMLabel(By by, String pageName, String elementName) {
        super(by, pageName, elementName);
        setElementType("HTMLabel");
    }
	
}
