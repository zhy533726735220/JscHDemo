package com.zhy;

import com.zhy.vo.IBean;

import java.util.List;

public class AppendSql {
    public static String appendSql(List<? extends IBean> list, String str) {
        String sql = null;
        StringBuffer sb = new StringBuffer(str);
        for (IBean vo : list) {
            sb.append("'" + vo.getKeyWord() + "'" + ",");
        }
        sql = sb.toString().substring(0, sb.toString().length() - 1);
        sql = sql + ");";
        return sql;
    }

}
