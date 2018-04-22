package PresentationLayer;

public class Role {
    private int id;
    private String _role;

    public Role(String _role, int _id) {
        this._role = _role;
        this._id = _id;
    }

    private int _id;

    public String get_role() {
        return _role;
    }

    public void set_role(String _role) {
        this._role = _role;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }
}
