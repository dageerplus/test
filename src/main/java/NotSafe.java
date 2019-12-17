import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class NotSafe {//notsafe----不安全的
    public static void main(String[] args) {
        List<String> list = Collections.synchronizedList(new ArrayList());
        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 6));
                System.out.println(list);

            }, String.valueOf(i)).start();
        }
    }
}
