package pen.model;

import pen.enums.PenType;
import pen.mechanism.MechanismBehavior;

public class BallpointPen extends Pen {
    private final double inkConsumptionRate = 1.0;

    public BallpointPen(String inkColor, MechanismBehavior mechanism) {
        super(inkColor, mechanism, PenType.BALLPOINT);
    }

    @Override
    public void write(String text) {
        System.out.println("[Ballpoint] Writing: " + text);
        writeText(text, inkConsumptionRate);
    }

    @Override
    public void refill(String color) {
        System.out.println("Replacing ballpoint cartridge...");
        super.refill(color);
    }
}
