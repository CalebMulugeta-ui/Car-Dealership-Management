import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.ListView;

import java.util.ArrayList;
import java.util.HashMap;


public class AutoParkApp extends Application {

    AutoParkView parkView;
    AutoPark parkModel;

    public void start(Stage primaryStage) {
        parkModel = AutoPark.createPark();
        parkView = new AutoParkView(parkModel);

        //initially disable buttons
        parkView.getAddButton().setDisable(true);
        parkView.getRemoveButton().setDisable(true);
        parkView.getCompleteSale().setDisable(true);


        primaryStage.setScene(new Scene(parkView, 800, 350));
        primaryStage.setTitle(parkModel.getName());
        primaryStage.show();


        ObservableList<Item> itemList = FXCollections.observableArrayList(parkModel.getItems());
        parkView.getInventoryList().setItems(itemList);


        if (parkModel.getRevenue() == 0) {
            ObservableList<Item> popularItem = FXCollections.observableArrayList(parkModel.firstThreeItems());
            parkView.getPopularItems().setItems(popularItem);
        }



        parkView.getInventoryList().setOnMouseReleased(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                parkView.getAddButton().setDisable(false);
            }
        });

        parkView.getCartList().setOnMouseReleased(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                parkView.getRemoveButton().setDisable(false);
            }
        });



        parkView.getAddButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                Item currentItem = parkView.getInventoryList().getSelectionModel().getSelectedItem();
                parkModel.soldOut(currentItem);
                parkModel.handleAdd(currentItem);

                ObservableList<String> cartList = FXCollections.observableArrayList(parkModel.cartSummary().values());
                parkView.getCartList().setItems(cartList);

                if(parkModel.checkCart()){
                    parkView.getCompleteSale().setDisable(true);
                }else {
                    parkView.getCompleteSale().setDisable(false);
                }

                parkView.getTitleLabel2().setText("Current Cart ($"+parkModel.cartTotal()+"):");
                parkView.update();
            }
        });

        parkView.getRemoveButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                String currentItem = parkView.getCartList().getSelectionModel().getSelectedItem();
                parkModel.handleRemove(currentItem);
                parkView.getTitleLabel2().setText("Current Cart ($"+parkModel.removedTotal(currentItem)+"):");
                if(parkModel.checkCart()){
                    parkView.getCompleteSale().setDisable(true);
                    parkView.getRemoveButton().setDisable(true);
                }else {
                    parkView.getCompleteSale().setDisable(false);
                    parkView.getRemoveButton().setDisable(false);
                }
                parkView.update();
            }
        });

        parkView.getCompleteSale().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {

                parkView.getTitleLabel2().setText("Current Cart ($0.0):");


                int current = Integer.parseInt(parkView.getParkSummary().getSalesField1().getText());
                current+=1;
                parkView.getParkSummary().getSalesField1().setText(String.valueOf(current));


                double revenueTotal = Double.parseDouble(parkView.getParkSummary().getRevenueField().getText());
                revenueTotal += parkModel.revenueTotal();
                parkView.getParkSummary().getRevenueField().setText(String.valueOf(revenueTotal));


                double average = revenueTotal/current;
                parkView.getParkSummary().getSalesField2().setText(String.format("%.2f",average));


                if(parkModel.checkCart()) {
                    parkView.getCompleteSale().setDisable(true);
                }else{
                    parkView.getCompleteSale().setDisable(false);
                }
                parkModel.removeAllItems();
                parkModel.resetCartQuan();

                ObservableList<Item> popularItem = FXCollections.observableArrayList(parkModel.getPopularItems());
                parkView.getPopularItems().setItems(popularItem);





                parkView.update();

            }
        });

        parkView.getResetStock().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                parkView.getTitleLabel2().setText("Current Cart ($0.0):");
                parkView.getParkSummary().getSalesField1().setText("0");
                parkView.getParkSummary().getRevenueField().setText("0.0");
                parkView.getParkSummary().getSalesField2().setText("N/A");

                parkView.getAddButton().setDisable(true);
                parkView.getRemoveButton().setDisable(true);
                parkView.getCompleteSale().setDisable(true);
                parkModel.reset();

                parkView.update();
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }


}