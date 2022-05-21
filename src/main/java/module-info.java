module pl.lublin.wsei.java.cwiczenia.lab4 {
    requires javafx.controls;
    requires javafx.fxml;


    opens pl.lublin.wsei.java.cwiczenia.lab4 to javafx.fxml;
    exports pl.lublin.wsei.java.cwiczenia.lab4;
}