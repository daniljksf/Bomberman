package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animator {

    //где хранятся анимации
    private TextureRegion[][] walkFrames;
    //длительность отображения одного кадра
    private float maxFrameTime;
    //время отображения текущего кадра
    private float currentFrameTime;
    private int frameColl;
    private int frameRow;
    //отдельный кадр анимации
    private int frameX;
    private int frameY;

    public Animator(TextureRegion region, int frameRow, int frameColl, float cycleTime){
        walkFrames = new TextureRegion[frameRow][frameColl];
        int frameWidth = region.getRegionWidth() / frameColl;
        int frameHeight = region.getRegionHeight() / frameRow;
        for(int i = 0; i < frameRow; i++){
            for(int j = 0; j < frameColl; j++){
                walkFrames[i][j] = new TextureRegion(region, j*frameWidth, i*frameHeight, frameWidth, frameHeight);
            }
        }
        this.frameRow = frameRow;
        this.frameColl = frameColl;
        maxFrameTime = cycleTime / frameColl;
        System.out.println(maxFrameTime);
        currentFrameTime = 0;
        frameX = 0;
        frameY = 0;
    }

    public void setFrameY(int frameY) {
        if (frameY < 0) return;
        this.frameY = frameY;
    }

    public void update(float dt){

        currentFrameTime += dt;
        if (currentFrameTime >= maxFrameTime){
            frameX++;
            currentFrameTime = 0;
        }
        if (frameX >= frameColl){
            frameX = 0;
        }
    }

    public int getFrameX() {
        return frameX;
    }

    public  TextureRegion getFrame(){
        return walkFrames[frameY][frameX];
    }
}