package pen.mechanism;

public class ClickMechanism implements MechanismBehavior {
    @Override
    public void onStart() {
        System.out.println("Click to start.");
    }

    @Override
    public void onClose() {
        System.out.println("Click to close.");
    }
}
