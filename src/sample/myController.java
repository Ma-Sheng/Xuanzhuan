package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class myController {
    @FXML
    private Button button;

    @FXML
    private BorderPane pane;

    @FXML
    private ImageView imageView1;

    @FXML
    private ImageView imageView2;

    @FXML
    private ImageView imageView3;

    @FXML
    private ImageView imageView4;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private AnchorPane leftAnchorPane;

    private volatile int i = 0;

    public synchronized void rotate() {


        new Thread(() -> {
            for (i = 0; ; i = i + 3) {
                // pane.setRotate(i);
                //anchorPane.setRotate(- i);

                if (i == 360) {
                    i = 0;
                }
                // 转到主线程中更新UI控件
                // 必须在主线程中才能更新控件
                Platform.runLater(() -> {
                    anchorPane.setRotate(i);
                    imageView1.setRotate(- i);
                    imageView2.setRotate(-10 * i);
                    imageView3.setRotate(- i);
                    imageView4.setRotate(-10 * i);
                    // leftAnchorPane.setRotate(- i);
                });

                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).start();
    }

    public void close() {
        Platform.exit();
    }
}
