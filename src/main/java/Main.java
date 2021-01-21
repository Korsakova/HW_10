import javax.tools.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        writeClass();
        compileMyClass();
        useNewClassLoader();
    }

    /**
     * Метод компиляции нового класса. Списано из инета
     */

    private static void compileMyClass() throws IOException {
        File loaderfile = new File("./Loader.java");
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
        File parentDirectory = loaderfile.getParentFile();
        fileManager.setLocation(StandardLocation.CLASS_OUTPUT, Collections.singletonList(parentDirectory));
        Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromFiles(Collections.singletonList(loaderfile));
        compiler.getTask(null, fileManager, null, null, null, compilationUnits).call();
        fileManager.close();

    }

    /**
     * Метод загрузки нового класса через класслоудер
     */
    private static void useNewClassLoader() throws Exception {
        ClassLoader classLoader = new NewClassLoader();
        Class<?> kindClass = classLoader.loadClass("Loader");
        Worker loader = (Worker) kindClass.newInstance();
        loader.doWork();


    }


    public static void writeClass() throws IOException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("что под саутами");
        String text = scanner.nextLine();
        String textagain = scanner.nextLine();

        String starter = "public class Loader implements Worker {\n" +
                "    static {\n" +
                "        System.out.println(\"" + text + "\");\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public void doWork() {\n" +
                "        System.out.println(\"" + textagain + "\");\n" +
                "    }\n" +
                "\n" +
                "}";


        byte[] ba = starter.getBytes();

        FileOutputStream fos = new FileOutputStream("Loader.java");

        fos.write(ba);

    }
}
