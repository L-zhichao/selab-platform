package tyut.selab.taskservice.enums;

public enum TaskStatus {
    ENABLE(1,"启用"),
    SHUT(0,"禁用");
    private Integer status;
    private String msg;

    TaskStatus(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}