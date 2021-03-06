package eltiempo;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
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
    Button btnRefrescar, atras, estadisticas;
    @FXML
    TextArea textInfo;
    @FXML
    ImageView iconoImagen;

    public int itemSeleccionado;
    public Parser parse1 = new Parser();

    //Initialize
    public void initialize() throws IOException, SAXException, ParserConfigurationException {
        //Mostrar iconos en los botones
        btnRefrescar.setGraphic(new ImageView("icons/refresh.png"));
        atras.setGraphic(new ImageView("icons/arrow.png"));
        estadisticas.setGraphic(new ImageView("icons/estadisticas.png"));

        //Llama a funcion que rellena los arrays de informacion
        parse1.anadirInfoArrays();

        listaTiempo.setVisible(true);
        textInfo.setVisible(false);

        //Desactiva el textArea
        //textInfo.setDisable(true);
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

            //Muestra el icono de ese dia
            Image icono = new Image("/icons/" + parse1.nubes.get(itemSeleccionado)+ ".png"); // ruta de la imagen de la previsi�
            iconoImagen.setImage(icono); // la asigna al ImageView.

            listaTiempo.setVisible(false);
            textInfo.setVisible(true);


        });

    }

    public void salirAplicacion() {
        Platform.exit();
    }

    public void about() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Aplicacion del tiempo");
        alert.setHeaderText("Aplicacion del tiempo");
        alert.setContentText("Descubre el pronostico del tiempo");
        alert.showAndWait();
    }

    public void retroceder() {
        listaTiempo.setVisible(true);
        textInfo.setVisible(false);
    }

    public void muestraEstadisticas() throws IOException {
        Stage stage = new Stage();
        Parent parent = FXMLLoader.load(
                EstadisticasController.class.getResource("estadisticas.fxml"));
        stage.setScene(new Scene(parent));
        stage.setTitle("Estadisticas");
        stage.initModality(Modality.WINDOW_MODAL);

        stage.show();
    }
}
