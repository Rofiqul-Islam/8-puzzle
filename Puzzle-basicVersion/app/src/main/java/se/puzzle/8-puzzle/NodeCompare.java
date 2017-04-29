package se.puzzle;

/**
 * Created by USER on 4/12/2016.
 */
import java.util.Comparator;


public class NodeCompare implements Comparator<Nodes> {

    @Override
    public int compare(Nodes node1, Nodes node2) {
        // TODO Auto-generated method stub
        if (node1.priority > node2.priority){
            return 1;
        }
        if (node1.priority < node2.priority){
            return -1;
        }
        return 0;
    }



}
