module com.quizappdev.quizapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires mysql.connector.java;
    requires java.sql;
    requires org.mongodb.driver.core;
    requires org.mongodb.driver.sync.client;
    requires org.mongodb.bson;

    opens com.quizappdev.quizapp to javafx.fxml;
    exports com.quizappdev.quizapp;
}