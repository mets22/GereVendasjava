package Controller;

import Filial.ParTotVendasTotClientesMes;
import Hipermercado.Hipermercado;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;


public class QueryTwoController {

    @FXML
    private Text mesLabel;

    @FXML
    private Text vendasLabel;

    @FXML
    private Text clientesLabel;

    private Parent parent;
    private Scene scene;
    private Stage stage;
    private Hipermercado facade;

    public void setFacade(Hipermercado facade){this.facade=facade;}

    public void setParent(Parent parent){this.parent = parent;}

    public void setScene(Scene scene){this.scene = scene;}

    public void setStage(Stage stage){this.stage = stage;}

    public void launchController(int mes){

        this.mesLabel.setText(String.valueOf(mes));

        ParTotVendasTotClientesMes par = facade.getTotVendasTotCli(mes);

        this.clientesLabel.setText(String.valueOf(par.getnClientes()));
        this.vendasLabel.setText(String.valueOf(par.getnVendas()));

        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();

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
