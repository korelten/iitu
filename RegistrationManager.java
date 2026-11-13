package registration;

import java.util.ArrayList;

// Класс менеджера: хранит ArrayList и выполняет операции с ним.
public class RegistrationManager {
    private final ArrayList<Registration> registrations = new ArrayList<>();
    private int nextId = 1; // Увеличиваем после добавления, даже если старый ID удален.

    // Проверяем данные и создаем регистрацию.
    public Registration addRegistration(String fullName, String email,
                                        String studentGroup, String eventTitle) {
        if (fullName == null || fullName.trim().length() < 3) {
            throw new IllegalArgumentException("Имя должно содержать минимум 3 символа.");
        }

        // У email должны быть символы до и после @.
        if (email == null) {
            throw new IllegalArgumentException("Email не должен быть пустым.");
        }
        String cleanEmail = email.trim();
        int atPosition = cleanEmail.indexOf('@');
        if (atPosition <= 0 || atPosition == cleanEmail.length() - 1) {
            throw new IllegalArgumentException("Email должен содержать @ с текстом до и после.");
        }

        if (studentGroup == null || studentGroup.trim().isEmpty()) {
            throw new IllegalArgumentException("Группа не должна быть пустой.");
        }

        // Допускаем только три мероприятия из задания.
        if (!"Web Design Workshop".equals(eventTitle)
                && !"JavaScript Quiz".equals(eventTitle)
                && !"IT Career Talk".equals(eventTitle)) {
            throw new IllegalArgumentException("Неизвестное мероприятие.");
        }

        Registration registration = new Registration(nextId,
                fullName.trim(), cleanEmail, studentGroup.trim(), eventTitle);
        registrations.add(registration);
        nextId++;
        return registration;
    }

    // Показываем все регистрации.
    public void showAllRegistrations() {
        if (registrations.isEmpty()) {
            System.out.println("Список регистраций пуст.");
            return;
        }

        // i = 0 — первый элемент; i < size() — до конца списка; i++ — следующий.
        for (int i = 0; i < registrations.size(); i++) {
            System.out.println(registrations.get(i));
        }
    }

    // Ищем регистрацию по ID. Если не нашли, возвращаем null.
    public Registration findRegistrationById(int id) {
        // i = 0 — первый элемент; i < size() — пока есть элементы; i++ — следующий.
        for (int i = 0; i < registrations.size(); i++) {
            Registration registration = registrations.get(i);
            if (registration.getId() == id) {
                return registration;
            }
        }
        return null;
    }

    // Удаляем запись по индексу, чтобы безопасно работать с ArrayList.
    public boolean deleteRegistrationById(int id) {
        // i = 0 — начало; i < size() — конец списка; i++ — следующий элемент.
        for (int i = 0; i < registrations.size(); i++) {
            if (registrations.get(i).getId() == id) {
                registrations.remove(i);
                return true;
            }
        }
        return false;
    }

    // Показываем только регистрации на выбранное мероприятие.
    public void showRegistrationsForEvent(String eventTitle) {
        boolean found = false;

        // i = 0 — первый элемент; i < size() — весь список; i++ — следующий.
        for (int i = 0; i < registrations.size(); i++) {
            Registration registration = registrations.get(i);
            if (registration.getEventTitle().equals(eventTitle)) {
                System.out.println(registration);
                found = true;
            }
        }

        if (!found) {
            System.out.println("На это мероприятие регистраций нет.");
        }
    }
}
