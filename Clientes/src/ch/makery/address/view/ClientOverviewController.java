package ch.makery.address.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import ch.makery.address.MainApp;
import ch.makery.address.model.Client;
import ch.makery.address.util.DateUtil;

public class ClientOverviewController {
    @FXML
    private TableView<Client> clientTable;
    @FXML
    private TableColumn<Client, String> NombreColumn;
    @FXML
    private TableColumn<Client, String> ApellidosColumn;

    @FXML
    private Label nombreLabel;
    @FXML
    private Label apellidosLabel;
    @FXML
    private Label noVentasLabel;
    @FXML
    private Label direccionLabel;
    @FXML
    private Label ciudadLabel;
    @FXML
    private Label codigopostalLabel;
    @FXML
    private Label fechaLabel;

    // Reference to the main application.
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public ClientOverviewController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        NombreColumn.setCellValueFactory(
                cellData -> cellData.getValue().nombreProperty());
        ApellidosColumn.setCellValueFactory(
                cellData -> cellData.getValue().apellidosProperty());

        // Clear person details.
        showClientDetails(null);

        // Listen for selection changes and show the person details when changed.
        clientTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showClientDetails(newValue));
    }

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        clientTable.setItems(mainApp.getClientData());
    }
    
    /**
     * Fills all text fields to show details about the person.
     * If the specified person is null, all text fields are cleared.
     * 
     * @param client the person or null
     */
    private void showClientDetails(Client client) {
        if (client != null) {
            // Fill the labels with info from the person object.
            nombreLabel.setText(client.getNombre());
            apellidosLabel.setText(client.getApellidos());
            noVentasLabel.setText(Integer.toString(client.getNoVentas()));
            direccionLabel.setText(client.getDireccion());
            //precio1Label.setText(Integer.toString(produc.getPrecio1()));
            ciudadLabel.setText(client.getCiudad());
            codigopostalLabel.setText(client.getCodigoPostal());

            // TODO: We need a way to convert the birthday into a String! 
            fechaLabel.setText(client.getFecha().toString());
        } else {
            // Person is null, remove all the text.
            nombreLabel.setText("");
            apellidosLabel.setText("");
            noVentasLabel.setText("");
            direccionLabel.setText("");
            ciudadLabel.setText("");
            codigopostalLabel.setText("");
            fechaLabel.setText("");
        }
    }
    
    /**
     * Called when the user clicks on the delete button.
     */
    @FXML
    private void handleDeleteClient() {
        int selectedIndex = clientTable.getSelectionModel().getSelectedIndex();
        clientTable.getItems().remove(selectedIndex);
    }
    
    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
     */
    @FXML
    private void handleNewClient() {
        Client tempClient = new Client();
        boolean okClicked = mainApp.showClientEditDialog(tempClient);
        if (okClicked) {
            mainApp.getClientData().add(tempClient);
        }
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected person.
     */
    @FXML
    private void handleEditClient() {
        Client selectedClient = clientTable.getSelectionModel().getSelectedItem();
        if (selectedClient != null) {
            boolean okClicked = mainApp.showClientEditDialog(selectedClient);
            if (okClicked) {
                showClientDetails(selectedClient);
            }

        } else {
            // Nothing selected.
            
        }
    }
}