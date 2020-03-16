package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;

public class MainWindowController implements Initializable{

	@FXML
	private Button btnFirst;

	@FXML
	private Button btnSecond;

	@FXML
	private Slider sldHorizont;

	@FXML
	private Slider sldVertical;

	@FXML
	private TextField txtFldText;

    @FXML
    private ToggleGroup os;

    @FXML
    private RadioButton rdBtn1;

    @FXML
    private RadioButton rdBtn2;

    @FXML
    private RadioButton rdBtn3;

    @FXML
    private RadioButton rdBtn4;

	@FXML
	private CheckBox chckbxColor1;

	@FXML
	private CheckBox chckbxColor2;

	@FXML
	private CheckBox chckbxColor3;

	@FXML
	private CheckBox chckbxColor4;

	@FXML
	private Label lblResults;

	@FXML
	private ListView<String> lstvwList;

	@FXML
	private ChoiceBox<Integer> chcbxSelects;

	@FXML
	private ComboBox<String> cmbbxSelects;

	private EventHandler<MouseEvent> selectList = new EventHandler<MouseEvent>() {
		
		@Override
		public void handle(MouseEvent event) {
			
			if(event.getSource() == lstvwList){
				String result = "You have selected " + ((ListView)event.getSource())
						.getSelectionModel()
						.getSelectedItem().toString();
				lblResults.setText(result);
			}
		}
	};
	
	private EventHandler<ScrollEvent> scrolling = new EventHandler<ScrollEvent>() {
		
		@Override
		public void handle(ScrollEvent event) {
			if(event.getSource() == lstvwList){
				lblResults.setText("You scrolled the list");
			}
		}

	}; 
	
	private EventHandler<MouseEvent> sliderDregged = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent event) {
			
			if(event.getSource() == sldHorizont)
				lblResults.setText("Horizontal slider dragged");
			if(event.getSource() == sldVertical)
				lblResults.setText("Vertical slider dragged");
		}
	};
	
	private EventHandler<MouseEvent> sliderDragging = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent event) {
			
			if(event.getSource() == sldHorizont)
				lblResults.setText("Horizontal slider under dragging");
			if(event.getSource() == sldVertical)
				lblResults.setText("Vertical slider under dragging");
		}
	};
	
	private EventHandler<KeyEvent> typingInTextfield = new EventHandler<KeyEvent>() {
		
		@Override
		public void handle(KeyEvent event) {
			
			if(! txtFldText.getText().equals("")){
				lblResults.setText("The text of the textField is " + txtFldText.getText());
			}
		}
	};
	
	private EventHandler<ActionEvent> click_select = new  EventHandler<ActionEvent>(){

		@Override
		public void handle(ActionEvent event) {
			
			if(event.getSource() == btnFirst)
				lblResults.setText("First button has been clicked");
			if(event.getSource() == btnSecond)
				lblResults.setText("Second button has been clicked");
			if(event.getSource() == cmbbxSelects){
			
				String select = ((ComboBox<String>)event
						.getSource()).getSelectionModel().getSelectedItem();
				lblResults.setText("You have chosen the " + select);
			}
			if(event.getSource() == chcbxSelects){
				
				Integer select = ((ChoiceBox<Integer>) event.getSource()).getValue();
				lblResults.setText("You have chosen the " + select.toString());
			}
			if(event.getSource() instanceof RadioButton){
				
				String selected = ((RadioButton)event.getSource())
						.getText();
				lblResults.setText("You have chosen the " + selected);
			}
			if(event.getSource() instanceof CheckBox){
				
				String result = "You have selected ";
				if (chckbxColor1.isSelected())
					result += chckbxColor1.getText() + " ";
				if (chckbxColor2.isSelected())
					result += chckbxColor2.getText() + " ";
				if (chckbxColor3.isSelected())
					result += chckbxColor3.getText() + " ";
				if (chckbxColor4.isSelected())
					result += chckbxColor4.getText() + " ";
				lblResults.setText(result);
			}

		}
		
	};
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		fillUpTheFields();
		adjustButtonEvents();
	}

	private void fillUpTheFields() {
		
		cmbbxSelects.getItems().addAll(new String[] { "Monday", "Tuesday", "Wednesday",
				"Thuesday", "Friday", "Saturday", "Sunday"});
		chcbxSelects.getItems().addAll(new Integer[]{ 1, 2, 3, 4, 5, 6, 7});
		
		ObservableList<String> listToview = FXCollections.observableArrayList(
			"Lorem ipsum" ,  "dolor sit amet," , "consectetur adipiscing",
					"elit. Pellentesque","quam leo,","aliquet at tincidunt et,",
					"blandit sed ipsum.","Phasellus magna","neque, interdum eget",
					"urna et, rutrum lacinia","dui. Aenean maximus",
					"urna sit amet urna lacinia,","finibus rhoncus purus",
					"maximus. Pellentesque","ultricies suscipit",
					"orci. Orci varius natoque","penatibus et magnis dis.");
		lstvwList.setItems(listToview);
		
		}
		
				
		
	

	private void adjustButtonEvents() {
		
		btnFirst.setOnAction(click_select);
		btnSecond.setOnAction(click_select);
		
		cmbbxSelects.setOnAction(click_select);
		chcbxSelects.setOnAction(click_select);
		
		lstvwList.setOnMouseClicked(selectList);
		lstvwList.setOnScroll(scrolling);
		sldHorizont.setOnMouseDragged(sliderDragging);
		sldHorizont.setOnMouseClicked(sliderDregged);
		sldVertical.setOnMouseDragged(sliderDragging);
		sldVertical.setOnMouseClicked(sliderDregged);
		
		chckbxColor1.setOnAction(click_select);
		chckbxColor2.setOnAction(click_select);
		chckbxColor3.setOnAction(click_select);
		chckbxColor4.setOnAction(click_select);
		
		rdBtn1.setOnAction(click_select);
		rdBtn2.setOnAction(click_select);
		rdBtn3.setOnAction(click_select);
		rdBtn4.setOnAction(click_select);
		
		txtFldText.setOnKeyTyped(typingInTextfield);
	}

}
