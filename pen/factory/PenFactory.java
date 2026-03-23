package pen.factory;

import pen.enums.MechanismType;
import pen.enums.PenType;
import pen.mechanism.CapMechanism;
import pen.mechanism.ClickMechanism;
import pen.mechanism.MechanismBehavior;
import pen.model.BallpointPen;
import pen.model.FountainPen;
import pen.model.GelPen;
import pen.model.Pen;

public class PenFactory {
    public static Pen createPen(PenType type, String inkColor, MechanismType mechanismType) {
        MechanismBehavior mechanism;

        switch (mechanismType) {
            case CAP:
                mechanism = new CapMechanism();
                break;
            case CLICK:
                mechanism = new ClickMechanism();
                break;
            default:
                throw new IllegalArgumentException("Unknown mechanism type: " + mechanismType);
        }

        switch (type) {
            case BALLPOINT:
                return new BallpointPen(inkColor, mechanism);
            case GEL:
                return new GelPen(inkColor, mechanism);
            case FOUNTAIN:
                return new FountainPen(inkColor, mechanism);
            default:
                throw new IllegalArgumentException("Unknown pen type: " + type);
        }
    }
}
