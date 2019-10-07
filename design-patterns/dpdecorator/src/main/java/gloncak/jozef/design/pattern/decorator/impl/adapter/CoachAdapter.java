package gloncak.jozef.design.pattern.decorator.impl.adapter;

import gloncak.jozef.design.pattern.decorator.api.Coach;

public class CoachAdapter implements Coach {

    private Coach coach;

    public CoachAdapter(Coach coach) {
        this.coach = coach;
    }

    @Override
    public boolean isMoving() {
        return this.coach.isMoving();
    }

    @Override
    public void pullBreak() {
        this.coach.pullBreak();
    }

    @Override
    public void releaseOfBreak() {
        this.coach.releaseOfBreak();
    }
}
