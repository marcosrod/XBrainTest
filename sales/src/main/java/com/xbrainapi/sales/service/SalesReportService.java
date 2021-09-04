package com.xbrainapi.sales.service;

import com.xbrainapi.sales.model.Sale;
import com.xbrainapi.sales.model.SaleReport;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class SalesReportService {

    //Method to organize and fill up the entire Sales Report
    public List<SaleReport> getSalesReport(List<Sale> sales, Date dateStart, Date dateEnd){
        int sellersCount = 0;
        int i;
        int j;
        List<Integer> idList = new ArrayList<>();
        List<SaleReport> report;
        report = new ArrayList<>();

        for(i = 0; i < sales.size(); i++){
            for(j = 0; j < idList.size(); j++) {
                if (sales.get(i).getSellerId() == idList.get(j)) {
                    sellersCount++;
                }
            }
            if(sellersCount == 0){
                idList.add(sales.get(i).getSellerId());
            }else{
                sellersCount = 0;
            }
        }
        Collections.sort(idList);

        for(i = 0; i < idList.size(); i++){
            report.add(setSaleReport(idList.get(i), sales, dateStart, dateEnd));
        }

        return report;
    }

    //Method to fill up a Sale for the Report
    private SaleReport setSaleReport(int id, List<Sale> sales, Date dateStart, Date dateEnd){
        SaleReport saleReport = new SaleReport();
        int i;
        for(i = 0; i < sales.size(); i++){
            if(sales.get(i).getSellerId() == id){
                if(saleReport.getSalesTotal() == 0){
                    saleReport.setName(sales.get(i).getSellerName());
                }
                saleReport.setSalesTotal(saleReport.getSalesTotal()+1);
            }
        }

        long diff = dateEnd.getTime() - dateStart.getTime();
        double daysTotal = (double) (TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)) + 1;
        saleReport.setDailySalesAverage(saleReport.getSalesTotal()/daysTotal);

        return saleReport;
    }
}
