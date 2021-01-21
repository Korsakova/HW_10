
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class NewClassLoader extends ClassLoader {

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if ("Loader".equals(name)) {
            return findClass(name);
        }
        return super.loadClass(name);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        System.out.println("i found " + name);
        if ("Loader".equals(name)) {
            try {
                byte[] bytes = Files.readAllBytes(Paths.get("./Loader.class"));
                return defineClass(name, bytes, 0, bytes.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return super.findClass(name);
    }

}
