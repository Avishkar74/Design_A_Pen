package pen.model;

import pen.enums.PenType;
import pen.mechanism.MechanismBehavior;

public class FountainPen extends Pen {
    private final double inkConsumptionRate = 0.8;

    public FountainPen(String inkColor, MechanismBehavior mechanism) {
        super(inkColor, mechanism, PenType.FOUNTAIN);
    }

    @Override
    public void write(String text) {
        System.out.println("This is a fountain pen writing: " + text);
        writeText(text, inkConsumptionRate);
    }

    @Override
    public void refill(String color) {
        System.out.println("Filling ink reservoir from bottle...");
        super.refill(color);
    }
}
