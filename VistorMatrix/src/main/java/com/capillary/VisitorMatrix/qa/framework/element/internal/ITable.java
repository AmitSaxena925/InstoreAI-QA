package com.capillary.VisitorMatrix.qa.framework.element.internal;

import org.openqa.selenium.WebElement;

public interface ITable extends IWebElement {

    int getRowCount();

    int getColumnCount();

    WebElement getCellAtIndex(int rowIdx, int colIdx);

}
