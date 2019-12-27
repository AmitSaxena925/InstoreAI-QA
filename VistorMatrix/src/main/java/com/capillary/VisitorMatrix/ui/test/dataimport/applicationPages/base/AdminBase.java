package com.capillary.VisitorMatrix.ui.test.dataimport.applicationPages.base;

import com.capillary.VisitorMatrix.qa.framework.base.BasePage;
import com.capillary.VisitorMatrix.ui.test.dataimport.applicationPages.common.Footer;
import com.capillary.VisitorMatrix.ui.test.dataimport.applicationPages.common.Header;
import com.capillary.VisitorMatrix.ui.test.dataimport.applicationPages.common.SideMenu;

public class AdminBase extends BasePage {

	private Header header;
	private Footer footer;
	private SideMenu sideMenu;

	protected AdminBase(String pageName) {
		super(pageName);
		header = new Header(pageName);
		footer = new Footer(pageName);
		sideMenu = new SideMenu(pageName);
	}

	public SideMenu getSideMenu() {
		return sideMenu;
	}

	public Header getHeader() {
		return header;

	}

	public Footer getFooter() {
		return footer;
	}

}
