package sample;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;


public class Controller {
    @FXML
    Label labelTitulo;
    @FXML
    ListView listaTiempo;
    @FXML
    ObservableList items =
            FXCollections.observableArrayList();
    @FXML
    Button btnRefrescar;
    @FXML
    TextArea textInfo;

    public Parser parse1 = new Parser();

    //Initialize
    public void initialize() throws IOException, SAXException, ParserConfigurationException {
        //Muestra icono boton
        btnRefrescar.setGraphic(new ImageView("refresh1.png"));
        //Desactiva el textArea
        textInfo.setDisable(true);
        //Llama a funcion que rellena los arrays de informacion
        parse1.anadirInfoArrays();

    }
   public void refrescar() throws IOException, SAXException, ParserConfigurationException {
items.get(1);
       for(int i = 0;i < parse1.dias.size();i++){
           items.add(parse1.dias.get(i));
       }
       listaTiempo.setItems(items);
       labelTitulo.setFont(Font.font(20));
       labelTitulo.setText(parse1.getNombreCiudad());

   }

    public void mostrarInfo(Event event) throws IOException, SAXException, ParserConfigurationException {

        for(int i = 0;i < items.size();i++){
            textInfo.setText(parse1.temperatura.get(i));
        }

    }

    public void salirAplicacion(ActionEvent actionEvent) {
        Platform.exit();
    }


}
