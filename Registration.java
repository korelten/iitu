package registration;

// Класс одной регистрации: хранит данные участника.
public class Registration {
    // private запрещает менять поля напрямую извне.
    private final int id;
    private final String fullName;
    private final String email;
    private final String studentGroup;
    private final String eventTitle;

    // Конструктор заполняет все поля при создании объекта.
    public Registration(int id, String fullName, String email,
                        String studentGroup, String eventTitle) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.studentGroup = studentGroup;
        this.eventTitle = eventTitle;
    }

    // Геттеры позволяют читать private-поля.
    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getStudentGroup() {
        return studentGroup;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    // Строка с информацией об участнике.
    @Override
    public String toString() {
        return id + " | " + fullName + " | " + email + " | "
                + studentGroup + " | " + eventTitle;
    }
}
