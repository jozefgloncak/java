package gloncak.jozef.design.pattern.observer.writer;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;

public class CustomizedBufferedWriter extends BufferedWriter {
    public CustomizedBufferedWriter(Writer out) {
        super(out);
    }

    public void writeLn(String str)  {
        try {
            super.write(String.format("%s%n", str));
            super.flush();
        } catch (IOException e) {
            System.out.println("Problem with writting to output file");
        }
    }

    @Override
    public void write(String str)  {
        try {
            super.write(String.format("%s", str));
            super.flush();
        } catch (IOException e) {
            System.out.println("Problem with writting to output file");
        }
    }
}
