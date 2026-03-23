package pen.model;

import pen.enums.PenType;
import pen.exception.InkEmptyException;
import pen.exception.PenNotStartedException;
import pen.mechanism.MechanismBehavior;

public abstract class Pen {
    private String inkColor;
    private double inkLevel;
    private boolean isStarted;
    // strategy pattern - swapping this out later should be painless
    private final MechanismBehavior mechanism;
    private final PenType penType;

    // TODO: might want to add ink level as a constructor param later
    protected Pen(String inkColor, MechanismBehavior mechanism, PenType penType) {
        this.inkColor = inkColor;
        this.mechanism = mechanism;
        this.penType = penType;
        this.inkLevel = 100.0;
        this.isStarted = false;
    }

    public void start() {
        if (isStarted) {
            System.out.println("Pen is already ready to write.");
            return;
        }
        mechanism.onStart();
        isStarted = true;
    }

    public void close() {
        if (!isStarted) {
            System.out.println("Pen is already closed.");
            return;
        }
        mechanism.onClose();
        isStarted = false;
    }

    public void write() {
        throw new UnsupportedOperationException("Use write(String text) to write something.");
    }

    public abstract void write(String text);

    public void refill(String color) {
        if (!color.equals(this.inkColor)) {
            System.out.println("Ink color changed from " + this.inkColor + " to " + color + ".");
            this.inkColor = color;
        }
        this.inkLevel = 100.0;
        System.out.println("Pen refilled. Ink level: 100.0");
    }

    protected void writeText(String text, double inkPerChar) {
        if (!isStarted) {
            throw new PenNotStartedException("Start the pen before writing.");
        }

        if (inkLevel <= 0) {
            throw new InkEmptyException("Pen is out of ink. Please refill.");
        }

        int canWrite = (int) (inkLevel / inkPerChar);
        if (canWrite < text.length()) {
            if (canWrite > 0) {
                System.out.println(text.substring(0, canWrite));
            }
            inkLevel = 0;
            throw new InkEmptyException("Ran out of ink mid-write. Partial text written.");
        }

        inkLevel -= text.length() * inkPerChar;
        if (inkLevel < 0) {
            inkLevel = 0;
        }
    }

    public String getInkColor() {
        return inkColor;
    }

    public double getInkLevel() {
        return inkLevel;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public PenType getPenType() {
        return penType;
    }
}
