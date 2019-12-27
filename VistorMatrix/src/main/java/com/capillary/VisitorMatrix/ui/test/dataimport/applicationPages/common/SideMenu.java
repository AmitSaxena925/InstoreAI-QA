package com.capillary.VisitorMatrix.ui.test.dataimport.applicationPages.common;

import org.openqa.selenium.By;
import com.capillary.VisitorMatrix.qa.framework.base.BasePage;
import com.capillary.VisitorMatrix.qa.framework.element.HTMButton;
import com.capillary.VisitorMatrix.qa.framework.element.HTMLink;

/**
 * Created by Arun on 2019-04-29
**/

public class SideMenu extends BasePage {

	public SideMenu(String pageName) {
		super("Intouch Side Menu ");
	}

	public HTMButton master_data_management() {
		return new HTMButton(By.xpath("//li/span[contains(@title,'Master Data Management')]"), getPageName(),
				"master data management button");
	}

	public HTMButton Data_ImportButton() {
		return new HTMButton(By.xpath("//li/span[contains(@title,'Data Import')]"), getPageName(), "DataImportbutton");
	}

	public HTMLink dataImportLink() {
		return new HTMLink(By.xpath("//a[contains(@title,'data import')]"), getPageName(), "data import link");
	}
}