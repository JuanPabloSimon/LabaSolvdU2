package com.solvd.musichall.models.decorator;

public class MusicianDecorator implements IPerform {
    protected IPerform performer;

    public MusicianDecorator(IPerform performer) {
        this.performer = performer;
    }

    @Override
    public void perform() {
        this.performer.perform();
    }
}
