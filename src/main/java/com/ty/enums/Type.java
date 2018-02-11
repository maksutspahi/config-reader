package com.ty.enums;

public enum Type {
    STRING {
        @Override
        public Object getValue(String value) {
            return value;
        }
    },
    INTEGER {
        @Override
        public Object getValue(String value) {
            return Integer.valueOf(value);
        }
    },
    DOUBLE {
        @Override
        public Object getValue(String value) {
            return Double.valueOf(value);
        }
    },
    BOOLEAN {
        @Override
        public Object getValue(String value) {
            return Boolean.valueOf(value);
        }
    };

    public abstract Object getValue(String value);
}
