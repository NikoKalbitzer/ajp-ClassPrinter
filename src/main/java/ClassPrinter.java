import java.util.HashMap;

public class ClassPrinter {
    Class[] classArray = new Class[100];
    public static void main(String... args){
        ClassPrinter p = new ClassPrinter();
        Class c = java.util.HashMap.class.getSuperclass();
        p.printSuperClasses("java.util.HashMap");
    }
         private Class<?> getClass(String name) {
        try {
            Class c = Class.forName(name);
            System.out.println(c.getCanonicalName());
            return c;
        } catch (ClassNotFoundException e) {
            System.out.println("class not found");
            return null;
        }
    }
        private int insertInArray(Class c){

        int i = 0;
        while(c.getSuperclass()!= null){
            c = c.getSuperclass();
            classArray[i] = c;
            i++;
            System.out.println(i);
        }
        return i;
        }

        private void printTree(int i){
            for(int j = i-1; i>=0; i--){
                System.out.println(classArray[i].getCanonicalName());
            }
        }
        private void printSuperClasses(String name){
            printTree(insertInArray(getClass(name)));

        }

}
