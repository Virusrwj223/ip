package mendel.discretetask;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public boolean getStatus() {
        return isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUnDone() {
        this.isDone = false;
    }

    public abstract String parseDetailsForDB();

    public abstract boolean isTargetDueDate(String formattedDate);

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(),this.description);
    }
}
