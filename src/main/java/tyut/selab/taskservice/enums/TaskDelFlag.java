package tyut.selab.taskservice.enums;

public enum TaskDelFlag {
    DEL(1,"删除"),
    RETAIN(0,"保留");
    private  Integer status;
    private String msg;
    TaskDelFlag(Integer status, String msg) {
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