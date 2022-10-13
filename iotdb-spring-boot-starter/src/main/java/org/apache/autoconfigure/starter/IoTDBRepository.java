package org.apache.autoconfigure.starter;

import java.util.Optional;

public interface IoTDBRepository<T> {

    Optional findById(Object id) ;
}
