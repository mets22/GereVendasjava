package Controller;


import Cliente.Cliente;
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
    private TextField queryThreeText;

    @FXML
    private Text produtosCompradosLabel;

    @FXML
    private Button executarQueryThreeButton;

    @FXML
    private Button executarQueryTwoButton;

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

    private static String ultimoficheirolido;
    private static int vendaserradas;
    private static int vendasazero;
    private static int nrclientestotal;
    private static int clientesncompraram;
    private static int nrprodutostotal;
    private static int nrprodutosncomprados;
    private static int produtoscomprados;
    private static int nrvendastotal;
    private static double faturacaototal;

    public void setFacade(Hipermercado facade){this.facade=facade;}

    public void setParent(Parent parent){this.parent = parent;}

    public void setScene(Scene scene){this.scene = scene;}

    public void setStage(Stage stage){this.stage = stage;}

    public void launchController(String modo){


        ficheiroClientes = getClass().getClassLoader().getResource("./Hipermercado/Clientes.txt").getPath();
        ficheiroProdutos = getClass().getClassLoader().getResource("./Hipermercado/Produtos.txt").getPath();
        ficheiroVendas  = getClass().getClassLoader().getResource("./Hipermercado/Vendas_1M.txt").getPath();
        //ficheiroEstado = getClass().getClassLoader().getResource("./Hipermercado/hipermercado.dat").getPath();


        BooleanBinding bindBotaoExecutarQueryThree = this.queryThreeText.textProperty().isEmpty();
        BooleanBinding bindBotaoExecutarQueryTwo = this.queryTwoMes.textProperty().isEmpty();

        this.executarQueryTwoButton.disableProperty().bind(bindBotaoExecutarQueryTwo);
        this.executarQueryThreeButton.disableProperty().bind(bindBotaoExecutarQueryThree);

        if(modo.equals("retorno")) actualizaTextBoxes();

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

        actualizaEstados();
        actualizaTextBoxes();
    }

    public void actualizaEstados(){
        ultimoficheirolido=facade.ultimoFicheiroLido();
        vendaserradas=facade.vendasErradas();
        vendasazero=facade.vendasAZero();
        nrclientestotal=facade.nrClientesTotal();
        clientesncompraram=facade.ClientesNCompraram();
        nrprodutostotal=facade.nrProdutosTotal();
        nrprodutosncomprados=facade.ProdutosNComprados();
        faturacaototal=facade.faturacaoTotal();
        nrvendastotal=facade.vendasLidasTotal();
        produtoscomprados=facade.produtosComprados();
    }

    public void actualizaTextBoxes(){
        this.ficheiroLidoLabel.setText(ultimoficheirolido);
        this.vendasErradasLabel.setText(String.valueOf(vendaserradas));
        this.vendasAZeroLabel.setText(String.valueOf(vendasazero));
        this.clientesLabel.setText(String.valueOf(nrclientestotal));
        this.clientesNCompraramLabel.setText(String.valueOf(clientesncompraram));
        this.produtosLabel.setText(String.valueOf(nrprodutostotal));
        this.produtosNcompradosLabel.setText(String.valueOf(nrprodutosncomprados));
        this.faturacaoLabel.setText(String.valueOf(faturacaototal));
        this.vendasLidasLabel.setText(String.valueOf(nrvendastotal));
        this.produtosCompradosLabel.setText(String.valueOf(produtoscomprados));
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

    public void outrasEstatisticasHandler(ActionEvent actionEvent) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/OutrasEstatisticas.fxml"));
        Parent hipermercado = fxmlLoader.load();

        OutrasEstatisticasContoller outrasEstatisticas = fxmlLoader.getController();
        outrasEstatisticas.setFacade(facade);
        outrasEstatisticas.setParent(hipermercado);
        outrasEstatisticas.setScene(new Scene(hipermercado,600,400));
        outrasEstatisticas.setStage(stage);
        outrasEstatisticas.launchController();
    }

    public void queryThreeHandler(ActionEvent actionEvent) throws  IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/QueryThree.fxml"));
        Parent hipermercado = fxmlLoader.load();

        QueryThreeController queryThreeController = fxmlLoader.getController();
        queryThreeController.setFacade(facade);
        queryThreeController.setParent(hipermercado);
        queryThreeController.setScene(new Scene(hipermercado,450,450));
        queryThreeController.setStage(stage);
        String codigo = this.queryThreeText.getText();
        queryThreeController.launchController(new Cliente(codigo));
    }

}
