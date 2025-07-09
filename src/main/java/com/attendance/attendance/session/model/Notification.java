public class Notification {
    private Long sessionId;
    private String code;
    private Instant timestamp;

    //constructors

    public Notification() {
    }

    public Notification(Long sessionId, String code, Instant timestamp) {
        this.sessionId = sessionId;
        this.code = code;
        this.timestamp = timestamp;

    //setters and getters

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}