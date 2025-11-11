import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.*;
import java.util.*;

public class AutoPark {

    private String name;
    private double revenue;
    private ArrayList<Item> items;
    private ArrayList<Item> cartList;


    public AutoPark(String name) {
        this.name = name;
        revenue = 0;
        items = new ArrayList<>();
        cartList = new ArrayList<>();
    }




    public boolean checkCart() {
        return cartSummary().isEmpty();
    }


    public double cartTotal(){
        double total = 0.0;
        for(Item a: cartList) {
            total += a.getPrice();
        }
        return total;
    }


    public double getRevenue() {
        return revenue;
    }

    public boolean addAnItem(Item item) {
        if(items.size() < 10){
            items.add(item);
            return true;
        }
        return false;
    }

    public ArrayList<Item> firstThreeItems(){
        ArrayList<Item> first3 = new ArrayList<>();
        for(int i=0; i<3; i++){
            first3.add(items.get(i));
        }
        return first3;
    }

    public HashMap<Item, String> cartSummary(){
        HashMap<Item, String> summaryMap = new HashMap<>();

        for(Item a: cartList){
            if(!summaryMap.containsKey(a)){
                String output = a.getCurrentCartQuan()+"x " + a.toString();
                summaryMap.put(a,output);
            }
        }
        return summaryMap;
    }





    public void handleAdd(Item anItem) {
        cartList.add(anItem);
        if(anItem.getInvQuantity()>0 && !cartList.contains(anItem)){
            anItem.setInvQuantity(anItem.getInvQuantity()-1);
            anItem.setCurrentCartQuan(anItem.getCurrentCartQuan()+1);
            cartList.add(anItem);
        }else{
            anItem.setInvQuantity(anItem.getInvQuantity()-1);
            anItem.setCurrentCartQuan(anItem.getCurrentCartQuan()+1);
        }
    }

    public void handleRemove(String anItem){
        Item removing = null;
        for(Map.Entry<Item,String> pairs: cartSummary().entrySet()){
            if(pairs.getValue().equals(anItem)){
                removing = pairs.getKey();
            }
        }

        if(removing != null){
            cartList.remove(removing);
            removing.setInvQuantity(removing.getInvQuantity()+1);
            removing.setCurrentCartQuan(removing.getCurrentCartQuan()-1);
        }
    }



    public void removeAllItems(){
        cartList.clear();
        cartSummary().clear();
    }

    public double revenueTotal(){
        double total = 0;
        for(Item i:cartList){
            total += i.getPrice();
        }
        return total;
    }

    public void resetCartQuan(){
        for(Item i: cartList){
            int quantity = i.getSoldQuantity();
            if(quantity>0){
                i.setSoldQuantity(quantity + i.getCurrentCartQuan());
                revenue += i.getPrice() * quantity;
                i.setCurrentCartQuan(0);
            }
        }
    }

    public TreeSet<Item> getPopularItems() {
        int x = 3;
        TreeSet<Item> sortedPopularList = new TreeSet<>();
        TreeSet<Item>popular = new TreeSet<>();

        sortedPopularList.addAll(cartList);


        for(int i=0; i<x; i++){
            popular.add(sortedPopularList.first());
            sortedPopularList.pollFirst();
        }

        return popular;
    }





    public double removedTotal(String anItem){
        double total = cartTotal();
        Item key = null;
        for (Map.Entry<Item, String> pairs: cartSummary().entrySet()){
            if(pairs.getValue().equals(anItem)){
                key = pairs.getKey();
            }
        }
        if(key != null){
            total = total - key.getPrice();
        }
        return total;
    }



    public void soldOut(Item anItem){
        if(anItem.getInvQuantity()==1){
            items.remove(anItem);
        }
    }

    public void reset(){
        cartList.clear();
        revenue = 0;

        items.clear();
        items.addAll(createPark().items);

    }



    public ArrayList<Item> getCartList() {
        return cartList;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    




    public static AutoPark createPark() {
        AutoPark park = new AutoPark("VroomVille Vehicle Haven");
        Sedan s1 = new Sedan("Hyundai", "Sonata", "Black", 2020, 35000, 10);
        Sedan s2 = new Sedan("BMW", "3 Series", "White", 2022, 42000, 10);
        park.addAnItem(s1);
        park.addAnItem(s2);
        SUV suv1 = new SUV("Chevy", "Trailblazer", "Red", 2021, true, 32000, 10);
        SUV suv2 = new SUV("Jeep", "Grand Cherokee", "Green", 2018, false, 21000, 10);
        park.addAnItem(suv1);
        park.addAnItem(suv2);
        Truck t1 = new Truck("Toyota", "Tacoma", 2019, "goods", true, 28000, 10);
        Truck t2 = new Truck("Ford", "Ranger", 2022, "equipment", false, 30000, 10);
        park.addAnItem(t1);
        park.addAnItem(t2);
        MiniVan v1 = new MiniVan("Ford", "Transit", 2020, "goods", true, 22000, 10);
        MiniVan v2 = new MiniVan("Ram", "ProMaster", 2019, "equipment", false, 19000, 10);
        park.addAnItem(v1);
        park.addAnItem(v2);
        Tire tire1 = new Tire(10, 30, true, 390, 20);
        Tire tire2 = new Tire(12, 35, false, 320, 20);
        park.addAnItem(tire1);
        park.addAnItem(tire2);

        return park;
    }


}



