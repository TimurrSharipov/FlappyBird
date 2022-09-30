package com.demo.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Obstacles {
    class WallPairs{
        Vector2 position;
        float speed;
        int offset;
        Rectangle emptySpace;

        public WallPairs(Vector2 pos){
            position = pos;
            speed = 2;
            offset = new Random().nextInt(250);
            emptySpace = new Rectangle(pos.x,pos.y- offset + 300,50,betweenDistance);
        }
        public void update(){
            position.x -= speed;
            if (position.x < -50){
                position.x = 800;
                offset = new Random().nextInt(250);
            }
            emptySpace.x = position.x;
        }
    }
    static WallPairs[] obs;
    private Texture txt;
    private int betweenDistance;

    public Obstacles() {
        obs = new WallPairs[4];
        txt = new Texture("wall.png");
        betweenDistance = 250;
        int startPositionX = 450;
        for (int i = 0; i < obs.length; i++) {
            obs[i] = new WallPairs(new Vector2(startPositionX,0));
            startPositionX += 200;
        }
    }
    public void render(SpriteBatch batch){
        for (int i = 0; i < obs.length; i++) {
            batch.draw(txt, obs[i].position.x, obs[i].position.y - obs[i].offset);
            batch.draw(txt, obs[i].position.x, obs[i].position.y + betweenDistance + txt.getHeight() - obs[i].offset);

        }
    }
    public void update(){
        for (int i = 0; i < obs.length; i++) {
            obs[i].update();
        }
    }
    public void recreate(){
        int startPositionX = 450;
        for (int i = 0; i < obs.length; i++) {
            obs[i] = new WallPairs(new Vector2(startPositionX,0));
            startPositionX += 200;
        }
    }
}
