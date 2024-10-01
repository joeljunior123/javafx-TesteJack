package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import gui.listeners.DataChangeListener;
import gui.util.Alerts;
import gui.util.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Withdraw;

public class WithdrawController implements Initializable, DataChangeListener{
	
	private List<Withdraw> listaMock = new ArrayList<>();
	
	@FXML
	private TableView<Withdraw> tableViewWithdraw;
	
	@FXML
	private TableColumn<Withdraw, Integer> tableColumnId;
	
	@FXML
	private TableColumn<Withdraw, Integer> tableColumnValue;
	
	@FXML
	private Button btWithdraw;
	
	private ObservableList<Withdraw> obsList;
	
	@FXML
	public void onBtWithdrawAction(ActionEvent event) {
		Stage parentStage = Utils.currentStage(event);
		Withdraw obj = new Withdraw(0, 0);
		createDialogForm(obj, "/gui/WithdrawForm.fxml", parentStage);	
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {		
		initializeNodes();
	}

	private void initializeNodes() {
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnValue.setCellValueFactory(new PropertyValueFactory<>("value"));
		
		Stage stage = (Stage) Main.getMainScene().getWindow();
		
		tableViewWithdraw.prefHeightProperty().bind(stage.heightProperty());
	}
	
	public void updateTableView() {
		List<Withdraw> list = listaMock;
		
		obsList = FXCollections.observableArrayList(list);
		tableViewWithdraw.setItems(obsList);
	}
	
	private void createDialogForm(Withdraw obj, String absoluteName, Stage parentStage) {
	    try {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
	        Pane pane = loader.load();

	        WithdrawFormController controller = loader.getController();
	        controller.setWithdrawController(this);
	        controller.setWithdraw(obj);
	        controller.subscriveDataChangeListener(this);
	        controller.updateFormData();

	        Stage dialogStage = new Stage();
	        dialogStage.setTitle("Withdraw data");
	        dialogStage.setScene(new Scene(pane));
	        dialogStage.setResizable(false);
	        dialogStage.initOwner(parentStage);
	        dialogStage.initModality(Modality.WINDOW_MODAL);
	        dialogStage.showAndWait();
	    } catch (IOException e) {
	        Alerts.showAlert("IO Exception", "Error Loading View", e.getMessage(), AlertType.ERROR);
	    }
	}

	@Override
	public void onChangeData() {
		updateTableView();
	}
	public List<Withdraw> getListaMock() {
		return listaMock;
	}
	
	public List<Withdraw> setListaMock(Withdraw entity) {
		listaMock.add(entity);
		return listaMock;
	}
}
