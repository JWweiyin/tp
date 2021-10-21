package seedu.duke.commands;

public abstract class Command {
    protected String name;
    protected String arguments;

    public Command(String name, String arguments) {
        this.name = name;
        this.arguments = arguments;
    }

    public Command(String name) {
        this.name = name;
        this.arguments = null;
    }

    public abstract CommandResult execute();

    @Override
    public String toString() {
        return this.name;
    }
}
