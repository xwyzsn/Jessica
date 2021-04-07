package jessca.study;

import java.util.Arrays;

public class Picture {

    String[] path;
    String group;
    String description;

    @Override
    public String toString() {
        return "Picture{" +
                "path=" + Arrays.toString(path) +
                ", group='" + group + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public String[] getPath() {
        return path;
    }

    public void setPath(String[] path) {
        this.path = path;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Picture(String[] path, String group, String description) {
        this.path = path;
        this.group = group;
        this.description = description;
    }
}