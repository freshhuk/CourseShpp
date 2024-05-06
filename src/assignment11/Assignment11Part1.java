package assignment11;

import acm.graphics.GLine;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
import java.util.ArrayList;


public class Assignment11Part1 extends WindowProgram {

    /**
     * The width of the application window
     */
    public static final int APPLICATION_WIDTH = 1000;

    /**
     * The height of the application window
     */
    public static final int APPLICATION_HEIGHT = 1000;

    public void run(){
        //todo Создать может быть структуру как модель для
        // передачи данных бо может быть передаватся дахера чего и как то это скоротить
        // для парсинга создать свое дерево куда хранить данніе полученіе со строки


        createLine();
        //True case
        //drawFunc("y=-10");
        //drawFunc("y=xr3");
        //drawFunc("y=-x");
        //drawFunc("y=-x^2");
        drawFunc("y=-x^3");
    }

    //Парсим функцию так : отельно Х, отдельно действие над ним
    private void drawFunc(String func){
        ArrayList<String> parsedFunc = parser(func);

        if (parsedFunc != null) {
            char typeFunc = getTypeFunc(parsedFunc.get(0));
            if(typeFunc == 'n'){
                double y = getHeight() / 2.;
                y -= Double.parseDouble(parsedFunc.get(0));
                createLineFunc(0, y, getWidth(), y);
            }
            else if (typeFunc == 'x') {
                if (parsedFunc.get(0).equals("x")) {
                    // Код для графика y = x
                    createLineFunc(0, getHeight(), getWidth(), 0);
                }
                else{
                    createLineFunc(0, 0, getWidth(), getHeight());
                }
            }
            else if (typeFunc == 'p') {
                double centerX = getWidth() / 2.0;
                double centerY = getHeight() / 2.0;
                if(parsedFunc.get(0).equals("x")){
                    drawParabola(centerX, centerY, true, true);  // Направо
                    drawParabola(centerX, centerY, false, true); // Налево
                }
                else{
                    drawParabola(centerX, centerY, true, false);  // Налево
                    drawParabola(centerX, centerY, false, false); // Налево
                }

            } else if (typeFunc == 'g') {
                double centerX = getWidth() / 2.0;
                double centerY = getHeight() / 2.0;

                if(parsedFunc.get(0).equals("x")){
                    drawParabola(centerX, centerY, true, true);  // Направо
                    drawParabola(centerX, centerY, false, false); // Налево
                }
                else {
                    drawParabola(centerX, centerY, true, false);  // Налево
                    drawParabola(centerX, centerY, false, true); // Направо
                }

            } else if(typeFunc == '0'){
                System.out.println("Unknown func");
            }


        }
    }

    private ArrayList<String> parser(String func){

        ArrayList<String> parsedFunc = new ArrayList<>();
        if (func.startsWith("y=")) {
            func = func.substring(2); // удаляем первые два символа ("y=")
            func = func.replace(" ", ""); // удаляем пробелы

            // Разбиваем строку на составляющие по операторам
            System.out.println(func);
            // Добавляем каждую составляющую в список
            parsedFunc.add(func);

        }
        else {
            return null;
        }

        return parsedFunc;
    }
    private char getTypeFunc(String func){
        char type = '0';
        if (func.equals("x") || func.equals("-x")){
            type = 'x';
        }
        else if (func.equals("x^2") || func.equals("-x^2")){
            type = 'p';
        }
        else if (func.equals("x^3") || func.equals("-x^3")){
            type = 'g';
        }
        else if (Character.isDigit(func.charAt(0)) || Character.isDigit(func.charAt(1))){
            type = 'n';
        }
        return type;
    }
    private void createLine(){
        double x = getWidth() / 2.;
        double y = getHeight() / 2.;

        GLine line = new GLine(0, y, getWidth(), y);
        add(line);
        GLine line2 = new GLine(x, 0, x, getHeight());
        add(line2);
    }
    private void createLineFunc(double x, double y, double x2, double y2){
        GLine line = new GLine(x, y, x2, y2);
        line.setColor(Color.RED);
        add(line);
    }
    private void drawParabola(double startX, double startY, boolean toRight, boolean itsParabola) {
        double x = startX;
        double y = startY;
        double increment = toRight ? 1 : -1;
        double lineDirection = itsParabola ? 1 : -1;
        for (int i = 1; i <= getWidth(); i++) {
            double nextX = x + (increment * i) - 1;
            double nextY = y - lineDirection * ((nextX - x) * (nextX - x));
            createLineFunc(x, y, nextX, nextY);
            x = nextX;
            y = nextY;
        }
    }
}