package eltiempo;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.DecimalFormat;

public class EstadisticasController {

    @FXML
    Label ciudad, estadisticas;
    public Parser parse1 = new Parser();
    DecimalFormat decimal = new DecimalFormat("#.#");
    double temperaturaMedia;


    //Initialize
    public void initialize() throws IOException, SAXException, ParserConfigurationException {

        //Llama a funcion que rellena los arrays de informacion
        parse1.anadirInfoArrays();

        for (int i = 0; i < parse1.temperatura.size(); i++) {
            temperaturaMedia= temperaturaMedia + Double.parseDouble(parse1.temperatura.get(i));
        }

        temperaturaMedia = temperaturaMedia / parse1.temperatura.size();

        ciudad.setText(parse1.getNombreCiudad());
        ciudad.setFont(Font.font(20));
        estadisticas.setText(String.valueOf("La temperatura media para de los próximos "
                + parse1.temperatura.size() + " días es de " + decimal.format( +temperaturaMedia) +" Cº"));
        estadisticas.setFont(Font.font(12));
        estadisticas.setFont(Font.font("Roboto"));
    }



}
