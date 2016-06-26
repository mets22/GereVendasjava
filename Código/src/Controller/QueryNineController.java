package Controller;


import Crono.Crono;
import Filial.ParStringDouble;
import Hipermercado.Hipermercado;
import Produto.Produto;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

public class QueryNineController {


    @FXML
    private TableColumn<ParStringDouble,String> quantidadeColumnOne;
    @FXML
    private TableColumn<ParStringDouble,String> quantidadeColumnTwo;
    @FXML
    private TableColumn<ParStringDouble,String> quantidadeColumnThree;
    @FXML
    private TableView<ParStringDouble> resultadosTableOne;
    @FXML
    private TableColumn<ParStringDouble,String> clienteColumnOne;
    @FXML
    private TableColumn<ParStringDouble,String> faturadoColumnOne;
    @FXML
    private TableView<ParStringDouble> resultadosTableTwo;
    @FXML
    private TableColumn<ParStringDouble,String> clienteColumnTwo;
    @FXML
    private TableColumn<ParStringDouble,String> faturadoColumnTwo;
    @FXML
    private TableView<ParStringDouble> tableResultadosThree;
    @FXML
    private TableColumn<ParStringDouble,String> clienteColumnThree;
    @FXML
    private TableColumn<ParStringDouble,String> faturadoColumnThree;


    private ObservableList<ParStringDouble> listOne;
    private ObservableList<ParStringDouble> listTwo;
    private ObservableList<ParStringDouble> listThree;


    private Parent parent;
    private Scene scene;
    private Stage stage;
    private Hipermercado facade;

    public void setFacade(Hipermercado facade) {
        this.facade = facade;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void launchController(Produto produto, int X){

        Crono.start();
        this.listOne = facade.clientesquemaiscompraram(1,produto,X);
        this.listTwo = facade.clientesquemaiscompraram(2,produto,X);
        this.listThree = facade.clientesquemaiscompraram(3,produto,X);
        Crono.stop();

        actualizaListaOne(this.listOne);
        actualizaListaTwo(this.listTwo);
        actualizaListaThree(this.listThree);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Aviso");
        alert.setHeaderText("Tempo:" + Crono.print());
        alert.show();

        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();

    }

    public void actualizaListaOne(ObservableList<ParStringDouble> listaPONE){
        this.clienteColumnOne.setCellValueFactory(cellData -> cellData.getValue().getCodigoClienteProperty());
        this.faturadoColumnOne.setCellValueFactory(cellData -> cellData.getValue().getTotalFaturadoPropert());
        this.quantidadeColumnOne.setCellValueFactory(cellData -> cellData.getValue().getQuantidadeProperty());
        this.resultadosTableOne.setItems(listaPONE);
    }
    public void actualizaListaTwo(ObservableList<ParStringDouble> listaPTWO){
        this.clienteColumnTwo.setCellValueFactory(cellData -> cellData.getValue().getCodigoClienteProperty());
        this.faturadoColumnTwo.setCellValueFactory(cellData -> cellData.getValue().getTotalFaturadoPropert());
        this.quantidadeColumnTwo.setCellValueFactory(cellData -> cellData.getValue().getQuantidadeProperty());
        this.resultadosTableTwo.setItems(listaPTWO);
    }
    public void actualizaListaThree(ObservableList<ParStringDouble> listaPTHREE){
        this.clienteColumnThree.setCellValueFactory(cellData -> cellData.getValue().getCodigoClienteProperty());
        this.faturadoColumnThree.setCellValueFactory(cellData -> cellData.getValue().getTotalFaturadoPropert());
        this.quantidadeColumnThree.setCellValueFactory(cellData -> cellData.getValue().getQuantidadeProperty());
        this.tableResultadosThree.setItems(listaPTHREE);
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
