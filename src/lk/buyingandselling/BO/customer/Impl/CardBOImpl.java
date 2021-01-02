/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.buyingandselling.BO.customer.Impl;

import java.util.ArrayList;
import lk.buyingandselling.BO.customer.CardBO;
import lk.buyingandselling.DAO.DAOFactory;
import lk.buyingandselling.DAO.customer.CardDAO;
import lk.buyingandselling.model.DTO.CardDTO;
import lk.buyingandselling.model.entity.Card;

/**
 *
 * @author SLR
 */
public class CardBOImpl implements CardBO {

    private CardDAO cardDAO;

    public CardBOImpl() {
        cardDAO = (CardDAO) DAOFactory.getDAOFactory().getSuperDAO(DAOFactory.DAOType.CARD);
    }

    @Override
    public boolean addCard(CardDTO cardDTO) throws Exception {
        return cardDAO.add(makeCard(cardDTO));
    }

    @Override
    public boolean deleteCard(CardDTO cardDTO) throws Exception {
        return cardDAO.delete(makeCard(cardDTO));
    }

    @Override
    public boolean updateCard(CardDTO cardDTO) throws Exception {
        return cardDAO.update(makeCard(cardDTO));
    }

    @Override
    public ArrayList<CardDTO> getAllCard() throws Exception {
        ArrayList<Card> cards = cardDAO.getAll();
        ArrayList<CardDTO> cardDTOs = new ArrayList<>();
        for (Card temp : cards) {
            CardDTO cardDTO = new CardDTO(
                    temp.getCardNo(),
                    temp.getValidDate(),
                    temp.getCVV(),
                    temp.getCardType(),
                    temp.getAmount(),
                    temp.getRecivedDate()
            );
            cardDTOs.add(cardDTO);
        }
        return cardDTOs;
    }

    @Override
    public ArrayList<CardDTO> searchCard(CardDTO cardDTO) throws Exception {
        ArrayList<Card> cards = cardDAO.getAll();
        ArrayList<CardDTO> cardDTOs = new ArrayList<>();
        for (Card temp : cards) {
            CardDTO card = new CardDTO(
                    temp.getCardNo(),
                    temp.getValidDate(),
                    temp.getCVV(),
                    temp.getCardType(),
                    temp.getAmount(),
                    temp.getRecivedDate()
            );
            cardDTOs.add(card);
        }
        return cardDTOs;
    }

    private Card makeCard(CardDTO cardDTO) {
        return new Card(
                cardDTO.getCardNo(),
                cardDTO.getValidDate(),
                cardDTO.getCVV(),
                cardDTO.getCardType(),
                cardDTO.getAmount(),
                cardDTO.getRecivedDate()
        );
    }
}
