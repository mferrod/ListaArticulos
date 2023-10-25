module com.politecnicomalaga.listaarticulos {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;
    exports model;
    opens com.politecnicomalaga.listaarticulos to javafx.fxml;
    exports com.politecnicomalaga.listaarticulos;
    opens listaclientes to javafx.fxml;
    exports listaclientes;
}
