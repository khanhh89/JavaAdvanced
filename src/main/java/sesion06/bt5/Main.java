package sesion06.bt5;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        TicketPool poolA = new TicketPool("A", 5);
        TicketPool poolB = new TicketPool("B", 5);
        TicketPool poolC = new TicketPool("C", 5);
        List<TicketPool> allPools = Arrays.asList(poolA, poolB, poolC);

        Thread timeoutThread = new Thread(new TimeoutManager(allPools));
        timeoutThread.setDaemon(true);
        timeoutThread.start();

        Thread q1 = new Thread(new BookingCounter("Quầy 1", allPools, true));
        Thread q2 = new Thread(new BookingCounter("Quầy 2", allPools, false));
        Thread q3 = new Thread(new BookingCounter("Quầy 3", allPools, true));
        Thread q4 = new Thread(new BookingCounter("Quầy 4", allPools, false));
        Thread q5 = new Thread(new BookingCounter("Quầy 5", allPools, true));

        q1.start(); q2.start(); q3.start(); q4.start(); q5.start();
    }
}
