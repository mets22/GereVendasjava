package Controller;


import Crono.Crono;
import Filial.TrioNComprasNProdsTotGasto;
import Cliente.Cliente;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class QueryThreeController {

    @FXML
    private TableView<TrioNComprasNProdsTotGasto> tabelaResultados;
    @FXML
    private TableColumn<TrioNComprasNProdsTotGasto,String> colunaMes;
    @FXML
    private TableColumn<TrioNComprasNProdsTotGasto,String > colunaCompras;
    @FXML
    private TableColumn<TrioNComprasNProdsTotGasto,String > colunaProdutos;
    @FXML
    private Text clienteLabel;
    @FXML
    private Text totalGastoLabel;

    private ObservableList<TrioNComprasNProdsTotGasto> valoresParaTabela;

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

    public void launchController(Cliente cliente) {

        double totalGasto = 0.0;

        Crono.start();
        this.valoresParaTabela = facade.getComprasMensais(cliente);
        actualizaTable(this.valoresParaTabela);
        this.clienteLabel.setText(cliente.getCodigo());
        for(TrioNComprasNProdsTotGasto t: this.valoresParaTabela){
            totalGasto+=t.getTotgasto();
        }
        this.totalGastoLabel.setText(String.valueOf(totalGasto));
        Crono.stop();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alerta");
        alert.setHeaderText("Tempo:" + Crono.print());
        alert.show();

        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();
    }

    public void actualizaTable(ObservableList<TrioNComprasNProdsTotGasto> lista){
        this.colunaMes.setCellValueFactory(cellData -> cellData.getValue().getMesProperty());
        this.colunaCompras.setCellValueFactory(cellData -> cellData.getValue().getNComprasProperty());
        this.colunaProdutos.setCellValueFactory(cellData -> cellData.getValue().getProdutosDistintosProperty());
        this.tabelaResultados.setItems(lista);
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
