package com.capillary.VisitorMatrix.qa.framework.element.internal;

public interface ICheckBox extends IWebElement {

    void toggle();

    void check();

    void unCheck();

    boolean isChecked();

}
