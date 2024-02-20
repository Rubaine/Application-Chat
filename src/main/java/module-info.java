module org.rubaine.applichat {
    requires javafx.controls;
    requires javafx.fxml;


    exports org.rubaine.applichat.main;
    opens org.rubaine.applichat.main to javafx.fxml;
    exports org.rubaine.applichat.controller;
    opens org.rubaine.applichat.controller to javafx.fxml;
    exports org.rubaine.applichat.network;
    opens org.rubaine.applichat.network to javafx.fxml;
}