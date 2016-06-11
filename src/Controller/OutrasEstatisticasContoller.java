package Controller;

import Hipermercado.Hipermercado;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import Hipermercado.OutrasEstatisticas;

import java.io.IOException;


public class OutrasEstatisticasContoller {

    @FXML
    private TableView<OutrasEstatisticas> estatisticasTable;
    @FXML
    private TableColumn<OutrasEstatisticas,String> mesColumn;
    @FXML
    private TableColumn<OutrasEstatisticas,String> comprasColumn;
    @FXML
    private TableColumn<OutrasEstatisticas,String> faturacaoColumn;
    @FXML
    private TableColumn<OutrasEstatisticas,String> clientesColumn;


    private ObservableList<OutrasEstatisticas> tabela;

    private Parent parent;
    private Scene scene;
    private Stage stage;
    private Hipermercado facade;


    public void setFacade(Hipermercado facade){this.facade=facade;}

    public void setParent(Parent parent){this.parent = parent;}

    public void setScene(Scene scene){this.scene = scene;}

    public void setStage(Stage stage){this.stage = stage;}


    public void launchController(){

        this.tabela = facade.outrasEstatisticasPedidas();
        this.actualizaTabela(this.tabela);

        stage.setResizable(false);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();

    }

    public void actualizaTabela(ObservableList<OutrasEstatisticas> tabela) {
        mesColumn.setCellValueFactory(cellData -> cellData.getValue().getMesProperty());
        comprasColumn.setCellValueFactory(cellData -> cellData.getValue().getNrComprasProperty());
        faturacaoColumn.setCellValueFactory(cellData -> cellData.getValue().getFaturacaoMesProperty());
        clientesColumn.setCellValueFactory(cellData -> cellData.getValue().getNrClientesDistintosProperty());
        this.estatisticasTable.setItems(tabela);

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

    public void FiliaisHandler(ActionEvent actionEvent) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/EstatisticasFiliais.fxml"));
        Parent hipermercado = fxmlLoader.load();

        EstatisticasFiliaisController estatisticasFiliaisController = fxmlLoader.getController();
        estatisticasFiliaisController.setFacade(facade);
        estatisticasFiliaisController.setParent(hipermercado);
        estatisticasFiliaisController.setScene(new Scene(hipermercado,640,450));
        estatisticasFiliaisController.setStage(stage);
        estatisticasFiliaisController.launchController();
    }
}
