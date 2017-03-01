package main;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.*;
import Controller.*;
import Repository.*;
import utils.*;
import view.*;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public static List<Statement> getStatements(){
        ArrayList<Statement> list = new ArrayList<>();

//        // Example 1
//        Expression const1 = new ConstExpression(2);
//
//        Statement s1 = new AssignStatement("v",const1);     // initial assign v = 2
//        Statement s2 = new PrintStatement(new VarExpression("v"));
//        Statement s3 = new CompStatement(s2,new AssignStatement("v",new ConstExpression(3)));
//        Statement s4 = new WhileStatement(s3,new BooleanExpression("==",new VarExpression("v"),new ConstExpression(2)));
//        Statement s5 = new CompStatement(s1,s4);
//
//        list.add(s5);
//
//        // Example 2
//
//
//        s1 = new AssignStatement("v",new ConstExpression(10));
//        s2 = new newH("a",new ConstExpression(22));
//
//        Expression v = new VarExpression("v");
//
//        s3 = new wH("a",new ConstExpression(30));
//        s4 = new AssignStatement("v", new ConstExpression(32));
//        s5 = new PrintStatement(v);
//        Statement s6 = new PrintStatement(new rH("a"));
//
//        Statement forkedThreadStmt = new CompStatement(s3,s4);
//        Statement forkedThreadStmt2 = new CompStatement(forkedThreadStmt,s5);
//        Statement forkedThreadStmt3 = new CompStatement(forkedThreadStmt2,s6);
//
//
//        Statement forkStatement = new ForkStatement(forkedThreadStmt3);
//
//        Statement s7 = new CompStatement(s1,s2);
//        Statement s8 = new CompStatement(s7,forkStatement);
//        Statement s9 = new CompStatement(s8,s5);
//        Statement s10 = new CompStatement(s9,s6);
//
//        list.add(s10);
        ///////////////////////////
        // POINT B

        Expression const1 = new ConstExpression(20);
        Expression v = new VarExpression("v");

        Statement s1 = new AssignStatement("v",const1);

        Expression expr1 = new ConstExpression(0);
        Expression expr2 = new ConstExpression(3);
        Expression expr3 = new ArithExpression('+',v,new ConstExpression(1));

        Statement insideFork = new CompStatement(new PrintStatement(v),new AssignStatement("v",new ArithExpression('+',v,new ConstExpression(1))));
        Statement stmt = new ForkStatement(insideFork);

        Statement forr = new ForStatement(stmt,expr1,expr2,expr3);

        Statement lastPrint = new PrintStatement(new ArithExpression('*',v,new ConstExpression(10)));

        Statement final1 = new CompStatement(s1,forr);
        Statement final2 = new CompStatement(final1,lastPrint);

        list.add(final2);

        //////////////////
        /// SIMPLE TEST EXAMPLE FOR LOCK

        const1 = new ConstExpression(20);
        v = new VarExpression("v");

        s1 = new AssignStatement("v",const1);

        Statement s2 = new newLock("x");
        Statement s3 = new lock("x");
        Statement s4 = new unlock("x");

        final1 = new CompStatement(s1,s2);
        final2 = new CompStatement(final1,s3);
        Statement final3 = new CompStatement(final2,s4);

        list.add(final3);

        /// INCOMIIIING
        /// BRACE YOURSELVES
        ////////////////////////
        const1 = new ConstExpression(20);
        Expression const2 = new ConstExpression(30);

        Expression v1 = new VarExpression("v1");
        Expression v2 = new VarExpression("v2");

        Statement newHv1 = new newH("v1",const1);
        Statement newHv2 = new newH("v2",const2);


        ////////
        Statement newLock_x = new newLock("x");
        Statement lock_x = new lock("x");
        Statement unlock_x = new unlock("x");

        Statement whv1Dec = new wH("v1",new ArithExpression('-', new rH("v1"),new ConstExpression(1)));
        Statement whv1Inc = new wH("v1",new ArithExpression('+', new rH("v1"),new ConstExpression(1)));

        ////// initializing second fork
        Statement insideSecondFork1 = new CompStatement(whv1Dec,unlock_x);
        Statement insideSecondFork2 = new CompStatement(lock_x,insideSecondFork1);
        Statement secondFork = new ForkStatement(insideSecondFork2);
        ///////
        // initializing first fork
        Statement insideFirstFork1 = new CompStatement(whv1Inc,unlock_x);
        Statement insideFirstFork2 = new CompStatement(lock_x,insideFirstFork1);
        Statement insideFirstFork3 = new CompStatement(secondFork,insideFirstFork2);
        Statement firstFork = new ForkStatement(insideFirstFork3);

        //////
        //second part
        ///////

        Statement newLock_q = new newLock("q");
        Statement lock_q = new lock("q");
        Statement unlock_q = new unlock("q");
        //////

        Statement whv2Inc5 = new wH("v2",new ArithExpression('+', new rH("v2"),new ConstExpression(5)));
        Statement whv2Inc1 = new wH("v2",new ArithExpression('+', new rH("v2"),new ConstExpression(1)));

        Statement assignM = new AssignStatement("m", new ConstExpression(100));

        //// initializing anotherSecondFork
        Statement insideAnotherSecondFork1 = new CompStatement(whv2Inc5,unlock_q);
        Statement insideAnotherSecondFork2 = new CompStatement(lock_q,insideAnotherSecondFork1);
        Statement anothersecondFork = new ForkStatement(insideAnotherSecondFork2);
        //// initializing first fork
        Statement insideAnotherFirstFork1 = new CompStatement(whv2Inc1,unlock_q);
        Statement insideAnotherFirstFork2 = new CompStatement(lock_q, insideAnotherFirstFork1);
        Statement insideAnotherFirstFork3 = new CompStatement(assignM, insideAnotherFirstFork2);
        Statement insideAnotherFirstFork4 = new CompStatement(anothersecondFork,insideAnotherFirstFork3);
        Statement anotherfirstFork = new ForkStatement(insideAnotherFirstFork4);
        ///////

        Statement assignZ200 = new AssignStatement("z",new ConstExpression(200));
        Statement assignZ300 = new AssignStatement("z",new ConstExpression(300));
        Statement assignZ400 = new AssignStatement("z",new ConstExpression(400));
        Statement assignZ500 = new AssignStatement("z",new ConstExpression(500));
        Statement printRhv1 = new PrintStatement(new rH("v1"));
        Statement printRhv2 = new PrintStatement(new rH("v2"));

        //// finals
        final1 = new CompStatement(printRhv2,unlock_q);
        final2 = new CompStatement(lock_q,final1);
        final3 = new CompStatement(unlock_x,final2);
        Statement final4 = new CompStatement(printRhv1,final3);
        Statement final5 = new CompStatement(lock_x,final4);
        Statement final6 = new CompStatement(assignZ500,final5);
        Statement final7 = new CompStatement(assignZ400,final6);
        Statement final8 = new CompStatement(assignZ300,final7);
        Statement final9 = new CompStatement(assignZ200,final8);
        Statement final10 = new CompStatement(anotherfirstFork,final9);
        Statement final11 = new CompStatement(newLock_q,final10);
        Statement final12 = new CompStatement(firstFork,final11);
        Statement final13 = new CompStatement(newLock_x,final12);
        Statement final14 = new CompStatement(newHv2,final13);
        Statement final15 = new CompStatement(newHv1,final14);

        list.add(final15);

        return list;
    }

    public void start(Stage primaryStage){
        IRepository repo = new Repository("logFile.txt");
        Controller ctrl = new Controller(repo);
        primaryStage.setTitle("Programs");
        primaryStage.setScene(new Scene(new MainWindow(ctrl, getStatements()).getView(), 700, 500));
        primaryStage.show();
    }
}