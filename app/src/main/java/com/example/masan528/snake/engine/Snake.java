package com.example.masan528.snake.engine;

import com.example.masan528.snake.Coordinate;

import java.util.ArrayList;

public class Snake {
    private ArrayList<Coordinate> snake = new ArrayList<Coordinate>();

    public Snake(){
        snake.clear();
        snake.add(new Coordinate(8,8));
        snake.add(new Coordinate(8,7));
        snake.add(new Coordinate(8,6));
        snake.add(new Coordinate(8,5));
        snake.add(new Coordinate(8,4));
    }

    public int getSize(){
        return snake.size();
    }

    public Coordinate head(){
        return snake.get(0);
    }

    public void add (Coordinate tail){
        snake.add(tail);
    }

    public Coordinate get (int i){
        return snake.get(i);
    }

    public void updateSnake(int x, int y){
        for (int i=snake.size()-1; i>0;i--){
            snake.get(i).setX(snake.get(i-1).getX());
            snake.get(i).setY(snake.get(i-1).getY());
        }
        snake.get(0).setX(snake.get(0).getX()+x);
        snake.get(0).setY(snake.get(0).getY()+y);
    }
}
