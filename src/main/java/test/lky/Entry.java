package test.lky;

import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by kylong on 2016/11/16.
 */
public class Entry {
    public static void main(String[] args) throws IOException {
        Resource resource = new ClassPathResource("sample.txt");
        File bill = resource.getFile();
        List<String> lines = FileUtils.readLines(bill);
        Double spend_rmb_sum = 0.0;
        Double total_usd_sum = 0.0;
        Double current_ex=689.21/100;
        Double profit = 0.0;
        for (String line : lines){
            String[] words = line.split(",");
            Double amount=Double.valueOf(words[0]);
            Double ex=Double.valueOf(words[1])/100;
            int status=Integer.valueOf(words[2]);
            if (status==1){
                spend_rmb_sum=spend_rmb_sum+amount*ex;
                total_usd_sum = total_usd_sum + amount;
            }else{
                spend_rmb_sum=spend_rmb_sum-amount*ex;
                total_usd_sum = total_usd_sum -amount;
            }
        }
        profit = total_usd_sum*current_ex-spend_rmb_sum;
        System.out.println(profit);
    }
}
