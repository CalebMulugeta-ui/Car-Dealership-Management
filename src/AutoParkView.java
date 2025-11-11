import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AutoParkView extends Pane {
    AutoPark model;

    private ListView<Item> inventoryList;
    private ListView<String> cartList;
    private ListView<Item> popularItems;
    private ParkSummaryPane parkSummary;
    private Button addButton;
    private Button removeButton;
    private Button completeSale;
    private Button resetStock;
    private Label titleLabel2;

    public AutoParkView(AutoPark initModel){
        model = initModel;


        inventoryList = new ListView<Item>();
        inventoryList.relocate(10, 30);
        inventoryList.setPrefSize(250, 270);
        Label titleLabel = new Label();
        titleLabel.setText("Park Inventory:");
        titleLabel.relocate(10,10);
        getChildren().add(inventoryList);
        getChildren().add(titleLabel);


        addButton = new Button("Add to Cart");
        addButton.relocate(65, 310);
        addButton.setPrefSize(100, 20);
        getChildren().add(addButton);


        cartList = new ListView<String>();
        cartList.relocate(270, 30);
        cartList.setPrefSize(250, 270);
        titleLabel2 = new Label();
        titleLabel2.setText("Current Cart ($0.0):");
        titleLabel2.relocate(270,10);
        getChildren().add(cartList);
        getChildren().add(titleLabel2);

        removeButton = new Button("Remove Item");
        removeButton.relocate(290, 310);
        removeButton.setPrefSize(100, 20);
        getChildren().add(removeButton);

        completeSale = new Button("Complete Sale");
        completeSale.relocate(400, 310);
        completeSale.setPrefSize(100, 20);
        getChildren().add(completeSale);



        popularItems = new ListView<Item>();
        popularItems.relocate(550, 170);
        popularItems.setPrefSize(200, 130);
        Label titleLabel3 = new Label();
        titleLabel3.setText("Most Popular Items:");
        titleLabel3.relocate(550,150);
        getChildren().add(popularItems);
        getChildren().add(titleLabel3);

        resetStock = new Button("Reset Stock");
        resetStock.relocate(600, 310);
        resetStock.setPrefSize(100, 20);
        getChildren().add(resetStock);

        parkSummary = new ParkSummaryPane("Park Summary: ");
        parkSummary.relocate(540, 20);
        getChildren().add(parkSummary);

    }



    //Getters
    public Button getAddButton() {
        return addButton;
    }
    public ListView<String> getCartList() {
        return cartList;
    }
    public Button getCompleteSale() {
        return completeSale;
    }
    public ListView<Item> getInventoryList() {
        return inventoryList;
    }
    public ParkSummaryPane getParkSummary() {
        return parkSummary;
    }
    public ListView<Item> getPopularItems() {
        return popularItems;
    }
    public Button getRemoveButton() {
        return removeButton;
    }
    public Button getResetStock() {
        return resetStock;
    }
    public Label getTitleLabel2() {
        return titleLabel2;
    }
    public void setTitleLabel2(Label titleLabel2) {
        this.titleLabel2 = titleLabel2;
    }

    public void setParkSummary(ParkSummaryPane parkSummary) {
        this.parkSummary = parkSummary;
    }

    public void update(){
        inventoryList.setItems(FXCollections.observableArrayList(model.getItems()));
        cartList.setItems(FXCollections.observableArrayList(model.cartSummary().values()));
        popularItems.setItems(FXCollections.observableArrayList(model.getPopularItems()));

    }




}
