package PresentationLayer;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return id == role.id &&
                _id == role._id &&
                Objects.equals(_role, role._role);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, _role, _id);
    }
}
