package gloncak.jozef.design.pattern.writer;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;

public class CustomizedBufferedWriter extends BufferedWriter {
    public CustomizedBufferedWriter(Writer out) {
        super(out);
    }

    @Override
    public void write(String str)  {
        try {
            super.write(String.format("%s%n", str));
            super.flush();
        } catch (IOException e) {
            System.out.println("Problem with writting to output file");
        }
    }
}
