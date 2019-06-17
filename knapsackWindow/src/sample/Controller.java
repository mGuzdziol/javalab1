package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import knapsackProblem.*;

import java.lang.reflect.Method;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

public class Controller implements Initializable {

    String st[] = { "Brute Force", "Random Search", "Greedy"};
    ObservableList list=FXCollections.observableArrayList();
    public Button addItemButton,exitButton,solveProblemButton,removeItemButton;
    public TextField weightSolText,valueSolText,capacityText,typeWeightText, typeValueText,itemAmountText;
    public TableView<ItemsTable> itemsTable;
    public TableColumn<ItemsTable,Integer> idItemCol,weightItemCol;
    public TableColumn<ItemsTable,Double> valueItemCol;
    public TableView<ItemsTable> knapsackTable;
    public TableColumn<ItemsTable,Integer> idKnapsackCol,weightKnapsackCol;
    public TableColumn<ItemsTable,Double> valueKnapsackCol;
    public ChoiceBox algChoice;
    public MenuItem plMenu,usMenu,gbMenu,infProgMenu;
    public Menu optionsMenu, helpMenu,langMenu;
    public Label timeLabel, capacityLabel, choseAlgLabel, typeWeightLabel, typeValueLabel,itemsLabel, knapWeightLabel, knapValueLabel, itemsAmountLabel,itemsInLabel, solutionLabel;
    public VBox rootBox;
    public ImageView knapImage;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    LocalDate localDate = LocalDate.now();
    Instance instance=new Instance();
    Solution solution = new Solution();
    Algorithms alg;
    ResourceBundle bundle = ResourceBundle.getBundle("bundles.messages");
    Locale locale;

    public void handleAddItemClick()
    {
        try {
            int w=Integer.parseInt(typeWeightText.getText());
            double v=Double.parseDouble(typeValueText.getText());
            String vS= typeValueText.getText();

            instance.addItem(new Item(v,w));

            if(locale.toString().equals("pl"))
            {
                itemsTable.getItems().add(new ItemsTable(Item.getCounter()-1,w,typeValueText.getText().replace(".",",")));
            }
            else
                itemsTable.getItems().add(new ItemsTable(Item.getCounter()-1,w,vS));

        }catch (Exception e)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(bundle.getString("error"));
            alert.setHeaderText(null);
            alert.setContentText(bundle.getString("wrongValues"));
            alert.showAndWait();
        }finally {
            typeWeightText.clear();
            typeValueText.clear();
        }
    }

    public void handleRemoveItemClick()
    {
        if(instance.getItemsArray().size()>0 && itemsTable.getSelectionModel().getSelectedItem()!=null) {
            ItemsTable it = itemsTable.getSelectionModel().getSelectedItem();
            itemsTable.getItems().remove(it);

            instance.removeItem(it.getId());

            for (int i = 1; i <= itemsTable.getItems().size(); i++)
                itemsTable.getItems().get(i - 1).setId(i);
        }
    }

    public void handleSolveProblemClick()
    {
        knapsackTable.getItems().clear();

        try {
            instance.setKnapsackCapacity(Integer.parseInt(capacityText.getText()));
        }
        catch (NumberFormatException e)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(bundle.getString("error"));
            alert.setHeaderText(null);
            alert.setContentText(bundle.getString("wrongCapacity"));
            alert.showAndWait();
        }

        String str = (String)algChoice.getValue();
        if(str==null)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(bundle.getString("error"));
            alert.setHeaderText(null);
            alert.setContentText(bundle.getString("algChoose"));
            alert.showAndWait();
        }
        else if(instance.getItemsArray().size()==0)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(bundle.getString("error"));
            alert.setHeaderText(null);
            alert.setContentText(bundle.getString("emptyArr"));
            alert.showAndWait();
        }
        else if(str.equals("Brute Force"))
        {
            alg=new BruteForce();
            solution=alg.solve(instance,0,0,new ArrayList<Integer>());
        }
        else if(str.equals("Random Search"))
        {
            alg=new RandomSearch();
            solution=alg.solve(instance,0,0,new ArrayList<Integer>());
        }
        else if(str.equals("Greedy"))
        {
            alg=new Greedy();
            solution=alg.solve(instance,0,0,new ArrayList<Integer>());
        }

        weightSolText.setText(String.valueOf(solution.getBestWeight()));
        valueSolText.setText(String.valueOf(solution.getBestValue()));

        if(locale.toString().equals("pl"))
        {
            valueSolText.setText(valueSolText.getText().replace(".",","));
        }

        if(solution.getBestItemID().size()==1)
        {
            itemAmountText.setText((solution.getBestItemID().size()) + " " + bundle.getString("oneItem"));
        }
        if(solution.getBestItemID().size()>1 && solution.getBestItemID().size()<5)
        {
            itemAmountText.setText((solution.getBestItemID().size()) + " " + bundle.getString("twoItems"));
        }
        if(solution.getBestItemID().size()>=5 || solution.getBestItemID().size()==0)
        {
            itemAmountText.setText((solution.getBestItemID().size()) + " " + bundle.getString("moreItems"));
        }

        for(int i=0; i<solution.getBestItemID().size();i++)
        {
            knapsackTable.getItems().add(new ItemsTable(solution.getBestItemID().get(i),instance.getItemsArray().get(solution.getBestItemID().get(i)-1).getItemWeight(),String.valueOf(instance.getItemsArray().get(solution.getBestItemID().get(i)-1).getItemValue())));
        }
    }

    public void handleExitButton(){
        System.exit(0);
    }

    public void handleInfProgMenu()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(bundle.getString("aboutPro"));
        alert.setHeaderText(null);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.setContentText(bundle.getString("aboutProText"));
        alert.showAndWait();
    }

    public void handlePlMenuClick()
    {
        loadLang("pl");
        dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        changText();
        valueSolText.setText(valueSolText.getText().replace(".",","));

        for(int i=0;i<itemsTable.getItems().size();i++) {
            itemsTable.getItems().get(i).setValue(itemsTable.getItems().get(i).getValue().replace(".",","));
            itemsTable.getColumns().get(2).setVisible(false);
            itemsTable.getColumns().get(2).setVisible(true);
        }

        for(int i=0;i<knapsackTable.getItems().size();i++) {
            knapsackTable.getItems().get(i).setValue(knapsackTable.getItems().get(i).getValue().replace(".",","));
            knapsackTable.getColumns().get(2).setVisible(false);
            knapsackTable.getColumns().get(2).setVisible(true);
        }
    }

    public void handleGbMenuClick()
    {
        loadLang("gb");
        dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        changText();
        valueSolText.setText(valueSolText.getText().replace(",","."));

        for(int i=0;i<itemsTable.getItems().size();i++) {
            itemsTable.getItems().get(i).setValue(itemsTable.getItems().get(i).getValue().replace(",","."));
            itemsTable.getColumns().get(2).setVisible(false);
            itemsTable.getColumns().get(2).setVisible(true);
        }

        for(int i=0;i<knapsackTable.getItems().size();i++) {
            knapsackTable.getItems().get(i).setValue(knapsackTable.getItems().get(i).getValue().replace(",","."));
            knapsackTable.getColumns().get(2).setVisible(false);
            knapsackTable.getColumns().get(2).setVisible(true);
        }
    }

    public void handleUsMenuClick()
    {
        loadLang("us");
        dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        changText();
        valueSolText.setText(valueSolText.getText().replace(",","."));

        for(int i=0;i<itemsTable.getItems().size();i++) {
            itemsTable.getItems().get(i).setValue(itemsTable.getItems().get(i).getValue().replace(",","."));
            itemsTable.getColumns().get(2).setVisible(false);
            itemsTable.getColumns().get(2).setVisible(true);
        }

        for(int i=0;i<knapsackTable.getItems().size();i++) {
            knapsackTable.getItems().get(i).setValue(knapsackTable.getItems().get(i).getValue().replace(",","."));
            knapsackTable.getColumns().get(2).setVisible(false);
            knapsackTable.getColumns().get(2).setVisible(true);
        }
    }

    public void loadLang(String lang)
    {
        locale = new Locale(lang);
        bundle = ResourceBundle.getBundle("bundles.messages",locale);
    }

    public void loadData()
    {
        list.removeAll(list);
        list.addAll(st);
        algChoice.getItems().addAll(list);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadLang("gb");
        dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        changText();

        loadData();
        timeLabel.setText(dtf.format(localDate));

        idItemCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        weightItemCol.setCellValueFactory(new PropertyValueFactory<>("weight"));
        valueItemCol.setCellValueFactory(new PropertyValueFactory<>("value"));

        idKnapsackCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        weightKnapsackCol.setCellValueFactory(new PropertyValueFactory<>("weight"));
        valueKnapsackCol.setCellValueFactory(new PropertyValueFactory<>("value"));
    }

    public void changText()
    {
        addItemButton.setText(bundle.getString("addItem"));
        removeItemButton.setText(bundle.getString("removeItem"));
        solveProblemButton.setText(bundle.getString("solveProblem"));
        exitButton.setText(bundle.getString("exit"));
        capacityLabel.setText(bundle.getString("typeCapacity"));
        choseAlgLabel.setText(bundle.getString("chooseAlg"));
        typeWeightLabel.setText(bundle.getString("typeWeight"));
        typeValueLabel.setText(bundle.getString("typeValue"));
        itemsLabel.setText(bundle.getString("items"));
        solutionLabel.setText(bundle.getString("solution"));
        knapWeightLabel.setText(bundle.getString("knapWeight"));
        knapValueLabel.setText(bundle.getString("knapValue"));
        itemsAmountLabel.setText(bundle.getString("itemAm"));
        itemsInLabel.setText(bundle.getString("inItems"));
        optionsMenu.setText(bundle.getString("options"));
        langMenu.setText(bundle.getString("language"));
        helpMenu.setText(bundle.getString("help"));
        infProgMenu.setText(bundle.getString("aboutPro"));
        Main.getInstance().updateTitle(bundle.getString("titleApp"));
        timeLabel.setText(dtf.format(localDate));
        if(solution.getBestWeight()!=0) {
            if (solution.getBestItemID().size() == 1) {
                itemAmountText.setText((solution.getBestItemID().size()) + " " + bundle.getString("oneItem"));
            }
            if (solution.getBestItemID().size() > 1 && solution.getBestItemID().size() < 5) {
                itemAmountText.setText((solution.getBestItemID().size()) + " " + bundle.getString("twoItems"));
            }
            if (solution.getBestItemID().size() >= 5 || solution.getBestItemID().size() == 0) {
                itemAmountText.setText((solution.getBestItemID().size()) + " " + bundle.getString("moreItems"));
            }
        }

    }
}
