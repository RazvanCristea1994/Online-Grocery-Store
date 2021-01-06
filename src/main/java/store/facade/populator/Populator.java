package store.facade.populator;

public interface Populator<Source, Target> {

    void populate(Target target, Source source);
}
