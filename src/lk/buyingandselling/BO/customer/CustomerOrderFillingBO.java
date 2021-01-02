/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.buyingandselling.BO.customer;

import java.util.ArrayList;
import lk.buyingandselling.BO.SuperBO;
import lk.buyingandselling.model.DTO.BatchDetailDTO;
import lk.buyingandselling.model.DTO.CustomerOrderDTO;
import lk.buyingandselling.model.DTO.CustomerOrderDetailDTO;

/**
 *
 * @author SLR
 */
public interface CustomerOrderFillingBO extends SuperBO {

    public boolean saveCustomerOrder(CustomerOrderDTO customerOrderDTO,
            ArrayList<CustomerOrderDetailDTO> customerOrderDetailDTOs,
            ArrayList<BatchDetailDTO> batchDetailDTOs) ;
}
