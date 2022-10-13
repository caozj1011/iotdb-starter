package org.apache.autoconfigure.starter;

import java.util.List;

public interface SimpleRepository<T> extends IoTDBRepository<T>{
    void save(T t);
    void saveAll(List<T> tList);
    List<T> selectAll(String sql);
}
