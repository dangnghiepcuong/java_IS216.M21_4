package Process;

public class Account
{
    // Thuộc tính tên đăng nhập
    private String Username;

    // Thuộc tính mật khẩu
    private String Password;

    /* Thuộc tính quyền của tài khoản (0: Bộ Y tế, 1: đơn vị tiêm chủng,
0: người dùng là công dân */
    private int Role;

    // Thuộc tính chỉ tình trạng của tài khoản (0: không hoạt động, 1: hoạt động)
    private int Status;

    public Account() {
    }

    public Account(String Username, String Password, int Role, int Status) {
        this.Username = Username;
        this.Password = Password;
        this.Role = Role;
        this.Status = Status;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public int getRole() {
        return Role;
    }

    public void setRole(int Role) {
        this.Role = Role;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }


}