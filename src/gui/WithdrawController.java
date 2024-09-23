package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Withdraw;
import model.services.WithdrawService;

public class WithdrawController implements Initializable{
	
	private WithdrawService service;
	
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
	public void onBtWithdrawAction() {
		System.out.println("Alo");
	}
	
	public void setWithdrawService(WithdrawService service) {
		this.service = service;
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
		if (service == null) {
			throw new IllegalStateException("Service was null");
		}
		List<Withdraw> list = service.findAll();
		
		obsList = FXCollections.observableArrayList(list);
		tableViewWithdraw.setItems(obsList);
	}
}
