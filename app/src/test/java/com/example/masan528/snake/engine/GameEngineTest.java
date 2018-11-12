package com.example.masan528.snake.engine;

import com.example.masan528.snake.Coordinate;
import com.example.masan528.snake.enums.Direction;
import com.example.masan528.snake.enums.GameState;
import com.example.masan528.snake.enums.TileType;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class GameEngineTest {
    @Test
    public void Init_wall_isCorrect() {

        GameEngine engine = new GameEngine();
        TileType [][]map= engine.getMap();
        for (int i=0;i<engine.WIDTH;i++){
            assertEquals(TileType.Wall,map[i][0]);
            assertEquals(TileType.Wall,map[i][engine.HEIGHT-1]);
        }

        for (int i=0;i<engine.HEIGHT;i++){
            assertEquals(TileType.Wall,map[0][i]);
            assertEquals(TileType.Wall,map[engine.WIDTH-1][i]);
        }

    }

    @Test
    public void Init_snake_isCorrect(){
        GameEngine engine = new GameEngine();
        TileType [][]map= engine.getMap();
        for (int i=1;i<engine.snake.getSize();i++){
            Coordinate coordinate = engine.snake.get(i);
            assertEquals(TileType.Body,map[coordinate.getX()][coordinate.getY()]);
        }
        Coordinate head = engine.snake.head();
        assertEquals(TileType.Head,map[head.getX()][head.getY()]);

        assertEquals(5,engine.snake.getSize());
    }

    @Test
    public void collision_wall(){
        GameEngine engine = new GameEngine();
        //TileType [][]map= engine.getMap();
        Coordinate head = engine.snake.head();
        int step_legal = engine.HEIGHT-1-head.getY()-1;
        for (int i=0; i<step_legal;i++){
            engine.update();
            assertEquals(engine.gameState,GameState.Running);
        }
        engine.update();
        assertEquals(engine.gameState,GameState.Lost);
    }

    @Test
    public void self_collision(){
        GameEngine engine = new GameEngine();
        //TileType [][]map= engine.getMap();
        engine.switchDirection(Direction.EAST);
        engine.update();
        for (int i=0;i<engine.snake.getSize();i++){
            engine.snake.get(i).printCoordinate();
        }
        assertEquals(engine.gameState,GameState.Running);
        engine.switchDirection(Direction.NORTH);
        engine.update();
        assertEquals(engine.gameState,GameState.Running);
        engine.switchDirection(Direction.WEST);
        engine.update();
        assertEquals(engine.gameState,GameState.Lost);
    }

    @Test
    public void increment_snake(){
        GameEngine engine = new GameEngine();
        engine.foodLocation = new Coordinate(8,9);
        TileType [][]map= engine.getMap();
        engine.update();
        assertEquals(engine.snake.getSize(),6);
        assertEquals(false,engine.foodLocation.equals(new Coordinate(8,9)));
    }
}