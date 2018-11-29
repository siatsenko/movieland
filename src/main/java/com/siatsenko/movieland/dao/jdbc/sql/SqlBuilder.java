package com.siatsenko.movieland.dao.jdbc.sql;

import com.siatsenko.movieland.entity.SortType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

@Repository
public class SqlBuilder {
    private List<String> allowedFields;

    //    private String orderReplace;
    private String orderReplace;

    public String setOrder(String query, Map<String, String> params) {
        if (params == null) {
            return query;
        }
        String map = params.toString();
        System.out.println(map);
        boolean isValid = false;
        StringJoiner stringJoiner = new StringJoiner(", ", "ORDER BY ", "");
        for (String key : params.keySet()) {
            String value = params.get(key);
            if (allowedFields.contains(key) && SortType.isValid(value)) {
                stringJoiner.add(key + " " + value);
                isValid = true;
            }
        }
        String newQuery = query.replace(orderReplace, stringJoiner.toString());
        return isValid ? newQuery : query;
//        return isValid ? query.replace(orderReplace, stringJoiner.toString()) : query;
    }

//    @Autowired
//    public void setAllowedFields(List<String> allowedFields) {
//        this.allowedFields = allowedFields;
//    }

    public void setAllowedFields(List<String> allowedFields) {
        this.allowedFields = new AllowedFields[];
    }

//    @Value("${sql.orderReplace:/*ORDER BY*/}")
//    public void setOrderReplace(String orderReplace) {
//        this.orderReplace = orderReplace;
//    }


//    @Autowired
//    public void setOrderReplace( String orderReplace) {
//        this.orderReplace = orderReplace;
//    }
}
