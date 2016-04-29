package ch.makery.address.view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import ch.makery.address.model.Client;
import ch.makery.address.util.DateUtil;


public class ClientEditDialogController {

    @FXML
    private TextField nombreField;
    @FXML
    private TextField apellidosField;
    @FXML
    private TextField noVentasField;
    @FXML
    private TextField direccionField;
    @FXML
    private TextField ciudadField;
    @FXML
    private TextField codigoPostalField;
    @FXML
    private TextField FechaField;


    private Stage dialogStage;
    private Client client;
    private boolean okClicked = false;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Sets the stage of this dialog.
     * 
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Sets the person to be edited in the dialog.
     * 
     * @param client
     */
    public void setClient(Client client) {
        this.client = client;

        nombreField.setText(client.getNombre());
        apellidosField.setText(client.getApellidos());
        noVentasField.setText(Integer.toString(client.getNoVentas()));
        direccionField.setText(client.getDireccion());
       // precio1Field.setText(Integer.toString(produc.getPrecio1()));
        ciudadField.setText(client.getCiudad());
        codigoPostalField.setText(client.getCodigoPostal());
        FechaField.setText(client.getFecha().toString());
        FechaField.setPromptText("dd.mm.yyyy");
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     * 
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
    /**
     * Called when the user clicks ok.
     * @throws ParseException 
     */
    @FXML
    private void handleOk() throws ParseException {
        if (isInputValid()) {
            client.setNombre(nombreField.getText());
            client.setApellidos(apellidosField.getText());
            client.setNoVentas(Integer.parseInt(noVentasField.getText()));
            client.setDireccion(direccionField.getText());
            //produc.setPrecio1(Integer.parseInt(precio1Field.getText()));
            client.setCiudad(ciudadField.getText());
            client.settCodigoPostal(codigoPostalField.getText());
            Date date = formatter.parse(FechaField.getText());
            client.setFecha(date);

            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     * 
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (nombreField.getText() == null || nombreField.getText().length() == 0) {
            errorMessage += "No valid first name!\n"; 
        }
        if (apellidosField.getText() == null || apellidosField.getText().length() == 0) {
            errorMessage += "No valid last name!\n"; 
        }
        
        
        
        if (noVentasField.getText() == null || noVentasField.getText().length() == 0) {
            errorMessage += "No valid postal code!\n"; 
        } else {
            // try to parse the postal code into an int.
            try {
                Integer.parseInt(noVentasField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid postal code (must be an integer)!\n"; 
            }
        }
        
        
        
        if (direccionField.getText() == null || direccionField.getText().length() == 0) {
            errorMessage += "No valid street!\n"; 
        }

        /*if (precio1Field.getText() == null || precio1Field.getText().length() == 0) {
            errorMessage += "No valid postal code!\n"; 
        } else {
            // try to parse the postal code into an int.
            try {
                Integer.parseInt(precio1Field.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid postal code (must be an integer)!\n"; 
            }
        }*/
        
        if (ciudadField.getText() == null || ciudadField.getText().length() == 0) {
            errorMessage += "No valid precio1!\n"; 
        }

        if (codigoPostalField.getText() == null || codigoPostalField.getText().length() == 0) {
            errorMessage += "No valid city!\n"; 
        }

        if (FechaField.getText() == null || FechaField.getText().length() == 0) {
            errorMessage += "No valid Fecha!\n";
        } else {
            if (!DateUtil.validDate(FechaField.getText())) {
                errorMessage += "No valid Fecha|. Use the format dd.mm.yyyy!\n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            
            return false;
        }
    }
}