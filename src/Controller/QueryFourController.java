package Controller;


import Crono.Crono;
import Filial.TrioNVendasNClientesTotFact;
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

public class QueryFourController {

    @FXML
    private TableView<TrioNVendasNClientesTotFact> resultadoTable;

    @FXML
    private TableColumn<TrioNVendasNClientesTotFact,String> mesColumn;
    @FXML
    private TableColumn<TrioNVendasNClientesTotFact,String> vendasColumn;
    @FXML
    private TableColumn<TrioNVendasNClientesTotFact,String> clientesColumn;
    @FXML
    private TableColumn<TrioNVendasNClientesTotFact,String> faturadoColumn;


    private ObservableList<TrioNVendasNClientesTotFact> lista;

    private Parent parent;
    private Scene scene;
    private Stage stage;
    private Hipermercado facade;


    public void setFacade(Hipermercado facade){this.facade=facade;}

    public void setParent(Parent parent){this.parent = parent;}

    public void setScene(Scene scene){this.scene = scene;}

    public void setStage(Stage stage){this.stage = stage;}


    public void launchController(Produto produto){

        Crono.start();
        this.lista = facade.getVendasMensais(produto);
        actualizaTabela(lista);
        Crono.stop();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alerta");
        alert.setContentText("Tempo:" + Crono.print());
        alert.show();


        stage.setResizable(false);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();

    }

    public void actualizaTabela(ObservableList<TrioNVendasNClientesTotFact> listar){
        this.mesColumn.setCellValueFactory(cellData -> cellData.getValue().getMesProperty());
        this.vendasColumn.setCellValueFactory(cellData -> cellData.getValue().getNrVendasProperty());
        this.clientesColumn.setCellValueFactory(cellData -> cellData.getValue().getNrClientesProperty());
        this.faturadoColumn.setCellValueFactory(cellData -> cellData.getValue().getTotalFaturadoProperty());
        this.resultadoTable.setItems(listar);
    }



    public void retrocederHandler(ActionEvent actionEvent) throws IOException {
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
