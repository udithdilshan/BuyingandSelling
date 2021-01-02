/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.buyingandselling.BO.customer;

import java.util.ArrayList;
import lk.buyingandselling.BO.SuperBO;
import lk.buyingandselling.model.DTO.CardDTO;

/**
 *
 * @author SLR
 */
public interface CardBO extends SuperBO {

    public boolean addCard(CardDTO cardDTO) throws Exception;

    public boolean deleteCard(CardDTO cardDTO) throws Exception;

    public boolean updateCard(CardDTO cardDTO) throws Exception;

    public ArrayList<CardDTO> getAllCard() throws Exception;

    public ArrayList<CardDTO> searchCard(CardDTO cardDTO) throws Exception;
}
