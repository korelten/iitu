package registration;

import java.util.Scanner;

// Главный класс: отвечает за меню и ввод данных с клавиатуры.
public class Main {

    // Один Scanner используется во всех методах Main.
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Создаем менеджер, который будет хранить регистрации.
        RegistrationManager manager = new RegistrationManager();
        boolean running = true;

        // Повторяем меню, пока пользователь не выберет 0.
        while (running) {
            printMenu();
            System.out.print("Выберите пункт: ");

            try {
                // nextLine() читает всю строку, parseInt() переводит ее в число.
                int choice = Integer.parseInt(scanner.nextLine().trim());

                switch (choice) {
                    case 1:
                        addRegistration(manager);
                        break;
                    case 2:
                        manager.showAllRegistrations();
                        break;
                    case 3:
                        findRegistration(manager);
                        break;
                    case 4:
                        deleteRegistration(manager);
                        break;
                    case 5:
                        showRegistrationsForEvent(manager);
                        break;
                    case 0:
                        running = false;
                        System.out.println("Программа завершена.");
                        break;
                    default:
                        System.out.println("Ошибка: выберите пункт от 0 до 5.");
                }
            } catch (NumberFormatException e) {
                // Если пользователь вместо номера ввел буквы.
                System.out.println("Ошибка: нужно ввести целое число.");
            }
        }
        scanner.close();
    }

    // Показываем доступные команды.
    private static void printMenu() {
        System.out.println("\n===== РЕГИСТРАЦИЯ НА МЕРОПРИЯТИЯ =====");
        System.out.println("1. Добавить регистрацию");
        System.out.println("2. Показать все регистрации");
        System.out.println("3. Найти регистрацию по ID");
        System.out.println("4. Удалить регистрацию по ID");
        System.out.println("5. Показать регистрации мероприятия");
        System.out.println("0. Выход");
    }

    // Запрашиваем данные и передаем их менеджеру.
    private static void addRegistration(RegistrationManager manager) {
        System.out.print("Полное имя: ");
        String fullName = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Группа: ");
        String studentGroup = scanner.nextLine();

        String eventTitle = selectEvent();
        // При неверном выборе отменяем добавление.
        if (eventTitle == null) {
            return;
        }

        try {
            Registration registration = manager.addRegistration(
                    fullName, email, studentGroup, eventTitle);
            System.out.println("Регистрация создана. ID: " + registration.getId());
        } catch (IllegalArgumentException e) {
            // Неверные данные не попадут в список.
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    // Возвращаем название события по введенному номеру.
    private static String selectEvent() {
        System.out.println("1. Web Design Workshop");
        System.out.println("2. JavaScript Quiz");
        System.out.println("3. IT Career Talk");
        System.out.print("Выберите мероприятие (1-3): ");

        try {
            int choice = Integer.parseInt(scanner.nextLine().trim());
            switch (choice) {
                case 1:
                    return "Web Design Workshop";
                case 2:
                    return "JavaScript Quiz";
                case 3:
                    return "IT Career Talk";
                default:
                    System.out.println("Ошибка: мероприятие должно быть от 1 до 3.");
                    return null;
            }
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: введите номер мероприятия цифрами.");
            return null;
        }
    }

    // Читаем ID. null означает, что пользователь ввел не число.
    private static Integer readId() {
        System.out.print("Введите ID: ");
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: ID должен быть целым числом.");
            return null;
        }
    }

    // Ищем участника по ID.
    private static void findRegistration(RegistrationManager manager) {
        Integer id = readId();
        if (id == null) {
            return;
        }

        Registration registration = manager.findRegistrationById(id);
        if (registration == null) {
            System.out.println("Регистрация не найдена.");
        } else {
            System.out.println(registration);
        }
    }

    // Удаляем участника по ID.
    private static void deleteRegistration(RegistrationManager manager) {
        Integer id = readId();
        if (id == null) {
            return;
        }

        if (manager.deleteRegistrationById(id)) {
            System.out.println("Регистрация удалена.");
        } else {
            System.out.println("Регистрация не найдена.");
        }
    }

    // Показываем участников одного мероприятия.
    private static void showRegistrationsForEvent(RegistrationManager manager) {
        String eventTitle = selectEvent();
        if (eventTitle == null) {
            return;
        }
        manager.showRegistrationsForEvent(eventTitle);
    }
}
