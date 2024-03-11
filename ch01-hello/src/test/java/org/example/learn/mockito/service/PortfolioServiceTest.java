package org.example.learn.mockito.service;

import org.example.learn.mockito.model.po.Stock;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class PortfolioServiceTest {

    PortfolioService portfolio;
    StockService stockService;

    @Before
    public void setUp() {
        //Create a portfolio object which is to be tested
        portfolio = new PortfolioService();

        //Create the mock object of stock service
        stockService = mock(StockService.class);

        //set the stockService to the portfolio
        portfolio.setStockService(stockService);
    }

    @Test
    public void testMarketValue1() {

        //Creates a list of stocks to be added to the portfolio
        List<Stock> stocks = new ArrayList<Stock>();
        Stock googleStock = new Stock("1", "Google", 10);
        Stock microsoftStock = new Stock("2", "Microsoft", 100);

        stocks.add(googleStock);
        stocks.add(microsoftStock);

        //add stocks to the portfolio
        portfolio.setStocks(stocks);

        //mock the behavior of stock service to return the value of various stocks
        when(stockService.getPrice(googleStock)).thenReturn(50.00);
        when(stockService.getPrice(microsoftStock)).thenReturn(1000.00);

        assertEquals(100500, portfolio.getMarketValue(), 0);
    }

    @Test
    public void testMarketValue2() {

        //Creates a list of stocks to be added to the portfolio
        List<Stock> stocks = new ArrayList<Stock>();
        Stock googleStock = new Stock("1", "Google", 10);
        Stock microsoftStock = new Stock("2", "Microsoft", 100);

        stocks.add(googleStock);
        stocks.add(microsoftStock);

        //add stocks to the portfolio
        portfolio.setStocks(stocks);
        //mock the behavior of stock service to return the value of various stocks
        when(stockService.getPrice(googleStock)).thenReturn(50.00);
        when(stockService.getPrice(microsoftStock)).thenReturn(1000.00);
        assertEquals(100500, portfolio.getMarketValue(), 0);

        // 覆盖之前的行为定义, 重新定义行为
        when(stockService.getPrice(googleStock)).thenReturn(5.00);
        when(stockService.getPrice(microsoftStock)).thenReturn(100.00);
        assertEquals(10050, portfolio.getMarketValue(), 0);
    }
}