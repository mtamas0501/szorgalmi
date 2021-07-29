package hu.ulyssys.java.course.szorgalmi.infos;

import java.util.List;

public interface CoreService<T> {

    List<T> getAll();

    void add(T example);

    void remove(T example);

    void update(T example);
}
