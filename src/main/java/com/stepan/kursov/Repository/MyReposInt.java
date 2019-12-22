package com.stepan.kursov.Repository;

import java.util.List;

public interface MyReposInt<T> {
    List<T> hardRequest(String request);
}
