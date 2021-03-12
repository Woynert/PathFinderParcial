package edu;

public class Agent{

    /*  L left
        R right
        U up
        D down
    */
    public String path;

    public int pos[] = new int[2];

    //constructor
    public Agent(int x, int y, String path){
        this.pos[0] = x;
        this.pos[1] = y;
        this.path   = path;
    }

    //move Right
    public void MoveRight(){
        path += "R";
    }

    //move Left
    public void MoveLeft(){
        path += "L";
    }

    //move Up
    public void MoveUp(){
        path += "U";
    }

    //move Down
    public void MoveDown(){
        path += "D";
    }
}