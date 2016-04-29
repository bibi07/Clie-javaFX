package ch.makery.address;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.prefs.Preferences;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import ch.makery.address.model.Client;
import ch.makery.address.model.ClientListWrapper;
import ch.makery.address.view.FechaStatisticsController;
import ch.makery.address.view.ClientEditDialogController;
import ch.makery.address.view.ClientOverviewController;
import ch.makery.address.view.RootLayoutController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ch.makery.address.util.DatabaseGateway;
import ch.makery.address.model.dao.PartnerDAO;
import ch.makery.address.model.dto.PartnerDTO;

public class MainApp extends Application {
	

    private Stage primaryStage;
    private BorderPane rootLayout;
    private DatabaseGateway dbClass;
    private PartnerDAO partnerDAO;
    
    
    
    // ... AFTER THE OTHER VARIABLES ...

    /**
     * The data as an observable list of Persons.
     */
    private ObservableList<Client> ClientData = FXCollections.observableArrayList();

    /**
     * Constructor
     */
    public MainApp() {
        // Add some sample data
    	if(this.partnerDAO == null)
    		partnerDAO = new PartnerDAO();
    	//Crea la base de datos
    	this.dbClass = new DatabaseGateway();
    	
        try {
			dbClass.CrearBD();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        List<PartnerDTO> partners = null;
		try {
			partners = partnerDAO.SelecceccionarTodosPartner();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}         
        
        for(int i = 0; i < partners.size(); i++){
        	 ClientData.add(new Client(String.valueOf((partners.get(i).getNombre())), String.valueOf(partners.get(i).getApellidos()),String.valueOf(partners.get(i).getDireccion()),partners.get(i).getCiudad(), partners.get(i).getCodigoPostal(), partners.get(i).getFecha(),partners.get(i).getNoVentas()));
        }
        
        /*ClientData.add(new Client("Bernado", "Buriostigui Ruiz"));
        ClientData.add(new Client("Javier", "Brito Pacheco"));
        ClientData.add(new Client("Jorge", "Tecuapa Zalasar"));
        ClientData.add(new Client("Luis", "Visoso Solis"));
        ClientData.add(new Client("David", "Torres Camarillo"));
        ClientData.add(new Client("Lucy", "Castrejo"));
        ClientData.add(new Client("Hector", "Rodrigues Flores"));
        ClientData.add(new Client("Bibiana", "Soriano Pineda"));
        ClientData.add(new Client("Carlos", "Bustamante"));
        */
    }

    /**
     * Returns the data as an observable list of Persons. 
     * @return
     */
    public ObservableList<Client> getClientData() {
        return ClientData;
    }

    // ... THE REST OF THE CLASS ...

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AddressApp");
        
     // Set the application icon.
       // this.primaryStage.getIcons().add(new Image("file:/resources/images/1461104638_news.png"));

        initRootLayout();

        showClientOverview();
    }

    /**
     * Initializes the root layout.
     */
    /**
     * Initializes the root layout and tries to load the last opened
     * person file.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class
                    .getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            // Give the controller access to the main app.
            RootLayoutController controller = loader.getController();
            controller.setMainApp(this);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Try to load last opened person file.
        File file = getClientFilePath();
        if (file != null) {
            loadClientDataFromFile(file);
        }
    }

    /**
     * Shows the person overview inside the root layout.
     */
    public void showClientOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/ClientOverview.fxml"));
            AnchorPane clientOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(clientOverview);

            // Give the controller access to the main app.
            ClientOverviewController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    /**
     * Opens a dialog to edit details for the specified person. If the user
     * clicks OK, the changes are saved into the provided person object and true
     * is returned.
     * 
     * @param client the person object to be edited
     * @return true if the user clicked OK, false otherwise.
     */
    public boolean showClientEditDialog(Client client) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/ClientEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Client");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            ClientEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setClient(client);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Returns the person file preference, i.e. the file that was last opened.
     * The preference is read from the OS specific registry. If no such
     * preference can be found, null is returned.
     * 
     * @return
     */
    public File getClientFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        String filePath = prefs.get("filePath", null);
        if (filePath != null) {
            return new File(filePath);
        } else {
            return null;
        }
    }

    /**
     * Sets the file path of the currently loaded file. The path is persisted in
     * the OS specific registry.
     * 
     * @param file the file or null to remove the path
     */
    public void setClientFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        if (file != null) {
            prefs.put("filePath", file.getPath());

            // Update the stage title.
            primaryStage.setTitle("AddressApp - " + file.getName());
        } else {
            prefs.remove("filePath");

            // Update the stage title.
            primaryStage.setTitle("AddressApp");
        }
    }
    
    /**
     * Loads person data from the specified file. The current person data will
     * be replaced.
     * 
     * @param file
     */
    public void loadClientDataFromFile(File file) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(ClientListWrapper.class);
            Unmarshaller um = context.createUnmarshaller();

            // Reading XML from the file and unmarshalling.
            ClientListWrapper wrapper = (ClientListWrapper) um.unmarshal(file);

            ClientData.clear();
            ClientData.addAll(wrapper.getClient());

            // Save the file path to the registry.
            setClientFilePath(file);

        } catch (Exception e) { // catches ANY exception
           /* Dialogs.create()
                    .title("Error")
                    .masthead("Could not load data from file:\n" + file.getPath())
                    .showException(e); */
        }
    }

    /**
     * Saves the current person data to the specified file.
     * 
     * @param file
     */
    public void saveClientDataToFile(File file) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(ClientListWrapper.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Wrapping our person data.
            ClientListWrapper wrapper = new ClientListWrapper();
            wrapper.setClient(ClientData);

            // Marshalling and saving XML to the file.
            m.marshal(wrapper, file);

            // Save the file path to the registry.
            setClientFilePath(file);
        } catch (Exception e) { // catches ANY exception
          /*  Dialogs.create().title("Error")
                    .masthead("Could not save data to file:\n" + file.getPath())
                    .showException(e); */
        }
    }
  
    /**
     * Opens a dialog to show birthday statistics.
     */
    public void showFechaStatistics() {
        try {
            // Load the fxml file and create a new stage for the popup.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/FechaStatistics.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Fecha Statistics");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the persons into the controller.
            FechaStatisticsController controller = loader.getController();
            controller.setProducData(ClientData);

            dialogStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}