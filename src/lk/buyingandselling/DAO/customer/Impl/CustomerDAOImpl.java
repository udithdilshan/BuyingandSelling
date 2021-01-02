/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.buyingandselling.DAO.customer.Impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import lk.buyingandselling.DAO.CrudOperation;
import lk.buyingandselling.DAO.customer.CustomerDAO;
import lk.buyingandselling.model.entity.Customer;

/**
 *
 * @author SLR
 */
public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public boolean add(Customer entity) throws Exception {
        String sql = "INSERT INTO customer VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
        return CrudOperation.executeUpdate(sql,
                entity.getCustomerId(),
                entity.getFirstname(),
                entity.getLastname(),
                entity.getAddress(),
                entity.getCompanyName(),
                entity.getNIC(),
                entity.getGender(),
                entity.getCity(),
                entity.getMobile(),
                entity.getEmail(),
                entity.getPostalCode(),
                entity.getAddedDate()
        );
    }

    @Override
    public ArrayList<Customer> getAll() throws Exception {
        String sql = "SELECT * FROM customer";
        ResultSet rs = CrudOperation.executeQuery(sql);
        ArrayList<Customer> customers = new ArrayList<>();
        while (rs.next()) {
            Customer c = new Customer(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7),
                    rs.getString(8),
                    rs.getInt(9),
                    rs.getString(10),
                    rs.getString(11),
                    rs.getString(12));
            customers.add(c);
        }
        return customers;
    }

    @Override
    public boolean delete(String id) throws Exception {
        String sql = "DELETE FROM customer WHERE customerId=?";
        return CrudOperation.executeUpdate(sql, id);
    }

    @Override
    public boolean update(Customer entity) throws Exception {
        String sql = "UPDATE customer SET firstname=?,lastname=?,address=?,"
                + "companyname=?,NIC =?, gender=?,city=?,mobile=?,email=?,postalCode=? WHERE customerId=?";
        return CrudOperation.executeUpdate(
                sql,
                entity.getFirstname(),
                entity.getLastname(),
                entity.getAddress(),
                entity.getCompanyName(),
                entity.getNIC(),
                entity.getGender(),
                entity.getCity(),
                entity.getMobile(),
                entity.getEmail(),
                entity.getPostalCode(),
                entity.getCustomerId()
        );
    }

    @Override
    public ArrayList<Customer> search(Customer entity) throws Exception {
        String sql;
        ResultSet rs = null;
        if (entity.getCustomerId().isEmpty() && entity.getFirstname().isEmpty()
                && entity.getLastname().isEmpty() && entity.getCompanyName().isEmpty()
                && !entity.getNIC().isEmpty() && entity.getGender().isEmpty()
                && entity.getCity().isEmpty()) {
            sql = "SELECT * FROM customer WHERE NIC LIKE ?";
            rs=CrudOperation.executeQuery(sql, entity.getNIC());
        } else if (entity.getCustomerId().isEmpty() && !entity.getFirstname().isEmpty()
                && !entity.getLastname().isEmpty() && entity.getCompanyName().isEmpty()
                && entity.getNIC().isEmpty() && entity.getGender().isEmpty()
                && entity.getCity().isEmpty()) {
            sql = "SELECT * FROM customer WHERE firstname LIKE ? OR lastname LIKE ?";
            rs = CrudOperation.executeQuery(sql, entity.getFirstname(), entity.getLastname());
        } else if (entity.getCustomerId().isEmpty() && !entity.getFirstname().isEmpty()
                && !entity.getLastname().isEmpty() && !entity.getCompanyName().isEmpty()
                && entity.getNIC().isEmpty() && entity.getGender().isEmpty()
                && entity.getCity().isEmpty()) {
            sql = "SELECT * FROM customer WHERE (firstname LIKE ? OR lastname LIKE ?) AND companyname LIKE ?";
            rs = CrudOperation.executeQuery(sql, entity.getFirstname(), entity.getLastname(), entity.getCompanyName());
        } else if (entity.getCustomerId().isEmpty() && !entity.getFirstname().isEmpty()
                && !entity.getLastname().isEmpty() && entity.getCompanyName().isEmpty()
                && !entity.getNIC().isEmpty() && entity.getGender().isEmpty()
                && entity.getCity().isEmpty()) {
            sql = "SELECT * FROM customer WHERE (firstname LIKE ? OR lastname LIKE ?) AND NIC LIKE ?";
            rs = CrudOperation.executeQuery(sql, entity.getFirstname(), entity.getLastname(), entity.getNIC());
        } else if (entity.getCustomerId().isEmpty() && !entity.getFirstname().isEmpty()
                && !entity.getLastname().isEmpty() && entity.getCompanyName().isEmpty()
                && entity.getNIC().isEmpty() && !entity.getGender().isEmpty()
                && entity.getCity().isEmpty()) {
            sql = "SELECT * FROM customer WHERE (firstname LIKE ? OR lastname LIKE ?) AND gender LIKE ?";
            rs = CrudOperation.executeQuery(sql, entity.getFirstname(), entity.getLastname(), entity.getGender());
        } else if (entity.getCustomerId().isEmpty() && !entity.getFirstname().isEmpty()
                && !entity.getLastname().isEmpty() && entity.getCompanyName().isEmpty()
                && entity.getNIC().isEmpty() && entity.getGender().isEmpty()
                && !entity.getCity().isEmpty()) {
            sql = "SELECT * FROM customer WHERE (firstname LIKE ? OR lastname LIKE ?) AND city LIKE ?";
            rs = CrudOperation.executeQuery(sql, entity.getFirstname(), entity.getLastname(), entity.getCity());
        } else if (entity.getCustomerId().isEmpty() && entity.getFirstname().isEmpty()
                && entity.getLastname().isEmpty() && !entity.getCompanyName().isEmpty()
                && entity.getNIC().isEmpty() && !entity.getGender().isEmpty()
                && entity.getCity().isEmpty()) {
            sql = "SELECT * FROM customer WHERE companyname LIKE ? AND gender LIKE ?";
            rs = CrudOperation.executeQuery(sql, entity.getCompanyName(), entity.getGender());
        } else if (entity.getCustomerId().isEmpty() && entity.getFirstname().isEmpty()
                && entity.getLastname().isEmpty() && !entity.getCompanyName().isEmpty()
                && entity.getNIC().isEmpty() && entity.getGender().isEmpty()
                && !entity.getCity().isEmpty()) {
            sql = "SELECT * FROM customer WHERE companyname LIKE ? AND city LIKE ?";
            rs = CrudOperation.executeQuery(sql, entity.getCompanyName(), entity.getCity());
        } else if (entity.getCustomerId().isEmpty() && entity.getFirstname().isEmpty()
                && entity.getLastname().isEmpty() && entity.getCompanyName().isEmpty()
                && entity.getNIC().isEmpty() && !entity.getGender().isEmpty()
                && !entity.getCity().isEmpty()) {
            sql = "SELECT * FROM customer WHERE gender LIKE ? AND city LIKE ?";
            rs = CrudOperation.executeQuery(sql, entity.getGender(), entity.getCity());
        } else if (entity.getCustomerId().isEmpty() && entity.getFirstname().isEmpty()
                && entity.getLastname().isEmpty() && !entity.getCompanyName().isEmpty()
                && entity.getNIC().isEmpty() && !entity.getGender().isEmpty()
                && !entity.getCity().isEmpty()) {
            sql = "SELECT * FROM customer WHERE gender LIKE ? AND city LIKE ? AND companyname LIKE ?";
            rs = CrudOperation.executeQuery(sql, entity.getGender(), entity.getCity(), entity.getCompanyName());
        } else if (entity.getCustomerId().isEmpty() && !entity.getFirstname().isEmpty()
                && !entity.getLastname().isEmpty() && !entity.getCompanyName().isEmpty()
                && entity.getNIC().isEmpty() && !entity.getGender().isEmpty()
                && !entity.getCity().isEmpty()) {
            sql = "SELECT * FROM customer WHERE (firstname LIKE ? OR lastname LIKE ?) "
                    + "AND gender LIKE ? AND city LIKE ? AND companyname LIKE ?";
            rs = CrudOperation.executeQuery(sql, entity.getFirstname(), entity.getLastname(), entity.getGender(), entity.getCity(), entity.getCompanyName());
        } else if (entity.getCustomerId().isEmpty() && !entity.getFirstname().isEmpty()
                && !entity.getLastname().isEmpty() && !entity.getCompanyName().isEmpty()
                && !entity.getNIC().isEmpty() && !entity.getGender().isEmpty()
                && !entity.getCity().isEmpty()) {
            sql = "SELECT * FROM customer WHERE customerId LIKE ? AND (firstname LIKE ? OR lastname LIKE ?) "
                    + "AND companyname LIKE ? AND NIC LIKE ? AND gender LIKE ? AND city LIKE ?";
            rs = CrudOperation.executeQuery(sql, entity.getFirstname(),
                    entity.getLastname(), entity.getCompanyName(),
                    entity.getNIC(), entity.getGender(),
                    entity.getCity());
        } else {
            sql = "SELECT * FROM customer WHERE customerId LIKE ? OR firstname LIKE ? "
                    + "OR lastname LIKE ? OR companyname LIKE ? OR NIC LIKE ? OR gender LIKE ? OR city LIKE ?";
            rs = CrudOperation.executeQuery(sql, entity.getCustomerId(),
                    entity.getFirstname(), entity.getLastname(),
                    entity.getCompanyName(), entity.getNIC(),
                    entity.getGender(), entity.getCity());
        }
        ArrayList<Customer> customers = new ArrayList<>();
        while (rs.next()) {
            Customer customer = new Customer(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7),
                    rs.getString(8),
                    rs.getInt(9),
                    rs.getString(10),
                    rs.getString(11),
                    rs.getString(12)
            );
            customers.add(customer);
        }
        return customers;
    }
}
