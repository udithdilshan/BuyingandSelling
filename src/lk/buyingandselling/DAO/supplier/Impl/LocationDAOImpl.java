/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.buyingandselling.DAO.supplier.Impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import lk.buyingandselling.DAO.CrudOperation;
import lk.buyingandselling.DAO.supplier.LocationDAO;
import lk.buyingandselling.model.entity.Location;

/**
 *
 * @author SLR
 */
public class LocationDAOImpl implements LocationDAO {

    @Override
    public boolean add(Location entity) throws Exception {
        String sql = "INSERT INTO location VALUES(?,?,?)";
        return CrudOperation.executeUpdate(
                sql,
                entity.getLocationId(),
                entity.getRackNo(),
                entity.getSectionName()
        );
    }

    @Override
    public ArrayList<Location> getAll() throws Exception {
        String sql = "SELECT * FROM location";
        ResultSet rs = CrudOperation.executeQuery(sql);
        ArrayList<Location> allLocation = new ArrayList();
        while (rs.next()) {
            allLocation.add(new Location(rs.getString(1), rs.getString(2), rs.getString(3)));
        }
        return allLocation;
    }

    @Override
    public ArrayList<Location> getAllSections() throws Exception {
        String sql = "describe location sectionname";
        ResultSet rs = CrudOperation.executeQuery(sql);
        ArrayList<Location> allSections = new ArrayList();
        while (rs.next()) {
            Location temp = new Location();
            temp.setSectionName(rs.getString(2));
            allSections.add(temp);
        }
        return allSections;
    }

    @Override
    public boolean addNewSection(Location entity) throws Exception {
        String sql = "ALTER TABLE Location MODIFY COLUMN SectionName ENUM(" + entity.getSectionName() + ") NOT NULL";
        return CrudOperation.executeUpdate(sql);
    }

    @Override
    public boolean delete(String id) throws Exception {
        String sql = "DELETE FROM location WHERE locationId=? ";
        return CrudOperation.executeUpdate(sql, id);
    }

    @Override
    public boolean update(Location entity) throws Exception {
        String sql = "UPDATE location SET rackNo=? ,SectionName=? WHERE locationId=?";
        return CrudOperation.executeUpdate(sql, entity.getRackNo(), entity.getSectionName(), entity.getLocationId());
    }

    @Override
    public ArrayList<Location> search(Location entity) throws Exception {
        String sql = "SELECT * FROM location WHERE locationId=? OR rackNo=? OR sectionName=?";
        ResultSet rs = CrudOperation.executeQuery(sql, entity.getLocationId(), entity.getRackNo(), entity.getSectionName());
        ArrayList<Location> locations = new ArrayList<>();
        while (rs.next()) {
            Location temp = new Location(rs.getString(1), rs.getString(2), rs.getString(3));
            locations.add(temp);
        }
        return locations;
    }
}
