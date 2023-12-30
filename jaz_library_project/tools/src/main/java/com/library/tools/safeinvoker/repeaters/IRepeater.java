package com.library.tools.safeinvoker.repeaters;

/**
 * Implementacja jest wykorzystywana w klasie SafeInvoker
 */
public interface IRepeater {

    /**
     * Metoda pobiera z rejestru konfiguracji IRepeaterExceptionRegistry
     * informacje o tym ile razy akcja ma zostać powtórzona
     * oraz o tym jaki odstęp czasu ma minąć pomiędzy kolejnymi wywołaniami akcji
     * @param exception wyjątek dla którego szukamy konfiguracji w rejestrze
     * @return this (zwraca samego siebie)
     */
    IRepeater For(Throwable exception);

    /**
     * podbija counter
     */
    void retry();

    /**
     * sprawdza czy maksymalna ilość wykonań akcji (ilość wzięta z konfiguracji) została już osiągnięta
     * @return true jeśli counter nie przekroczył granicy
     * @return false jeśli counter przekroczył granicę
     */
    boolean shouldRetry();

    /**
     * czeka zadaną ilość czasu ()
     * @return
     */
    IRepeater waiting();
}
