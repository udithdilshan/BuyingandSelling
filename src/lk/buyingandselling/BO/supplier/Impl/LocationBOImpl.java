/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.buyingandselling.BO.supplier.Impl;

import java.util.ArrayList;
import lk.buyingandselling.BO.supplier.LocationBO;
import lk.buyingandselling.DAO.DAOFactory;
import lk.buyingandselling.DAO.supplier.LocationDAO;
import lk.buyingandselling.model.DTO.LocationDTO;
import lk.buyingandselling.model.entity.Location;

/**
 *
 * @author SLR
 */
public class LocationBOImpl implements LocationBO {

    private LocationDAO locationDAO;

    public LocationBOImpl() {
        this.locationDAO = (LocationDAO) DAOFactory.getDAOFactory().getSuperDAO(DAOFactory.DAOType.LOCATION);
    }

    @Override
    public boolean addLocation(LocationDTO locationDTO) throws Exception {
        return locationDAO.add(setLocation(locationDTO));
    }

    @Override
    public ArrayList<LocationDTO> getAllLocation() throws Exception {
        ArrayList<LocationDTO> allLocationDTOs = new ArrayList();
        ArrayList<Location> allLocations = locationDAO.getAll();
        for (Location location : allLocations) {
            allLocationDTOs.add(new LocationDTO(
                    location.getLocationId(),
                    location.getRackNo(),
                    location.getSectionName())
            );
        }
        return allLocationDTOs;
    }

    private Location setLocation(LocationDTO locationDTO) {
        return new Location(
                locationDTO.getLocationId(),
                locationDTO.getRackNo(),
                locationDTO.getSectionName()
        );
    }

    @Override
    public ArrayList<LocationDTO> getAllSections() throws Exception {
        ArrayList<Location> allSections = locationDAO.getAllSections();
        ArrayList<LocationDTO> allSectionNames = new ArrayList<>();
        String l = allSections.get(0).getSectionName();
        String t[] = l.split("enum");
        String p[] = t[1].split("(')");

        for (String r : p) {
            LocationDTO temp = new LocationDTO();
            if (r.compareTo("(") != 0 && r.compareTo(")") != 0 && r.compareTo(",") != 0) {
                temp.setSectionName(r);
                allSectionNames.add(temp);
            }

        }
        return allSectionNames;
    }

    @Override
    public boolean addNewSection(LocationDTO locationDTO) throws Exception {
        Location location = new Location();
        location.setSectionName(locationDTO.getSectionName());
        return locationDAO.addNewSection(location);
    }

    @Override
    public boolean deleteLocation(String locationID) throws Exception {
        return locationDAO.delete(locationID);
    }

    @Override
    public boolean updateLocation(LocationDTO locationDTO) throws Exception {
        Location location = new Location(
                locationDTO.getLocationId(),
                locationDTO.getRackNo(),
                locationDTO.getSectionName()
        );
        return locationDAO.update(location);
    }

    @Override
    public ArrayList<LocationDTO> searchLocation(LocationDTO locationDTO) throws Exception {
        ArrayList<Location> locations = locationDAO.search(setLocation(locationDTO));

        ArrayList<LocationDTO> locationsDTO = new ArrayList<>();

        for (Location temp : locations) {
            LocationDTO location = new LocationDTO(
                    temp.getLocationId(),
                    temp.getRackNo(),
                    temp.getSectionName()
            );
            locationsDTO.add(location);
        }
        return locationsDTO;
    }
}
