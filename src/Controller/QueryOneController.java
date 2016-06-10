package Controller;


import Hipermercado.Hipermercado;
import Produto.Produto;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;

public class QueryOneController {

    @FXML
    private Text totalLabel;

    @FXML
    private TableColumn<Produto,String > colunaProduto;

    @FXML
    private TableView resultadosTable;

    private Parent parent;
    private Scene scene;
    private Stage stage;
    private Hipermercado facade;

    private ObservableList<Produto> listadeProdutos;



    public void setFacade(Hipermercado facade){this.facade=facade;}

    public void setParent(Parent parent){this.parent = parent;}

    public void setScene(Scene scene){this.scene = scene;}

    public void setStage(Stage stage){this.stage = stage;}

    public void launchController(){
        this.listadeProdutos = facade.listaProdutosNaoComprados();
        this.actualizaTabelaResultados(listadeProdutos);

        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();

    }


    public void actualizaTabelaResultados(ObservableList<Produto> lista){
        colunaProduto.setCellValueFactory(cellData -> cellData.getValue().codigoSimpleStringProperty());
        resultadosTable.setItems(lista);
        totalLabel.setText(String.valueOf(lista.size()));
    }


    public void retrocederHandler(ActionEvent actionEvent) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/Hipermercado.fxml"));
        Parent Hipermercado = fxmlLoader.load();

        HipermercadoController hipermercadoController = fxmlLoader.getController();

        hipermercadoController.setFacade(facade);
        hipermercadoController.setParent(Hipermercado);
        hipermercadoController.setScene(new Scene(Hipermercado,600,400));
        hipermercadoController.setStage(stage);
        hipermercadoController.launchController();

    }
}
