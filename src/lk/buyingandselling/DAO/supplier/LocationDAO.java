/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.buyingandselling.DAO.supplier;

import java.util.ArrayList;
import lk.buyingandselling.DAO.CrudDAO;
import lk.buyingandselling.model.entity.Location;

/**
 *
 * @author SLR
 */
public interface LocationDAO extends CrudDAO<Location, String> {

    public ArrayList<Location> getAllSections() throws Exception;

    public boolean addNewSection(Location entity) throws Exception;
}
