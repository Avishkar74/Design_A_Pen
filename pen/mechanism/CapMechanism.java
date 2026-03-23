package pen.mechanism;

public class CapMechanism implements MechanismBehavior {
    @Override
    public void onStart() {
        System.out.println("Pen has been uncapped.");
    }

    @Override
    public void onClose() {
        System.out.println("Pen has been capped.");
    }
}
