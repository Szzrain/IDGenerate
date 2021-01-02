package com.szzrain.sfz;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    public MenuItem beijing;
    public MenuItem tianjin;
    public MenuItem hebei;
    public MenuButton region;
    public Text summon;
    public Button summonbutton;
    public TextField year;
    public TextField month;
    public TextField day;
    public TextField city;

    public ObservableList<MenuItem> items;

    //all buttons will invoke this method
    public void bc(ActionEvent actionEvent){
        for (MenuItem m:
             items) {
            if (actionEvent.getSource()==m){
                region.setText(m.getText());
                SummonMain.setCurrentRegion(m.getText());
                break;
            }
        }
    }

    int[] days = {31,28,31,30,31,30,31,31,30,31,30,31};

    public void summonID(ActionEvent actionEvent){
        String t = SummonMain.getCurrentRegion()+"100"+getYear()+getMonth()+getDate()+"001";
        System.out.println(t);
        setResult(SummonMain.SFZSummon(t));
    }

    public void setResult(String s){
        summon.setText(s);
    }

    int y=2000;
    int m=1;
    public String getYear(){
        try {
            y = Integer.parseInt(year.getCharacters().toString());
            return String.valueOf(y);
        } catch (NumberFormatException ignored){
            return String.valueOf( new Random().nextInt(21)+1980);
        }
    }

    public String getMonth(){
        try {
            m=Integer.parseInt(month.getCharacters().toString());
            return numCheck(m);
        }catch (NumberFormatException ignored){
            m=new Random().nextInt(12)+1;
            return numCheck(m);
        }
    }

    public String getDate(){
        int d;
        try {
            d=Integer.parseInt(day.getCharacters().toString());
            if (!(d>0&&d<=days[m-1])){
                return randomDate();
            }
            return String.valueOf(d);
        }catch (NumberFormatException ignored){
            return randomDate();
        }
    }

    public String randomDate(){
        int d;
        if ((y%100==0&&y%400==0)||(y%100!=0&&y%4==0)){
            days[1]=29;
        }
        d=new Random().nextInt(days[m-1])+1;
        return numCheck(d);
    }

    public String numCheck(int i){
        if (i<10){
            return "0"+ i;
        }
        return String.valueOf(i);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        items=region.getItems();
    }
}
