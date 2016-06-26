package Controller;


import Crono.Crono;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import Filial.ParClienteTotGasto;
import Hipermercado.Hipermercado;
import javafx.stage.Stage;

import java.io.IOException;

public class QuerySevenController {

    @FXML
    private TableView<ParClienteTotGasto> resultadosTabelaOne;
    @FXML
    private TableColumn<ParClienteTotGasto,String > clienteColumnOne;
    @FXML
    private TableColumn<ParClienteTotGasto,String> faturadoColumnOne;
    @FXML
    private TableView<ParClienteTotGasto> resultadosTabelaTwo;
    @FXML
    private TableColumn<ParClienteTotGasto,String> clienteColumnTwo;
    @FXML
    private TableColumn<ParClienteTotGasto,String> faturadoColumnTwo;
    @FXML
    private TableView<ParClienteTotGasto> resultadosTabelaThree;
    @FXML
    private TableColumn<ParClienteTotGasto,String> clienteColumnThree;
    @FXML
    private TableColumn<ParClienteTotGasto,String> faturadoColumnThree;

    private Parent parent;
    private Scene scene;
    private Stage stage;
    private Hipermercado facade;

    private ObservableList<ParClienteTotGasto> listaOne;
    private ObservableList<ParClienteTotGasto> listaTwo;
    private ObservableList<ParClienteTotGasto> listaThree;



    public void setFacade(Hipermercado facade){this.facade=facade;}

    public void setParent(Parent parent){this.parent = parent;}

    public void setScene(Scene scene){this.scene = scene;}

    public void setStage(Stage stage){this.stage = stage;}


    public void launchController(){
        Crono.start();
        this.listaOne = facade.getTop3Cli(1);
        this.listaTwo = facade.getTop3Cli(2);
        this.listaThree = facade.getTop3Cli(3);
        Crono.stop();
        actualizaListaOne(this.listaOne);
        actualizaListaTwo(this.listaTwo);
        actualizaListaThree(this.listaThree);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Aviso");
        alert.setHeaderText("Tempo:" + Crono.print());
        alert.show();

        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();
    }


    public void actualizaListaOne(ObservableList<ParClienteTotGasto> listaPONE){
        this.clienteColumnOne.setCellValueFactory(cellData -> cellData.getValue().getCodigoClienteProperty());
        this.faturadoColumnOne.setCellValueFactory(cellData -> cellData.getValue().getTotalGastoProperty());
        this.resultadosTabelaOne.setItems(listaPONE);
    }

    public void actualizaListaTwo(ObservableList<ParClienteTotGasto> listaPTWO){
        this.clienteColumnTwo.setCellValueFactory(cellData -> cellData.getValue().getCodigoClienteProperty());
        this.faturadoColumnTwo.setCellValueFactory(cellData -> cellData.getValue().getTotalGastoProperty());
        this.resultadosTabelaTwo.setItems(listaPTWO);
    }

    public void actualizaListaThree(ObservableList<ParClienteTotGasto> listaPTHREE){
        this.clienteColumnThree.setCellValueFactory(cellData -> cellData.getValue().getCodigoClienteProperty());
        this.faturadoColumnThree.setCellValueFactory(cellData -> cellData.getValue().getTotalGastoProperty());
        this.resultadosTabelaThree.setItems(listaPTHREE);
    }


    public void retrocederHandler(ActionEvent actionEvent) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/Hipermercado.fxml"));
        Parent Hipermercado = fxmlLoader.load();

        HipermercadoController hipermercadoController = fxmlLoader.getController();

        hipermercadoController.setFacade(facade);
        hipermercadoController.setParent(Hipermercado);
        hipermercadoController.setScene(new Scene(Hipermercado,600,400));
        hipermercadoController.setStage(stage);
        hipermercadoController.launchController("retorno");

    }
}
