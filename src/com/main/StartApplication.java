package com.main;

import com.series.NumberSeries;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class StartApplication extends Application {
    private TextField txtPrime;
    private TextArea txtAreaPrimeOutput;
    private TextField txtFibonacci;
    private TextArea txtAreaFibonacciOutput;

    public static void main(String[] args) {
        launch(args); // Calling JavaFX here
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // TODO Auto-generated method stub

        // VBox Layout houses the layout for the Fibonacci and Prime number series layout.
        VBox vbMain = new VBox();

        // Hbox for holding the controls within a horizontal layout for nice styling
        HBox hbPanes = new HBox();
        hbPanes.getChildren().addAll(getFibonacciPane(), getPrimePane());
        hbPanes.setSpacing(10); // Sets the spacing between the layouts

        // Calls the API class for calculating the Fibonacci and Prime number series respectively.
        Button btnEvaluate = new Button("Evaluate");

        // Aligns the Evaluate button for proper user presentation in the middle.
        HBox hbControls = new HBox(btnEvaluate);
        hbControls.setAlignment(Pos.CENTER);

        // Sets the padding for the TOP, LEFT, RIGHT AND BOTTOM for the main Vertical box layout
        // for more proper presentation.
        vbMain.setPadding(new Insets(5));
        vbMain.getChildren().addAll(hbPanes, hbControls);
        vbMain.setSpacing(5);

        // Container for the main layout.
        Scene scene = new Scene(vbMain,600,280 );

        // The main stage for all of the controls
        primaryStage.setScene(scene);
        primaryStage.setTitle("Number Series application.");
        primaryStage.show();

        // On click action handler for call the API when the button is clicked.
        btnEvaluate.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                NumberSeries numberSeries = new NumberSeries();

                // Initializes the prime and fibonacci series amount.
                int fibonacciNumberLimit = 0;
                int primeNumberLimit = 0;

                /*Attempting to cast the fibonacci text from the input control to a number
                Throws an exception if there is an issue and displays a messages and prevents
                further continuation.*/
                try {
                    fibonacciNumberLimit = Integer.valueOf(txtFibonacci.getText().trim());
                }
                catch (NumberFormatException numex){
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Something else was entered instead of a number, try again!", ButtonType.OK);
                    alert.showAndWait();

                    return;
                }

                /*Attempting to cast the prime text from the input control to a number
                Throws an exception if there is an issue and displays a messages and prevents
                further continuation.*/
                try {
                    primeNumberLimit = Integer.valueOf(txtPrime.getText().trim());
                }
                catch (NumberFormatException numex){
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Something else was entered instead of a number, try again!", ButtonType.OK);
                    alert.showAndWait();

                    return;
                }

                // Calling the fibonacci and prime number API respectively and saving the results within lists.
                ArrayList fibonacciSeries = NumberSeries.getFibonacci(fibonacciNumberLimit);
                ArrayList primeSeries = NumberSeries.getPrimeList(primeNumberLimit);

                // Beautifying the output removing the [ and ] from the arraylist result.
                txtAreaFibonacciOutput.setText(String.valueOf(fibonacciSeries)
                                                     .replace("[","")
                                                     .replace("]",""));

                // Beautifying the output removing the [ and ] from the arraylist result.
                txtAreaPrimeOutput.setText(String.valueOf(primeSeries)
                                                 .replace("[","")
                                                 .replace("]",""));
            }
        });
    }

    public VBox getPrimePane(){
        // VBox Prime Layout
        VBox vbPrime = new VBox();
        Label labelPrimePrompt = new Label("Please number of terms for your Prime series");
        txtPrime = new TextField("10");
        txtAreaPrimeOutput = new TextArea("Your Prime series output will pop-up here once you click Evaluate!");

        // Setting the size of the prime text input.
        txtPrime.setMaxWidth(75);
        txtPrime.setPrefWidth(75);

        // Setting the size of the prime text area.
        txtAreaPrimeOutput.setMaxWidth(300);
        txtAreaPrimeOutput.setPrefWidth(300);

        // Preventing the text area from being edited and also wrapping the text so that
        // the h-scroll bar is not needed.
        txtAreaPrimeOutput.setEditable(false);
        txtAreaPrimeOutput.setWrapText(true);

        // This listener basically listens for any input on the number series control
        // it allows only numbers.
        txtPrime.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    txtPrime.setText(newValue.replaceAll("[^\\d]", ""));

                    Alert alert = new Alert(Alert.AlertType.WARNING, "You can only enter numbers (No letters/Symbols).", ButtonType.OK);
                    alert.showAndWait();
                }
            }
        });

        vbPrime.getChildren().addAll(labelPrimePrompt, txtPrime, txtAreaPrimeOutput);
        vbPrime.setSpacing(5);

        // Creates a border around layout.
        vbPrime.setPadding(new Insets(5));
        vbPrime.setStyle("-fx-border-color: grey ;\n" +
                         "-fx-border-width: 1 ; \n" +
                         "-fx-border-style: solid;");

        return vbPrime;
    }

    public VBox getFibonacciPane(){
        // VBox Fibonacci Layout
        VBox vbFibonacci = new VBox();
        Label labelFibonacciPrompt = new Label("Please number of terms for your Fibonacci series");
        txtFibonacci = new TextField("10");
        txtAreaFibonacciOutput = new TextArea("Your Fibonacci series output will pop-up here once you click Evaluate!");

        txtFibonacci.setMaxWidth(75);
        txtFibonacci.setPrefWidth(75);
        txtAreaFibonacciOutput.setMaxWidth(300);
        txtAreaFibonacciOutput.setPrefWidth(300);

        // Preventing the text area from being edited and also wrapping the text so that
        // the h-scroll bar is not needed.
        txtAreaFibonacciOutput.setEditable(false);
        txtAreaFibonacciOutput.setWrapText(true);

        txtFibonacci.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    txtFibonacci.setText(newValue.replaceAll("[^\\d]", ""));

                    Alert alert = new Alert(Alert.AlertType.WARNING, "You can only enter numbers (No letters/Symbols).", ButtonType.OK);
                    alert.showAndWait();
                }
            }
        });

        // This listener basically listens for any input on the number series control
        // it allows only numbers.
        vbFibonacci.getChildren().addAll(labelFibonacciPrompt, txtFibonacci, txtAreaFibonacciOutput);
        vbFibonacci.setSpacing(5);

        // Creates a border around layout.
        vbFibonacci.setPadding(new Insets(5));
        vbFibonacci.setStyle("-fx-border-color: grey ;\n" +
                "-fx-border-width: 1 ; \n" +
                "-fx-border-style: solid;");

        return vbFibonacci;
    }
}
