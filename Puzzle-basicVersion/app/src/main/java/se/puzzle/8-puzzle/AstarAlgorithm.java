package se.puzzle;

/**
 * Created by USER on 4/12/2016.
 */
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Stack;


public class AstarAlgorithm {

    public Nodes puzzlesover(int[] a) throws IOException {


        Nodes[] states = new Nodes[4];//0 can go up,down,left,right four stage
        Nodes goalNodeFound = new Nodes();//goal
        goalNodeFound = null;
        Stack stack = new Stack();
        Nodes current = new Nodes();//current puzzle condition
        LinkedList<ArrayList<?>> visited = new LinkedList<ArrayList<?>>();//check this stage is visited before
        int count = 0;
        //initail array
        //Creating start node
        Nodes start = new Nodes();
        ArrayList<Integer> startState = new ArrayList<>();

        startState.add(a[0]);
        startState.add(a[1]);
        startState.add(a[2]);
        startState.add(a[3]);
        startState.add(a[4]);
        startState.add(a[5]);
        startState.add(a[6]);
        startState.add(a[7]);
        startState.add(a[8]);


        start.state = startState;
        start.parent = null;
        start.move = null;
        start.priority = 0;
        start.distance = -1;

        //Creating goal node
        Nodes goal = new Nodes();
        ArrayList<Integer> goalState = new ArrayList<>();//arraylist for goal

        goalState.add(0);
        goalState.add(1);
        goalState.add(2);
        goalState.add(3);
        goalState.add(4);
        goalState.add(5);
        goalState.add(6);
        goalState.add(7);
        goalState.add(8);


        //Creating goal node
        goal.state = goalState;
        goal.parent = null;
        goal.distance = -1;
        goal.move = null;
        //System.out.println(goal.state.toString());

        Comparator<Nodes> comparator = new NodesCompare();//comparator class for prioty queue
        PriorityQueue<Nodes> pQ = new PriorityQueue<Nodes>(100, comparator);
        pQ.add(start);
        visited.add(start.state);

        while (!pQ.isEmpty()) {
            count++;
            current = pQ.remove();//current stage

            states = findStates(current);//check in what stage can go from current stage

            for (int i = 0; i <= 3; i++) {
                if (states[i] != null) {
                    if (states[i].state.equals(goal.state)) {
                        goalNodeFound = states[i];//if goal state fourn break

                        break;
                    } else {
                        if (!visited.contains(states[i].state)) {
                            states[i].distance = current.distance + 1;
                            visited.add(states[i].state);
                            states[i].priority = costFunction(states[i], goal);
                            pQ.add(states[i]);
                        }
                    }
                }
            }
            if (goalNodeFound != null)
                break;

        }
        return goalNodeFound;
    }










    private static int costFunction(Nodes node, Nodes goal) {
        // TODO Auto-generated method stub

        int priority;
        int count = 0;

        //Heuristic Function Calculation

        for(int i=0; i<9; i++){
            if(node.state.get(i) != goal.state.get(i)){
                count++;
            }
        }

        priority = node.distance + count;
        return priority;
    }

    private static int costFunctionLength(Nodes node, Nodes goal) {
        // TODO Auto-generated method stub

        int priority;
        int count = 0;
        int index;
        //Heuristic Function Calculation

        for(int i=0; i<9; i++){
            index =	goal.state.indexOf(node.state.get(i));
            count = count + Math.abs(index - i);
        }

        priority = node.distance + count;
        return priority;
    }


    private static Nodes[] findStates(Nodes state) {
        // TODO Auto-generated method stub
        Nodes state1,state2,state3,state4;

        state1 = moveUP(state);
        state2 = moveDOWN(state);
        state3 = moveLEFT(state);
        state4 = moveRIGHT(state);

        Nodes[] states = {state1, state2, state3, state4};

        return states;
    }



    private static Nodes moveRIGHT(Nodes node) {
        // TODO Auto-generated method stub
        int space = node.state.indexOf(0);
        ArrayList<Integer> childState;
        int temp;
        Nodes childNode = new Nodes();

        if (space != 2 && space != 5 && space != 8) {
            childState = (ArrayList<Integer>) node.state.clone();
            temp = childState.get(space+1);
            childState.set(space+1,0);
            childState.set(space,temp);
            childNode.state = childState;
            childNode.parent = node;
            childNode.distance = node.distance + 1;
            childNode.move = "RIGHT";
            return childNode;
        }
        else{
            return null;
        }
    }

    private static Nodes moveLEFT(Nodes node) {
        // TODO Auto-generated method stub
        int space = node.state.indexOf(0);
        ArrayList<Integer> childState;
        int temp;
        Nodes childNode = new Nodes();

        if (space != 0 && space != 3 && space != 6) {
            childState = (ArrayList<Integer>) node.state.clone();
            temp = childState.get(space-1);
            childState.set(space-1,0);
            childState.set(space,temp);
            childNode.state = childState;
            childNode.parent = node;
            childNode.distance = node.distance + 1;
            childNode.move = "LEFT";
            return childNode;
        }
        else{
            return null;
        }
    }

    private static Nodes moveDOWN(Nodes node) {
        // TODO Auto-generated method stub
        int space = node.state.indexOf(0);
        ArrayList<Integer> childState;
        int temp;
        Nodes childNode = new Nodes();

        if (space <= 5) {
            childState = (ArrayList<Integer>) node.state.clone();
            temp = childState.get(space+3);
            childState.set(space+3,0);
            childState.set(space,temp);
            childNode.state = childState;
            childNode.parent = node;
            childNode.distance = node.distance + 1;
            childNode.move = "DOWN";
            return childNode;
        }
        else{
            return null;
        }
    }

    private static Nodes moveUP(Nodes node) {
        // TODO Auto-generated method stub
        int space = node.state.indexOf(0);
        ArrayList<Integer> childState;
        int temp;
        Nodes childNode = new Nodes();

        if (space > 2) {
            childState = (ArrayList<Integer>) node.state.clone();
            temp = childState.get(space-3);
            childState.set(space-3,0);
            childState.set(space,temp);
            childNode.state = childState;
            childNode.parent = node;
            childNode.distance = node.distance + 1;
            childNode.move = "UP";
            return childNode;
        }
        else{
            return null;
        }
    }


}

