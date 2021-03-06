package Controller;


import Hipermercado.Hipermercado;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import Hipermercado.EstatisticaFilial;

import java.io.IOException;

public class EstatisticasFiliaisController {
    
    @FXML
    private TableView<EstatisticaFilial> filialOneTable;
    @FXML
    private TableColumn<EstatisticaFilial,String> filialOneMesColumn;
    @FXML
    private TableColumn<EstatisticaFilial,String> filialOneFaturacaoColumn;
    @FXML
    private TableView<EstatisticaFilial> filialTwoTable;
    @FXML
    private TableColumn<EstatisticaFilial,String> filialTwoMesColumn;
    @FXML
    private TableColumn<EstatisticaFilial,String> filialTwoFaturacaoColumn;
    @FXML
    private TableView<EstatisticaFilial> filialThreeTable;
    @FXML
    private TableColumn<EstatisticaFilial,String> filialThreeMesColumn;
    @FXML
    private TableColumn<EstatisticaFilial,String> filialThreeFaturacaoColumn;

    private Parent parent;
    private Scene scene;
    private Stage stage;
    private Hipermercado facade;

    private ObservableList<EstatisticaFilial> tabelafilialone;
    private ObservableList<EstatisticaFilial> tabelafilialtwo;
    private ObservableList<EstatisticaFilial> tabelafilialthree;

    public void setFacade(Hipermercado facade){this.facade=facade;}

    public void setParent(Parent parent){this.parent = parent;}

    public void setScene(Scene scene){this.scene = scene;}

    public void setStage(Stage stage){this.stage = stage;}

    public void launchController(){
        this.tabelafilialone = this.facade.estatisticaFilialObservableList(1);
        this.tabelafilialtwo = this.facade.estatisticaFilialObservableList(2);
        this.tabelafilialthree = this.facade.estatisticaFilialObservableList(3);

        actualizaTabelaResultadosOne(this.tabelafilialone);
        actualizaTabelaResultadosTwo(this.tabelafilialtwo);
        actualizaTabelaResultadosThree(this.tabelafilialthree);

        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();

    }

    public void actualizaTabelaResultadosOne(ObservableList<EstatisticaFilial> lista){
        filialOneMesColumn.setCellValueFactory(cellData -> cellData.getValue().getMesProperty());
        filialOneFaturacaoColumn.setCellValueFactory(cellData -> cellData.getValue().getFaturadoProperty());
        filialOneTable.setItems(lista);
    }

    public void actualizaTabelaResultadosTwo(ObservableList<EstatisticaFilial> lista){
        filialTwoMesColumn.setCellValueFactory(cellData -> cellData.getValue().getMesProperty());
        filialTwoFaturacaoColumn.setCellValueFactory(cellData -> cellData.getValue().getFaturadoProperty());
        filialTwoTable.setItems(lista);
    }

    public void actualizaTabelaResultadosThree(ObservableList<EstatisticaFilial> lista){
        filialThreeMesColumn.setCellValueFactory(cellData -> cellData.getValue().getMesProperty());
        filialThreeFaturacaoColumn.setCellValueFactory(cellData -> cellData.getValue().getFaturadoProperty());
        filialThreeTable.setItems(lista);
    }


    public void retrocederHandler(ActionEvent actionEvent) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/OutrasEstatisticas.fxml"));
        Parent hipermercado = fxmlLoader.load();

        OutrasEstatisticasContoller outrasEstatisticas = fxmlLoader.getController();
        outrasEstatisticas.setFacade(facade);
        outrasEstatisticas.setParent(hipermercado);
        outrasEstatisticas.setScene(new Scene(hipermercado,600,400));
        outrasEstatisticas.setStage(stage);
        outrasEstatisticas.launchController();

    }
}
