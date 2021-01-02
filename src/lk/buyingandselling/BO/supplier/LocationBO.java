/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.buyingandselling.BO.supplier;

import java.util.ArrayList;
import lk.buyingandselling.BO.SuperBO;
import lk.buyingandselling.model.DTO.LocationDTO;

/**
 *
 * @author SLR
 */
public interface LocationBO extends SuperBO{
    
    public boolean addLocation(LocationDTO locationDTO) throws Exception;

    public ArrayList<LocationDTO> getAllLocation() throws Exception;
    
    public ArrayList<LocationDTO> getAllSections() throws Exception;
    
    public boolean addNewSection(LocationDTO locationDTO) throws Exception;
    
    public boolean deleteLocation(String locationID) throws Exception;
    
    public boolean updateLocation(LocationDTO locationDTO) throws Exception;
    
    public ArrayList<LocationDTO> searchLocation(LocationDTO locationDTO) throws Exception;
    
}
