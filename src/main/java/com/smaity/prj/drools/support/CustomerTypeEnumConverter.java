package com.smaity.prj.drools.support;
/**
 * Customer Type enum converter File
 *
 * @Author: Sajal Maity
 * @Date: 05/31/2020
 */

import com.smaity.prj.drools.domain.CustomerType;
import org.springframework.core.convert.converter.Converter;

public class CustomerTypeEnumConverter implements Converter<String, CustomerType> {
    @Override
    public CustomerType convert(String s) {
        try {
            return CustomerType.valueOf(s.toUpperCase());
        } catch (Exception ex) {
            return null;
        }
    }
}
