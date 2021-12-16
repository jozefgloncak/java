package gloncak.jozef;

import java.awt.*;
import java.util.Random;

public class App {
    public static final int DEFAULT_SLEEP_TIME = 1000;
    public static final int MAX_Y = 400;
    public static final int MAX_X = 400;
    private static final String HELP = "--help";

    static long howOftenMove = DEFAULT_SLEEP_TIME;
    static int x = MAX_X;
    static int y = MAX_Y;

    public static void main(String[] args) throws AWTException, InterruptedException {
        if (args.length > 0) {
            try {
                if (HELP.equals(args[0])) {
                    displayHelp();
                }
                howOftenMove = Long.parseLong(args[0]);
            } catch (NumberFormatException e) {
            }
        }
        if (args.length > 1) {
            try {
                x = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
            }
        }
        if (args.length > 2) {
            try {
                y = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
            }
        }

        displaySettings();

        Robot robot = new Robot();
        Random random = new Random();
        while (true) {
            robot.mouseMove(random.nextInt(x), random.nextInt(y));
            Thread.sleep(howOftenMove*1000);
        }
    }

    private static void displaySettings() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Mouse cursor will be moved every %d seconds.\n", howOftenMove));
        sb.append(String.format("Cursor will be moved inside area (defined from top left corner in pixels) x = " +
                "%d, y = %d", x, y));
        System.out.println(sb.toString());
    }

    private static void displayHelp() {
        StringBuilder sb = new StringBuilder();
        sb.append("Examples:\n");
        sb.append("- mouse-mover [howOftenMove] [maxXPosition] [maxYPosition]\n");
        sb.append("-- howOftenMove - how often will be mouse moved in seconds\n");
        sb.append("-- maxXPosition - measured in pixel from top left corner where mouse cursor will appear\n");
        sb.append("-- maxYPosition - measured in pixel from top left corner where mouse cursor will appear\n\n");
        sb.append("- mousemover --help - Display this\n");
        System.out.println(sb.toString());
        System.exit(0);
    }
}
