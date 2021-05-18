package com.szzrain.sfz;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class SummonMain extends Application {
    //the final digit
    public static String SFZSummon(String num){
        if (num.length()!=17){
            return "";
        }
        int add17 = 0;
        int T;
        char[] numC = num.toCharArray();
        int[] weight = {7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2};
        char[] ma ={'1','0','X','9','8','7','6','5','4','3','2'};
        for (int i = 0; i < numC.length; i++) {
            add17 += (((int)numC[i])-48)*weight[i];
        }
        T=add17%11;
        return num+ ma[T];
    }

    private static String currentRegion = "";

    public static String getCurrentRegion(){
        return currentRegion;
    }

    static Map<String, String> region = new HashMap<>();

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Main.fxml"));
        primaryStage.setTitle("SummonMain");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
    static {
        Init.init();
    }

    public static void setCurrentRegion(String s){
        if (region.get(s)!=null) {
            currentRegion =
                    region.get(s);
        }
    }
}
