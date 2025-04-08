import java.io.*;
import java.util.*;

public class TaskManager {
    // Data structure: ArrayList to store tasks
    private List<String> tasks;
    // File to store tasks
    private final String TASK_FILE = "tasks.txt";

    // Constructor: initialize tasks and load from file
    public TaskManager(){
        tasks = new ArrayList<>();
        loadTasks();
    }

    // Adds a new task
    public void addTask(String task) {
        tasks.add(task);
        System.out.println("\nTask added.");
    }

    // Removes task by index (user enters 1-based index)
    public void removeTask(int index){
        if(index < 0 || index >= tasks.size()){
            System.out.println("\nInvalid task number.");
        } else {
            tasks.remove(index);
            System.out.println("\nTask removed.");
        }
    }

    // Lists all tasks
    public void listTasks(){
        if(tasks.isEmpty()){
            System.out.println("\nNo tasks available.");
        } else {
            System.out.println("\n--- Task List ---");
            for(int i = 0; i < tasks.size(); i++){
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    // Saves tasks to file (stretch challenge)
    public void saveTasks(){
        try (PrintWriter writer = new PrintWriter(new FileWriter(TASK_FILE))){
            for(String task : tasks){
                writer.println(task);
            }
        } catch(IOException e) {
            System.out.println("\nError saving tasks: " + e.getMessage());
        }
    }

    // Loads tasks from file (if it exists)
    public void loadTasks(){
        File file = new File(TASK_FILE);
        if (!file.exists()){
            // No existing file, so nothing to load
            return;
        }
        try (Scanner fileScanner = new Scanner(file)){
            while(fileScanner.hasNextLine()){
                String task = fileScanner.nextLine();
                tasks.add(task);
            }
        } catch(IOException e) {
            System.out.println("\nError loading tasks: " + e.getMessage());
        }
    }

    // Main loop for the Task Manager program
    public void start(){
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while(running){
            System.out.println("\n=== Task Manager Menu ===");
            System.out.println("1. List tasks");
            System.out.println("2. Add task");
            System.out.println("3. Remove task");
            System.out.println("4. Save and Exit");
            System.out.print("\nChoose an option: ");

            // Reading user option using nextInt and a newline
            int option = scanner.nextInt();
            scanner.nextLine(); // consume newline

            // Using conditionals to execute the chosen option
            switch(option){
                case 1:
                    listTasks();
                    break;
                case 2:
                    System.out.print("\nEnter task description: ");
                    String task = scanner.nextLine();
                    addTask(task);
                    break;
                case 3:
                    listTasks(); // Show current tasks before removing
                    System.out.print("\nEnter the task number to remove: ");
                    int index = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    removeTask(index - 1); // Adjust for 0-based index
                    break;
                case 4:
                    saveTasks();
                    System.out.println("\nTasks saved. Exiting program.");
                    running = false;
                    break;
                default:
                    System.out.println("\nInvalid option. Please try again.");
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        TaskManager manager = new TaskManager();
        manager.start();
    }
}
