package com.github.ksgfk.teaathome.control.inter;

import com.github.ksgfk.teaathome.models.Depository;
import com.github.ksgfk.teaathome.models.Product;

import java.sql.SQLException;

public interface ControlDepositoryInter {
    public boolean add(Depository depository);

    public boolean delete(int id);

    public boolean updata(Depository depository);

    public Depository findid(int id);

    public boolean addProduct(Product product, int id);

    public boolean deleteProduct(Product product);

    Depository findName(String name) throws SQLException;
}
