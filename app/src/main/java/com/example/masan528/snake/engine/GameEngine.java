package com.example.masan528.snake.engine;

import android.annotation.TargetApi;
import android.os.Build;

import com.example.masan528.snake.Coordinate;
import com.example.masan528.snake.enums.Direction;
import com.example.masan528.snake.enums.GameState;
import com.example.masan528.snake.enums.TileType;
import java.util.concurrent.ThreadLocalRandom;

import java.util.ArrayList;

public class GameEngine {
    public static final int WIDTH=28;  //x
    public static final int HEIGHT=42; //y
    private Direction direction = Direction.SOUTH;
    public GameState gameState = GameState.Running;


    public ArrayList<Coordinate> walls = new ArrayList<Coordinate>();
    //ArrayList<Coordinate> snake = new ArrayList<Coordinate>();
    public Coordinate foodLocation = new Coordinate(0,0);
    public Snake snake;
    public GameEngine(){
        init();
    }

    private void init() {
        initWalls();
        snake = new Snake();
        initFood();
    }

    private void initWalls() {
        //top and bottom
        for (int i=0;i<WIDTH;i++){
            walls.add(new Coordinate(i,0));
            walls.add(new Coordinate(i,HEIGHT-1));
        }

        for (int i =0;i<HEIGHT;i++){
            walls.add(new Coordinate(0,i));
            walls.add(new Coordinate(WIDTH-1,i));
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void initFood() {
        int randomx = ThreadLocalRandom.current().nextInt(1,27);
        int randomy = ThreadLocalRandom.current().nextInt(1,41);
        foodLocation.setX(randomx);
        foodLocation.setY(randomy);
    }

    public TileType[][] getMap(){
        TileType [][] map = new TileType[WIDTH][HEIGHT];

        for (int i =0;i< WIDTH; i++){
            for (int j=0;j< HEIGHT; j++){
                map[i][j] = TileType.Blank;
            }
        }

        for (int i=0; i<walls.size();i++){
            Coordinate co = walls.get(i);
            map[co.getX()][co.getY()] = TileType.Wall;
        }

        map[snake.head().getX()][snake.head().getY()]=TileType.Head;
        for (int i=1; i<snake.getSize();i++){
            map[snake.get(i).getX()][snake.get(i).getY()]=TileType.Body;
        }

        map[foodLocation.getX()][foodLocation.getY()] = TileType.Food;
        return map;
    }

    public void switchDirection(Direction next){
        if (direction == Direction.SOUTH && next == Direction.NORTH ){
            return;
        }
        if (direction == Direction.NORTH && next == Direction.SOUTH ){
            return;
        }
        if (direction == Direction.WEST && next == Direction.EAST ){
            return;
        }
        if (direction == Direction.EAST && next == Direction.WEST ){
            return;
        }
        direction = next;
    }

    public void update(){
        Coordinate end = snake.get(snake.getSize()-1);
        switch (direction){

            case EAST:
                snake.updateSnake(1,0);
                break;
            case WEST:
                snake.updateSnake(-1,0);
                break;
            case NORTH:
                snake.updateSnake(0,-1);
                break;
            case SOUTH:
                snake.updateSnake(0,1);
                break;
        }

        for (Coordinate wall: walls){
            if (snake.head().equals(wall)){
                gameState = GameState.Lost;
            }
        }

        for (int i=1; i<snake.getSize();i++){
            if(snake.head().equals(snake.get(i))){
                gameState = GameState.Lost;
            }
        }

        if (snake.head().equals(foodLocation)){
            snake.add(end);
            initFood();
        }
    }

}
