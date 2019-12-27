package com.capillary.VisitorMatrix.ui.test.dataimport.applicationPages;

import org.openqa.selenium.By;

import com.capillary.VisitorMatrix.qa.framework.element.HTMButton;
import com.capillary.VisitorMatrix.qa.framework.element.HTMCheckBox;
import com.capillary.VisitorMatrix.qa.framework.element.HTMLabel;
import com.capillary.VisitorMatrix.qa.framework.element.HTMRadioButton;
import com.capillary.VisitorMatrix.qa.framework.element.HTMSelect;
import com.capillary.VisitorMatrix.qa.framework.element.HTMTextInput;
import com.capillary.VisitorMatrix.qa.framework.element.HTMWebElement;
import com.capillary.VisitorMatrix.ui.test.dataimport.applicationPages.base.AdminBase;

public class DataImportPage extends AdminBase{
	public DataImportPage()
	{
		super("Data import File upload page");
	}

	public HTMLabel uploadDataCSV()
	{
		return new HTMLabel(By.xpath("//td[contains(text(),'Upload data CSV')]"), getPageName(), "upload Data csv label");
	}
	
	public HTMButton uploadFile()
	{
		return new HTMButton(By.id("load_form__csv_field"), getPageName(), "Uploading file button");
	}
	public HTMTextInput norowsignoredfromTop()
	{
		return new HTMTextInput(By.id("load_form__ignore_rows_top"), getPageName(), "load_form__ignore_rows_top textbox");
	}
	public HTMTextInput norowsignoredfrombottom()
	{
		return new HTMTextInput(By.id("load_form__ignore_rows_bottom"), getPageName(), "load_form__ignore_rows_bottom textbox");
	}
	public HTMTextInput loadFormDelimiter()
	{
		return new HTMTextInput(By.id("load_form__delimiter"), getPageName(), "load_form__delimiter textbox");
	}
	public HTMTextInput loadFormEnclosure()
	{	
		return new HTMTextInput(By.id("load_form__enclosure"), getPageName(), "load_form__enclosure textbox");
	}
	public HTMTextInput loadFormEscape()
	{	
		return new HTMTextInput(By.id("load_form__escape"), getPageName(), "load_form__escape textbox");
	}
	public HTMButton submitButton()
	{
		return new HTMButton(By.name("submit"), getPageName(), "submit Button");
	}
	public HTMWebElement chooseProfile()
	{
		return new HTMWebElement(By.id("templates_form__profile_id"), getPageName(), "Chose profile select button");
	}
	public HTMWebElement chooseInstoreAiDevicesProfile()
	{
		return new HTMWebElement(By.xpath("//select[@id='templates_form__profile_id']/optgroup[contains(@label,'INSTORE AI DEVICES')]/option[contains(text(),' Add Devices ')]"), getPageName(), "templates_form__profile_id Select");
	}
	public HTMSelect chooseTemplate()
	{
		return new HTMSelect(By.id("templates_form__template_field"), getPageName(), "templates_form__template_field Select");
	}
	
	public HTMButton setupImportSubmitButton()
	{
		return new HTMButton(By.id("templates_form__submit"), getPageName(), "setup Import Submit Button");
	}
	
	public HTMLabel  cancelFileImport()
	{
		return new HTMLabel(By.xpath("//h3[contains(text(),'Cancel File Import')]"), getPageName(), "Cancel File Import label");
	}
	
	public HTMRadioButton deleteFileImport()
	{
		return new HTMRadioButton(By.xpath("//input[@value='delete']"), getPageName(), "Delete radio button");
	}
	
	public HTMButton deleteFile()
	{
		return new HTMButton(By.id("cancel_templates_form__submit"), getPageName(), "cancel_templates_form__submit button");
	}
	
	public HTMButton submitMapping()
	{
		return new HTMButton(By.xpath("//h3[contains(text(),'Configure Field Mappings')]/parent::div/descendant::tr/td/input[@id='submit']"), getPageName(), "Submit all mapping button");
	}
	
	public HTMLabel importToTemporaryDB()
	
	{
		return new HTMLabel(By.xpath("//h3[contains(text(),'Import to Temporary DB')]"), getPageName(), "Import To Temporary DB Label");
	}
	
	public HTMCheckBox processData(String dbName)
	{
		return new HTMCheckBox(By.id("maindb_form__process"), getPageName(), "move to "+dbName+"DB");
	}
	
	public HTMLabel noOfRecords()
	{
		return new HTMLabel(By.xpath("//td[contains(text(),'No of records')]/following-sibling::td"), getPageName(), "No of records");
	}
	
	public HTMButton importToDBSubmit()
	{
		return new HTMButton(By.id("maindb_form__submit"), getPageName(), "import to temp db");
	}
	
	public HTMLabel importToMainDB()
	{
		return new HTMLabel(By.xpath("//h3[contains(text(),'Import to Main DB')]"), getPageName(), "Import to Main DB Label");
	}
	
	public HTMCheckBox importToDatabase()
	{
		return new HTMCheckBox(By.id("maindb_form__import"), getPageName(), "main db from import checkbox");
	}
	
	public HTMButton maindbFormSubmit()
	{
		return new HTMButton(By.id("maindb_form__submit"),getPageName(), "maindb_form__submit button");
	}
		
	public HTMWebElement importMessage()
	{
		return new HTMWebElement(By.id("flash_message"), getPageName(), "Verify message after import");
	}

	//td[contains(text(),'Process Data')]/following-sibling::td/input
	//td[contains(text(),'Import to Database')]/following-sibling::td/input[@name='maindb_form__import']
}