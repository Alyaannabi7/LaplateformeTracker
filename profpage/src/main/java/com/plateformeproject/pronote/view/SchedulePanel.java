package com.plateformeproject.pronote.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class SchedulePanel extends VBox {

    public SchedulePanel() {
        setStyle();
        createHeader();
        createTimeSlots();
        createAlert();
    }

    private void setStyle() {
        this.getStyleClass().add("panel");
        this.setPrefSize(680, 380);           // Taille d'origine
        this.setSpacing(15);
        this.setPadding(new Insets(20));
        this.setAlignment(Pos.TOP_CENTER);
    }

    private void createHeader() {
        Label title = new Label("");
        title.getStyleClass().add("neon-title");
        title.setFont(Font.font("Orbitron", FontWeight.BOLD, 24));

        Label subtitle = new Label("EMPLOI DU TEMPS - MISSION JOUR 1");
        subtitle.setStyle("-fx-text-fill: #88ddff; -fx-font-size: 14px;");

        VBox header = new VBox(-40, title, subtitle);
        header.setAlignment(Pos.CENTER);

        this.getChildren().add(header);
    }

    private void createTimeSlots() {
        GridPane grid = new GridPane();
        grid.setHgap(12);
        grid.setVgap(12);
        grid.setAlignment(Pos.CENTER);

        // Row 1
        addTimeSlot(grid, 0, 0, "09:00 - 10:30", "KICK OFF\nSALLE 8-1\nPR. DURAND", false);
        addTimeSlot(grid, 1, 0, "11:00 - 12:30", "CONSULTATION TECHNIQUE\nSALLE 5-3\nPR. DUBOIS", false);

        // Row 2 - Red alert
        addTimeSlot(grid, 0, 1, "14:30 - 16:00", "CONSULTATION TECHNIQUE\nSALLE 5-3\nPR. DUBOIS", true);

        // Row 3
        addTimeSlot(grid, 1, 1, "15:30", "SPORTaa\nSALLE G-1\nPR. MARTIN", false);
        addTimeSlot(grid, 0, 2, "16:30 - 18:00", "SPORT\nSALLE G-1\nPR. MARTIN", false);

        this.getChildren().add(grid);
    }

    private void addTimeSlot(GridPane grid, int col, int row, String time, String details, boolean isAlert) {
        VBox slot = new VBox(8);
        slot.setPrefWidth(280);
        slot.setPadding(new Insets(12));
        slot.setAlignment(Pos.CENTER);
        slot.setStyle("-fx-background-color: rgba(15, 35, 65, 0.95); " +
                "-fx-border-radius: 10; -fx-background-radius: 10;");

        if (isAlert) {
            slot.setStyle(slot.getStyle() +
                    "-fx-border-color: #ff3366; " +
                    "-fx-effect: dropshadow(gaussian, #ff3366, 25, 0.8, 0, 0);");
        } else {
            slot.setStyle(slot.getStyle() +
                    "-fx-border-color: #00f7ff; " +
                    "-fx-effect: dropshadow(gaussian, #00f7ff, 18, 0.6, 0, 0);");
        }

        Label timeLabel = new Label(time);
        timeLabel.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 17px; -fx-font-weight: bold;");

        Label detailsLabel = new Label(details);
        detailsLabel.setStyle("-fx-text-fill: #aaddff; -fx-font-size: 13.5px;");
        detailsLabel.setAlignment(Pos.CENTER);
        detailsLabel.setWrapText(true);

        slot.getChildren().addAll(timeLabel, detailsLabel);

        grid.add(slot, col, row);
    }

    private void createAlert() {
        Label alert = new Label("ALERTE MISSION: EN COURS");
        alert.setStyle("-fx-background-color: rgba(255, 50, 80, 0.9); " +
                "-fx-text-fill: white; " +
                "-fx-font-size: 16px; " +
                "-fx-font-weight: bold; " +
                "-fx-padding: 10 30; " +
                "-fx-border-radius: 8; -fx-background-radius: 8;");

        this.getChildren().add(alert);
    }
}