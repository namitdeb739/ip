package c3po.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import c3po.exception.StorageLoadingException;
import c3po.exception.TaskNotFoundException;
import c3po.task.Deadline;
import c3po.task.Event;
import c3po.task.Task;
import c3po.task.TaskList;
import c3po.task.Todo;

/**
 * Represents a storage to store and load tasks.
 */
public class Storage {
    private File file;

    /**
     * Constructs a storage with the specified file path.
     *
     * @param filePath The file path to store the tasks.
     */
    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    /**
     * Loads the tasks from the file.
     *
     * @return The tasks loaded from the file.
     * @throws StorageLoadingException If there is an error loading the tasks.
     */
    public ArrayList<Task> loadTasks() throws StorageLoadingException {
        ArrayList<Task> tasks = new ArrayList<Task>();
        try {
            Scanner scanner = new Scanner(this.file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] taskField = line.split("\\|");

                Task task = getTask(taskField);

                boolean isDone = taskField[1].strip().equals("1");
                if (isDone) {
                    task.mark();
                }

                tasks.add(task);
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            try {
                this.file.createNewFile();
            } catch (java.io.IOException ioException) {
                throw new StorageLoadingException();
            }
        } catch (IllegalArgumentException e) {
            throw new StorageLoadingException();
        }

        return tasks;
    }

    /**
     * Saves the tasks to the file.
     *
     * @param tasks The tasks to save.
     */
    public void saveTasks(TaskList tasks) {
        try {
            if (!this.file.exists()) {
                this.file.createNewFile();
            } else {
                this.file.delete();
                this.file.createNewFile();
            }

            java.io.FileWriter writer = new java.io.FileWriter(this.file);

            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                writer.write(task.toFileString() + "\n");
            }

            writer.close();
        } catch (java.io.IOException e) {
            System.out.println(
                    "I'm afraid I cannot do that, sir. It appears my security clearance is insufficient.");
        } catch (TaskNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Hooray! I have saved your tasks, sir.");
        }

        System.out.println(tasks);
    }


    private Task getTask(String[] taskField) {
        String taskType = taskField[0].strip();
        String taskDescription = taskField[2].strip();

        // Uninitialised but safe as either it will be
        // initialised in the switch statement or an
        // exception will be thrown
        Task task;
        switch (taskType) {
        case "T":
            task = new Todo(taskDescription);
            break;
        case "D":
            if (taskField.length < 4) {
                throw new IllegalArgumentException();
            }
            LocalDateTime by = LocalDateTime.parse(taskField[3].strip());
            task = new Deadline(taskDescription, by);
            break;
        case "E":
            if (taskField.length < 5) {
                throw new IllegalArgumentException();
            }
            LocalDateTime from = LocalDateTime.parse(taskField[3].strip());
            LocalDateTime to = LocalDateTime.parse(taskField[4].strip());
            task = new Event(taskDescription, from, to);
            break;
        default:
            throw new IllegalArgumentException();
        }
        return task;
    }


}
