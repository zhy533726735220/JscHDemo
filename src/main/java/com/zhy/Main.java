package com.zhy;

import com.zhy.vo.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        Map<String, String> maps = new HashMap<String, String>();
        String sql = null;
        maps.put("身份证", "cardId");
        maps.put("姓名", "username");
        maps.put("手机号", "mobile");
//        maps.put("电话", "mobile");
        ExcelReadUtils excelUtils = new ExcelReadUtils(maps);
        excelUtils.init("F:\\系统报名表.xls");
        List<User> users = null;
        try {
            users = excelUtils.bindToModels(User.class);
            // 下载生活照
//            sql = AppendSql.appendSql(users, "select contacter_id from users_cards where users_cards.card_id in ( ");
            // 下载证件照
//            sql = AppendSql.appendSql(users, "select users.realname, users_cards.photo from users left join users_cards on users.id = users_cards.user_id  where users_cards.card_id in (");
            // 下载表单
//            sql = AppendSql.appendSql(users, "select users.realname, course_users_fields.field from course_users_fields left join users on course_users_fields.user_id = users.id left join users_cards on users_cards.user_id = course_users_fields.user_id where course_users_fields.form = '2' and  users_cards.card_id in (");
//            sql = sql.replaceAll(";", "") + "order by users.realname;";
            System.out.println(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }

        SSHTunnel.connection();
        // 下载生活照
//        SSHTunnel.sql(sql, users);
        // 下载证件照
//        SSHTunnel.sqlCard(sql, users);
        sql = "select users.realname, users_cards.photo from users left join users_cards on users.id = users_cards.user_id  where users_cards.contacter_id = '31657';";
        SSHTunnel.photo("select users.realname, users_cards.photo from users left join users_cards on users.id = users_cards.user_id  where users_cards.contacter_id = '31657';", users);
        System.out.println(SSHTunnel.sqlField(sql, users));
    }
}
