package com.library.tools.safeinvoker.repeaters;

/**
 * implementacja jest singletonem, który przetrzymuje informacje
 * o tym ile razy dana akcja ma zostać wykonana i w jakim odstępie czasu.
 * Z instancji  klasy RepeaterExceptionRegistry obiekty klasy Repeater będą czerpały konfigurację
 */
public interface IRepeaterExceptionRegistry {

    /**
     * Do mapy gdzie kluczem jest nazwa klasy wyjątku, a wartością obiekt RegistryEntry,
     * zapisuje informacje o tym ile razu i w jakim odstepie czasu akcja ma zostać powtórzona dla konkretnego typu wyjątku
     * @param exceptionClass klasa wyjątku
     * @param retries ilość powtórzeń
     * @param delay interwał czasowy
     */
    void add(Class<? extends Throwable> exceptionClass, int retries, long delay);

    /**
     * z mapy zwraca obiekt RegistryEntry dla konkretnego wyjątku
     * @param exception wyjątek który wyskoczył podczas wykonywania akcji
     * @return zwraca obiekt w którym jest zapisana nazwa wyjątku, ilość powtórzeń danej operacji, oraz interwał czasowy
     */
     RegistryEntry EntryFor(Throwable exception);



    public record RegistryEntry(String exceptionName, long delay, int retriesCount) {
        public static RegistryEntry Default(Throwable ex) {
            return new RegistryEntry(ex.getClass().getName(), 0, 0);
        }
    }
}
