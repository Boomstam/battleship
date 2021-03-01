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
    public Object getPropertyValue(Object o, int i) throws HibernateException {
        return null;
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
        int weaponType = resultSet.getInt(strings[0]);

        if (resultSet.wasNull())
            return null;

        int range = resultSet.getInt(strings[1]);
        int ammo = resultSet.getInt(strings[2]);
        Weapon weapon = new Weapon(weaponType, range, ammo);

        return weapon;
    }

    @Override
    public void nullSafeSet(PreparedStatement preparedStatement, Object o, int i, SharedSessionContractImplementor sharedSessionContractImplementor) throws HibernateException, SQLException {

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
        return null;
    }

    @Override
    public Object assemble(Serializable serializable, SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        return null;
    }

    @Override
    public Object replace(Object o, Object o1, SharedSessionContractImplementor sharedSessionContractImplementor, Object o2) throws HibernateException {
        return null;
    }
}
