package dev.alexisdev.validate;

public interface Validate {

    static <T> T notNull(T object, String message, Object... replacements) {
        if (object == null) {
            throw new IllegalArgumentException(String.format(message, replacements));
        }
        return object;
    }

    static <T> T notNull(T object) {
        return notNull(object, null);
    }

    public static void state(boolean condition, String message, Object... replacements) {
        if (!condition) {
            throw new IllegalStateException(String.format(message, replacements));
        }
    }

    static void state(boolean condition) {
        state(condition, null);
    }
}
