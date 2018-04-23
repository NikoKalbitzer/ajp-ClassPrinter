import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

public class ClassPrinter {
    Class[] classArray = new Class[100];

    public static void main(String... args) {
        ClassPrinter p = new ClassPrinter();
        Class c = java.util.HashMap.class.getSuperclass();
        try {
            String test = args[0];

            System.out.println("Information on " + test);
            System.out.println();
            p.printSuperClasses(test);
            System.out.println();
            System.out.println("Class definition: ");
            System.out.println();
            p.printField(p.getClass(test));
            System.out.println();
            p.printConstructors(p.getClass(test));
            System.out.println();
            p.printMethods(p.getClass(test));
            System.out.println("}");
        }
        catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Please insert a class Name.");
        }

    }

    private Class<?> getClass(String name) {
        try {
            Class c = Class.forName(name);
            //System.out.println(c.getCanonicalName());
            return c;
        } catch (ClassNotFoundException e) {
            System.out.println("class not found");
            return null;
        }
    }

    private int insertInArray(Class c) {
        int i = 0;
        classArray[i] = c;
        i++;
        while (c.getSuperclass() != null) {
            c = c.getSuperclass();
            //System.out.println(c);
            classArray[i] = c;
            i++;
        }
        return i;
    }

    private void printTree(int j) {
        int a = 0;
        for (int i = j - 1; i >= 0; i--) {
            for (int b = 0; b < a; b++) {
                System.out.print("  ");
            }
            a++;
            System.out.println(classArray[i].getCanonicalName());
        }
    }

    public void printSuperClasses(String name) {
        printTree(insertInArray(getClass(name)));

    }

    public void printField(Class<?> c) {
        System.out.println(Modifier.toString(c.getModifiers()) + " " + c.toString() + " {");
        System.out.println();
        System.out.println("    //Fields");

        Field fieldarray[] = new Field[100];
        fieldarray = c.getDeclaredFields();
        for (int i = 0; i < fieldarray.length; i++) {
            System.out.println("    " + Modifier.toString(fieldarray[i].getModifiers()) + " " + fieldarray[i].getGenericType() + " " + fieldarray[i].getName());
        }

    }

    public void printMethods(Class<?> c) {
        LinkedList<Method> l = new LinkedList<Method>();
        l.addAll(Arrays.asList(c.getMethods()));
        System.out.println("    //Methods ");
        while (l.peek() != null) {
            System.out.println("    " + l.poll().toGenericString() );
        }
    }

    public void printConstructors(Class<?> c) {
        System.out.println("    //Constructors");
        LinkedList<Constructor> l = new LinkedList<Constructor>();
        l.addAll(Arrays.asList(c.getConstructors()));
        while (l.peek() != null) {
            System.out.println("    " + l.poll().toGenericString());
        }
    }


}
