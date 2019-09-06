package gloncak.jozef.design.pattern;

import gloncak.jozef.design.pattern.impl.NumberGeneratorImpl;
import gloncak.jozef.design.pattern.impl.dp.EvenNumberObserverImpl;
import gloncak.jozef.design.pattern.impl.dp.Number3DivisibleNumberObserverImpl;
import gloncak.jozef.design.pattern.impl.dp.OddNumberObserverImpl;
import gloncak.jozef.design.pattern.writer.CustomizedBufferedWriter;

import java.io.*;

/**
 * Hello world!
 *
 */
public class Main
{
    private static NumberGeneratorImpl numberGenerator = null;
    private static String fileName = "output.txt";
    private static CustomizedBufferedWriter fw;

    static {
        try {
            fw = new CustomizedBufferedWriter(new BufferedWriter(new FileWriter(fileName)));
        } catch (IOException e) {
            System.out.format("Creation of file %s wasn't success", fileName);
        }
    }

    public static void main( String[] args ) throws IOException {
        BufferedReader consoleReader =
                new BufferedReader(new InputStreamReader(System.in));
        String cmd = "start"; //default cmd - auto start of generation at start of application

        do {
            switch (cmd) {
                case "start": handleStartGenerator(); break;
                case "stop": handleStopGenerator(); break;
                case "help": handleHelpCommand(); break;
                case "register":
                    cmd = consoleReader.readLine();
                    switch (cmd) {
                        case "odd": handleRegisterOddObserver(); break;
                        case "even": handleRegisterEvenObserver(); break;
                        case "3": handleRegister3Observer(); break;
                    }
                    break;
                case "list": handleListCommand(); break;
                case "unregister":
                    cmd = consoleReader.readLine();
                    handleUnregisterObserver(Integer.parseInt(cmd));
                    break;
            }
            cmd = consoleReader.readLine();
        } while (!"quit".equals(cmd));
        handleStopGenerator();

        System.out.println("End of demo of Observer design pattern");
        fw.close();
    }

    private static void handleListCommand() {
        System.out.println(numberGenerator.provideListOfObservers());
    }

    private static void handleHelpCommand() {
        System.out.println("Select one of following commands:");
        System.out.println("- start - to start generation of numbers:");
        System.out.println("- stop - to stop generation of numbers");
        System.out.println("- quit - to quit application");
        System.out.println("- register - register new observer");
        System.out.println("-- register even");
        System.out.println("-- register odd");
        System.out.println("-- register 3");
        System.out.println("- list - list registered observers");
        System.out.println("- unregister - unregister existing observer");
        System.out.println("-- unregister [type] [id]");
    }

    private static void handleStopGenerator() {
        if (numberGenerator != null) {
            numberGenerator.stopGenerate();
            numberGenerator = null;
        }
        System.out.println("Stop of number generator");
        fw.write(":::::Stop of number generator");
    }

    private static void handleStartGenerator() {
        System.out.println("Start of number generator");
        fw.write("");
        fw.write("::::::Start of number generator");

        numberGenerator = new NumberGeneratorImpl(fw);
        numberGenerator.startGenerate();

        handleRegisterOddObserver();
        handleRegisterEvenObserver();
        handleRegister3Observer();

        System.out.format("Number generating in progress. See %s file e.g. via command >>less +F -N output.txt%n", fileName);
    }

    private static void handleRegister3Observer() {
        if (numberGenerator == null) {
            System.out.println("It is necessary to start number generator");
            return;
        }
        System.out.println("Register number 3 divisible observer");
        Number3DivisibleNumberObserverImpl number3DivisibleNumberObserver = new Number3DivisibleNumberObserverImpl(fw);
        numberGenerator.register3DivisibleNumberObserver(number3DivisibleNumberObserver);
    }

    private static void handleRegisterEvenObserver() {
        if (numberGenerator == null) {
            System.out.println("It is necessary to start number generator");
            return;
        }
        System.out.println("Register even number observer");
        EvenNumberObserverImpl evenNumberObserver = new EvenNumberObserverImpl(fw);
        numberGenerator.registerEvenNumberObserver(evenNumberObserver);

    }

    private static void handleRegisterOddObserver() {
        if (numberGenerator == null) {
            System.out.println("It is necessary to start number generator");
            return;
        }
        System.out.println("Register odd number observer");
        OddNumberObserverImpl oddNumberObserver = new OddNumberObserverImpl(fw);
        numberGenerator.registerOddNumberObserver(oddNumberObserver);
    }

    private static void handleUnregisterObserver(int observerID) {
        numberGenerator.unregisterObserver(observerID);
    }

}
