package game;

public enum EaseFunction {
    CONSTANT_START(
            ((t) -> 0.0f)
    ),

    LINEAR(
            ((t) -> t)
    ),

    IN_OUT_QUAD(
            (t) -> (float) (t < 0.5 ? 2 * t * t : 1 - Math.pow(-2 * t + 2, 2) / 2)
    ),
    IN_OUT_BOUNCE(
            new EasingFunction() {
                @Override
                public float ease(float t) {
                    return t < 0.5
                            ? (1 - easeOutBounce(1 - 2 * t)) / 2
                            : (1 + easeOutBounce(2 * t - 1)) / 2;
                }
            }
    );

    private EasingFunction easingFunction;

    EaseFunction(EasingFunction easingFunction) {
        this.easingFunction = easingFunction;
    }

    public EasingFunction getEasingFunction() {
        return easingFunction;
    }

    private static float easeOutBounce(float t) {
        final float n1 = 7.5625f;
        final float d1 = 2.75f;

        if (t < 1 / d1) {
            return n1 * t * t;
        } else if (t < 2 / d1) {
            return n1 * (t -= 1.5f / d1) * t + 0.75f;
        } else if (t < 2.5 / d1) {
            return n1 * (t -= 2.25f / d1) * t + 0.9375f;
        } else {
            return n1 * (t -= 2.625f / d1) * t + 0.984375f;
        }
    }
}
