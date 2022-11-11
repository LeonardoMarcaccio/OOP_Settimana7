package it.unibo.mvc;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import it.unibo.mvc.api.DrawNumberController;
import it.unibo.mvc.api.DrawNumberView;
import it.unibo.mvc.controller.DrawNumberControllerImpl;
import it.unibo.mvc.model.DrawNumberImpl;

/**
 * Application entry-point.
 */
public final class LaunchApp {

    private LaunchApp() { }

    /**
     * Runs the application.
     *
     * @param args ignored
     * @throws ClassNotFoundException if the fetches class does not exist
     * @throws NoSuchMethodException if the 0-ary constructor do not exist
     * @throws InvocationTargetException if the constructor throws exceptions
     * @throws InstantiationException if the constructor throws exceptions
     * @throws IllegalAccessException in case of reflection issues
     * @throws IllegalArgumentException in case of reflection issues
     */
     public static void main(final String... args)
        throws
            ClassNotFoundException,
            NoSuchMethodException,
            InvocationTargetException,
            InstantiationException,
            IllegalAccessException {
        final var model = new DrawNumberImpl();
        final DrawNumberController app = new DrawNumberControllerImpl(model);

        // Sotto consiglio della tutor, ho copiato la soluzione in quanto una "non Hardcoded Solution" non può essere fatta
        for (final var viewType: List.of("Stdout", "Swing")) {
            // ottengo l'instanza della classe appena trovata
            final var clazz = Class.forName("it.unibo.mvc.view.DrawNumber" + viewType + "View");

            // Creo tre instanze della stessa classe appena trovata
            for (int i = 0; i < 3; i++) {
                final var newView = clazz.getConstructor().newInstance();

                // Se la nuova instanza appena creata può essere assegnata a DrawNumberView allora la aggiungo al controller, altrimenti ho preso una classe errata
                if (DrawNumberView.class.isAssignableFrom(newView.getClass())) {
                    app.addView((DrawNumberView) newView);
                } else {
                    throw new IllegalStateException(
                        newView.getClass() + " is not a subclass of " + DrawNumberView.class
                    );
                }
            }
        }
    }
}