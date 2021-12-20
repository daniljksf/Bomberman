package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.logic.BoardService;
import com.mygdx.game.logic.BombService;
import com.mygdx.game.logic.Configuration;
import com.mygdx.game.logic.GameLogic;
import com.mygdx.game.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

//это местный мейн, в create происходить создание
//в рендер отрисовка
//в апдейт вся логика игры
public class MyGdxGame extends ApplicationAdapter {
	//это поле для отрисовки
	private SpriteBatch batch;
	private BackGround backGround;
	private Board board;
	private GameLogic gameLogic;
	private BombService bombService;

	private enum Angle {
		LEFT_DOWN(true),
		RIGHT_UP(false);

		private boolean type;

		Angle(boolean type) {
			this.type = type;
		}
	}

	@Override
	public void create () {
		Configuration configs = Configuration.loadConfigs("configs.json");

		batch = new SpriteBatch();
		backGround = new BackGround();
		board = BoardService.createBoard(configs.getPathToMap());
		bombService = new BombService(board);

		Cell one = BoardService.getEmptyCell(board, Angle.LEFT_DOWN.type);
		Cell two = BoardService.getEmptyCell(board, Angle.RIGHT_UP.type);

		Bomber bomberOne = new Bomber(one.getX(), one.getY(), board, bombService, configs.getPathToTextureBomberOne(), configs.getPathToTextureDeadBomberOne());
		Bomber bomberTwo = new Bomber(two.getX(), two.getY(), board, bombService, configs.getPathToTextureBomberTwo(), configs.getPathToTextureDeadBomberTwo());

		List<Bomber> bombers = new ArrayList<>();
		bombers.add(bomberOne);
		bombers.add(bomberTwo);

		gameLogic = new GameLogic(bombers);
	}

	@Override
	public void render () {
		update();

		batch.begin();
		backGround.render(batch);
		board.render(batch);
		gameLogic.render(batch);
		batch.end();
	}

	public void update(){
		if (!gameLogic.gameEnd()) {
			//сюда нужно добавить таймер и если будет 3 секунды то взрыв
			long currentTime = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
			gameLogic.checkEvent();
			gameLogic.checkTime(currentTime);
			bombService.explode(currentTime);
			bombService.explodeWave(currentTime);
			//тут будет описана логика обновлений игры
		}
	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}
