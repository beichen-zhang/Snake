package com.example.masan528.snake;

public class Coordinate {
    private int x;
    private int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public boolean equals(Coordinate other) {
        if ((this.x == other.getX())&&(this.y==other.getY())){
            return true;
        }
        return false;
    }

    public void printCoordinate(){
        System.out.println("x:"+this.getX()+"; y:"+this.getY());
    }
}
