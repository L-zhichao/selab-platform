package tyut.selab.recruitservice.enums;

public enum RegistrationDelFlag {
    DEL(1,"删除"),
    RETAIN(0,"保留");
    private  Integer status;
    private String msg;
    RegistrationDelFlag(Integer status,String msg) {
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
