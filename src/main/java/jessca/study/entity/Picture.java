package jessca.study.entity;

import java.util.Arrays;

public class Picture {

    String[] path;
    String group;
    String description;
    String uploadtime;

    @Override
    public String toString() {
        return "Picture{" +
                "path=" + Arrays.toString(path) +
                ", group='" + group + '\'' +
                ", description='" + description + '\'' +
                ", uploadtime='" + uploadtime + '\'' +
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

    public String getUploadtime() {
        return uploadtime;
    }

    public void setUploadtime(String uploadtime) {
        this.uploadtime = uploadtime;
    }

    public Picture(String[] path, String group, String description, String uploadtime) {
        this.path = path;
        this.group = group;
        this.description = description;
        this.uploadtime = uploadtime;
    }
}