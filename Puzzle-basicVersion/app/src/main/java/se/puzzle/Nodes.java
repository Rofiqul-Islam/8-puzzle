package se.puzzle;

/**
 * Created by USER on 4/12/2016.
 */

import java.util.ArrayList;


public class Nodes {
    String name;
    ArrayList<Integer> state;
    Nodes parent;
    int distance;
    String move;
    public int priority;

    public Nodes(String name){
        this.name = name;
    }

    public Nodes(){

    }


    public String getName(){
        return this.name;
    }
}

