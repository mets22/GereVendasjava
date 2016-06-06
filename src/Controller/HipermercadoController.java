package Controller;


import Crono.Crono;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import Hipermercado.Hipermercado;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class HipermercadoController {


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
    }

    public void handlerProcurarEstados(ActionEvent actionEvent) {
    }

    public void handlerGuardarEstado(ActionEvent actionEvent) {
    }

    public void handlerRestaurarEstado(ActionEvent actionEvent) {
    }

    public void handlerSair(ActionEvent actionEvent) {
        Platform.exit();
        System.exit(0);
    }
}
