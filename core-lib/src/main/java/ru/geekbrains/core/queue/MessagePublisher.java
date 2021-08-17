package ru.geekbrains.core.queue;

public interface MessagePublisher {
    void publish(final String message);
}
