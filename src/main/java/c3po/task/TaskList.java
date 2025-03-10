package c3po.task;

import java.util.ArrayList;
import java.util.stream.Stream;

import c3po.exception.TaskNotFoundException;

/**
 * Represents a list of tasks. The task list can manage tasks, including marking tasks as done,
 * deleting tasks, and adding tasks.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Constructs a task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Constructs a task list with the specified tasks.
     *
     * @param tasks The tasks to add to the task list.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The number of tasks in the task list.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Returns a stream of tasks.
     *
     * @return A stream of tasks.
     */
    public Stream<Task> stream() {
        return this.tasks.stream();
    }

    /**
     * Marks a task as done.
     *
     * @param index The index of the task to mark.
     * @return The marked task.
     * @throws TaskNotFoundException If the task number is invalid.
     */
    public Task mark(int index) throws TaskNotFoundException {
        if (index >= this.tasks.size() || index < 0) {
            throw new TaskNotFoundException(index);
        }
        Task task = this.tasks.get(index);
        task.mark();
        return task;
    }

    /**
     * Unmarks a task as done.
     *
     * @param index The index of the task to unmark.
     * @return The unmarked task.
     * @throws TaskNotFoundException If the task number is invalid.
     */
    public Task unmark(int index) throws TaskNotFoundException {
        if (index >= this.tasks.size() || index < 0) {
            throw new TaskNotFoundException(index);
        }
        Task task = this.tasks.get(index);
        task.unmark();
        return task;
    }

    /**
     * Returns the task with the specified task number.
     *
     * @param index The index of the task to get.
     * @return The task with the specified task number.
     * @throws TaskNotFoundException If the task number is invalid.
     */
    public Task get(int index) throws TaskNotFoundException {
        if (index >= this.tasks.size() || index < 0) {
            throw new TaskNotFoundException(index);
        }
        return this.tasks.get(index);
    }

    /**
     * Deletes a task.
     *
     * @param index The index of the task to delete.
     * @return The deleted task.
     * @throws TaskNotFoundException If the task number is invalid.
     */
    public Task delete(int index) throws TaskNotFoundException {
        if (index >= this.tasks.size() || index < 0) {
            throw new TaskNotFoundException(index);
        }
        return this.tasks.remove(index);
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to add.
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Returns the string representation of the task list.
     *
     * @return The string representation of the task list.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        if (sb.length() > 0 && sb.charAt(sb.length() - 1) == '\n') {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    /**
     * Finds tasks with the specified keyword.
     *
     * @param keywords The keyword to search for.
     * @return The tasks with the specified keyword.
     */
    public TaskList find(String... keywords) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        tasks.stream().filter(task -> task.descriptionContainsAll(keywords))
                .forEach(foundTasks::add);
        return new TaskList(foundTasks);
    }

    /**
     * Returns the index of the specified task.
     *
     * @param task The task to find.
     * @return The index of the specified task.
     */
    public int indexOf(Task task) {
        return this.tasks.indexOf(task);
    }
}
