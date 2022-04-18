package com.example.testdistancecalculator.models;

public enum Method {
    CROWFIGHT("По прямой"), MATR("По матрице расстояний"), ALL("Все");

    private final String nameMethod;

    Method(String nameMethod) {
        this.nameMethod = nameMethod;
    }

    public String getNameMethod() {
        return nameMethod;
    }

    public Method getEnumsMethod(String nameMethod) {
        for (Method element : Method.values()) {
            if (element.getNameMethod().equals(nameMethod)) {
                return element;
            }
        }
        return null;
    }
}
