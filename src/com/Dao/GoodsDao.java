package com.Dao;

import com.good.Goods;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GoodsDao {
  BaseDao baseDao=new BaseDao();

    //商品查询
    public List<Goods> findByGoods(Goods goods){
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pra = null;
        List<Goods> list = new ArrayList<>();
        try {
            // 1、获得连接对象
            conn = baseDao.getConnection();
            // 2、获得预编译语句集并执行SQL语句
            StringBuffer sf = new StringBuffer();
            List<Object> paramList = new ArrayList<>();
            sf.append(" select * from goodsinfo where 1=1 ");
            if(goods!=null){
                //根据Id查询
                if(goods.getGoodId()>0){
                    sf.append(" and id= ? ");
                    paramList.add(goods.getGoodId());
                }
                // 根据GoodName查询
                if(goods.getGoodName()!=null){
                    sf.append(" and goodsInfo_name=? ");
                    paramList.add(goods.getGoodName());
                }
                // 根据GoodPrice查询
                if(goods.getGoodPrice()>0){
                    sf.append(" and goodsInfo_price >= ? ");
                    paramList.add(goods.getGoodPrice());
                }
                // 根据GoodPrice查询：范围
                if(goods.getGoodPrice()>0){
                    sf.append(" and goodsInfo_price <= ? ");
                    paramList.add(goods.getGoodPrice());
                }
            }
            //获得预编语句
            pra = conn.prepareStatement(sf.toString());
            if(paramList!=null && paramList.size()>0){
                for(int i=0;i<paramList.size();i++){
                    pra.setObject(i+1, paramList.get(i));
                }
            }
            //执行sql语句
            rs = pra.executeQuery();
            while(rs.next()){
                Goods entity=new Goods();
                entity.setGoodId(rs.getInt("id"));
                entity.setGoodName(rs.getString("goodsInfo_name"));
                entity.setGoodPrice(rs.getInt("goodsInfo_price"));
                entity.setGoodStock(rs.getInt("goods_stock"));
                entity.setGoodPic(rs.getString("goodsInfo_pic"));
                entity.setGoodDescription(rs.getString("goodsInfo_description"));
                list.add(entity);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
                baseDao.closeAll(conn,pra,rs);
            }
        return list;
    }
    //增加商品
    public int insertGoods(Goods goods){
        int rows=0;
        //创建语句集
        String sql= "insert into goodsinfo(goodsInfo_name,goodsInfo_pic," +
                        "goodsInfo_price,goodsInfo_description,goods_stock) " +
                        "values (?,?,?,?,?)";
        //添加数据
        List <Object> paramList=new ArrayList<>();
        paramList.add(goods.getGoodName());
        paramList.add(goods.getGoodPic());
        paramList.add(goods.getGoodPrice());
        paramList.add(goods.getGoodDescription());
        paramList.add(goods.getGoodStock());
        rows=baseDao.executeUpdate(sql,paramList);
        return rows;
    }
    //删除商品
    public int deleteGoods(int goodId){
        int rows=0;
        Connection conn=null;
        PreparedStatement pra=null;
        ResultSet rs=null;
        try {
            //获得连接对象
            conn=baseDao.getConnection();
            //创建sql语句
            String sql="delete from goodsinfo where id = ? ";
            //预编译
            pra=conn.prepareStatement(sql);
            //设置值
            pra.setObject(1,goodId);
            rows=pra.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            baseDao.closeAll(conn,pra,rs);
        }
        return rows;
    }
    //更新商品
    public int updateGoods(Goods goods){
        //创建语句集
        StringBuffer sf=new StringBuffer();
        sf.append(" update goodsinfo set  ");
        sf.append("  goodsInfo_name= ? ");
        sf.append("  ,goodsInfo_pic = ? ");
        sf.append("  ,goodsInfo_price = ? ");
        sf.append("  ,goodsInfo_description = ? ");
        sf.append("  ,goods_stock  = ? ");
        sf.append("  where id = ? ");
        String sql=sf.toString();
        //添加数据
        List<Object> paramList=new ArrayList<>();
        paramList.add(goods.getGoodName());
        paramList.add(goods.getGoodPic());
        paramList.add(goods.getGoodPrice());
        paramList.add(goods.getGoodDescription());
        paramList.add(goods.getGoodStock());
        paramList.add(goods.getGoodId());
        //执行编译
        int rows=baseDao.executeUpdate(sql,paramList);
        return rows;
    }





}
