package view;
import javax.swing.*;
import java.awt.*;
import java.util.Stack;

/**
 * Главный содержатель всех панелей
 */

public class MainFrame {

    private static JFrame instance;
    public static final int WIDTH = 800;
    private static final int HEIGHT = 650;
    public static int userId;
    public static boolean isTeacher;

    /**
     * С помощью стека храним память о предыдущих панелях, чтобы вернуться назад в случае необходимости
     */

    private static final Stack<Container> memory = new Stack<>();

    private MainFrame() {
    }

    public static JFrame getInstance() {
        if (instance == null) {
            instance = new JFrame("Department of Automation and Computer Engineering");
            instance.setSize(WIDTH, HEIGHT);
            instance.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            TeacherGridView gridView = new TeacherGridView();
            instance.setContentPane(gridView.getPanel());
            ImageIcon icon =
                    new ImageIcon("C:\\Users\\Мама\\IdeaProjects\\coursework\\gui\\src\\main\\resources\\nstu.png");
            instance.setIconImage(icon.getImage());
            instance.setResizable(false);
            instance.setVisible(true);
            instance.setLocationRelativeTo(null);
        }
        return instance;
    }

    public static void replacePanel(JPanel jPanel) { // Сохранение первоначальной сетки в память
        if (instance.getContentPane() != null) {
            memory.push(instance.getContentPane());
        }

        instance.setContentPane(jPanel);
        instance.revalidate();
    }

    public static void previousPanel() { // Возвращение к предыдущей панели
        Container pop = memory.pop();
        instance.setContentPane(pop);
        instance.revalidate();
    }
}
