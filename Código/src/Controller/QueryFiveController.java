package Controller;

import Crono.Crono;
import Filial.ParProdQuantidade;
import Hipermercado.Hipermercado;
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
import Cliente.Cliente;

import java.io.IOException;

public class QueryFiveController {

    @FXML
    private TableView<ParProdQuantidade> tabelaResultados;

    @FXML
    private TableColumn<ParProdQuantidade,String> produtoColumn;

    @FXML
    private TableColumn<ParProdQuantidade,String> vendasColumn;


    private ObservableList<ParProdQuantidade> lista;


    private Parent parent;
    private Scene scene;
    private Stage stage;
    private Hipermercado facade;


    public void setFacade(Hipermercado facade){this.facade=facade;}

    public void setParent(Parent parent){this.parent = parent;}

    public void setScene(Scene scene){this.scene = scene;}

    public void setStage(Stage stage){this.stage = stage;}

    public void launchController(Cliente cliente){

        Crono.start();
        this.lista = facade.getProdsMaisComprados(cliente);
        actualizaLista(this.lista);
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

    public void actualizaLista(ObservableList<ParProdQuantidade> list){
        this.produtoColumn.setCellValueFactory(cellData -> cellData.getValue().getCodigoProdutoProperty());
        this.vendasColumn.setCellValueFactory(cellData -> cellData.getValue().getQuantidadeProperty());
        this.tabelaResultados.setItems(list);
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
