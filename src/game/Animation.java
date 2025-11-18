package game;

public class Animation {
    private float start;
    private float end;
    private float animationTimeSeconds;
    private EasingFunction easingFunction;
    private float t;
    private float signage = 1.0f;

    public Animation(float start, float end, float animationTimeSeconds, EaseFunction easeFunction) {
        this.start = start;
        this.end = end;
        this.animationTimeSeconds = animationTimeSeconds;
        this.easingFunction = easeFunction.getEasingFunction();
        this.t = 0.0f;
    }

    public void update(float secondsPerFrame) {
        float delta = secondsPerFrame / animationTimeSeconds;
        t += delta * signage;
        float decimalPart = t % 1.0f;
        int sign = (int) t % 2;
        if (t > 0 && sign == 1) {
            t = 1.0f - decimalPart;
            signage = -1.0f;
        } else if (t < 0) {
            t = decimalPart;
            signage = 1.0f;
        }
    }

    public float sample() {
        float interpolation = easingFunction.ease(start, end, t);
        return start * interpolation + end * (1.0f - interpolation);
    }
}
