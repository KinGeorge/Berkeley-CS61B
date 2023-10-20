import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {
    static StudentArrayDeque<Integer> stu = new StudentArrayDeque<>();
    static ArrayDequeSolution<Integer> gt = new ArrayDequeSolution<>();
    String message = "";
    @Test
    public void testAddFirst() {
        for (int i = 0; i < 1000; i++) {
            if (stu.size() == 0) {
                int rand = StdRandom.uniform(1000);
                int headOrBack = StdRandom.uniform(2);
                if (headOrBack == 0) {
                    message = message + "addFirst(" + rand + ")\n";
                    gt.addFirst(rand);
                    stu.addFirst(rand);
                } else {
                    message = message + "addLast(" + rand + ")\n";
                    gt.addLast(rand);
                    stu.addLast(rand);
                }
            } else {
                int x = StdRandom.uniform(4);
                int rand = StdRandom.uniform(1000);
                Integer testremoveNumber = 1;
                Integer stdremoveNumber = 1;
                switch (x) {
                    case 0:
                        message = message + "addFirst(" + rand + ")\n";
                        gt.addFirst(rand);
                        stu.addFirst(rand);
                        break;
                    case 1:
                        message = message + "addLast(" + rand + ")\n";
                        gt.addLast(rand);
                        stu.addLast(rand);
                        break;
                    case 2:
                        message = message + "removeFirst()\n";
                        testremoveNumber = gt.removeFirst();
                        stdremoveNumber = stu.removeFirst();
                        break;
                    case 3:
                        message = message + "removeLast()\n";
                        testremoveNumber = gt.removeLast();
                        stdremoveNumber = stu.removeLast();
                        break;
                    default:
                }
                assertEquals(message, stdremoveNumber, testremoveNumber);
            }
        }

    }
}
