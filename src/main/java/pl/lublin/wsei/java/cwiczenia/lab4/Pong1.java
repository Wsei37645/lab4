package pl.lublin.wsei.java.cwiczenia.lab4;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.EventListener;
import java.util.Random;


public class Pong1 extends Application {

    private static final double WIDTH = 800;
    private static final double HEIGHT = 600;
    private static final double MARGIN = 50;
    private static final double ARENAWIDTH = WIDTH - 2*MARGIN;
    private static final double ARENAHEIGHT = HEIGHT - 2*MARGIN;
    private static final double ARENAX1 = MARGIN;
    private static final double ARENAY1 = MARGIN;
    private static final double ARENAX2 = ARENAX1 + ARENAWIDTH;
    private static final double ARENAY2 = ARENAY1 + ARENAHEIGHT;
    private static final double R = 10;

    @Override
    public void start(Stage stage) {
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Timeline t =new Timeline(new KeyFrame(Duration.millis(100), e -> run(gc)));
        t.setCycleCount(Timeline.INDEFINITE);
        stage.setTitle("Kulki!");
        stage.setScene(new Scene(new StackPane(canvas)));
        stage.show();
        initKule();
        t.play();


    }

    private static final int LICZBAKULEK =10;
    private Kulka[] kulki = new Kulka[LICZBAKULEK];
    private rugby[] rugby = new rugby[LICZBAKULEK];

    private void initKule(){
        Random lott = new Random();
        for (int i=0; i<LICZBAKULEK; i++) {

            kulki[i] = new Kulka(
                    lott.nextDouble() * ARENAWIDTH + ARENAX1,
                    lott.nextDouble() * ARENAHEIGHT + ARENAY1,
                    5 + lott.nextDouble()*20,
                    5 + lott.nextDouble()*20,
                    Color.WHITE);

            rugby[i] = new rugby(
                    lott.nextDouble() * ARENAWIDTH + ARENAX1,
                    lott.nextDouble() * ARENAHEIGHT + ARENAY1,
                    5 + lott.nextDouble()*20,
                    5 + lott.nextDouble()*20,
                    5,
                    10,
                    20,
                    Color.FLORALWHITE);
        }

    }

    private void run(GraphicsContext gc) {

        gc.setFill(Color.BLACK);
        gc.fillRect(ARENAX1, ARENAY1, ARENAWIDTH, ARENAHEIGHT);
        for (int i=0; i<LICZBAKULEK; i++) {
            kulki[i].checkBoundaryCollision(ARENAX1, ARENAY1, ARENAX2, ARENAY2);
            kulki[i].update();
            kulki[i].draw(gc);
            rugby[i].checkBoundaryCollision(ARENAX1, ARENAY1, ARENAX2, ARENAY2);
            rugby[i].update();
            rugby[i].draw(gc);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}