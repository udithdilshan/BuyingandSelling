/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.buyingandselling.BO.supplier.Impl;

import java.util.ArrayList;
import lk.buyingandselling.BO.supplier.ItemBO;
import lk.buyingandselling.DAO.DAOFactory;
import lk.buyingandselling.DAO.supplier.ItemDAO;
import lk.buyingandselling.model.DTO.ItemDTO;
import lk.buyingandselling.model.entity.Item;

/**
 *
 * @author SLR
 */
public class ItemBOImpl implements ItemBO {

    private ItemDAO itemDAO;

    public ItemBOImpl() {
        itemDAO = (ItemDAO) DAOFactory.getDAOFactory().getSuperDAO(DAOFactory.DAOType.ITEM);
    }

    @Override
    public boolean addItem(ItemDTO itemDTO) throws Exception {
        return itemDAO.add(setItem(itemDTO));
    }

    @Override
    public boolean deleteItem(String ItemCode) throws Exception {
        return itemDAO.delete(ItemCode);
    }

    @Override
    public boolean updateItem(ItemDTO itemDTO) throws Exception {
        return itemDAO.update(setItem(itemDTO));
    }

    @Override
    public ArrayList<ItemDTO> getAllItems() throws Exception {
        ArrayList<ItemDTO> allItems=new ArrayList<>();
        ArrayList<Item> allItem=itemDAO.getAll();
        for (Item temp:allItem) {
         ItemDTO item=new ItemDTO(
                 temp.getItemCode(),
                 temp.getLocationId(),
                 temp.getDescription(),
                 temp.getCategory()
         );
         allItems.add(item);
        }
        return allItems;
    }

    @Override
    public ArrayList<ItemDTO> getAllCategory() throws Exception {
        ArrayList<ItemDTO> allCategorys = new ArrayList<>();
        ArrayList<Item> allCatagoryList = itemDAO.getAllCategory();
        String l = allCatagoryList.get(0).getCategory();
        String t[] = l.split("enum");
        String p[] = t[1].split("(')");

        for (String r : p) {
            ItemDTO temp = new ItemDTO();
            if (r.compareTo("(") != 0 && r.compareTo(")") != 0 && r.compareTo(",") != 0) {
                temp.setCategory(r);
                allCategorys.add(temp);
            }
        }
        return allCategorys;
    }

    @Override
    public boolean addNewCategory(ItemDTO itemDTO) throws Exception {
        return itemDAO.addNewCategory(setItem(itemDTO));
    }

    private Item setItem(ItemDTO itemDTO){
        return new Item(
                itemDTO.getItemCode(),
                itemDTO.getLocationId(),
                itemDTO.getDescription(),
                itemDTO.getCategory()
        );
    }

    @Override
    public ArrayList<ItemDTO> searchItem(ItemDTO itemDTO) throws Exception {
        ArrayList<ItemDTO> allItemDTOs=new ArrayList<>();
       
        ArrayList<Item> allItems=itemDAO.search(setItem(itemDTO));
        for(Item temp: allItems){
            allItemDTOs.add(new ItemDTO(
                    temp.getItemCode(),
                    temp.getLocationId(),
                    temp.getDescription(),
                    temp.getCategory())
            );
        }
        return allItemDTOs;
    }
}
