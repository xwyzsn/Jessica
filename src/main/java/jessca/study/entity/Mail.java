package jessca.study.entity;

import lombok.Data;

@Data
public class Mail {
    private String from;
    private String replyTo;
    private String[] to;
    private String[] cc;
    private String[] bcc;
    private String sentDate;
    private String subject;
    private String text;
    private String[] filenames;
}
