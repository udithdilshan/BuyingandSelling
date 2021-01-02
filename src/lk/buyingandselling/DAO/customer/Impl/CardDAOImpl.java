/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.buyingandselling.DAO.customer.Impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import lk.buyingandselling.DAO.CrudOperation;
import lk.buyingandselling.DAO.customer.CardDAO;
import lk.buyingandselling.model.entity.Card;

/**
 *
 * @author SLR
 */
public class CardDAOImpl implements CardDAO {

    @Override
    public boolean add(Card entity) throws Exception {
        String sql = "INSERT INTO card VALUES(?,?,?,?,?,?)";
        return CrudOperation.executeUpdate(
                sql,
                entity.getCardNo(),
                entity.getValidDate(),
                entity.getCVV(),
                entity.getCardType(),
                entity.getAmount(),
                entity.getRecivedDate()
        );
    }

    @Override
    public ArrayList<Card> getAll() throws Exception {
        String sql = "SELECT * FROM card";
        ResultSet rs = CrudOperation.executeQuery(sql);
        ArrayList<Card> cards = new ArrayList<>();
        while (rs.next()) {
            Card card = new Card(
                    rs.getLong(1),
                    rs.getString(2),
                    rs.getInt(3),
                    rs.getString(4),
                    rs.getDouble(5),
                    rs.getString(6)
            );
            cards.add(card);
        }
        return cards;
    }

    @Override
    public boolean delete(Card id) throws Exception {
        String sql = "DELETE FROM card WHERE cardNo=? AND recivedDate=?";
        return CrudOperation.executeUpdate(sql, id.getCardNo(), id.getRecivedDate());
    }

    @Override
    public boolean update(Card entity) throws Exception {
        String sql = "UPDATE card SET validDate=? ,cvv=? ,cardType=?,bankName=?,status=?,amount=?,recivedDate=? WHERE cardNo=?";
        return CrudOperation.executeUpdate(
                sql,
                entity.getValidDate(),
                entity.getCVV(),
                entity.getCardType(),
                entity.getAmount(),
                entity.getCardNo(),
                entity.getRecivedDate()
        );
    }

    @Override
    public ArrayList<Card> search(Card entity) throws Exception {
        String sql;
        ResultSet rs = null;
        if (entity.getCardNo() == 0 && !entity.getRecivedDate().isEmpty() && entity.getCardType().isEmpty()) {
            sql = "SELECT * FROM card WHERE recivedDate=?";
            rs = CrudOperation.executeQuery(sql, entity.getRecivedDate());
        } else if (entity.getCardNo() != 0 && !entity.getRecivedDate().isEmpty() && entity.getCardType().isEmpty()) {
            sql = "SELECT * FROM card WHERE cardNo =? AND recivedDate=?";
            rs = CrudOperation.executeQuery(sql, entity.getCardNo(), entity.getRecivedDate());
        } else if (entity.getCardNo() != 0 && !entity.getRecivedDate().isEmpty() && !entity.getCardType().isEmpty()) {
            sql = "SELECT * FROM card WHERE recivedDate=? AND cardType=?";
            rs = CrudOperation.executeQuery(sql, entity.getRecivedDate(), entity.getCardType());
        } else if (entity.getCardNo() != 0 && entity.getRecivedDate().isEmpty() && !entity.getCardType().isEmpty()) {
            sql = "SELECT * FROM card WHERE  cardType=?";
            rs = CrudOperation.executeQuery(sql, entity.getCardType());
        } else {
            sql = "SELECT * FROM card WHERE cardNo=? OR recivedDate =? OR cardType=?";
            rs = CrudOperation.executeQuery(sql, entity.getCardNo(), entity.getRecivedDate(), entity.getCardType());
        }
        ArrayList<Card> cards = new ArrayList<>();
        while (rs.next()) {
            Card card = new Card(
                    rs.getLong(1),
                    rs.getString(2),
                    rs.getInt(3),
                    rs.getString(4),
                    rs.getDouble(5),
                    rs.getString(6)
            );
            cards.add(card);
        }
        return cards;
    }
}
