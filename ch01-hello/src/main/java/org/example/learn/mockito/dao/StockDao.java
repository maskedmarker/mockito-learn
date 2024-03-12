package org.example.learn.mockito.dao;

import org.example.learn.mockito.model.po.Stock;

public interface StockDao {

    Stock query(int stockId);
    void update(Stock stock);
}
