package eltiempo;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

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
    @FXML
    ImageView iconoImagen;


    public int itemSeleccionado;

    public Parser parse1 = new Parser();

    //Initialize
    public void initialize() throws IOException, SAXException, ParserConfigurationException {
        //Muestra icono boton
        btnRefrescar.setGraphic(new ImageView("icons/refresh1.png"));

        //Desactiva el textArea
        //textInfo.setDisable(true);

        //Llama a funcion que rellena los arrays de informacion
        parse1.anadirInfoArrays();


    }

   public void refrescar()  throws IOException, SAXException, ParserConfigurationException {

       //Limpiar el ObservableList
       items.clear();

       // Ponemos el nombre de la ciudad en el label, como titulo
       labelTitulo.setText(parse1.getNombreCiudad());

       //Para cada item llama a la funcion toString para dar la informacion y la anadimos al ObservableList
       for(int i = 0;i < parse1.dias.size();i++){
           items.add(parse1.dias.get(i));
           //items.add(parse1.toString(i));
       }

       //Seteamos el ListView con los Items del ObservableList
       listaTiempo.setItems(items);

       //Cambiamos la fuente de tama�o
       labelTitulo.setFont(Font.font(20));

   }

    public void mostrarInfo()throws IOException, SAXException, ParserConfigurationException{

        //Listener para que en funcion de la fila seleccionada del ListView haga una cosa u otra.
        listaTiempo.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            //Sacamos el numero del �ndice que tenemos seleccionado
            itemSeleccionado = listaTiempo.getSelectionModel().getSelectedIndex();

            //Muestra la infomacion de la fila seleccionada
            textInfo.setText(parse1.toString(itemSeleccionado));
            Image icono = new Image("/icons/" + parse1.nubes.get(itemSeleccionado).toString() + ".png"); // ruta de la imagen de la previsi�
            iconoImagen.setImage(icono); // la asigna al ImageView.

        });

    }

    public void salirAplicacion(ActionEvent actionEvent) {
        Platform.exit();
    }


}
