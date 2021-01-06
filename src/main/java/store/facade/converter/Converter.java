package store.facade.converter;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public interface Converter<Source, Target> {

    Target convert(Source source);

    default List<Target> convertAll(List<Source> source){
        return source.stream()
                .map(this::convert)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
