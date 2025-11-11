import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;

public class ParkSummaryPane extends Pane {
    private Label salesLabel1;
    private Label revenueLabel;
    private Label salesLabel2;

    private TextField salesField1;
    private TextField revenueField;
    private TextField salesField2;

    public TextField getRevenueField() {
        return revenueField;
    }
    public TextField getSalesField1() {
        return salesField1;
    }
    public TextField getSalesField2() {
        return salesField2;
    }



    public ParkSummaryPane(String title){
        //This pane will have sales/revenue/sales
        Pane innerPane = new Pane();

        //Create labels
        //#Sales
        salesLabel1  = new Label("#Sales:");
        salesLabel1.relocate(10,20);
        salesLabel1.setPrefSize(80,30);
        //Revenue
        revenueLabel  = new Label("Revenue:");
        revenueLabel.relocate(10,55);
        revenueLabel.setPrefSize(80,30);
        //$Sales
        salesLabel2  = new Label("$/Sales:");
        salesLabel2.relocate(10,90);
        salesLabel2.setPrefSize(80,30);

        //Create TextFields
        //#Sales
        salesField1 = new TextField();
        salesField1.setText("0");
        salesField1.relocate(100, 20);
        salesField1.setPrefSize(100, 30);
        //Revnue
        revenueField = new TextField();
        revenueField.setText("0.0");
        revenueField.relocate(100, 55);
        revenueField.setPrefSize(100, 30);
        //$Sales
        salesField2 = new TextField();
        salesField2.setText("N/A");
        salesField2.relocate(100, 90);
        salesField2.setPrefSize(100, 30);




        innerPane.getChildren().addAll(salesLabel1,salesLabel2,revenueLabel,salesField1,salesField2,revenueField);

        Label titleLabel = new Label();
        titleLabel.setText(title);
        titleLabel.relocate(10,-10);
        this.getChildren().addAll(innerPane, titleLabel);

    }
}
