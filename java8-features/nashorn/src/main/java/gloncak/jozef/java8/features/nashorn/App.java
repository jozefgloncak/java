package gloncak.jozef.java8.features.nashorn;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class App {
    public static void main(String[] args) {
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine nashorn = scriptEngineManager.getEngineByName("nashorn");

        try {
            nashorn.eval("print(\"Hello world\")");
        } catch (ScriptException e) {
            e.printStackTrace();
        }

    }
}
