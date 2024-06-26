import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

public class NotebookStore {

    private Set<Notebook> notebooks = new HashSet<>();

    public void addNotebook(Notebook notebook) {
        notebooks.add(notebook);
    }

    public Set<Notebook> filterNotebooks(Map<String, Object> criteria) {
        Set<Notebook> filteredNotebooks = new HashSet<>(notebooks);
        for (Map.Entry<String, Object> entry : criteria.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            filteredNotebooks.removeIf(notebook -> !matchesCriteria(notebook, key, value));
        }
        return filteredNotebooks;
    }

    private boolean matchesCriteria(Notebook notebook, String key, Object value) {
        switch (key) {
            case "ram":
                return notebook.getRam() >= (int) value;
            case "hdd":
                return notebook.getHdd() >= (int) value;
            case "os":
                return notebook.getOs().equalsIgnoreCase((String) value);
            case "color":
                return notebook.getColor().equalsIgnoreCase((String) value);
            default:
                return false;
        }
    }

    private Set<Integer> getAvailableRams() {
        Set<Integer> rams = new HashSet<>();
        for (Notebook notebook : notebooks) {
            rams.add(notebook.getRam());
        }
        return rams;
    }

    private Set<Integer> getAvailableHdds() {
        Set<Integer> hdds = new HashSet<>();
        for (Notebook notebook : notebooks) {
            hdds.add(notebook.getHdd());
        }
        return hdds;
    }

    private Set<String> getAvailableOses() {
        Set<String> oses = new HashSet<>();
        for (Notebook notebook : notebooks) {
            oses.add(notebook.getOs());
        }
        return oses;
    }

    private Set<String> getAvailableColors() {
        Set<String> colors = new HashSet<>();
        for (Notebook notebook : notebooks) {
            colors.add(notebook.getColor());
        }
        return colors;
    }

    public static void main(String[] args) {
        NotebookStore store = new NotebookStore();
        store.addNotebook(new Notebook("Dell", 16, 512, "Windows", "Black"));
        store.addNotebook(new Notebook("HP", 8, 256, "Linux", "Silver"));
        store.addNotebook(new Notebook("Apple", 8, 512, "macOS", "Grey"));
        store.addNotebook(new Notebook("Asus", 16, 1024, "Windows", "Blue"));

        try (Scanner scanner = new Scanner(System.in)) {
            Map<String, Object> criteria = new HashMap<>();

            while (true) {
                System.out.println("Выберите параметры для поиска ноутбука и введите значения для поиска:");

                while (true) {
                    System.out.println("1 - ОЗУ\n2 - Объем ЖД\n3 - Операционная система\n4 - Цвет\nQ - Выйти из системы");
                    String criterion = scanner.nextLine();

                    if (criterion.equalsIgnoreCase("Q")) {
                        System.out.println("Выход из системы.");
                        return;
                    }

                    switch (criterion) {
                        case "1":
                            System.out.println("Доступные варианты ОЗУ: " + store.getAvailableRams());
                            System.out.println("Введите минимальное значение ОЗУ (в ГБ):");
                            int ram = scanner.nextInt();
                            scanner.nextLine(); 
                            criteria.put("ram", ram);
                            break;
                        case "2":
                            System.out.println("Доступные варианты Объема ЖД: " + store.getAvailableHdds());
                            System.out.println("Введите минимальный объем ЖД (в ГБ):");
                            int hdd = scanner.nextInt();
                            scanner.nextLine(); // Consume newline
                            criteria.put("hdd", hdd);
                            break;
                        case "3":
                            System.out.println("Доступные варианты Операционных систем: " + store.getAvailableOses());
                            System.out.println("Введите операционную систему:");
                            String os = scanner.nextLine();
                            criteria.put("os", os);
                            break;
                        case "4":
                            System.out.println("Доступные варианты Цветов: " + store.getAvailableColors());
                            System.out.println("Введите цвет ноутбука:");
                            String color = scanner.nextLine();
                            criteria.put("color", color);
                            break;
                        default:
                            System.out.println("Некорректный параметр поиска.");
                            break;
                    }

                    System.out.println("Хотите ли вы ввести еще один дополнительный параметр для поиска?\n1 - Да\n2 - Нет");
                    int moreCriteria = scanner.nextInt();
                    scanner.nextLine(); 

                    if (moreCriteria == 2) {
                        break;
                    }
                }

                Set<Notebook> filteredNotebooks = store.filterNotebooks(criteria);
                if (filteredNotebooks.isEmpty()) {
                    System.out.println("По указанным Вами параметрам поиска в базе ноутбуков отсутствуют соответствующие им.");
                } else {
                    System.out.println("Ноутбуки, соответствующие параметрам поиска:");
                    for (Notebook notebook : filteredNotebooks) {
                        System.out.println(notebook);
                    }
                }

                System.out.println("Хотите выполнить новый поиск? (Y/N)");
                String newSearch = scanner.nextLine();
                if (newSearch.equalsIgnoreCase("N")) {
                    break;
                } else {
                    criteria.clear(); 
                }
            }
        }
    }
}
