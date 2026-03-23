package pen.model;

import pen.enums.PenType;
import pen.mechanism.MechanismBehavior;

public class GelPen extends Pen {
    private final double inkConsumptionRate = 1.5;

    public GelPen(String inkColor, MechanismBehavior mechanism) {
        super(inkColor, mechanism, PenType.GEL);
    }

    @Override
    public void write(String text) {
        System.out.println("[Gel] Writing: " + text);
        writeText(text, inkConsumptionRate);
    }

    @Override
    public void refill(String color) {
        System.out.println("Replacing gel ink cartridge...");
        super.refill(color);
    }
}
