package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import gui.listeners.DataChangeListener;
import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Withdraw;
import model.exception.ValidationException;

public class WithdrawFormController implements Initializable{
	
	private Withdraw entity;
	
	private WithdrawController withdrawController;
    
    private List<DataChangeListener> dataChangeListeners = new ArrayList<>();
	
	@FXML
	private TextField txtId;
	
	@FXML
	private TextField txtValue;
	
	@FXML
	private Label labelErrorName;
	
	@FXML
	private Button btWithdraw;
	
	@FXML
	private Button btCancel;
	
    public void setWithdrawController(WithdrawController controller) {
        this.withdrawController = controller;
    }
	
	public void setWithdraw(Withdraw entity) {
		this.entity = entity;
	}

	public void subscriveDataChangeListener(DataChangeListener listener) {
		dataChangeListeners.add(listener);
	}
	
	@FXML
    public void onBtWithdrawAction(ActionEvent event) {
        if (entity == null) {
            throw new IllegalStateException("Entity was null");
        }
        try {
            entity = getFormData();
            withdrawController.setListaMock(entity);
            String notesMessage = calculateNotes(entity.getValue());
            notifyDataChangeListeners();
            Alerts.showAlert("Withdrawal Successful", null, notesMessage, AlertType.INFORMATION);
            Utils.currentStage(event).close();
        } catch (ValidationException e) {
            setErrorMessage(e.getErrors());
        }
    }

	
	private String calculateNotes(int value) {
	    int[] noteDenominations = {100, 50, 20, 10, 5, 2};
	    int[] noteCounts = new int[noteDenominations.length];
	    int remainingValue = value;

	    StringBuilder sb = new StringBuilder();
	    sb.append("The number of notes withdrawn:\n");

	    for (int i = 0; i < noteDenominations.length; i++) {
	        noteCounts[i] = remainingValue / noteDenominations[i];
	        remainingValue %= noteDenominations[i];

	        if (noteCounts[i] > 0) {
	            sb.append(noteCounts[i]).append(" x ").append(noteDenominations[i]).append("\n");
	        }
	    }

	    if (remainingValue > 0) {
	        sb.append("Remaining amount refounded: ").append(remainingValue);
	    }

	    return sb.toString();
	}
	
	
	
	private void notifyDataChangeListeners() {
		for(DataChangeListener listener: dataChangeListeners) {
			listener.onChangeData();
		}
	}

	private Withdraw getFormData() {
		Withdraw obj = new Withdraw(0, 0);
		
		ValidationException exception = new ValidationException("Validation Error");
		
		obj.setId(Utils.tryParseToInt(txtId.getText()));
		
		if (txtValue.getText() == null || txtValue.getText().trim().equals("")) {
			exception.addErrors("Value", "Field can't be empty");
		}
		obj.setValue(Utils.tryParseToInt(txtValue.getText()));
		
		if (exception.getErrors().size() > 0) {
			throw exception;
		}
		
		return obj;	
	}

	@FXML
	public void onBtCancelAction(ActionEvent event) {
		Utils.currentStage(event).close();
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}
	
	private void initializeNodes() {
		Constraints.setTextFieldInteger(txtId);
		Constraints.setTextFieldInteger(txtValue);
	}
	
	public void updateFormData() {
		if (entity == null) {
			throw new IllegalStateException("Entity was Null");
		}
		txtId.setText(String.valueOf(entity.getId()));
		txtValue.setText(String.valueOf(entity.getValue()));
	}
	
	private void setErrorMessage(Map<String, String> errors) {
		Set<String> fields = errors.keySet();
		
		if (fields.contains("Value")) {
			labelErrorName.setText(errors.get("Value"));
		}
	}
	
	
}
