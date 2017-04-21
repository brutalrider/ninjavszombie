package com.myproject.game.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.myproject.game.MainGame;


/**
 * Created by Brutal on 26/01/2017.
 */

public class Hud implements Disposable{
    public Stage stage;
    private Viewport viewport;

    // Variables del HUD
    private Integer worldTimer;
    private float timeCount;
    private static Integer coins;
    private float fps;
    private int distance;

    // Etiquetas que se muestran en el HUD
    private Label distanceLabel;
    private Label countdownLabel;
    private Label fpsLabel;

    private Label.LabelStyle labelStyle;

    public Hud(SpriteBatch sb) {
        worldTimer = 0;
        timeCount = 0;
        coins = 0;

        viewport =  new FitViewport(MainGame.V_WIDTH, MainGame.V_HEIGHT);
        stage = new Stage(viewport,sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        labelStyle = new Label.LabelStyle(new BitmapFont(Gdx.files.internal("fonts/consolas.fnt"), false), Color.WHITE);
        fpsLabel = new Label(String.format("%2.0f", fps), labelStyle);
        fpsLabel.setFontScale(2);
        countdownLabel = new Label(String.format("%03d", worldTimer), labelStyle);
        countdownLabel.setFontScale(2);
        distanceLabel = new Label(String.format("%d", distance), labelStyle);
        distanceLabel.setFontScale(2);


        table.add(fpsLabel).padTop(1).padLeft(1);
        table.add(countdownLabel).expandX().padTop(2);
        table.add(distanceLabel).expandX().padTop(2);

        stage.addActor(table);
    }

    public void update(float dt){
        timeCount += dt;
        fpsLabel.setText(String.format("%2.0f", fps));
        distanceLabel.setText(String.format("%d", distance));
        if(timeCount >= 1){
            worldTimer++;
            countdownLabel.setText(String.format("%03d", worldTimer));
            timeCount = 0;
        }
    }


    public void setFps(float fps){
        this.fps = fps;
    }
    public void setDistance(int distance){
        this.distance = distance;
    }
    @Override
    public void dispose() {
        stage.dispose();
    }
}
