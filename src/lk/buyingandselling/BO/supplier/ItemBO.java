/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.buyingandselling.BO.supplier;

import java.util.ArrayList;
import lk.buyingandselling.BO.SuperBO;
import lk.buyingandselling.model.DTO.ItemDTO;

/**
 *
 * @author SLR
 */
public interface ItemBO extends SuperBO{

    public boolean addItem(ItemDTO itemDTO) throws Exception;

    public boolean deleteItem(String ItemCode) throws Exception;

    public boolean updateItem(ItemDTO itemDTO) throws Exception;

    public ArrayList<ItemDTO> getAllItems() throws Exception;
    
    public ArrayList<ItemDTO> getAllCategory() throws Exception;
    
    public boolean addNewCategory(ItemDTO itemDTO) throws Exception;
    
    public ArrayList<ItemDTO> searchItem(ItemDTO itemDTO) throws Exception;

}
