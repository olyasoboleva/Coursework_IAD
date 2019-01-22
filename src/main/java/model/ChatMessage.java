package model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;

@Data
@NoArgsConstructor
public class ChatMessage {
    private String content;
    private String sender;
    private long time;
}
