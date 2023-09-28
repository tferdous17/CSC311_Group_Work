package com.example.csc311_group_work;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MazeController implements Initializable {
    @FXML
    public ImageView robotImg, mazeImg;
    @FXML
    public Group group_Rectangles;
    public StackPane robotImgContainer;
    public Rectangle testRect;
    List<Rectangle> walls = new ArrayList<>();
    List<String> codes = new ArrayList<>();

    AnimationTimer collisionTimer = new AnimationTimer() {
        @Override
        public void handle(long l) {
            if (collisionHandler(robotImgContainer, group_Rectangles)) {
                System.out.println("Collision occurred.");
                collisionTimer.stop();
            }
        }
    };

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mazeImg.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent key) {
                if (key.getCode().equals(KeyCode.RIGHT)) {
                    robotImg.setLayoutX(robotImg.getLayoutX() + 5);
                } else if (key.getCode().equals(KeyCode.LEFT)) {
                    robotImg.setLayoutX(robotImg.getLayoutX() - 5);
                }else if (key.getCode().equals(KeyCode.UP)) {
                    robotImg.setLayoutY(robotImg.getLayoutY() - 5);
                }else if (key.getCode().equals(KeyCode.DOWN)) {
                    robotImg.setLayoutY(robotImg.getLayoutY() + 5);
                }
            }
        });
    }

    private boolean collisionHandler(StackPane robotImgContainer, Group groupRectangles) {
        for (Node r : group_Rectangles.getChildren()) {
            if (r.getLayoutBounds().intersects(robotImgContainer.getBoundsInParent())) {
                return true;
            }
        }
        return false;
    }
}
