package pen;

import pen.enums.MechanismType;
import pen.enums.PenType;
import pen.exception.InkEmptyException;
import pen.factory.PenFactory;
import pen.model.BallpointPen;
import pen.model.Pen;

public class Main {
    public static void main(String[] args) {
        System.out.println("========== PEN DESIGN SYSTEM DEMO ==========");

        System.out.println("\n1) Ballpoint + CAP");
        Pen ballpoint = PenFactory.createPen(PenType.BALLPOINT, "blue", MechanismType.CAP);
        ballpoint.start();
        ballpoint.write("Hello World");
        ballpoint.close();
        System.out.println("Ink level after writing: " + ballpoint.getInkLevel());

        System.out.println("\n2) Gel + CLICK");
        Pen gel = PenFactory.createPen(PenType.GEL, "black", MechanismType.CLICK);
        gel.start();
        gel.write("Design Patterns are fun");
        gel.close();
        System.out.println("Ink level after writing: " + gel.getInkLevel());

        System.out.println("\n3) Fountain + CAP");
        Pen fountain = PenFactory.createPen(PenType.FOUNTAIN, "red", MechanismType.CAP);
        fountain.start();
        fountain.write("The quick brown fox");
        fountain.close();
        System.out.println("Ink level after writing: " + fountain.getInkLevel());

        System.out.println("\n4) Calling write() with no arguments");
        try {
            ballpoint.write();
        } catch (UnsupportedOperationException ex) {
            System.out.println("Caught exception: " + ex.getMessage());
        }

        System.out.println("\n5) Trying to write without starting");
        Pen notStarted = PenFactory.createPen(PenType.GEL, "purple", MechanismType.CLICK);
        try {
            notStarted.write("This should fail");
        } catch (RuntimeException ex) {
            System.out.println("Caught exception: " + ex.getMessage());
        }

        System.out.println("\n6) Draining ink and testing mid-write failure");
        BallpointPen drained = (BallpointPen) PenFactory.createPen(PenType.BALLPOINT, "black", MechanismType.CLICK);
        drained.start();
        String chunk = "abcdefghij";
        for (int i = 0; i < 9; i++) {
            drained.write(chunk);
        }
        System.out.println("Ink after pre-drain: " + drained.getInkLevel());

        try {
            drained.write("this sentence is intentionally long to force a partial write event");
        } catch (InkEmptyException ex) {
            System.out.println("Caught exception: " + ex.getMessage());
            System.out.println("Ink level now: " + drained.getInkLevel());
        }
        drained.close();

        System.out.println("\n7) Refill drained ballpoint with same color, then different color");
        drained.refill("black");
        System.out.println("After same-color refill -> color: " + drained.getInkColor() + ", ink: " + drained.getInkLevel());
        drained.refill("green");
        System.out.println("After color-change refill -> color: " + drained.getInkColor() + ", ink: " + drained.getInkLevel());

        System.out.println("\n8) Fountain refill behavior and post-refill write");
        Pen fountain2 = PenFactory.createPen(PenType.FOUNTAIN, "red", MechanismType.CAP);
        fountain2.start();
        fountain2.write("Before refill run");
        fountain2.close();
        fountain2.refill("green");

        fountain2.start();
        fountain2.write("After refill, writing with updated ink color state");
        fountain2.close();
        System.out.println("Fountain final color: " + fountain2.getInkColor());
        System.out.println("Fountain final ink level: " + fountain2.getInkLevel());

        System.out.println("\n========== DEMO COMPLETE ==========");
    }
}
