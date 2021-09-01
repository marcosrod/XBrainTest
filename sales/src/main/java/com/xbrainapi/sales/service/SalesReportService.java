package com.xbrainapi.sales.service;

import com.xbrainapi.sales.model.Sale;
import com.xbrainapi.sales.model.SellerReport;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class SalesReportService {

    public List<SellerReport> getSellersReport(List<Sale> sales, Date dateStart, Date dateEnd){
        int sellersCount = 0;
        int i;
        int j;
        List<Integer> idList = new ArrayList<>();
        List<SellerReport> report;
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
            report.add(setSellerReport(idList.get(i), sales, dateStart, dateEnd));
        }

        return report;
    }

    private SellerReport setSellerReport(int id, List<Sale> sales, Date dateStart, Date dateEnd){
        SellerReport sellerReport = new SellerReport();
        int i;
        for(i = 0; i < sales.size(); i++){
            if(sales.get(i).getSellerId() == id){
                if(sellerReport.getSalesTotal() == 0){
                    sellerReport.setName(sales.get(i).getSellerName());
                }
                sellerReport.setSalesTotal(sellerReport.getSalesTotal()+1);
            }
        }

        long diff = dateEnd.getTime() - dateStart.getTime();
        double daysTotal = (double) (TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)) + 1;
        sellerReport.setDailySalesAverage(sellerReport.getSalesTotal()/daysTotal);

        return sellerReport;
    }
}
