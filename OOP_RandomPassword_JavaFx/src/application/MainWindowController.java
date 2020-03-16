package application;

import java.awt.color.CMMException;
import java.net.URL;
import java.util.ResourceBundle;

import application.logic.GeneratePwd;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class MainWindowController implements Initializable{
	

    @FXML
    private TextField txtPwd;

    @FXML
    private TextField txtPhrase;

    @FXML
    private CheckBox chckbxNumbers;

    @FXML
    private CheckBox chckbxSpecChar;

    @FXML
    private TextField txtLength;

    @FXML
    private CheckBox chckbxUngapped;
    
    private GeneratePwd service;
    
    @FXML
    void adjustTheProperLength(KeyEvent event) {
    	
    	if(txtPhrase.getText().equals(""))
    		return;
    	Integer desiredTextLength = txtPhrase.getText().length();
    	
    	try {
    		if(txtLength.getText().equals(""))
    			txtLength.setText(String.valueOf(desiredTextLength + 3));
    		Integer tempLength = Integer.parseInt(txtLength.getText());
    		if(tempLength <= desiredTextLength || (desiredTextLength + 3) > tempLength )
    			txtLength.setText(String.valueOf(desiredTextLength + 3));
    	} catch (Exception e) {
    		
    	}
    	
    }

    @FXML
    void createPwd(ActionEvent event) {

    	if(txtLength.getText().equals(""))
    		return;
    	try {
    		Integer tempLength = Integer.parseInt(txtLength.getText());
    		String desiredText = txtPhrase.getText();
    		service.adjustDetails(tempLength,
    				chckbxNumbers.isSelected(), chckbxSpecChar.isSelected(),
    				desiredText, chckbxUngapped.isSelected());
    		txtPwd.setText(GeneratePwd.getTheDesiredPwd());
    		
    	} catch (Exception e) {
    		
    	}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		service = GeneratePwd.getGeneratePwdObject();
	}
	
}
