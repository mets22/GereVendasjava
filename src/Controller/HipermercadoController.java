package Controller;


import Crono.Crono;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import Hipermercado.Hipermercado;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class HipermercadoController {

    @FXML
    private Button executarButton;

    @FXML
    private TextField queryTwoMes;

    @FXML
    private Text ficheiroLidoLabel;

    @FXML
    private Text vendasLidasLabel;

    @FXML
    private Text vendasErradasLabel;

    @FXML
    private Text vendasAZeroLabel;

    @FXML
    private Text clientesLabel;

    @FXML
    private Text clientesNCompraramLabel;

    @FXML
    private Text produtosLabel;

    @FXML
    private Text produtosNcompradosLabel;

    @FXML
    private Text faturacaoLabel;

    @FXML
    private TextField pathFicheiroVendas;

    @FXML
    private TextField pathFicheiroProdutos;

    @FXML
    private TextField pathEstadosText;

    @FXML
    private TextField pathFicheiroClientes;

    private Parent parent;
    private Scene scene;
    private Stage stage;
    private Hipermercado facade;


    private static String ficheiroClientes;
    private static String ficheiroProdutos;
    private static String ficheiroVendas;
    private static String ficheiroEstado;

    public void setFacade(Hipermercado facade){this.facade=facade;}

    public void setParent(Parent parent){this.parent = parent;}

    public void setScene(Scene scene){this.scene = scene;}

    public void setStage(Stage stage){this.stage = stage;}

    public void launchController(){


        ficheiroClientes = getClass().getClassLoader().getResource("./Hipermercado/Clientes.txt").getPath();
        ficheiroProdutos = getClass().getClassLoader().getResource("./Hipermercado/Produtos.txt").getPath();
        ficheiroVendas  = getClass().getClassLoader().getResource("./Hipermercado/Vendas_1M.txt").getPath();
        //ficheiroEstado = getClass().getClassLoader().getResource("./Hipermercado/hipermercado.dat").getPath();


        BooleanBinding bindButaoExecutarQueryTwo = this.queryTwoMes.textProperty().isEmpty();

        this.executarButton.disableProperty().bind(bindButaoExecutarQueryTwo);

        stage.setResizable(false);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }


    public void handlerProcurarFichVendas(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Abrir Ficheiro de Vendas");
        File a = fileChooser.showOpenDialog(stage);
        this.pathFicheiroVendas.setText(a.getAbsolutePath().toString());
    }


    public void handlerProcurarFichClientes(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Abrir Ficheiro de Clientes");
        File a = fileChooser.showOpenDialog(stage);
        this.pathFicheiroClientes.setText(a.getAbsolutePath().toString());
    }

    public void handlerProcurarFichProdutos(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Abrir Ficheiro de Produtos");
        File a = fileChooser.showOpenDialog(stage);
        this.pathFicheiroProdutos.setText(a.getAbsolutePath().toString());
    }

    public void handlerCarregarFichs(ActionEvent actionEvent) {
        String pathprodutos, pathclientes,pathvendas;
        if(!(pathprodutos=this.pathFicheiroProdutos.getText().trim()).equals("")) ficheiroProdutos = pathprodutos;
        if(!(pathclientes=this.pathFicheiroClientes.getText().trim()).equals("")) ficheiroClientes = pathclientes;
        if(!(pathvendas = this.pathFicheiroVendas.getText().trim()).equals("")) ficheiroVendas = pathvendas;
        Crono.start();
        facade.lerFicheiros(ficheiroClientes,ficheiroProdutos,ficheiroVendas);
        Crono.stop();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Leitura de ficheiros");
        alert.setHeaderText("Leitura dos ficheiros com sucesso");
        alert.setContentText("Tempo de leitura: " + Crono.print());
        alert.show();

        this.ficheiroLidoLabel.setText(facade.ultimoFicheiroLido());
        this.vendasErradasLabel.setText(String.valueOf(facade.vendasErradas()));
        this.vendasAZeroLabel.setText(String.valueOf(facade.vendasAZero()));
        this.clientesLabel.setText(String.valueOf(facade.nrClientesTotal()));
        this.clientesNCompraramLabel.setText(String.valueOf(facade.ClientesNCompraram()));
        this.produtosLabel.setText(String.valueOf(facade.nrProdutosTotal()));
        this.produtosNcompradosLabel.setText(String.valueOf(facade.ProdutosNComprados()));
        this.faturacaoLabel.setText(String.valueOf(facade.faturacaoTotal()));
        this.vendasLidasLabel.setText(String.valueOf(facade.vendasLidasTotal()));
    }

    public void handlerProcurarEstados(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Abrir ficheiro de estado");
        File a = fileChooser.showOpenDialog(stage);
        this.pathEstadosText.setText(a.getAbsolutePath().toString());
    }

    public void handlerGuardarEstado(ActionEvent actionEvent) {
        String pathestado;
        if(!(pathestado=this.pathEstadosText.getText().trim()).equals("")) ficheiroEstado = pathestado;
        try{
            Crono.start();
            facade.gravaEstado(ficheiroEstado);
            Crono.stop();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Guardar estado");
            alert.setHeaderText("Estado guardado com sucesso");
            alert.setContentText("Tempo de execução: " + Crono.print());
            alert.show();
        }catch (IOException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Guardar estado");
            alert.setHeaderText("Erro na gravação de estado");
            alert.show();
        }
    }

    public void handlerRestaurarEstado(ActionEvent actionEvent) {
        String pathestado;
        if(!(pathestado=this.pathEstadosText.getText().trim()).equals("")) ficheiroEstado = pathestado;
        try{
            Crono.start();
            facade = Hipermercado.carregaEstado(ficheiroEstado);
            Crono.stop();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Restaurar estado");
            alert.setHeaderText("Estado restaurado com sucesso");
            alert.setContentText("Tempo de execução:" + Crono.print());
            alert.show();
        }catch (IOException |ClassNotFoundException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Restaurar estado");
            alert.setHeaderText("Erro na recuperação de estado");
            alert.show();
        }
    }

    public void handlerSair(ActionEvent actionEvent) {
        Platform.exit();
        System.exit(0);
    }

    public void queryOneHandler(ActionEvent actionEvent) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/QueryOne.fxml"));
        Parent Hipermercado = fxmlLoader.load();

        QueryOneController queryOneController = fxmlLoader.getController();

        queryOneController.setFacade(facade);
        queryOneController.setParent(Hipermercado);
        queryOneController.setScene(new Scene(Hipermercado,320,480));
        queryOneController.setStage(stage);
        queryOneController.launchController();
    }

    public void queryTwoHandler(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/QueryTwo.fxml"));
        Parent Hipermercado = fxmlLoader.load();

        if((Integer.valueOf(this.queryTwoMes.getText())<0) || (Integer.valueOf(this.queryTwoMes.getText())>12)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setContentText("Precisa de introduzir um valor válido");
            alert.show();
        }

        else {
            int mes = Integer.valueOf(this.queryTwoMes.getText());
            QueryTwoController queryTwoController = fxmlLoader.getController();
            queryTwoController.setFacade(facade);
            queryTwoController.setParent(Hipermercado);
            queryTwoController.setScene(new Scene(Hipermercado, 300, 160));
            queryTwoController.setStage(stage);
            queryTwoController.launchController(mes);
        }
    }
}
