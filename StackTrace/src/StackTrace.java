import java.util.ArrayList;
import java.util.List;

public class StackTrace {
    public static void main(String[] args) {
        CallerClass callerClass = new CallerClass();
        callerClass.isCall();
        System.out.println(Call.getCallerClassAndMethodName());
        anotherMethod();
    }
    private static void anotherMethod(){
        System.out.println(Call.getCallerClassAndMethodName());
    }
}

class CallerClass{
    public void isCall() {
        System.out.println(Call.getCallerClassAndMethodName());
    }

    public CallerClass() {
        isCall();
    }
}

class Call{
     public static List<Object> getCallerClassAndMethodName() {
        List<Object> list = new ArrayList<>();
         StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if(stackTrace[2].getMethodName().equals("main")) {
            list.add(null);
            list.add(null);
        }else{
            list.add(stackTrace[2].getClassName());
            list.add(stackTrace[2].getMethodName());
        }
        return list;
    }
}
