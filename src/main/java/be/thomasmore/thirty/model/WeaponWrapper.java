package be.thomasmore.thirty.model;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.type.IntegerType;
import org.hibernate.type.Type;
import org.hibernate.usertype.CompositeUserType;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Objects;

public class WeaponWrapper implements CompositeUserType {
    @Override
    public String[] getPropertyNames() {
        return new String[] { "type", "range", "ammo" };
    }

    @Override
    public Type[] getPropertyTypes() {
        return new Type[] { IntegerType.INSTANCE, IntegerType.INSTANCE, IntegerType.INSTANCE };
    }

    @Override
    public Object getPropertyValue(Object component, int property) throws HibernateException {
        Weapon weapon = (Weapon) component;

        switch (property) {
            case 0:
                return weapon.getType();
            case 1:
                return weapon.getRange();
            case 2:
                return weapon.getAmmo();
        }
        throw new IllegalArgumentException(property + " is an invalid property index for class type "
                + component.getClass().getName());
    }

    @Override
    public void setPropertyValue(Object o, int i, Object o1) throws HibernateException {

    }

    @Override
    public Class returnedClass() {
        return Weapon.class;
    }

    @Override
    public boolean equals(Object o, Object o1) throws HibernateException {
        return o.equals(o1);
    }

    @Override
    public int hashCode(Object o) throws HibernateException {
        return o.hashCode();
    }

    @Override
    public Object nullSafeGet(ResultSet resultSet, String[] strings, SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException, SQLException {
        if (resultSet.wasNull())
            return null;
        int weaponType = resultSet.getInt(strings[0]);
        int range = resultSet.getInt(strings[1]);
        int ammo = resultSet.getInt(strings[2]);
        Weapon weapon = new Weapon(weaponType, range, ammo);
        return weapon;
    }

    @Override
    public void nullSafeSet(PreparedStatement preparedStatement, Object o, int index, SharedSessionContractImplementor sharedSessionContractImplementor) throws HibernateException, SQLException {
        if (Objects.isNull(o)) {
            preparedStatement.setNull(index, Types.INTEGER);
            preparedStatement.setNull(index + 1, Types.INTEGER);
            preparedStatement.setNull(index + 2, Types.INTEGER);
        } else {
            Weapon weapon = (Weapon) o;
            preparedStatement.setInt(index,weapon.getType().ordinal());
            preparedStatement.setInt(index+1,weapon.getRange());
            preparedStatement.setInt(index+2,weapon.getAmmo());
        }
    }

    @Override
    public Object deepCopy(Object o) throws HibernateException {
        return null;
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Serializable disassemble(Object o, SharedSessionContractImplementor sharedSessionContractImplementor) throws HibernateException {
        return (Serializable)o;
    }

    @Override
    public Object assemble(Serializable serializable, SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        return o;
    }

    @Override
    public Object replace(Object o, Object o1, SharedSessionContractImplementor sharedSessionContractImplementor, Object o2) throws HibernateException {
        return o2;
    }
}
