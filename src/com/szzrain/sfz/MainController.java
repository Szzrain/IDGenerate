package com.szzrain.sfz;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    public MenuButton region;
    public Button summonbutton;
    public TextField year;
    public TextField month;
    public TextField day;
    //TODO: make use of this textfield
    public TextField city;
    public TextField textResult;



    //all buttons will invoke this method
    public void bc(ActionEvent actionEvent){
        region.setText(((MenuItem)(actionEvent.getSource())).getText());
        SummonMain.setCurrentRegion(((MenuItem)(actionEvent.getSource())).getText());
    }

    int[] days = {31,28,31,30,31,30,31,31,30,31,30,31};

    public void summonID(ActionEvent actionEvent){
        //generate first 17 digit of ID
        String t = SummonMain.getCurrentRegion()+getCity()+getYear()+getMonth()+getDate()+"001";//not finished yet. String should be replaced by method
        //debug option, this line can be delete
        System.out.println(t);
        //
        setResult(SummonMain.SFZSummon(t));
    }


    public String getCity(){
        try {
            Integer.parseInt(city.getText());
            return city.getText();
        }catch (NumberFormatException ignored){
            return "000";
        }
    }
    //result show on GUI this method will be invoke by main class
    public void setResult(String s){
        if("".equals(s)){
            textResult.setText("错误！");
            return;
        }
        textResult.setText(s);
    }

    //default data
    int y=2000;
    int m=1;
    public String getYear(){
        try {
            y = Integer.parseInt(year.getCharacters().toString());
            return String.valueOf(y);
        } catch (NumberFormatException ignored){
            //if input is illegal
            return String.valueOf( new Random().nextInt(21)+1980);
        }
    }

    public String getMonth(){
        try {
            m=Integer.parseInt(month.getCharacters().toString());
            if(m>0&&m<=12){
            return numCheck(m);}
        }catch (NumberFormatException ignored){
        }
        //if input is illegal
        m=new Random().nextInt(12)+1;
        return numCheck(m);
    }

    public String getDate(){
        int d;
        try {
            d=Integer.parseInt(day.getCharacters().toString());
            if (!(d>0&&d<=days[m-1])){
                //if user input is illegal
                return randomDate();
            }
            return String.valueOf(d);
        }catch (NumberFormatException ignored){
            //if user didn't input or type some strange thing
            return randomDate();
        }
    }

    //generate a random date
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
        for (String s : SummonMain.region.keySet()) {
            MenuItem t = new MenuItem(s);
            t.setOnAction(this::bc);
            region.getItems().add(t);
        }
    }
}
