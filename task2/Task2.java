package task2;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Task2 {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Необходимо передать два файла: координаты окружности и координаты точек");
            return;
        }

        try (BufferedReader circleReader = new BufferedReader(new FileReader(args[0]));
             BufferedReader pointsReader = new BufferedReader(new FileReader(args[1]))) {

            String[] circleCoordinates = circleReader.readLine().split(" ");
            float centerX = Float.parseFloat(circleCoordinates[0]);
            float centerY = Float.parseFloat(circleCoordinates[1]);
            float radius = Float.parseFloat(circleReader.readLine());

            String point;
            while ((point = pointsReader.readLine()) != null) {
                String[] coordinates = point.split(" ");
                float pointX = Float.parseFloat(coordinates[0]);
                float pointY = Float.parseFloat(coordinates[1]);

                float distance = (float) Math.sqrt(Math.pow(pointX - centerX, 2) + Math.pow(pointY - centerY, 2));

                int position;
                if (distance == radius) {
                    position = 0;
                } else if (distance < radius) {
                    position = 1;
                } else {
                    position = 2;
                }

                System.out.println(position);
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
